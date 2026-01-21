package com.company.assetmgmt.dto;

import com.company.assetmgmt.model.AssetClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetUpdateRequest {
    @NotBlank
    private String description;

    @NotNull
    private AssetClass assetClass;

    @NotNull
    private BigDecimal amount;

    private String remark;

    @NotBlank
    private String subsidiary;
}
