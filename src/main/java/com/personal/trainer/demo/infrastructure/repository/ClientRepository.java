package com.personal.trainer.demo.infrastructure.repository;

import com.personal.trainer.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByEmail(String email);
}
