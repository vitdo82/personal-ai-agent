package com.vitdo82.paa.serviceapi.quize.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.quiz")
@Data
public class QuizProperty {

    private boolean enabled;
}
