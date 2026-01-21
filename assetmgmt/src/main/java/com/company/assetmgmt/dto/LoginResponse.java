package com.company.assetmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    UserSummary user;
}
