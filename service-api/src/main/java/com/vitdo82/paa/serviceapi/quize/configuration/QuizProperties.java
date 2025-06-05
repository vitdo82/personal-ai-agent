package com.vitdo82.paa.serviceapi.quize.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.quiz")
public class QuizProperties {

    private boolean enabled;
}
