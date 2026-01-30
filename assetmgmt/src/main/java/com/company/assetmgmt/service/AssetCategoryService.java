package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.AssetCategoryCreateRequest;
import com.company.assetmgmt.model.AssetCategory;

public interface AssetCategoryService {
    AssetCategory create(AssetCategoryCreateRequest request);
}
