package com.company.assetmgmt.controller;

import com.company.assetmgmt.model.AuditLog;
import com.company.assetmgmt.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditLogRepository auditLogRepository;

    @GetMapping("/asset/{assetId}")
    @PreAuthorize("hasAnyRole('AUDIT','ADMIN','DIRECTORS')")
    public List <AuditLog> getAssetAuditLogs(@PathVariable UUID assetId) {
        return auditLogRepository.findByAssetId(assetId);
    }
}
