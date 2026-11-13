package com.seleste.bff_agendador.infrastructure.security.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public FeignError feignError(){
        return new FeignError();
    }
}
