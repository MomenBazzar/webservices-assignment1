package com.MomenBazzar.Hosbital.controller;

import com.MomenBazzar.Hosbital.dto.MedicationAddDto;
import com.MomenBazzar.Hosbital.dto.MedicationReadDto;
import com.MomenBazzar.Hosbital.dto.MedicationUpdateDto;
import com.MomenBazzar.Hosbital.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationReadDto> getMedicationById(@PathVariable Long id) {
        Optional<MedicationReadDto> medicationDto = medicationService.getMedicationById(id);
        return medicationDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addMedication(@Valid @RequestBody MedicationAddDto addDto, BindingResult bindingResult) {
        List<String> errors = ValidationErrorHandler.getErrorsList(bindingResult);
        if (errors != null) return ResponseEntity.badRequest().body(errors);

        Optional<MedicationReadDto> medicationDto = medicationService.addMedication(addDto);
        return medicationDto.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedication(@PathVariable Long id, @Valid @RequestBody MedicationUpdateDto updateDto,
                                              BindingResult bindingResult) {
        List<String> errors = ValidationErrorHandler.getErrorsList(bindingResult);
        if (errors != null) return ResponseEntity.badRequest().body(errors);

        Optional<MedicationReadDto> medicationDto = medicationService.updateMedication(id, updateDto);
        return medicationDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        boolean deleted = medicationService.deleteMedication(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<MedicationReadDto>> getAllMedications() {
        List<MedicationReadDto> medicationDtos = medicationService.getAllMedications();
        return ResponseEntity.ok(medicationDtos);
    }
}
