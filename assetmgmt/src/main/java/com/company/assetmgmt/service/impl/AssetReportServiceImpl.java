package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.report.AssetFinancialSummary;
import com.company.assetmgmt.dto.report.CountReport;
import com.company.assetmgmt.repository.AssetReportRepository;
import com.company.assetmgmt.service.AssetReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetReportServiceImpl implements AssetReportService {
    private final AssetReportRepository reportRepository;

    @Override
    public long getTotalAssets() {
        return reportRepository.totalAssets();
    }

    @Override
    public List<CountReport> getAssetsByStatus() {
        return reportRepository.assetsByStatus();
    }

    @Override
    public List<CountReport> getAssetsByBranch() {
        return reportRepository.assetsByBranch();
    }

    @Override
    public List<CountReport> getAssetsByCategory() {
        return reportRepository.assetsByCategory();
    }

    @Override
    public List<CountReport> getAssetsByAssetClass() {
        return reportRepository.assetsByAssetClass();
    }

    @Override
    public AssetFinancialSummary getFinancialSummary() {
        return reportRepository.financialSummary();
    }
}
