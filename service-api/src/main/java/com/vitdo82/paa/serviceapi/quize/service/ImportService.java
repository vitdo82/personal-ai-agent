package com.vitdo82.paa.serviceapi.quize.service;

import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.vitdo82.paa.serviceapi.quize.repository.models.QuizEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportService {

    private final JsonQuizBuilder jsonQuizBuilder;
    private final QuizService quizService;

    public void importData(String path) {
        if (StringUtils.isEmpty(path)) path = "data";

        try {
            URL url = getClass().getClassLoader().getResource(path);
            List<QuizEntity> quizz = jsonQuizBuilder.buildQuizz(url);
            List<String> ids = quizService.save(quizz);

            log.info("Imported {} quizzes", ids.size());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    // @EventListener(ApplicationReadyEvent.class)
    // public void importOnStartup() {
    //     this.importData(null);
    // }
}
