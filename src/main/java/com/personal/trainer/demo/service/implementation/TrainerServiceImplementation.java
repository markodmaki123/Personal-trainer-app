package com.personal.trainer.demo.service.implementation;


import com.personal.trainer.demo.contract.ExerciseDTO;
import com.personal.trainer.demo.contract.ExerciseMachineDTO;
import com.personal.trainer.demo.contract.ExercisePropDTO;
import com.personal.trainer.demo.infrastructure.repository.ExerciseRepository;
import com.personal.trainer.demo.infrastructure.repository.TrainerRepository;
import com.personal.trainer.demo.model.Exercise;
import com.personal.trainer.demo.model.ExerciseMachine;
import com.personal.trainer.demo.model.ExerciseProp;
import com.personal.trainer.demo.service.TrainerService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainerServiceImplementation implements TrainerService {

    final TrainerRepository trainerRepository;

    final ExerciseRepository exerciseRepository;

    final ModelMapper modelMapper;

    @Autowired
    public TrainerServiceImplementation(TrainerRepository trainerRepository,
                                        ExerciseRepository exerciseRepository,
                                        ModelMapper modelMapper) {
        this.trainerRepository = trainerRepository;
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Exercise createExercise(ExerciseDTO exercise) {
        Exercise dto = new Exercise();
        dto.setName(exercise.getName());
        dto.setNote(exercise.getNote());
        dto.setTrainer(exercise.getTrainer());

        Set<ExerciseProp> props = new HashSet<>();
        for (ExercisePropDTO propDto : exercise.getProps()) {
            ExerciseProp prop = modelMapper.map(propDto, ExerciseProp.class);
            props.add(prop);
        }
        for (ExerciseProp prop : props) {
            prop.addExercise(dto);
        }
        dto.setProps(props);


        Set<ExerciseMachine> machines = new HashSet<>();
        for (ExerciseMachineDTO machineDto : exercise.getMachines()) {
            ExerciseMachine machine = modelMapper.map(machineDto, ExerciseMachine.class);
            machines.add(machine);
        }
        for (ExerciseMachine machine : machines) {
            machine.addExercise(dto);
        }
        dto.setMachines(machines);

        return exerciseRepository.save(dto);
    }

    @Override
    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise getExercise(Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);

        return exercise.orElseThrow(() -> new RuntimeException("Vezba nije pronadjena: " + id));
    }

    @Override
    public void deleteExercise(Long id){
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.ifPresent(exerciseRepository::delete);
    }

}
