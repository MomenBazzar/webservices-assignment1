package com.MomenBazzar.Hosbital.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentAddDto {
    @NotNull(message = "Appointment date is required")
    private LocalDateTime appointmentDate;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Medication ID is required")
    private Long medicationId;
}
