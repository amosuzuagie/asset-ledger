package com.company.assetmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserSummary {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
