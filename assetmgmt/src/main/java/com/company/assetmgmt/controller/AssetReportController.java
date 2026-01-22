package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.report.AssetFinancialSummary;
import com.company.assetmgmt.dto.report.CountReport;
import com.company.assetmgmt.service.AssetReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports/assets")
public class AssetReportController {

    private final AssetReportService reportService;

    public AssetReportController(AssetReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/total")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','DIRECTORS','AUDIT')")
    public long totalAssets() {
        return reportService.getTotalAssets();
    }

    @GetMapping("/by-status")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','DIRECTORS','AUDIT')")
    public List<CountReport> byStatus() {
        return reportService.getAssetsByStatus();
    }

    @GetMapping("/by-branch")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','DIRECTORS')")
    public List<CountReport> byBranch() {
        return reportService.getAssetsByBranch();
    }

    @GetMapping("/by-category")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','DIRECTORS')")
    public List<CountReport> byCategory() {
        return reportService.getAssetsByCategory();
    }

    @GetMapping("/by-asset-class")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','DIRECTORS')")
    public List<CountReport> byAssetClass() {
        return reportService.getAssetsByAssetClass();
    }

    @GetMapping("/financial-summary")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','DIRECTORS')")
    public AssetFinancialSummary financialSummary() {
        return reportService.getFinancialSummary();
    }
}
