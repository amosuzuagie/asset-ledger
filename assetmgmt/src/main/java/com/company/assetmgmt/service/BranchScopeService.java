package com.company.assetmgmt.service;

import java.util.Set;
import java.util.UUID;

public interface BranchScopeService {
    boolean isGlobalScope();

    Set<UUID> getAccessibleBranchIds();
}
