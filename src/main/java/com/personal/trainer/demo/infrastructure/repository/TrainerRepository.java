package com.personal.trainer.demo.infrastructure.repository;

import com.personal.trainer.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Long> {

    Optional<Trainer> findByEmail(String email);
}
