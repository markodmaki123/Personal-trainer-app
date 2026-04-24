package com.personal.trainer.demo.contract;

import com.personal.trainer.demo.model.Exercise;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExercisePropDTO {

    Long id;
    String name;
    String description;
    //Set<Exercise> exercises = new HashSet<>();
}
