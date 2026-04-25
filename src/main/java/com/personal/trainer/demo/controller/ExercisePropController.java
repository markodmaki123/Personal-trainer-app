package com.personal.trainer.demo.controller;

import com.personal.trainer.demo.contract.ExercisePropDTO;
import com.personal.trainer.demo.model.ExerciseProp;
import com.personal.trainer.demo.service.EquipmentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowedHeaders = "*"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping(value = "api/exercise-prop")
public class ExercisePropController {

    final EquipmentService equipmentService;

    final ModelMapper modelMapper;

    @Autowired
    public ExercisePropController(EquipmentService equipmentService, ModelMapper modelMapper) {
        this.equipmentService = equipmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ExerciseProp> addProp(@RequestBody ExercisePropDTO propDTO) {
        ExerciseProp prop = equipmentService.addExerciseProp(propDTO);
        return ResponseEntity.ok(prop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProp(@PathVariable Long id) {
        equipmentService.deleteExerciseProp(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExercisePropDTO>> getAll() {
        List<ExerciseProp> props = equipmentService.getProps().stream()
                .toList();
        List<ExercisePropDTO> propDTOs = props.stream()
                .map(prop -> modelMapper.map(prop, ExercisePropDTO.class))
                .toList();
        return ResponseEntity.ok(propDTOs);
    }
}
