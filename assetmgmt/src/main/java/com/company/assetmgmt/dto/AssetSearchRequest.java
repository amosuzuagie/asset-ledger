package com.company.assetmgmt.dto;

import com.company.assetmgmt.model.AssetClass;
import com.company.assetmgmt.model.AssetStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssetSearchRequest {
    private String serialNumber;
    private String tagId;
    private AssetClass assetClass;
    private AssetStatus status;
    private Long branchId;
    private String subsidiary;

    private LocalDate acquiredFrom;
    private LocalDate acquiredTo;
}
