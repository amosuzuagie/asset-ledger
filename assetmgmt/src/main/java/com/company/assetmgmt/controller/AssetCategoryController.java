package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.AssetCategoryCreateRequest;
import com.company.assetmgmt.model.AssetCategory;
import com.company.assetmgmt.service.AssetCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class AssetCategoryController {
    private final AssetCategoryService assetCategoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AssetCategory> create(
            @RequestBody @Valid AssetCategoryCreateRequest request) {

        return ResponseEntity.ok(assetCategoryService.create(request));
    }
//    @GetMapping
//    public List<AssetCategory> list() {
//        return assetCategoryService.findAll();
//    }
}
