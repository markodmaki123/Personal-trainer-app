package com.personal.trainer.demo.service.implementation;

import com.personal.trainer.demo.contract.ClientDTO;
import com.personal.trainer.demo.contract.LoginDTO;
import com.personal.trainer.demo.contract.TrainerDTO;
import com.personal.trainer.demo.infrastructure.repository.ClientRepository;
import com.personal.trainer.demo.infrastructure.repository.TrainerRepository;
import com.personal.trainer.demo.model.Client;
import com.personal.trainer.demo.model.Exercise;
import com.personal.trainer.demo.model.Trainer;
import com.personal.trainer.demo.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceImplementation implements AuthService {

    final TrainerRepository trainerRepository;

    final ClientRepository clientRepository;

    final ModelMapper modelMapper;

    @Autowired
    public AuthServiceImplementation(TrainerRepository trainerRepository,
                                     ModelMapper modelMapper,
                                     ClientRepository clientRepository) {
        this.trainerRepository = trainerRepository;
        this.modelMapper = modelMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public Trainer registerTrainer(TrainerDTO trainer) {
        Trainer register = new Trainer();
        register.setName(trainer.getName());
        register.setEmail(trainer.getEmail());
        register.setPassword(trainer.getPassword());
        register.setSpecializedFor(trainer.getSpecializedFor());
        register.setGender(trainer.getGender());
        register.setBiography(trainer.getBiography());
        register.setBirthDate(trainer.getBirthDate());
        register.setSurname(trainer.getSurname());

        Set<Exercise> exercises = new HashSet<>();
        register.setExercises(exercises);

        return trainerRepository.save(register);
    }

    @Override
    public Client registerClient(ClientDTO client) {
        Client user = new Client();
        user.setEmail(client.getEmail());
        user.setPassword(client.getPassword());
        user.setName(client.getName());
        user.setSurname(client.getSurname());
        user.setGender(client.getGender());
        user.setBirthDate(client.getBirthDate());
        user.setHeight(client.getHeight());
        user.setWeight(client.getWeight());
        user.setMedicalHistory(client.getMedicalHistory());
        user.setWidth(client.getWidth());

        return clientRepository.save(user);
    }

    @Override
    public List<Trainer> getTrainers() {
        return new ArrayList<>(trainerRepository.findAll());
    }

    @Override
    public List<Client> getClients() {
        return new ArrayList<>(clientRepository.findAll());
    }

    @Override
    public TrainerDTO getByTrainerEmail(String email){
        Optional<Trainer> trainer = trainerRepository.findByEmail(email);

        return trainer.map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Trener nije pronadjen: " + email));
    }

    @Override
    public ClientDTO getByClientEmail(String email){
        Optional<Client> client = clientRepository.findByEmail(email);

        return client.map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Klijent nije pronadjen: " + email));
    }

    @Override
    public void removeByTrainerEmail(String email) {
        Optional<Trainer> trainer = trainerRepository.findByEmail(email);
        trainer.ifPresent(trainerRepository::delete);
    }

    @Override
    public void removeByClientEmail(String email) {
        Optional<Client> client = clientRepository.findByEmail(email);
        client.ifPresent(clientRepository::delete);
    }

    @Override
    public LoginDTO login(String email, String password) {
        LoginDTO login = new LoginDTO();
        Optional<Client> client = clientRepository.findByEmail(email);
        Optional<Trainer> trainer = trainerRepository.findByEmail(email);

        String type= "";
        if (trainer.isPresent()) {
            type = "trainer";
        } else if (client.isPresent()) {
            type = "client";
        }else {
            throw new RuntimeException("Invalid credentials");
        }

        login.setType(type);
        login.setPassword(null);
        login.setEmail(email);
        return login;
    }

    TrainerDTO mapToDTO(Trainer trainer) {
        TrainerDTO dto = new TrainerDTO();
        dto.setId(trainer.getId());
        dto.setEmail(trainer.getEmail());
        dto.setPassword(trainer.getPassword());
        dto.setName(trainer.getName());
        dto.setSurname(trainer.getSurname());
        dto.setGender(trainer.getGender());
        dto.setBirthDate(trainer.getBirthDate());
        dto.setBiography(trainer.getBiography());
        dto.setSpecializedFor(trainer.getSpecializedFor());
        return dto;
    }

    ClientDTO mapToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setEmail(client.getEmail());
        dto.setPassword(client.getPassword());
        dto.setName(client.getName());
        dto.setSurname(client.getSurname());
        dto.setGender(client.getGender());
        dto.setBirthDate(client.getBirthDate());
        dto.setHeight(client.getHeight());
        dto.setWeight(client.getWeight());
        dto.setMedicalHistory(client.getMedicalHistory());
        dto.setWidth(client.getWidth());
        return dto;
    }
}
