package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.BranchRequest;
import com.company.assetmgmt.dto.BranchResponse;

import java.util.List;
import java.util.UUID;

public interface BranchService {
    BranchResponse create(BranchRequest request);
    BranchResponse update(UUID id, BranchRequest request);
    List<BranchResponse> findAll();
    BranchResponse getBranchById(UUID branchId);
}
