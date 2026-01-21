package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.LoginRequest;
import com.company.assetmgmt.dto.LoginResponse;
import com.company.assetmgmt.dto.RegisterRequest;
import com.company.assetmgmt.dto.RegisterResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
}
