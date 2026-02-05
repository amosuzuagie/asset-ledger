package com.company.assetmgmt.repository;

import com.company.assetmgmt.model.Branch;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    boolean existsByCode(@NotBlank String code);

    @Query("""
        SELECT b FROM Branch b
        WHERE b.id = :id AND b.deleted = true
    """)
    Branch findDeletedById(UUID id);

    @Query("""
        SELECT b FROM Branch b
        WHERE b.deleted = true
    """)
    Page<Branch> findAllDeleted(Pageable pageable);

    @Query("""
        SELECT b FROM Branch b
        WHERE b.deleted = false
    """)
    Page<Branch> findAllActive(Pageable pageable);
}
