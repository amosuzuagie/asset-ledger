package com.company.assetmgmt.dto;

import com.company.assetmgmt.model.AssetClass;
import com.company.assetmgmt.model.AssetStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AssetResponse {
    private UUID id;
    private String description;
    private AssetClass assetClass;
    private String serialNumber;
    private String tagId;
    private AssetStatus status;

    private LocalDate acquisitionDate;
    private BigDecimal amount;
    private String remark;

    private LocalDate disposalDate;
    private BigDecimal disposalCost;

    private String subsidiary;

    private BranchSummary branch;
}
