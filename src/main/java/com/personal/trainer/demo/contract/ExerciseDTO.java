package com.personal.trainer.demo.contract;

import com.personal.trainer.demo.model.Trainer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciseDTO {

    Long id;
    String name;
    String note;
    String videoLink;
    Long trainerId;

    Set<Long> props;
    Set<Long> machines;
}
