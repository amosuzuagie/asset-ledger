package com.company.assetmgmt.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BranchManagerResponse {
    private UUID userId;
    private String fullName;
    private UUID branchId;
    private String branchName;
}
