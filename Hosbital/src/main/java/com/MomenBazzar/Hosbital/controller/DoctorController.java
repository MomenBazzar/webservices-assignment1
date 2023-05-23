package com.MomenBazzar.Hosbital.controller;

import com.MomenBazzar.Hosbital.dto.*;
import com.MomenBazzar.Hosbital.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorReadDto> getDoctorById(@PathVariable Long id) {
        Optional<DoctorReadDto> doctorDto = doctorService.getDoctorById(id);
        return doctorDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addDoctor(@Valid @RequestBody DoctorAddDto addDto, BindingResult bindingResult) {
        List<String> errors = ValidationErrorHandler.getErrorsList(bindingResult);
        if (errors != null) return ResponseEntity.badRequest().body(errors);

        Optional<DoctorReadDto> doctorDto = doctorService.addDoctor(addDto);
        return doctorDto.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElse(ResponseEntity.badRequest().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorUpdateDto updateDto,
                                          BindingResult bindingResult) {
        List<String> errors = ValidationErrorHandler.getErrorsList(bindingResult);
        if (errors != null) return ResponseEntity.badRequest().body(errors);

        Optional<DoctorReadDto> doctorDto = doctorService.updateDoctor(id, updateDto);
        return doctorDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        boolean deleted = doctorService.deleteDoctor(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<DoctorReadDto>> getAllDoctors() {
        List<DoctorReadDto> doctorDtos = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctorDtos);
    }
}


