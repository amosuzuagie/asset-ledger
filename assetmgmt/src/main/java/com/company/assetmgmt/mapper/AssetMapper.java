package com.company.assetmgmt.mapper;

import com.company.assetmgmt.dto.AssetCreateRequest;
import com.company.assetmgmt.dto.AssetResponse;
import com.company.assetmgmt.dto.AssetUpdateRequest;
import com.company.assetmgmt.model.Asset;

public class AssetMapper {
    private AssetMapper() {}

    public static Asset toEntity(AssetCreateRequest request) {
        Asset asset = new Asset();
        asset.setTagId(request.getTagId());
        asset.setAssetName(request.getAssetName());
        asset.setSerialNumber(request.getSerialNumber());
        asset.setDateOfAcquisition(request.getDateOfAcquisition());
        asset.setAmount(request.getAmount());
        asset.setLocation(request.getLocation());
        asset.setSubsidiary(request.getSubsidiary());
        asset.setRemark(request.getRemark());
        return asset;
    }

    public static AssetResponse toResponse(Asset asset) {
        AssetResponse response = new AssetResponse();
        response.setId(asset.getId());
        response.setTagId(asset.getTagId());
        response.setAssetName(asset.getAssetName());
        response.setLocation(asset.getLocation());
        response.setCategoryName(
                asset.getCategory() != null ? asset.getCategory().getName() : null
        );
        response.setCategoryId(
                asset.getCategory() != null ? asset.getCategory().getId() : null
        );
        response.setStatus(asset.getStatus());
        response.setBranchName(
                asset.getBranch() != null ? asset.getBranch().getName() : null
        );
        response.setBranchId(
                asset.getBranch() != null ? asset.getBranch().getId() : null
        );
        response.setAmount(asset.getAmount());
        response.setDateOfAcquisition(asset.getDateOfAcquisition());
        response.setCostOfDisposal(asset.getCostOfDisposal());
        response.setDisposalRemark(asset.getDisposalRemark());
        return response;
    }


    public static AssetResponse toAllowedUpdate(Asset existing, AssetUpdateRequest request) {
        existing.setAssetName(request.getAssetName());
        existing.setSerialNumber(request.getSerialNumber());
        existing.setRemark(request.getRemark());
        existing.setAmount(request.getAmount());
        existing.setLocation(request.getLocation());
        existing.setSubsidiary(request.getSubsidiary());
        existing.setDateOfAcquisition(request.getDateOfAcquisition());

        return toResponse(existing);
    }
}
