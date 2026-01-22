package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.AssignManagerRequest;
import com.company.assetmgmt.dto.BranchManagerResponse;

import java.util.List;
import java.util.UUID;

public interface BranchAssignmentService {
    void assignManagerToBranch(AssignManagerRequest request);

    void unassignManagerFromBranch(UUID userId, UUID branchId);

    List<BranchManagerResponse> getBranchManagers(UUID branchId);
}
