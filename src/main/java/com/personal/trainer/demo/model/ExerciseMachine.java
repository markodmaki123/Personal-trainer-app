package com.personal.trainer.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "exercise_machines")
public class ExerciseMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @ManyToMany(mappedBy = "machines")
    Set<Exercise> exercises = new HashSet<>();

    public void addExercise(Exercise eq) {
        exercises.add(eq);
    }

    public void removeExercise(Exercise eq) {
        exercises.remove(eq);
    }

}
