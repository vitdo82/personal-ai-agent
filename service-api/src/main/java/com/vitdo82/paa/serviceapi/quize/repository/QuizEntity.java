package com.vitdo82.paa.serviceapi.quize.repository;

import com.vitdo82.paa.serviceapi.core.repository.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class QuizEntity extends BaseEntity {

    private String name;
    private String description;

    @OneToMany(mappedBy = "quiz")
    private List<QuizQuestionEntity> questions = new ArrayList<>();

}
