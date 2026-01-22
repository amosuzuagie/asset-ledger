package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.AssignManagerRequest;
import com.company.assetmgmt.dto.BranchManagerResponse;
import com.company.assetmgmt.model.Branch;
import com.company.assetmgmt.model.User;
import com.company.assetmgmt.model.UserBranch;
import com.company.assetmgmt.model.enums.Role;
import com.company.assetmgmt.repository.BranchRepository;
import com.company.assetmgmt.repository.UserBranchRepository;
import com.company.assetmgmt.repository.UserRepository;
import com.company.assetmgmt.service.AuditService;
import com.company.assetmgmt.service.BranchAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchAssignmentServiceImpl implements BranchAssignmentService {
    private final AuditService auditService;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final UserBranchRepository userBranchRepository;

    @Override
    public void assignManagerToBranch(AssignManagerRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getRoles() != Role.MANAGERS) {
            throw new IllegalStateException("User is not a manager");
        }

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

        userBranchRepository
                .findByUserIdAndBranchId(user.getId(), branch.getId())
                .ifPresent(ub -> {
                    throw new IllegalStateException("Manager already assigned to branch");
                });

        UserBranch userBranch = UserBranch.builder()
                .user(user)
                .branch(branch)
                .build();

        userBranchRepository.save(userBranch);

        auditService.log(
                "ASSIGN_MANAGER",
                null,
                "Assigned manager " + user.getEmail() + " to branch " + branch.getName()
        );
    }

    @Override
    public void unassignManagerFromBranch(UUID userId, UUID branchId) {
        userBranchRepository.deleteByUserIdAndBranchId(userId, branchId);
    }

    @Override
    public List<BranchManagerResponse> getBranchManagers(UUID branchId) {

        return userBranchRepository.findAll()
                .stream()
                .filter(ub -> ub.getBranch().getId().equals(branchId))
                .map(ub -> {
                    BranchManagerResponse dto = new BranchManagerResponse();
                    dto.setUserId(ub.getUser().getId());
                    dto.setFullName(
                            ub.getUser().getFirstName() + " " + ub.getUser().getLastName()
                    );
                    dto.setBranchId(branchId);
                    dto.setBranchName(ub.getBranch().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
