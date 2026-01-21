package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.*;
import com.company.assetmgmt.exception.ResourceNotFoundException;
import com.company.assetmgmt.model.Asset;
import com.company.assetmgmt.model.AssetStatus;
import com.company.assetmgmt.model.Branch;
import com.company.assetmgmt.repository.AssetRepository;
import com.company.assetmgmt.repository.BranchRepository;
import com.company.assetmgmt.service.AssetService;
import com.company.assetmgmt.service.AuditService;
import com.company.assetmgmt.specification.AssetSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AuditService auditService;
    private final AssetRepository assetRepository;
    private final BranchRepository branchRepository;

    @Override
    public AssetResponse createAsset(AssetCreateRequest request) {
        Branch branch = null;

        if (request.getBranchId() != null) {
            branch = branchRepository.findById(request.getBranchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
        }

        Asset asset = Asset.builder()
                .description(request.getDescription())
                .assetClass(request.getAssetClass())
                .serialNumber(request.getSerialNumber())
                .tagId(request.getTagId())
                .acquisitionDate(request.getAcquisitionDate())
                .amount(request.getAmount())
                .remark(request.getRemark())
                .subsidiary(request.getSubsidiary())
                .status(branch == null ? AssetStatus.IN_STORE : AssetStatus.ASSIGNED)
                .branch(branch)
                .build();

        Asset savedAsset = assetRepository.save(asset);

        auditService.log(
                "CREATE_ASSET",
                savedAsset.getId(),
                "Asset created"
        );

        return mapToResponse(savedAsset);
    }

    @Override
    public AssetResponse updateAsset(UUID assetId, AssetUpdateRequest request) {
        Asset asset = getAssetEntity(assetId);

        asset.setDescription(request.getDescription());
        asset.setAssetClass(request.getAssetClass());
        asset.setAmount(request.getAmount());
        asset.setRemark(request.getRemark());
        asset.setSubsidiary(request.getSubsidiary());

        auditService.log(
                "UPDATE_ASSET",
                asset.getId(),
                "Asset details updated"
        );

        return mapToResponse(asset);
    }

    @Override
    public AssetResponse assignAssetToBranch(UUID assetId, UUID branchId) {
        Asset asset = getAssetEntity(assetId);

        if (asset.getStatus() == AssetStatus.DISPOSED) {
            throw new IllegalStateException("Disposed asset cannot be reassigned");
        }

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Branch not found"));

        asset.setBranch(branch);
        asset.setStatus(AssetStatus.ASSIGNED);

        auditService.log(
                "ASSIGN_ASSET",
                asset.getId(),
                "Assigned to branch ID: " + branchId
        );

        return mapToResponse(asset);
    }

    @Override
    public AssetResponse disposeAsset(UUID assetId, String remark) {
        Asset asset = getAssetEntity(assetId);

        asset.setStatus(AssetStatus.DISPOSED);
        asset.setDisposalDate(LocalDate.now());
        asset.setRemark(remark);
        asset.setBranch(null);

        auditService.log(
                "DISPOSE_ASSET",
                asset.getId(),
                "Disposed: " + remark
        );

        return mapToResponse(asset);
    }

    @Override
    @Transactional(readOnly = true)
    public AssetResponse getAssetById(UUID assetId) {
        return mapToResponse(getAssetEntity(assetId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssetResponse> getAllAssets() {
        return assetRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssetResponse> searchAssets(AssetSearchRequest filter, Pageable pageable) {
        return assetRepository.findAll(AssetSpecification.build(filter), pageable)
                .map(this::mapToResponse);
    }

    // =============================
    // HELPERS
    // =============================

    private Asset getAssetEntity(UUID assetId) {
        return assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }

    private AssetResponse mapToResponse(Asset asset) {
        BranchSummary branchSummary = null;

        if (asset.getBranch() != null) {
            Branch b = asset.getBranch();
            branchSummary = new BranchSummary(
                    b.getId(),
                    b.getName(),
                    b.getState(),
                    b.getLocation()
            );
        }

        return new AssetResponse(
                asset.getId(),
                asset.getDescription(),
                asset.getAssetClass(),
                asset.getSerialNumber(),
                asset.getTagId(),
                asset.getStatus(),
                asset.getAcquisitionDate(),
                asset.getAmount(),
                asset.getRemark(),
                asset.getDisposalDate(),
                asset.getDisposalCost(),
                asset.getSubsidiary(),
                branchSummary
        );
    }
}
