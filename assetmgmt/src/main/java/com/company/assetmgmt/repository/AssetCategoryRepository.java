package com.company.assetmgmt.repository;

import com.company.assetmgmt.model.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory, UUID> {
}
