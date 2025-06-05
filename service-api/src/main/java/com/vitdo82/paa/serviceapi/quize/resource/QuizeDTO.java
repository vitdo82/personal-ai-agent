package com.vitdo82.paa.serviceapi.quize.resources;

import java.util.List;

public interface QuizeDTO {

    record QuizResponse(
            String id,
            String name,
            String description,
            List<QuizQuestion> questions
    ) {
    }

    record QuizQuestion(
            String id,
            String question,
            String enabled,
            QuestionType type,
            List<QuizAnswer> answers
    ) {
    }

    enum QuestionType {
        SINGLE,
        MULTIPLE,
    }

    record QuizAnswer(String id,
                      String text,
                      boolean isCorrect
    ) { }
}
