package com.company.assetmgmt.repository;

import com.company.assetmgmt.model.Branch;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    boolean existsByCode(@NotBlank String code);
}
