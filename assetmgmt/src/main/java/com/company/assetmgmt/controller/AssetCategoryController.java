package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.BranchRequest;
import com.company.assetmgmt.dto.BranchResponse;
import com.company.assetmgmt.dto.CategoryRequest;
import com.company.assetmgmt.dto.CategoryResponse;
import com.company.assetmgmt.model.AssetCategory;
import com.company.assetmgmt.service.AssetCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class AssetCategoryController {
    private final AssetCategoryService assetCategoryService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE')")
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.ok(assetCategoryService.create(request));
    }

    @PutMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE')")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID categoryId, @Valid @RequestBody CategoryRequest request
    ) {
        return ResponseEntity.ok(assetCategoryService.update(categoryId, request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','MANAGERS','DIRECTORS','AUDIT')")
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(assetCategoryService.findAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(assetCategoryService.getCategoryById(categoryId));
    }
}
