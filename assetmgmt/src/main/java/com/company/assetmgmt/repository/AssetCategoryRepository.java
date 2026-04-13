package com.company.assetmgmt.repository;

import com.company.assetmgmt.model.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory, UUID> {
    boolean existsByName(String name);

    boolean existsByNameAndDeletedFalse(String assetCategory);

    Optional<AssetCategory> findByNameAndDeletedFalse(String assetCategory);
}
