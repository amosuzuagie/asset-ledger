package com.company.assetmgmt.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class AssetDisposalResponse {
    private UUID assetId;
    private LocalDate dateOfDisposal;
    private BigDecimal costOfDisposal;
    private String remark;
}
