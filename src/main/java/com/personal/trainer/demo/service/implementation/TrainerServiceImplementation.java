package com.personal.trainer.demo.service.implementation;


import com.personal.trainer.demo.contract.ExerciseDTO;
import com.personal.trainer.demo.contract.ExerciseMachineDTO;
import com.personal.trainer.demo.contract.ExercisePropDTO;
import com.personal.trainer.demo.infrastructure.repository.ExerciseMachineRepository;
import com.personal.trainer.demo.infrastructure.repository.ExercisePropRepository;
import com.personal.trainer.demo.infrastructure.repository.ExerciseRepository;
import com.personal.trainer.demo.infrastructure.repository.TrainerRepository;
import com.personal.trainer.demo.model.Exercise;
import com.personal.trainer.demo.model.ExerciseMachine;
import com.personal.trainer.demo.model.ExerciseProp;
import com.personal.trainer.demo.model.Trainer;
import com.personal.trainer.demo.service.TrainerService;
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
public class TrainerServiceImplementation implements TrainerService {

    final TrainerRepository trainerRepository;

    final ExerciseRepository exerciseRepository;

    final ExerciseMachineRepository exerciseMachineRepository;

    final ExercisePropRepository exercisePropRepository;

    final ModelMapper modelMapper;

    @Autowired
    public TrainerServiceImplementation(TrainerRepository trainerRepository,
                                        ExerciseRepository exerciseRepository,
                                        ModelMapper modelMapper,
                                        ExercisePropRepository exercisePropRepository,
                                        ExerciseMachineRepository exerciseMachineRepository) {
        this.trainerRepository = trainerRepository;
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
        this.exercisePropRepository = exercisePropRepository;
        this.exerciseMachineRepository = exerciseMachineRepository;
    }

    @Override
    public Exercise createExercise(ExerciseDTO exercise) {
        Exercise dto = new Exercise();
        dto.setName(exercise.getName());
        dto.setNote(exercise.getNote());
        dto.setVideoLink(exercise.getVideoLink());
        Optional<Trainer> trainer = trainerRepository.findByEmail(exercise.getTrainerEmail());
        trainer.ifPresent(dto::setTrainer);

        for (Long propId : exercise.getProps()) {
            Optional<ExerciseProp> prop = exercisePropRepository.findById(propId);
            prop.ifPresent(dto::addProp);
        }
        for (ExerciseProp prop : dto.getProps()) {
            prop.addExercise(dto);
            exercisePropRepository.save(prop);
        }

        for (Long machineId : exercise.getMachines()) {
            Optional<ExerciseMachine> machine = exerciseMachineRepository.findById(machineId);
            machine.ifPresent(dto::addMachine);
        }
        for (ExerciseMachine machine : dto.getMachines()) {
            machine.addExercise(dto);
            exerciseMachineRepository.save(machine);
        }

        return exerciseRepository.save(dto);
    }

    @Override
    public List<ExerciseDTO> getExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        List<ExerciseDTO> exerciseDTOS = new ArrayList<>();
        for(Exercise exercise : exercises) {
            exerciseDTOS.add(this.mapToDTO(exercise));
        }
        return exerciseDTOS;
    }

    @Override
    public ExerciseDTO getExercise(Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        Exercise ex = exercise.orElseThrow(() -> new RuntimeException("Vezba nije pronadjena: " + id));
        return this.mapToDTO(ex);
    }

    @Override
    public List<ExerciseDTO> getExerciseByTrainer(String email) {
        List<Exercise> exercises = exerciseRepository.findByTrainer_Email(email);

        return exercises.stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void deleteExercise(Long id){
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        Exercise ex = exercise.orElseThrow(() ->
                new RuntimeException("Vezba nije pronadjena: " + id));

        Set<ExerciseProp> props = new HashSet<>();
        exercise.ifPresent(exerciseProp -> props.addAll(exerciseProp.getProps()));
        for (ExerciseProp prop : props) {
            prop.removeExercise(ex);
        }

        Set<ExerciseMachine> machines = new HashSet<>();
        exercise.ifPresent(exerciseMachine -> machines.addAll(exerciseMachine.getMachines()));
        for (ExerciseMachine machine : machines) {
            machine.removeExercise(ex);
        }

        exercise.ifPresent(exerciseRepository::delete);
    }

    ExerciseDTO mapToDTO(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();

        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        dto.setNote(exercise.getNote());
        dto.setVideoLink(exercise.getVideoLink());

        if (exercise.getTrainer() != null) {
            dto.setTrainerEmail(exercise.getTrainer().getEmail());
        }

        Set<Long> propIds = new HashSet<>();
        if (exercise.getProps() != null) {
            for (ExerciseProp prop : exercise.getProps()) {
                propIds.add(prop.getId());
            }
        }
        dto.setProps(propIds);

        Set<Long> machineIds = new HashSet<>();
        if (exercise.getMachines() != null) {
            for (ExerciseMachine machine : exercise.getMachines()) {
                machineIds.add(machine.getId());
            }
        }
        dto.setMachines(machineIds);

        return dto;
    }

}
