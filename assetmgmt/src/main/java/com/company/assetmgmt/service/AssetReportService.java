package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.report.AssetFinancialSummary;
import com.company.assetmgmt.dto.report.CountReport;

import java.util.List;

public interface AssetReportService {
    long getTotalAssets();

    List<CountReport> getAssetsByStatus();

    List<CountReport> getAssetsByBranch();

    List<CountReport> getAssetsByCategory();

    List<CountReport> getAssetsByAssetClass();

    AssetFinancialSummary getFinancialSummary();
}
