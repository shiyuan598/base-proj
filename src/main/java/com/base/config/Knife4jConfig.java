package com.base.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Knife4jConfig {
    private static final String SECURITY_SCHEME_NAME = "Authorization"; // 修改为和前面一致的名称

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Base Project API")
                        .version("1.0")
                        .description("Base Project API Documentation")
                        .contact(new Contact()
                                .name("wangshiyuan")
                                .email("wangyuanhpu@163.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .in(SecurityScheme.In.HEADER)
                                        .bearerFormat("JWT")
                                        .scheme("bearer")
                        ))
                .security(List.of(new SecurityRequirement().addList(SECURITY_SCHEME_NAME)));
    }
}