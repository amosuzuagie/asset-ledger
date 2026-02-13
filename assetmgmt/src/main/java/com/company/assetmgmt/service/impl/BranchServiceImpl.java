package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.BranchRequest;
import com.company.assetmgmt.dto.BranchResponse;
import com.company.assetmgmt.exception.BusinessRuleException;
import com.company.assetmgmt.exception.ResourceNotFoundException;
import com.company.assetmgmt.model.Branch;
import com.company.assetmgmt.repository.AssetRepository;
import com.company.assetmgmt.repository.BranchRepository;
import com.company.assetmgmt.service.BranchService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final AssetRepository assetRepository;

    @Override
    public BranchResponse create(BranchRequest request) {
        if (branchRepository.existsByCode(request.getCode())) {
            throw new BusinessRuleException("Branch code already exists");
        }

        Branch branch = Branch.builder()
                .name(request.getName())
                .code(request.getCode())
                .state(request.getState())
                .location(request.getLocation())
                .address(request.getAddress())
                .build();

        branchRepository.save(branch);

        return new BranchResponse(
                branch.getId(),
                branch.getName(),
                branch.getCode(),
                branch.getState(),
                branch.getLocation(),
                branch.getAddress()
        );
    }

    @Override
    public BranchResponse update(UUID id, BranchRequest request) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));

        branch.setName(request.getName());
        branch.setCode(request.getCode());
        branch.setState(request.getState());
        branch.setLocation(request.getLocation());
        branch.setAddress(request.getAddress());

        return mapToResponse(branchRepository.save(branch));
    }

    @Override
    public List<BranchResponse> findAll() {
        return branchRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BranchResponse getBranchById(UUID branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
        return mapToResponse(branch);

    }

    @Override
    public void deleteBranch(UUID branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found."));

        if (assetRepository.existsByBranchId(branchId)) {
            throw new IllegalStateException("Cannot delete branch. Assets are still assigned to this branch.");
        }

        branch.markDeleted();
        branchRepository.save(branch);
    }

    @Override
    public void restoreBranch(UUID branchId) {
        Branch branch = branchRepository.findDeletedById(branchId);

        branch.restore();
        branchRepository.save(branch);

    }

    @Override
    public Page<BranchResponse> getDeletedBranches(Pageable pageable) {
        Page<Branch> branches = branchRepository.findAllDeleted(pageable);

        return branches.map(this::mapToResponse);
    }

    private BranchResponse mapToResponse(Branch branch) {
        return new BranchResponse(
                branch.getId(),
                branch.getName(),
                branch.getCode(),
                branch.getState(),
                branch.getLocation(),
                branch.getAddress()
        );
    }
}
