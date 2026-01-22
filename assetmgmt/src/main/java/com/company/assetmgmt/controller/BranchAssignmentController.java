package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.AssignManagerRequest;
import com.company.assetmgmt.dto.BranchManagerResponse;
import com.company.assetmgmt.service.BranchAssignmentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/branches/managers")
public class BranchAssignmentController {
    private final BranchAssignmentService branchAssignmentService;

    public BranchAssignmentController(BranchAssignmentService branchAssignmentService) {
        this.branchAssignmentService = branchAssignmentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void assign(@RequestBody @Valid AssignManagerRequest request) {
        branchAssignmentService.assignManagerToBranch(request);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void unassign(
            @RequestParam UUID userId,
            @RequestParam UUID branchId
    ) {
        branchAssignmentService.unassignManagerFromBranch(userId, branchId);
    }

    @GetMapping("/{branchId}")
    @PreAuthorize("hasAnyRole('ADMIN','DIRECTORS')")
    public List<BranchManagerResponse> managers(@PathVariable UUID branchId) {
        return branchAssignmentService.getBranchManagers(branchId);
    }
}
