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
    private String assetCode;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category ID is required")
    private UUID categoryId;

    private String serialNumber;

    private LocalDate dateOfAcquisition;

    private BigDecimal amount;

    private String subsidiary;

    private String remark;

    private UUID branchId;
}
