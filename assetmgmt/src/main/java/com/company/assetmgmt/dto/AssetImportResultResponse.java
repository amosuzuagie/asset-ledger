package com.company.assetmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AssetImportResultResponse {
    private int totalProcessed;
    private int successCount;
    private int failureCount;

    private List<AssetImportRow> failedRows;
}
