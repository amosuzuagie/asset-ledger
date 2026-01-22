package com.company.assetmgmt.repository;

import com.company.assetmgmt.dto.report.AssetFinancialSummary;
import com.company.assetmgmt.dto.report.CountReport;
import com.company.assetmgmt.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AssetReportRepository extends JpaRepository<Asset, UUID> {
    // Total assets
    @Query("""
        SELECT COUNT(a)
        FROM Asset a
    """)
    long totalAssets();

    // Active vs disposed
    @Query("""
        SELECT new com.company.assetmgmt.dto.report.CountReport(
            CASE WHEN a.disposed = true THEN 'DISPOSED' ELSE 'ACTIVE' END,
            COUNT(a)
        )
        FROM Asset a
        GROUP BY a.disposed
    """)
    List<CountReport> assetsByStatus();

    // By branch
    @Query("""
        SELECT new com.company.assetmgmt.dto.report.CountReport(
            b.name,
            COUNT(a)
        )
        FROM Asset a
        JOIN a.branch b
        GROUP BY b.name
    """)
    List<CountReport> assetsByBranch();

    // By category
    @Query("""
        SELECT new com.company.assetmgmt.dto.report.CountReport(
            c.name,
            COUNT(a)
        )
        FROM Asset a
        JOIN a.category c
        GROUP BY c.name
    """)
    List<CountReport> assetsByCategory();

    // By asset class
    @Query("""
        SELECT new com.company.assetmgmt.dto.report.CountReport(
            CAST(a.assetClass AS string),
            COUNT(a)
        )
        FROM Asset a
        GROUP BY a.assetClass
    """)
    List<CountReport> assetsByAssetClass();

    // Financial summary
    @Query("""
        SELECT new com.company.assetmgmt.dto.report.AssetFinancialSummary(
            COALESCE(SUM(a.amount), 0),
            COALESCE(SUM(a.costOfDisposal), 0),
            COALESCE(SUM(a.amount), 0) - COALESCE(SUM(a.costOfDisposal), 0)
        )
        FROM Asset a
    """)
    AssetFinancialSummary financialSummary();
}
