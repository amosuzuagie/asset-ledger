package com.company.assetmgmt.mapper;

import com.company.assetmgmt.dto.AssetCreateRequest;
import com.company.assetmgmt.dto.AssetResponse;
import com.company.assetmgmt.dto.AssetUpdateRequest;
import com.company.assetmgmt.model.Asset;

public class AssetMapper {
    private AssetMapper() {}

    public static Asset toEntity(AssetCreateRequest request) {
        Asset asset = new Asset();
        asset.setAssetCode(request.getAssetCode());
        asset.setDescription(request.getDescription());
        asset.setSerialNumber(request.getSerialNumber());
        asset.setDateOfAcquisition(request.getDateOfAcquisition());
        asset.setAmount(request.getAmount());
        asset.setSubsidiary(request.getSubsidiary());
        asset.setRemark(request.getRemark());
        return asset;
    }

    public static AssetResponse toResponse(Asset asset) {
        AssetResponse response = new AssetResponse();
        response.setId(asset.getId());
        response.setAssetCode(asset.getAssetCode());
        response.setDescription(asset.getDescription());
        response.setAssetClass(asset.getAssetClass());
        response.setCategoryName(
                asset.getCategory() != null ? asset.getCategory().getName() : null
        );
        response.setStatus(asset.getStatus());
        response.setBranchName(
                asset.getBranch() != null ? asset.getBranch().getName() : null
        );
        response.setAmount(asset.getAmount());
        response.setDateOfAcquisition(asset.getDateOfAcquisition());
        return response;
    }


    public static AssetResponse toAllowedUpdate(Asset existing, AssetUpdateRequest request) {
        existing.setDescription(request.getDescription());
        existing.setSerialNumber(request.getSerialNumber());
        existing.setRemark(request.getRemark());
        existing.setAmount(request.getAmount());
        existing.setSubsidiary(request.getSubsidiary());
        existing.setDateOfAcquisition(request.getDateOfAcquisition());

        return toResponse(existing);
    }
}
