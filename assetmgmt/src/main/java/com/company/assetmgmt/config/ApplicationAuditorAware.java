package com.company.assetmgmt.config;

import com.company.assetmgmt.security.CustomUserDetails;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class ApplicationAuditorAware implements AuditorAware<UUID> {
    private static final UUID SYSTEM_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @NotNull
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.of(SYSTEM_UUID);
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        assert userDetails != null;
        return Optional.ofNullable(userDetails.getUser().getId());
    }
}
