package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.LoginRequest;
import com.company.assetmgmt.dto.LoginResponse;
import com.company.assetmgmt.dto.RegisterRequest;
import com.company.assetmgmt.dto.RegisterResponse;
import com.company.assetmgmt.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }
}
