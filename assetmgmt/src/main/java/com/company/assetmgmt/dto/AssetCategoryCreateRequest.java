package com.company.assetmgmt.dto;

import com.company.assetmgmt.model.enums.AssetClass;

public record AssetCategoryCreateRequest(
        String name,
        AssetClass assetClass,
        String description
) {}
