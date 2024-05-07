package com.example.ca4u.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("CA4U API 문서")
                .description("Springdoc을 사용한 Swagger UI")
                .description("010-8864-1668 / tlsdnd1668@naver.com / 김신웅")
                .version("1.0.0");
    }
}