package com.MomenBazzar.Hosbital.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentReadDto {
    private Long id;
    private LocalDateTime appointmentDate;
    private DoctorReadDto doctor;
    private PatientReadDto patient;
    private MedicationReadDto medication;
}
