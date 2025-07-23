package com.vitdo82.paa.serviceapi.quize.repository.models;

import org.springframework.data.annotation.Transient;

import com.vitdo82.paa.serviceapi.core.repository.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class QuizAnswerEntity extends BaseEntity {

    private String text;

    @Transient
    private boolean isCorrect;
}
