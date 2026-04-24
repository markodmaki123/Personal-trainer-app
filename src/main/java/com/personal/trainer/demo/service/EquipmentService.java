package com.personal.trainer.demo.service;

import com.personal.trainer.demo.contract.ExerciseMachineDTO;
import com.personal.trainer.demo.contract.ExercisePropDTO;
import com.personal.trainer.demo.model.ExerciseMachine;
import com.personal.trainer.demo.model.ExerciseProp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService {

    ExerciseMachine addExerciseMachine(ExerciseMachineDTO exerciseMachine);

    void deleteExerciseMachine(Long exerciseMachineId);

    ExerciseProp addExerciseProp(ExercisePropDTO exerciseProp);

    void deleteExerciseProp(Long exercisePropId);

    List<ExerciseMachine> getMachines();

    List<ExerciseProp> getProps();
}
