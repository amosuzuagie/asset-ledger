package com.company.assetmgmt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BranchRequest {
    @NotBlank(message = "Branch name is required")
    private String name;

    @NotBlank(message = "Branch code is required")
    private String code;

    @NotBlank(message = "State is required")
    private String state;

    private String location;


    private String address;
}
