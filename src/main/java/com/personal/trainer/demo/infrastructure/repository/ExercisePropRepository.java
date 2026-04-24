package com.personal.trainer.demo.infrastructure.repository;

import com.personal.trainer.demo.model.ExerciseProp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercisePropRepository extends JpaRepository<ExerciseProp,Long> {
}
