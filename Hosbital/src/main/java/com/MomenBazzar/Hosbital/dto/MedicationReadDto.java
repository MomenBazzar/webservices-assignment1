package com.MomenBazzar.Hosbital.dto;

import lombok.Data;

@Data
public class MedicationReadDto {
    private Long id;
    private String medicationName;
    private String description;
    private String dosage;
}
