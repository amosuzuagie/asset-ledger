package com.company.assetmgmt.repository;

import com.company.assetmgmt.model.Asset;
import com.company.assetmgmt.model.AssetStatus;
import com.company.assetmgmt.repository.projection.CountByKeyProjection;
import com.company.assetmgmt.repository.projection.SumByKeyProjection;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID>, JpaSpecificationExecutor<Asset> {
    Optional<Asset> findBySerialNumber(String serialNumber);

//    @Query("""
//       select distinct a
//       from Asset a
//       join a.tags t
//       where t.id = :tagId
//       """)
//    Optional<Asset> findByTagId(String tagId);

    List<Asset> findByStatus(AssetStatus status);

    List<Asset> findByBranch_Id(UUID branchId);

    @Query("""
       select a.status as key, count(a) as total
       from Asset a
       group by a.status
       """)
    List<CountByKeyProjection> countAssetsByStatus();

    @Query("""
       select c.assetClass as key, count(a) as total
       from Asset a
       join a.category c
       group by c.assetClass
       """)
    List<CountByKeyProjection> countAssetsByClass();

    @Query("""
       select coalesce(b.name, 'IN_STORE') as key, count(a) as total
       from Asset a
       left join a.branch b
       group by b.name
       """)
    List<CountByKeyProjection> countAssetsByBranch();

    @Query("""
       select c.assetClass as key, sum(a.amount) as total
       from Asset a
       join a.category c
       group by c.assetClass
       """)
    List<SumByKeyProjection> sumAssetValueByClass();

    @Query("""
       select coalesce(b.name, 'IN_STORE') as key, sum(a.amount) as total
       from Asset a
       left join a.branch b
       group by b.name
       """)
    List<SumByKeyProjection> sumAssetValueByBranch();

    @Query("select coalesce(sum(a.amount), 0) from Asset a")
    BigDecimal totalAssetValue();

    boolean existsByAssetCode(@NotBlank String assetCode);
}
