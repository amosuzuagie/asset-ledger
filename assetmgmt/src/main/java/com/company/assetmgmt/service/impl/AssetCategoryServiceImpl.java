package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.CategoryRequest;
import com.company.assetmgmt.dto.CategoryResponse;
import com.company.assetmgmt.model.AssetCategory;
import com.company.assetmgmt.repository.AssetCategoryRepository;
import com.company.assetmgmt.service.AssetCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssetCategoryServiceImpl implements AssetCategoryService {
    private final AssetCategoryRepository repository;

    @Override
    public CategoryResponse create(CategoryRequest request) {
        boolean exists = repository.existsByNameAndAssetClass(
                request.name(),
                request.assetClass()
        );

        if (exists) {
            throw new IllegalStateException("Category already exists for asset class");
        }

        AssetCategory category = repository.save(
                AssetCategory.builder()
                        .name(request.name())
                        .assetClass(request.assetClass())
                        .description(request.description())
                        .build()
        );

        return toResponse(category);
    }

    @Override
    public CategoryResponse update(UUID categoryId, CategoryRequest request) {
        AssetCategory category = repository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        category.setName(request.name());
        category.setAssetClass(request.assetClass());
        category.setDescription(request.description());

        repository.save(category);

        return toResponse(category);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(UUID categoryId) {
        AssetCategory category = repository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return toResponse(category);

    }

    private CategoryResponse toResponse(AssetCategory category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getAssetClass(),
                category.getDescription()
        );
    }
}
