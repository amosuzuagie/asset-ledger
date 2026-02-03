package com.company.assetmgmt.dto;

import com.company.assetmgmt.model.enums.AssetClass;
import com.company.assetmgmt.model.enums.AssetStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetResponse {
    private UUID id;
    private String assetCode;
    private String description;

    private AssetClass assetClass;

    private UUID categoryId;
    private String categoryName;

    private AssetStatus status;

    private UUID branchId;
    private String branchName;

    private BigDecimal amount;
    private LocalDate dateOfAcquisition;

    private BigDecimal costOfDisposal;
    private String disposalRemark;
}