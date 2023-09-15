package com.aprakash.context;

import com.aprakash.ApplicationLauncher;
import com.aprakash.services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
public class MyBankApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
