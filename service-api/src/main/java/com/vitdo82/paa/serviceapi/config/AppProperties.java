package com.vitdo82.paa.serviceapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Assistants assistants = new Assistants();
    private Quiz quiz = new Quiz();

    @Data
    public static class Assistants {
        private boolean enabled;
    }

    @Data
    public static class Quiz {
        private boolean enabled;
    }
} 