package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.model.AuditLog;
import com.company.assetmgmt.repository.AuditLogRepository;
import com.company.assetmgmt.security.SecurityUtil;
import com.company.assetmgmt.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
    private final AuditLogRepository auditLogRepository;

    @Override
    public void log(String action, UUID assetId, String description) {

        AuditLog log = AuditLog.builder()
                .action(action)
                .assetId(assetId)
                .username(SecurityUtil.getCurrentUsername())
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();

        auditLogRepository.save(log);
    }
}
