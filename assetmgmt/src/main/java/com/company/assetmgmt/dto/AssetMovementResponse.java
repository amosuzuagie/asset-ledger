package com.company.assetmgmt.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AssetMovementResponse {
    private UUID id;
    private UUID assetId;

    private UUID fromBranchId;
    private String fromBranchName;

    private UUID toBranchId;
    private String toBranchName;

    private String reason;
    private Instant movementDate;
}
