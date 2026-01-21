package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.AssetCreateRequest;
import com.company.assetmgmt.dto.AssetResponse;
import com.company.assetmgmt.dto.AssetSearchRequest;
import com.company.assetmgmt.dto.AssetUpdateRequest;
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

    @PostMapping
    public ResponseEntity<AssetResponse> createAsset(
            @Valid @RequestBody AssetCreateRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assetService.createAsset(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE')")
    public ResponseEntity<AssetResponse> updateAsset(
            @PathVariable UUID id,
            @Valid @RequestBody AssetUpdateRequest request
    ) {
        return ResponseEntity.ok(assetService.updateAsset(id, request));
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
    public ResponseEntity<AssetResponse> disposeAsset(
            @PathVariable("asset_id") UUID assetId,
            @RequestParam String remark
    ) {
        return ResponseEntity.ok(
                assetService.disposeAsset(assetId, remark)
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
}
