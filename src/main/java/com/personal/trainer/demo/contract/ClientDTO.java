package com.personal.trainer.demo.contract;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDTO {

    Long id;
    String email;
    String password;
    String name;
    String surname;
    LocalDate birthDate;
    String gender;
    int height;
    int weight;
    String medicalHistory;
    int width;
}
