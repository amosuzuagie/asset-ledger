package com.company.assetmgmt.dto;

import com.company.assetmgmt.model.AssetClass;
import com.company.assetmgmt.model.AssetStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class AssetSearchRequest {
    private String assetCode;
    private String description;

    private AssetClass assetClass;
    private UUID categoryId;
    private UUID branchId;

    private AssetStatus status;
    private String subsidiary;

    private LocalDate acquiredFrom;
    private LocalDate acquiredTo;
}
