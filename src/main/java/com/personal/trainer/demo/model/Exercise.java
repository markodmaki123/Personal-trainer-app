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
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "note", nullable = false)
    String note;

    @Column(name = "videoLink", nullable = false)
    String videoLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    Trainer trainer;

    @ManyToMany
    @JoinTable(
            name = "exercise_exercise_machine",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_machine_id")
    )
    Set<ExerciseMachine> machines = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "exercise_exercise_prop",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_prop_id")
    )
    Set<ExerciseProp> props = new HashSet<>();

    public void addMachine(ExerciseMachine eq) {
        machines.add(eq);
        eq.getExercises().add(this);
    }

    public void removeMachine(ExerciseMachine eq) {
        machines.remove(eq);
        eq.getExercises().remove(this);
    }

    public void addProp(ExerciseProp eq) {
        props.add(eq);
        eq.getExercises().add(this);
    }

    public void removeProp(ExerciseProp eq) {
        props.remove(eq);
        eq.getExercises().remove(this);
    }

}
