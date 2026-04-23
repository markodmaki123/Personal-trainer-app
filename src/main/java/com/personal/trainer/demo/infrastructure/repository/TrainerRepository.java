package com.personal.trainer.demo.infrastructure.repository;

import com.personal.trainer.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {

    Optional<Trainer> findByEmail(String email);
}
