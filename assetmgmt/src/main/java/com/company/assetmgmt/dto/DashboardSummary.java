package com.company.assetmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
public class DashboardSummary {
    private long totalAssets;
    private BigDecimal totalAssetValue;

    private Map<String, Long> assetsByStatus;
    private Map<String, Long> assetsByClass;
    private Map<String, Long> assetsByBranch;

    private Map<String, BigDecimal> assetValueByClass;
    private Map<String, BigDecimal> assetValueByBranch;
}
