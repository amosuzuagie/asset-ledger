package com.company.assetmgmt.config;

import com.company.assetmgmt.model.User;
import com.company.assetmgmt.model.enums.Role;
import com.company.assetmgmt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(2)
@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .email("admin@test.com")
                    .password(passwordEncoder.encode("Change@pr0d"))
                    .firstName("Admin")
                    .lastName("Super")
                    .roles(Role.ADMIN)
                    .enabled(true)
                    .build();

            userRepository.save(admin);
        }
    }
}
