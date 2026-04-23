package com.personal.trainer.demo.infrastructure.repository;

import com.personal.trainer.demo.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
}
