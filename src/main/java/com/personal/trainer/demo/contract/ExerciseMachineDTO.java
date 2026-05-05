package com.personal.trainer.demo.contract;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciseMachineDTO {

    Long id;
    String name;
    String description;
}
