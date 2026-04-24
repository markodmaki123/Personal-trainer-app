package com.personal.trainer.demo.service.implementation;

import com.personal.trainer.demo.contract.ClientDTO;
import com.personal.trainer.demo.contract.ExerciseMachineDTO;
import com.personal.trainer.demo.contract.ExercisePropDTO;
import com.personal.trainer.demo.infrastructure.repository.ExerciseMachineRepository;
import com.personal.trainer.demo.infrastructure.repository.ExercisePropRepository;
import com.personal.trainer.demo.model.*;
import com.personal.trainer.demo.service.EquipmentService;
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
public class EquipmentServiceImplementation implements EquipmentService {

    final ExerciseMachineRepository exerciseMachineRepository;

    final ExercisePropRepository exercisePropRepository;

    final ModelMapper modelMapper;

    @Autowired
    public EquipmentServiceImplementation(ExercisePropRepository exercisePropRepository,
                                          ExerciseMachineRepository exerciseMachineRepository,
                                          ModelMapper modelMapper) {
        this.exercisePropRepository = exercisePropRepository;
        this.exerciseMachineRepository = exerciseMachineRepository;
        this.modelMapper = modelMapper;
    }

    public void deleteExerciseMachine(Long exerciseMachineId) {
        Optional<ExerciseMachine> exerciseMachine = exerciseMachineRepository.findById(exerciseMachineId);
        exerciseMachine.ifPresent(exerciseMachineRepository::delete);
    }

    public void deleteExerciseProp(Long exercisePropId) {
        Optional<ExerciseProp> exerciseProp = exercisePropRepository.findById(exercisePropId);
        exerciseProp.ifPresent(exercisePropRepository::delete);
    }

    @Override
    public ExerciseMachine addExerciseMachine(ExerciseMachineDTO machine) {
        ExerciseMachine machineDto = new ExerciseMachine();
        machineDto.setName(machine.getName());
        machineDto.setDescription(machine.getDescription());

        Set<Exercise> exercises = new HashSet<>();
        machineDto.setExercises(exercises);

        return exerciseMachineRepository.save(machineDto);
    }

    @Override
    public ExerciseProp addExerciseProp(ExercisePropDTO prop) {
        ExerciseProp propDto = new ExerciseProp();
        propDto.setName(prop.getName());
        propDto.setDescription(prop.getDescription());

        Set<Exercise> exercises = new HashSet<>();
        propDto.setExercises(exercises);

        return exercisePropRepository.save(propDto);
    }

    @Override
    public List<ExerciseMachine> getMachines() {
        return new ArrayList<>(exerciseMachineRepository.findAll());
    }

    @Override
    public List<ExerciseProp> getProps() {
        return new ArrayList<>(exercisePropRepository.findAll());
    }

}
