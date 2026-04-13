package com.company.assetmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AssetImportPreviewResponse {
    private int totalRows;
    private int validRows;
    private int invalidRows;

    private List<AssetImportRow> rows;
}
