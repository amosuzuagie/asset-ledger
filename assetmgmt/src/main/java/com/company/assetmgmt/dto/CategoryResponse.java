package com.company.assetmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private UUID id;
    private String name;
//    private AssetClass assetClass;
    private String description;
}
