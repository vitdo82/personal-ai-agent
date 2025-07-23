package com.vitdo82.paa.serviceapi.quize.service;

import com.vitdo82.paa.serviceapi.quize.repository.models.QuizEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImportServiceTest {

    @Mock
    private JsonQuizBuilder jsonQuizBuilder;
    @Mock
    private QuizService quizService;

    @InjectMocks
    private ImportService importService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void importData_shouldImportQuizzes() throws Exception {
        QuizEntity quiz = new QuizEntity();
        when(jsonQuizBuilder.buildQuizz(any(URL.class))).thenReturn(List.of(quiz));
        when(quizService.save(any())).thenReturn(List.of("1"));

        importService.importData("data");

        verify(jsonQuizBuilder, times(1)).buildQuizz(any(URL.class));
        verify(quizService, times(1)).save(any());
    }

    @Test
    void importData_shouldHandleException() throws Exception {
        when(jsonQuizBuilder.buildQuizz(any(URL.class))).thenThrow(new RuntimeException("Test error"));
        when(quizService.save(any())).thenReturn(Collections.emptyList());

        importService.importData("/data");
        // No exception should be thrown
    }
}
