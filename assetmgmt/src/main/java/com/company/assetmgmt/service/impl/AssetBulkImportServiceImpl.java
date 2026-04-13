package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.AssetImportPreviewResponse;
import com.company.assetmgmt.dto.AssetImportResultResponse;
import com.company.assetmgmt.dto.AssetImportRow;
import com.company.assetmgmt.exception.BusinessRuleException;
import com.company.assetmgmt.model.Asset;
import com.company.assetmgmt.model.AssetCategory;
import com.company.assetmgmt.model.Branch;
import com.company.assetmgmt.repository.AssetCategoryRepository;
import com.company.assetmgmt.repository.AssetRepository;
import com.company.assetmgmt.repository.BranchRepository;
import com.company.assetmgmt.service.AssetBulkImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AssetBulkImportServiceImpl implements AssetBulkImportService {

    private final AssetRepository assetRepository;
    private final BranchRepository branchRepository;
    private final AssetCategoryRepository categoryRepository;


    @Override
    public AssetImportPreviewResponse preview(MultipartFile file) {
        List<AssetImportRow> rows = parseFile(file);

        rows.forEach(this::validateRow);

        long valid = rows.stream().filter(AssetImportRow::isValid).count();
        long inValid = rows.size() - valid;

        return new AssetImportPreviewResponse(
                rows.size(),
                (int) valid,
                (int) inValid,
                rows
        );
    }

    @Override
    public AssetImportResultResponse importAssets(List<AssetImportRow> rows) {
        int success = 0;
        List<AssetImportRow> failed = new ArrayList<>();

        for (AssetImportRow row : rows) {
            validateRow(row);

            if (!row.isValid()) {
                failed.add(row);
                continue;
            }

            try {
                saveAsset(row);
                success++;
            } catch (Exception ex) {
                row.getErrors().add("Unexpected error during save");
                failed.add(row);
            }
        }
        return new AssetImportResultResponse(
                rows.size(),
                success,
                failed.size(),
                failed
        );
    }

    private List<AssetImportRow> parseFile(MultipartFile file) {
        List<AssetImportRow> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream())
        )) {
            String line;
            int rowNumber = 0;

            while ((line = reader.readLine()) != null) {
                rowNumber++;

                if (rowNumber == 1) continue; // skip header

                String[] columns = line.split(",");

                AssetImportRow row = new AssetImportRow();
                row.setRowNumber(rowNumber);
                row.setTagId(columns[0]);
                row.setAssetName(columns[1]);
                row.setBranchCode(columns[2]);
                row.setAssetCategory(columns[3]);
                row.setPurchaseCost(columns[4]);
                row.setPurchaseDate(columns[5]);

                rows.add(row);
            }
        } catch (IOException ex) {
            throw new BusinessRuleException("Invalid file format");
        }

        return rows;
    }

    private void validateRow(AssetImportRow row) {
        if (row.getTagId() == null || row.getTagId().isBlank()) {
            row.getErrors().add("Asset tag is required");
        }

        if (assetRepository.existsByTagId(row.getTagId())) {
            row.getErrors().add("Asset tag already exists");
        }

        if (!branchRepository.existsByCodeAndDeletedFalse(row.getBranchCode())) {
            row.getErrors().add("Invalid branch code");
        }

        if (!categoryRepository.existsByNameAndDeletedFalse(row.getAssetCategory())) {
            row.getErrors().add("Invalid category");
        }
    }

    private void saveAsset(AssetImportRow row) {
        Branch branch = branchRepository.findByCodeAndDeletedFalse(row.getBranchCode())
                .orElseThrow(() -> new BusinessRuleException("Branch not found"));

        AssetCategory category = categoryRepository.findByNameAndDeletedFalse(row.getAssetCategory())
                .orElseThrow(() -> new BusinessRuleException("Category not found"));


        Asset asset = new Asset();
        asset.setTagId(row.getTagId());
        asset.setAssetName(row.getAssetName());
        asset.setBranch(branch);
        asset.setCategory(category);

        assetRepository.save(asset);
    }
}
