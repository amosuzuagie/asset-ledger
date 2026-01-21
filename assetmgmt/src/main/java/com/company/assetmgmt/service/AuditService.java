package com.company.assetmgmt.service;

import java.util.UUID;

public interface AuditService {
    void log(String action, UUID assetId, String description);
}
