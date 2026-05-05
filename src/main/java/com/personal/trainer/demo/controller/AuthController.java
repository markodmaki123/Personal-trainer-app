package com.personal.trainer.demo.controller;

import com.personal.trainer.demo.contract.*;
import com.personal.trainer.demo.model.Client;
import com.personal.trainer.demo.model.ExerciseMachine;
import com.personal.trainer.demo.model.ExerciseProp;
import com.personal.trainer.demo.model.Trainer;
import com.personal.trainer.demo.service.AuthService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowedHeaders = "*"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping(value = "api/auth")
public class AuthController {

    final AuthService authService;

    final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/trainer")
    public ResponseEntity<TrainerDTO> registerTrainer(@RequestBody TrainerDTO trainerDTO) {
        Trainer trainer = authService.registerTrainer(trainerDTO);

        TrainerDTO trainerResponse = modelMapper.map(trainer, TrainerDTO.class);
        return ResponseEntity.ok(trainerResponse);
    }

    @PostMapping("/client")
    public ResponseEntity<ClientDTO> registerClient(@RequestBody ClientDTO clientDTO) {
        Client client = authService.registerClient(clientDTO);

        ClientDTO clientResponse = modelMapper.map(client, ClientDTO.class);
        return ResponseEntity.ok(clientResponse);
    }

    @DeleteMapping("/trainer/{id}")
    public ResponseEntity<Void> removeTrainer(@PathVariable String id) {
        authService.removeByTrainerEmail(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> removeClient(@PathVariable String id) {
        authService.removeByClientEmail(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/trainer/all")
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        List<Trainer> trainers = authService.getTrainers().stream()
                .toList();
        List<TrainerDTO> trainerDTOs = trainers.stream()
                .map(trainer -> modelMapper.map(trainer, TrainerDTO.class))
                .toList();
        return ResponseEntity.ok(trainerDTOs);
    }

    @GetMapping("/client/all")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = authService.getClients().stream()
                .toList();
        List<ClientDTO> clientDTOS = clients.stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .toList();
        return ResponseEntity.ok(clientDTOS);
    }

    @GetMapping("/trainer")
    public TrainerDTO getByTrainerEmail(@RequestParam String email) {
        return authService.getByTrainerEmail(email);
    }

    @GetMapping("/client")
    public ClientDTO getByClientEmail(@RequestParam String email) {
        return authService.getByClientEmail(email);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginDTO log = authService.login(loginDTO.getEmail(),loginDTO.getPassword());
        return ResponseEntity.ok(log);
    }

}
