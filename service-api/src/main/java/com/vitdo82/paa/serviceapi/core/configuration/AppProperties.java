package com.vitdo82.paa.serviceapi.core.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.vitdo82.paa.serviceapi.config.AppProperties.Assistants;
import com.vitdo82.paa.serviceapi.config.AppProperties.Quiz;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private Assistants assistants;
    private Quiz quiz;
}
