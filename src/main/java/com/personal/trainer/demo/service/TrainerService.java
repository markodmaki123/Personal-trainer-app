package com.personal.trainer.demo.service;

import com.personal.trainer.demo.contract.ExerciseDTO;
import com.personal.trainer.demo.model.Exercise;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface TrainerService {

    Exercise createExercise(ExerciseDTO exercise);

    List<ExerciseDTO> getExercises();

    ExerciseDTO getExercise(Long id);

    List<ExerciseDTO> getExerciseByTrainer(String email);

    void deleteExercise(Long id);

}
