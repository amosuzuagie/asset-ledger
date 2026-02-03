package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.repository.UserBranchRepository;
import com.company.assetmgmt.security.SecurityUtil;
import com.company.assetmgmt.service.BranchScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchScopeServiceImpl implements BranchScopeService {
    private final UserBranchRepository userBranchRepository;

    @Override
    public boolean isGlobalScope() {
        return SecurityUtil.hasRole("ADMIN")
                || SecurityUtil.hasRole("FINANCE")
                || SecurityUtil.hasRole("DIRECTORS")
                || SecurityUtil.hasRole("AUDIT");
    }

    @Override
    public Set<UUID> getAccessibleBranchIds() {

        if (isGlobalScope()) {
            return Set.of(); // empty = unrestricted
        }

        UUID userId = SecurityUtil.getCurrentUserId();
        return userBranchRepository.findBranchIdsByUserId(userId);

//        return userBranchRepository.findByUserId(userId)
//                .stream()
//                .map(ub -> ub.getBranch().getId())
//                .collect(Collectors.toSet());
    }
}
