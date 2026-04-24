package com.personal.trainer.demo.contract;

import com.personal.trainer.demo.model.Exercise;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciseMachineDTO {

    Long id;
    String name;
    String description;
    //<Exercise> exercises = new HashSet<>();
}
