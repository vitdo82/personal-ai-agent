package com.vitdo82.paa.serviceapi.quize.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitdo82.paa.serviceapi.quize.repository.QuizAnswerEntity;
import com.vitdo82.paa.serviceapi.quize.repository.QuizEntity;
import com.vitdo82.paa.serviceapi.quize.repository.QuizQuestionEntity;
import com.vitdo82.paa.serviceapi.quize.repository.QuizRepository;
import com.vitdo82.paa.serviceapi.quize.repository.projection.QuizName;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public Optional<QuizEntity> getQuiz(String id) {
        return quizRepository.findById(id);
    }

    public List<QuizEntity> getQuizzes() {
        return quizRepository.findAll();
    }

    public List<QuizName> getQuizNames() {
        return quizRepository.findAllNames();
    }

    public String buildQuize(String name, Integer questionCount, String domain) {
        QuizEntity quize = new QuizEntity();
        quize.setName(name);
        quize.setQuestions(this.generateQuestions(domain, questionCount));
        quizRepository.save(quize);
        
        return quize.getId();
    }

    public List<String> save(List<QuizEntity> quizz) {
        log.info("Batch save quize, count: {}", quizz.size());
        List<QuizEntity> result = quizRepository.saveAll(quizz);

        return result.stream().map(QuizEntity::getId).toList();
    }

    private List<QuizQuestionEntity> generateQuestions(String domain, Integer count) {
        QuizQuestionEntity question = new QuizQuestionEntity();
        question.setEnabled(true);
        question.setQuestion("Test question");
        
        QuizAnswerEntity answerYes = new QuizAnswerEntity();
        answerYes.setCorrect(true);
        answerYes.setText("Answer YES");

        QuizAnswerEntity answerNo = new QuizAnswerEntity();
        answerYes.setCorrect(false);
        answerYes.setText("Answer NO");

        question.setQuizAnswer(List.of(answerYes, answerNo));
        return List.of(question);
    }
}
