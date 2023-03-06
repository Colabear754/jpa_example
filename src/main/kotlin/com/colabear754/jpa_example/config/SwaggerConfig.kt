package com.colabear754.jpa_example.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun swaggerApi(): OpenAPI = OpenAPI()
        .components(Components())
        .info(
            Info()
            .title("JPA 테스트")
            .description("JPA 테스트입니다.")
            .version("1.0.0"))
}