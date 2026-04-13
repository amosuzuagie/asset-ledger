package com.company.assetmgmt.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class AssetUpdateRequest {
    private String assetName;

    private String serialNumber;

    private String location;

    private LocalDate dateOfAcquisition;

    private BigDecimal amount;

    private String subsidiary;

    private String remark;

    private UUID categoryId;
}
