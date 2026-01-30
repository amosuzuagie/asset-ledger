package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.*;
import com.company.assetmgmt.service.AssetMovementService;
import com.company.assetmgmt.service.AssetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;
    private final AssetMovementService assetMovementService;

    @PostMapping("/{category_id}")
    public ResponseEntity<AssetResponse> createAsset(
            @Valid @RequestBody AssetCreateRequest request, @PathVariable("category_id") UUID categoryId
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assetService.createAsset(request, categoryId));
    }

    @PutMapping("/{asset_id}/category/{category_id}")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE')")
    public ResponseEntity<AssetResponse> updateAsset(
            @PathVariable("asset_id") UUID assetId,
            @PathVariable("category_id") UUID categoryId,
            @Valid @RequestBody AssetUpdateRequest request
    ) {
        return ResponseEntity.ok(assetService.updateAsset(assetId, request, categoryId));
    }

    @PutMapping("/{asset_id}/assign/{branch_id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGERS')")
    public ResponseEntity<AssetResponse> assignAsset(
            @PathVariable("asset_id") UUID assetId,
            @PathVariable("branch_id") UUID branchId
    ) {
        return ResponseEntity.ok(
                assetService.assignAssetToBranch(assetId, branchId)
        );
    }

    @PutMapping("/{asset_id}/dispose")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE')")
    public ResponseEntity<AssetDisposalResponse> disposeAsset(@RequestBody @Valid AssetDisposalRequest request) {
        return ResponseEntity.ok(
                assetService.disposeAsset(request)
        );
    }

    @GetMapping("/{asset_id}")
    public ResponseEntity<AssetResponse> getAsset(
            @PathVariable("asset_id") UUID assetId
    ) {
        return ResponseEntity.ok(assetService.getAssetById(assetId));
    }

    @GetMapping
    public ResponseEntity<List<AssetResponse>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @PostMapping("/search")
    public Page<AssetResponse> searchAssets(
            @RequestBody AssetSearchRequest filter,
            @ParameterObject @PageableDefault(size = 20) Pageable pageable
    ) {
        return assetService.searchAssets(filter, pageable);
    }

    @DeleteMapping("/{asset_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAsset(@PathVariable("asset_id") UUID assetId) {
        assetService.deleteAsset(assetId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{asset_id}/restore")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> restoreAsset(@PathVariable("asset_id") UUID assetId) {
        assetService.restoreAsset(assetId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/move")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','MANAGERS')")
    public ResponseEntity<Void> moveAsset(@Valid @RequestBody AssetMovementRequest request) {
        assetMovementService.moveAsset(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{asset_id}/movements")
    public ResponseEntity<List<AssetMovementResponse>> getAssetMovementHistory(@PathVariable("asset_id") UUID assetId) {
        return ResponseEntity.ok(assetMovementService.getAssetMovementHistory(assetId));
    }
}
