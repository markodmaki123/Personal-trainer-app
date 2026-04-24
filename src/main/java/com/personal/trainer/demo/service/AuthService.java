package com.personal.trainer.demo.service;

import com.personal.trainer.demo.contract.ClientDTO;
import com.personal.trainer.demo.contract.TrainerDTO;
import com.personal.trainer.demo.model.Client;
import com.personal.trainer.demo.model.Trainer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {

    Trainer RegisterTrainer(TrainerDTO trainer);

    Client RegisterClient(ClientDTO client);

    List<Trainer> getTrainers();

    List<Client> getClients();

    TrainerDTO getByTrainerEmail(String email);

    ClientDTO getByClientEmail(String email);

    void removeByTrainerEmail(String email);

    void removeByClientEmail(String email);
}
