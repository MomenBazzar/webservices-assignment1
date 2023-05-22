package com.MomenBazzar.Hosbital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MedicationUpdateDto {
    @NotBlank(message = "Medication name is required")
    @Size(max = 255, message = "Medication name cannot exceed {max} characters")
    private String medicationName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Dosage is required")
    @Size(max = 255, message = "Dosage cannot exceed {max} characters")
    private String dosage;
}
