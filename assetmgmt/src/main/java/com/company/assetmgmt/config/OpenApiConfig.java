package com.company.assetmgmt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Spring Property Management System",
                        email = "contact@pms.com",
                        url = "https://pms.com"
                ),
                description = "Open API documentation for Spring Property Management System",
                title = "OpenAPI Specification",
                version = "1.0",
                license = @License(
                        name = "License name",
                        url = "https://amos.com/licence"
                ),
                termsOfService = "https://amos.com/terms"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local ENV"
                ),
                @Server(
                        url = "https://production.url",
                        description = "Pod ENV"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
