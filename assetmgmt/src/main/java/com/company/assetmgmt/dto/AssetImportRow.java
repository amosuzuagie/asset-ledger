package com.company.assetmgmt.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AssetImportRow {
    private Integer rowNumber;

    private String tagId;
    private String assetName;
    private String branchCode;
    private String assetCategory;
    private String purchaseCost;
    private String purchaseDate;

    private List<String> errors = new ArrayList<>();

    public boolean isValid() {
        return errors == null || errors.isEmpty();
    }
}
