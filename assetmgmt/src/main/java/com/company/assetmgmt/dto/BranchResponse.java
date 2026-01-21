package com.company.assetmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BranchResponse {
    private UUID id;
    private String name;
//    private String code;
    private String state;
    private String location;
    private String address;
}
