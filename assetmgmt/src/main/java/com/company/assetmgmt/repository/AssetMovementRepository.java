package com.company.assetmgmt.repository;

import com.company.assetmgmt.model.AssetMovementHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssetMovementRepository extends JpaRepository<AssetMovementHistory, UUID> {
    List<AssetMovementHistory> findByAssetIdOrderByMovementDateDesc(UUID assetId);
}
