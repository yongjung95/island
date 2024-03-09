package com.tictoccroc.island.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("째깍섬 예약 API")
                        .description("매장별, 수업을 예약 및 취소하고, 예약자 및 예약이력을 조회할 수 있습니다.")
                        .version("1.0.0"));
    }
}
