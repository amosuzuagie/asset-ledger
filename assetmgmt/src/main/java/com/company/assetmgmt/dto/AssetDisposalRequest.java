package com.company.assetmgmt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AssetDisposalRequest {
    @NotNull
    private UUID assetId;
    private BigDecimal costOfDisposal;
    private String remark;
}
