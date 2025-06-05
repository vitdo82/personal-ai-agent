package com.vitdo82.paa.serviceapi.quize.service;

import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitdo82.paa.serviceapi.quize.repository.QuizEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonQuizBuilder {

    private final ObjectMapper objectMapper;

    public List<QuizEntity> buildQuizz(URL path) {
        try {
            return objectMapper.readValue(path, new TypeReference<List<QuizEntity>>() {});
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return List.of();
    }
}
