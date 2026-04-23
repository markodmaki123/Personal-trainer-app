package com.personal.trainer.demo.infrastructure.repository;

import com.personal.trainer.demo.model.ExerciseMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseMachineRepository extends JpaRepository<ExerciseMachine,Long> {
}
