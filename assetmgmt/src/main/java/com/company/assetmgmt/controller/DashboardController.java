package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.DashboardSummary;
import com.company.assetmgmt.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','DIRECTORS')")
    public DashboardSummary getDashboard() {
        return dashboardService.getDashboardSummary();
    }
}
