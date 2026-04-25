package com.personal.trainer.demo.controller;

import com.personal.trainer.demo.contract.ExerciseDTO;
import com.personal.trainer.demo.model.Exercise;
import com.personal.trainer.demo.service.AuthService;
import com.personal.trainer.demo.service.EquipmentService;
import com.personal.trainer.demo.service.TrainerService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowedHeaders = "*"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping(value = "api/exercise")
public class TrainerController {

    final TrainerService trainerService;


    final ModelMapper modelMapper;

    @Autowired
    public TrainerController(TrainerService trainerService,
                             ModelMapper modelMapper) {
        this.trainerService = trainerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<ExerciseDTO> makeExercise(@RequestBody ExerciseDTO exerciseDTO) {
        Exercise exercise = trainerService.createExercise(exerciseDTO);
        return ResponseEntity.ok(exerciseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeExercise(@PathVariable Long id) {
        trainerService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
        List<ExerciseDTO> exercises = trainerService.getExercises().stream()
                .toList();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/{id}")
    public ExerciseDTO getByExerciseId(@PathVariable Long id) {
        return trainerService.getExercise(id);
    }
}
