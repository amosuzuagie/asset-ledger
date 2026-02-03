package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.CategoryRequest;
import com.company.assetmgmt.dto.CategoryResponse;
import com.company.assetmgmt.model.AssetCategory;

import java.util.List;
import java.util.UUID;

public interface AssetCategoryService {
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(UUID categoryId, CategoryRequest request);
    List<CategoryResponse> findAll();
    CategoryResponse getCategoryById(UUID categoryId);
}
