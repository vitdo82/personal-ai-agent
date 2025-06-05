package com.vitdo82.paa.serviceapi.quize.resource;

import java.util.List;

public interface QuizeDTO {

        record QuizResponse(
                        String id,
                        String name,
                        String description,
                        List<QuizQuestion> questions) {
        }

        record QuizQuestion(
                        String id,
                        String question,
                        boolean enabled,
                        QuestionType type,
                        List<QuizAnswer> answers) {
        }

        enum QuestionType {
                SINGLE,
                MULTIPLE,
        }

        record QuizAnswer(String id,
                        String text,
                        boolean isCorrect) {
        }

        record QuizResult(
                        String quizId,
                        String quizName,
                        String result) {
        }

        record QuizCreateRequest(
                        String name, Integer questionCount, String domain) {
        }
}
