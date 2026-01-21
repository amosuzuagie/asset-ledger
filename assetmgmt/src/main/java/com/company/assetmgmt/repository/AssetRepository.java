package com.company.assetmgmt.repository;

import com.company.assetmgmt.model.Asset;
import com.company.assetmgmt.model.AssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID> {
    Optional<Asset> findBySerialNumber(String serialNumber);

    Optional<Asset> findByTagId(String tagId);

    List<Asset> findByStatus(AssetStatus status);

    List<Asset> findByBranch_Id(UUID branchId);
}
