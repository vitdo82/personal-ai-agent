package com.vitdo82.paa.serviceapi.quize.repository.models;

import java.util.List;

import com.vitdo82.paa.serviceapi.core.repository.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class CategoryEntity extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "category")
    private List<QuizEntity> quizzes;

}
