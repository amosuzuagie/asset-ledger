package com.company.assetmgmt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AssetMovementRequest {
    @NotNull(message = "Asset is required")
    private UUID assetId;

    private UUID toBranchId;

    private String reason;
}
