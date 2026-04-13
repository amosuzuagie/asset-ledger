package com.company.assetmgmt.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull(message = "Name is required")
        String name,

//        @NotNull(message = "Asset class is required")
//        AssetClass assetClass,
        String description
) {}
