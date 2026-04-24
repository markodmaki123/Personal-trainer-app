package com.personal.trainer.demo.infrastructure.repository;

import com.personal.trainer.demo.model.ExerciseMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseMachineRepository extends JpaRepository<ExerciseMachine,Long> {
}
