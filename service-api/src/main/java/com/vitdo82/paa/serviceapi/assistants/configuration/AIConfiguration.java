package com.vitdo82.paa.serviceapi.assistants.configuration;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfiguration {

  @Bean
  public OllamaApi ollamaApi() {
    return OllamaApi.builder().baseUrl("http://localhost:11434").build();
  }
}
