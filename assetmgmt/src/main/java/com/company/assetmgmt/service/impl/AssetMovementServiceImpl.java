package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.AssetMovementRequest;
import com.company.assetmgmt.dto.AssetMovementResponse;
import com.company.assetmgmt.exception.BusinessRuleException;
import com.company.assetmgmt.exception.ResourceNotFoundException;
import com.company.assetmgmt.model.Asset;
import com.company.assetmgmt.model.AssetMovementHistory;
import com.company.assetmgmt.model.Branch;
import com.company.assetmgmt.model.enums.AssetStatus;
import com.company.assetmgmt.repository.AssetMovementRepository;
import com.company.assetmgmt.repository.AssetRepository;
import com.company.assetmgmt.repository.BranchRepository;
import com.company.assetmgmt.service.AssetMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssetMovementServiceImpl implements AssetMovementService {
    private final AssetRepository assetRepository;
    private final BranchRepository branchRepository;
    private final AssetMovementRepository movementRepository;

    @Override
    public void moveAsset(AssetMovementRequest request) {
        Asset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (asset.getStatus() == AssetStatus.DISPOSED) {
            throw new BusinessRuleException("Disposed assets cannot be moved");
        }

        Branch fromBranch = asset.getBranch();
        Branch toBranch = null;

        if (request.getToBranchId() != null) {
            toBranch = branchRepository.findById(request.getToBranchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));

            System.out.println("###TO_BRANCH: " + toBranch.getName());
        }

        AssetMovementHistory history = AssetMovementHistory.builder()
                .asset(asset)
                .fromBranch(fromBranch)
                .toBranch(toBranch)
                .reason(request.getReason())
                .movementDate(Instant.now())
                .build();
        System.out.println("FROM_BRANCH: " + fromBranch + "TO_BRANCH: " + toBranch);

        movementRepository.save(history);

        asset.setBranch(toBranch);
        assetRepository.save(asset);
    }

    @Override
    public List<AssetMovementResponse> getAssetMovementHistory(UUID assetId) {
        return movementRepository
                .findByAssetIdOrderByMovementDateDesc(assetId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private AssetMovementResponse mapToResponse(AssetMovementHistory history) {
        AssetMovementResponse response = new AssetMovementResponse();

        response.setId(history.getId());
        response.setAssetId(history.getAsset().getId());
        response.setMovementDate(history.getMovementDate());
        response.setReason(history.getReason());

        if (history.getFromBranch() != null) {
            response.setFromBranchId(history.getFromBranch().getId());
            response.setFromBranchName(history.getFromBranch().getName());
        }

        if (history.getToBranch() != null) {
            response.setToBranchId(history.getToBranch().getId());
            response.setToBranchName(history.getToBranch().getName());
        }

        return response;
    }
}
