package com.company.assetmgmt.dto.report;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetFinancialSummary {
    private BigDecimal totalAcquisitionCost;
    private BigDecimal totalDisposalCost;
    private BigDecimal netAssetValue;

    public AssetFinancialSummary(BigDecimal totalAcquisitionCost, BigDecimal totalDisposalCost, BigDecimal netAssetValue) {
        this.totalAcquisitionCost = totalAcquisitionCost;
        this.totalDisposalCost = totalDisposalCost;
        this.netAssetValue = netAssetValue;
    }
}
