package com.personal.trainer.demo.contract;

import com.personal.trainer.demo.model.Exercise;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainerDTO {

    Long id;
    String email;
    String password;
    String name;
    String surname;
    LocalDate birthDate;
    String biography;
    String specializedFor;
    String gender;
    Set<Exercise> exercises = new HashSet<>();
}
