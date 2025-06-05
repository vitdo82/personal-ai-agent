package com.vitdo82.paa.serviceapi.assistants.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.assistants")
public class AssistanceProperties {

    private boolean enabled;

}
