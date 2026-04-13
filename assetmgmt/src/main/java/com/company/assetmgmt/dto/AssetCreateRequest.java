package com.company.assetmgmt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class AssetCreateRequest {
    @NotBlank(message = "Asset code is required")
    private String tagId;

    @NotBlank(message = "Asset name is required")
    private String assetName;

    @NotNull(message = "Category ID is required")
    private UUID categoryId;

    private String serialNumber;

    private String location;

    private LocalDate dateOfAcquisition;

    private BigDecimal amount;

    private String subsidiary;

    private String remark;

    private UUID branchId;
}
