package com.MomenBazzar.Hosbital.controller;

import com.MomenBazzar.Hosbital.dto.AppointmentAddDto;
import com.MomenBazzar.Hosbital.dto.AppointmentReadDto;
import com.MomenBazzar.Hosbital.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentReadDto> getAppointmentById(@PathVariable Long id) {
        Optional<AppointmentReadDto> appointmentDto = appointmentService.getAppointmentById(id);
        return appointmentDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addAppointment(@Valid @RequestBody AppointmentAddDto addDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = ValidationErrorHandler.getErrorsList(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Optional<AppointmentReadDto> appointmentDto = appointmentService.addAppointment(addDto);
            return appointmentDto.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        boolean deleted = appointmentService.deleteAppointment(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AppointmentReadDto>> getAllAppointments() {
        List<AppointmentReadDto> appointmentDtos = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointmentDtos);
    }
}

