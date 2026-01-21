package com.company.assetmgmt.repository;

import com.company.assetmgmt.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {
    List<AuditLog> findByAssetId(UUID assetId);
    List<AuditLog> findByUsername(String username);
}
