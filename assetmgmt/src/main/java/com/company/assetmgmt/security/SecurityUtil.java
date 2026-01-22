package com.company.assetmgmt.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.UUID;

public final class SecurityUtil {
    private SecurityUtil () {}

    public static boolean hasRole(String role) {
        Authentication auth = getAuthentication();
        if (auth == null) return false;

        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).filter(Objects::nonNull)
                .anyMatch(r -> r.equals("ROLE_" + role));
    }

    public static String getCurrentUsername() {
        Authentication auth = getAuthentication();
        return auth != null ? auth.getName() : "SYSTEM";
    }

    public static UUID getCurrentUserId() {
        Authentication auth = getAuthentication();
        return auth != null ? (UUID) auth.getPrincipal() : null;
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
