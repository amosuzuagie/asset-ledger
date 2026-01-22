package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.AssetMovementRequest;
import com.company.assetmgmt.dto.AssetMovementResponse;

import java.util.List;
import java.util.UUID;

public interface AssetMovementService {
    void moveAsset(AssetMovementRequest request);

    List<AssetMovementResponse> getAssetMovementHistory(UUID assetId);
}
