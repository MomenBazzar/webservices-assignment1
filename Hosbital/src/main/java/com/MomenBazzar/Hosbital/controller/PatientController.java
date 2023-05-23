package com.MomenBazzar.Hosbital.controller;

import com.MomenBazzar.Hosbital.dto.*;
import com.MomenBazzar.Hosbital.service.MedicationService;
import com.MomenBazzar.Hosbital.service.PatientService;
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
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientReadDto> getPatientById(@PathVariable Long id) {
        Optional<PatientReadDto> patientDto = patientService.getPatientById(id);
        return patientDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addPatient(@Valid @RequestBody PatientAddDto addDto, BindingResult bindingResult) {
        List<String> errors = ValidationErrorHandler.getErrorsList(bindingResult);
        if (errors != null) return ResponseEntity.badRequest().body(errors);

        Optional<PatientReadDto> patientDto = patientService.addPatient(addDto);
        return patientDto.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientUpdateDto updateDto,
                                           BindingResult bindingResult) {
        List<String> errors = ValidationErrorHandler.getErrorsList(bindingResult);
        if (errors != null) return ResponseEntity.badRequest().body(errors);

        Optional<PatientReadDto> patientDto = patientService.updatePatient(id, updateDto);
        return patientDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        boolean deleted = patientService.deletePatient(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PatientReadDto>> getAllPatients() {
        List<PatientReadDto> patientDtos = patientService.getAllPatients();
        return ResponseEntity.ok(patientDtos);
    }
}

