package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.AssetCreateRequest;
import com.company.assetmgmt.dto.AssetResponse;
import com.company.assetmgmt.dto.AssetUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface AssetService {
    AssetResponse createAsset(AssetCreateRequest request);

    AssetResponse updateAsset(UUID assetId, AssetUpdateRequest request);

    AssetResponse assignAssetToBranch(UUID assetId, UUID branchId);

    AssetResponse disposeAsset(UUID assetId, String remark);

    AssetResponse getAssetById(UUID assetId);

    List<AssetResponse> getAllAssets();
}
