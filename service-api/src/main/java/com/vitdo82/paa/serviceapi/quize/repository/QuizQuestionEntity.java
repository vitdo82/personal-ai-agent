package com.vitdo82.paa.serviceapi.quize.repository;

import com.vitdo82.paa.serviceapi.core.repository.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class QuizQuestionEntity extends BaseEntity {

    private String question;

    private boolean enabled;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "questions", nullable = false)
    @ManyToOne
//    @JoinColumn(name = "quiz_id")
    private QuizEntity quiz;

    @OneToMany
    private List<QuizAnswerEntity> quizAnswer = new ArrayList<>();
}
