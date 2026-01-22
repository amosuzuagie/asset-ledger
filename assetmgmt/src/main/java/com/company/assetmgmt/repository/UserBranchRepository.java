package com.company.assetmgmt.repository;

import com.company.assetmgmt.model.UserBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserBranchRepository extends JpaRepository<UserBranch, UUID> {
    List<UserBranch> findByUserId(UUID userId);

    Optional<UserBranch> findByUserIdAndBranchId(UUID userId, UUID branchId);

    void deleteByUserIdAndBranchId(UUID userId, UUID branchId);
}
