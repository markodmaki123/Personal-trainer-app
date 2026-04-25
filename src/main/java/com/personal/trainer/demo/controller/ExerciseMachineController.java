package com.personal.trainer.demo.controller;

import com.personal.trainer.demo.contract.ExerciseMachineDTO;
import com.personal.trainer.demo.contract.ExercisePropDTO;
import com.personal.trainer.demo.model.ExerciseMachine;
import com.personal.trainer.demo.model.ExerciseProp;
import com.personal.trainer.demo.service.EquipmentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "api/exercise-machines")
public class ExerciseMachineController {

    final EquipmentService equipmentService;

    final ModelMapper modelMapper;

    @Autowired
    public ExerciseMachineController(EquipmentService equipmentService, ModelMapper modelMapper) {
        this.equipmentService = equipmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ExerciseMachine> addMachine(@RequestBody ExerciseMachineDTO machineDTO) {
        ExerciseMachine machine = equipmentService.addExerciseMachine(machineDTO);
        return ResponseEntity.ok(machine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMachine(@PathVariable Long id) {
        equipmentService.deleteExerciseMachine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExerciseMachineDTO>> getAll() {
        List<ExerciseMachine> machines = equipmentService.getMachines().stream()
                .toList();
        List<ExerciseMachineDTO> machineDTOS = machines.stream()
                .map(machine -> modelMapper.map(machine, ExerciseMachineDTO.class))
                .toList();
        return ResponseEntity.ok(machineDTOS);
    }
}
