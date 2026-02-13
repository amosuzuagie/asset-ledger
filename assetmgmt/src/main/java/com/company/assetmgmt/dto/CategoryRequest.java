package com.company.assetmgmt.dto;

import com.company.assetmgmt.model.enums.AssetClass;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull(message = "Name is required")
        String name,

        @NotNull(message = "Asset class is required")
        AssetClass assetClass,
        String description
) {}
