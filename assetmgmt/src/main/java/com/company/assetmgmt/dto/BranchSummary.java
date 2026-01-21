package com.company.assetmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BranchSummary {
    private UUID id;
    private String name;
//    private String code;
    private String state;
    private String location;
}
