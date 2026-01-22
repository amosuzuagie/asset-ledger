package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AssetService {
    AssetResponse createAsset(AssetCreateRequest request, UUID categoryId);

    AssetResponse updateAsset(UUID assetId, AssetUpdateRequest request, UUID categoryId);

    AssetResponse assignAssetToBranch(UUID assetId, UUID branchId);

    AssetResponse getAssetById(UUID assetId);

    List<AssetResponse> getAllAssets();

    Page<AssetResponse> searchAssets(AssetSearchRequest filter, Pageable pageable);

    void deleteAsset(UUID assetId);

    void restoreAsset(UUID assetId);

    AssetDisposalResponse disposeAsset(AssetDisposalRequest request);
}
