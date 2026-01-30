package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.AssetCategoryCreateRequest;
import com.company.assetmgmt.model.AssetCategory;
import com.company.assetmgmt.repository.AssetCategoryRepository;
import com.company.assetmgmt.service.AssetCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetCategoryServiceImpl implements AssetCategoryService {
    private final AssetCategoryRepository repository;
    @Override
    public AssetCategory create(AssetCategoryCreateRequest request) {
        boolean exists = repository.existsByNameAndAssetClass(
                request.name(),
                request.assetClass()
        );

        if (exists) {
            throw new IllegalStateException("Category already exists for asset class");
        }

        return repository.save(
                AssetCategory.builder()
                        .name(request.name())
                        .assetClass(request.assetClass())
                        .description(request.description())
                        .build()
        );
    }
}
