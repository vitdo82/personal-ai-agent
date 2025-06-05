package com.vitdo82.paa.serviceapi.quize.repository;

import com.vitdo82.paa.serviceapi.quize.repository.projection.QuizName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, String> {

    @Query("SELECT id as id, name as name, description as description FROM QuizEntity")
    List<QuizName> findAllNames();
}
