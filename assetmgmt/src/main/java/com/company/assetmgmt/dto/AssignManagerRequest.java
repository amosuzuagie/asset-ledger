package com.company.assetmgmt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AssignManagerRequest {
    @NotNull
    private UUID userId;

    @NotNull
    private UUID branchId;
}
