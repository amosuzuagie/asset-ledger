package com.company.assetmgmt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BranchRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String state;

    private String location;


    private String address;
}
