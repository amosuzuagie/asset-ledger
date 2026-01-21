package com.company.assetmgmt.dto;

import com.company.assetmgmt.model.AssetClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class AssetCreateRequest {
    @NotBlank
    private String description;

    @NotNull
    private AssetClass assetClass;

    @NotBlank
    private String serialNumber;

    @NotBlank
    private String tagId;

    @NotNull
    private LocalDate acquisitionDate;

    @NotNull
    private BigDecimal amount;

    private String remark;

    @NotBlank
    private String subsidiary;

    /**
     * Optional:
     * Asset may be created in store
     */
    private UUID branchId;

}
