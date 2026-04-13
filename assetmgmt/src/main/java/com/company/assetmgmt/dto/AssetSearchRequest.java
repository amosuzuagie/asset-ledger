package com.company.assetmgmt.dto;

import com.company.assetmgmt.model.enums.AssetStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class AssetSearchRequest {
    private String tagId;
    private String assetName;

//    private AssetClass assetClass;
    private UUID categoryId;
    private UUID branchId;

    private AssetStatus status;
    private String subsidiary;

    private LocalDate acquiredFrom;
    private LocalDate acquiredTo;
}
