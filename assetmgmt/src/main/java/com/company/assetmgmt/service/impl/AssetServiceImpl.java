package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.*;
import com.company.assetmgmt.exception.BusinessRuleException;
import com.company.assetmgmt.exception.ResourceNotFoundException;
import com.company.assetmgmt.mapper.AssetMapper;
import com.company.assetmgmt.model.*;
import com.company.assetmgmt.model.enums.AssetClass;
import com.company.assetmgmt.model.enums.AssetStatus;
import com.company.assetmgmt.repository.AssetCategoryRepository;
import com.company.assetmgmt.repository.AssetMovementRepository;
import com.company.assetmgmt.repository.AssetRepository;
import com.company.assetmgmt.repository.BranchRepository;
import com.company.assetmgmt.security.SecurityUtil;
import com.company.assetmgmt.service.AssetService;
import com.company.assetmgmt.service.AuditService;
import com.company.assetmgmt.specification.AssetSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AuditService auditService;
    private final AssetRepository assetRepository;
    private final BranchRepository branchRepository;
    private final BranchScopeServiceImpl branchScopeService;
    private final AssetCategoryRepository categoryRepository;
    private final AssetMovementRepository movementRepository;


    @Override
    public AssetResponse createAsset(AssetCreateRequest request, UUID categoryId) {

        // Asset code must be unique
        if (assetRepository.existsByAssetCode(request.getAssetCode())) {
            throw new IllegalStateException("Asset code already exists");
        }

        AssetCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset category not found"));

        AssetClass lockedClass = category.getAssetClass();

        Asset asset = AssetMapper.toEntity(request);
        asset.setAssetClass(lockedClass);
        asset.setCategory(category);

        asset.setStatus(
                asset.getBranch() == null ? AssetStatus.IN_STORE : AssetStatus.ASSIGNED
        );

        assetRepository.save(asset);

        return AssetMapper.toResponse(asset);
    }

    @Override
    public AssetResponse updateAsset(UUID assetId, AssetUpdateRequest request, UUID categoryId) {
        if (!SecurityUtil.hasRole("ADMIN") || !SecurityUtil.hasRole("FINANCE")) {
            throw new SecurityException("You are not allowed to update assets");
        }

        Asset existing = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (existing.isDisposed()) {
            throw new IllegalStateException("Disposed asset cannot be modified");
        }

        //Prevent asset class changes
        AssetClass existingClass = existing.getAssetClass();

        if (categoryId != null) {
            AssetCategory category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Asset category not found"));

            if (!category.getAssetClass().equals(existingClass)) {
                throw new IllegalStateException("Category does not belong to asset class: " + existingClass);
            }
            existing.setCategory(category);
        }

        return AssetMapper.toAllowedUpdate(existing, request);
    }

    @Override
    public AssetResponse assignAssetToBranch(UUID assetId, UUID branchId) {
        Asset asset = getAssetEntity(assetId);

        if (asset.getStatus() == AssetStatus.DISPOSED) {
            throw new IllegalStateException("Disposed asset cannot be reassigned");
        }

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Branch not found"));

        asset.setBranch(branch);
        asset.setStatus(AssetStatus.ASSIGNED);

        AssetMovementHistory history = AssetMovementHistory.builder()
                .asset(asset)
                .fromBranch(asset.getBranch())
                .toBranch(branch)
                .reason("Asset assigned to " + branch.getName())
                .movementDate(Instant.now())
                .build();

        movementRepository.save(history);

        auditService.log(
                "ASSIGN_ASSET",
                asset.getId(),
                "Assigned to branch ID: " + branchId
        );

        return AssetMapper.toResponse(asset);
    }

    @Override
    @Transactional(readOnly = true)
    public AssetResponse getAssetById(UUID assetId) {
        return  AssetMapper.toResponse(getAssetEntity(assetId));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssetResponse> getAllAssets(Pageable pageable) {
        return assetRepository.findAll(pageable)
                .map(AssetMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssetResponse> searchAssets(AssetSearchRequest filter, Pageable pageable) {
        Specification<Asset> spec = Specification.where(
                AssetSpecification.hasAssetCodeLike(filter.getAssetCode())
                ).and(AssetSpecification.hasDescriptionLike(filter.getDescription()))
                .and(AssetSpecification.hasAssetClass(filter.getAssetClass()))
                .and(AssetSpecification.hasCategory(filter.getCategoryId()))
                .and(AssetSpecification.hasBranch(filter.getBranchId()))
                .and(AssetSpecification.hasStatus(filter.getStatus()))
                .and(AssetSpecification.hasSubsidiary(filter.getSubsidiary()))
                .and(AssetSpecification.acquireBetween(filter.getAcquiredFrom(), filter.getAcquiredTo()));

        if (!branchScopeService.isGlobalScope()) {
            spec = spec.and(
                    AssetSpecification.branchIn(branchScopeService.getAccessibleBranchIds())
            );
        }

        return assetRepository.findAll(spec, pageable)
                .map(AssetMapper::toResponse);
    }

    @Override
    public void deleteAsset(UUID assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (asset.getStatus() == AssetStatus.ASSIGNED) {
            throw new BusinessRuleException("Assigned asset must be unassigned before deletion");
        }

        asset.markDeleted();

        auditService.log(
                "DELETE_ASSET",
                asset.getId(),
                "Asset soft deleted"
        );
    }

    @Override
    public void restoreAsset(UUID assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found"));

        asset.restore();

        auditService.log(
                "RESTORE_ASSET",
                asset.getId(),
                "Asset restored"
        );
    }

    @Override
    public AssetDisposalResponse disposeAsset(AssetDisposalRequest request) {
        Asset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (asset.isDisposed()) {
            throw new IllegalStateException("Asset already disposed");
        }

        // Record final movement
        AssetMovementHistory history = AssetMovementHistory.builder()
                .asset(asset)
                .fromBranch(asset.getBranch())
                .toBranch(null)
                .reason("ASSET DISPOSAL")
                .movementDate(Instant.now())
                .build();

        movementRepository.save(history);

        asset.setBranch(null);
        asset.setDisposed(true);
        asset.setStatus(AssetStatus.DISPOSED);
        asset.setDateOfDisposal(LocalDate.now());
        asset.setDisposalRemark(request.getRemark());
        asset.setCostOfDisposal(request.getCostOfDisposal());

        assetRepository.save(asset);

        AssetDisposalResponse response = new AssetDisposalResponse();
        response.setAssetId(asset.getId());
        response.setRemark(asset.getDisposalRemark());
        response.setCostOfDisposal(asset.getCostOfDisposal());
        response.setDateOfDisposal(asset.getDateOfDisposal());

        auditService.log(
                "DISPOSE_ASSET",
                asset.getId(),
                "Disposed: " + request.getRemark()
        );

        return response;
    }

    @Override
    public Page<AssetResponse> getDeletedAssets(Pageable pageable) {
        Page<Asset> assets = assetRepository.findAllDeleted(pageable);

        return assets.map(this::mapToResponse);
    }

    // =============================
    // HELPERS
    // =============================

    private Asset getAssetEntity(UUID assetId) {
        return assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }

    private AssetResponse mapToResponse(Asset a) {
        return new AssetResponse(
                a.getId(),
                a.getAssetCode(),
                a.getDescription(),
                a.getAssetClass(),
                a.getCategory().getId(),
                a.getCategory().getName(),
                a.getStatus(),
                a.getBranch().getId(),
                a.getBranch().getName(),
                a.getAmount(),
                a.getDateOfAcquisition(),
                a.getCostOfDisposal(),
                a.getDisposalRemark()
        );
    }
}
