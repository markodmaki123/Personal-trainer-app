package com.personal.trainer.demo.infrastructure.repository;

import com.personal.trainer.demo.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

    List<Exercise> findByTrainer_Email(String email);
}
