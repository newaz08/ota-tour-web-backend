package com.technonext.ota.b2c.tour.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {
    private static final String SECURITY_SCHEME_NAME = "JWT Token";
    private static final String SECURITY_SCHEME_NAME_REFRESH_TOKEN = "Authorization";

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(apiInfo());
        openAPI.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
        openAPI.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME_REFRESH_TOKEN));
        openAPI.components(apiComponents());
        return openAPI;
    }

    private Info apiInfo() {
        Info info = new Info();
        info.setTitle("OTA-Tour-B2C");
        info.setDescription("OTA Tour Packages for B2C");
        info.setContact(apiContact());
        info.setVersion("0.0.1");
        info.setLicense(apiLicense());
        info.setTermsOfService("OTA-Tour Terms of Services (work in progress)");
        return info;
    }

    private Contact apiContact() {
        Contact contact = new Contact();
        contact.setName("OTA-Tour-B2C");
        return contact;
    }

    private License apiLicense() {
        License license = new License();
        return license;
    }

    private Components apiComponents() {
        Components components = new Components();
        components.addSecuritySchemes(
            SECURITY_SCHEME_NAME,
            new SecurityScheme()
                .name(SECURITY_SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
        );

        components.addSecuritySchemes(
            SECURITY_SCHEME_NAME_REFRESH_TOKEN,
            new SecurityScheme()
                .name(SECURITY_SCHEME_NAME_REFRESH_TOKEN)
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat(SECURITY_SCHEME_NAME_REFRESH_TOKEN)
        );
        return components;
    }
}
