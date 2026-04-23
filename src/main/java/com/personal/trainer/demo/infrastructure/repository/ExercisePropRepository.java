package com.personal.trainer.demo.infrastructure.repository;

import com.personal.trainer.demo.model.ExerciseProp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisePropRepository extends JpaRepository<ExerciseProp,Long> {
}
