package com.MomenBazzar.Hosbital.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentUpdateDto {
    @NotNull(message = "Appointment ID is required")
    private Long id;

    private LocalDateTime appointmentDate;

    private Long doctorId;

    private Long patientId;

    private Long medicationId;
}
