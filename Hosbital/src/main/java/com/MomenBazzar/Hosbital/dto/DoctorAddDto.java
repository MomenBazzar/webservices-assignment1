package com.MomenBazzar.Hosbital.dto;

import com.MomenBazzar.Hosbital.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorAddDto {

    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "First name cannot exceed {max} characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name cannot exceed {max} characters")
    private String lastName;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed {max} characters")
    private String address;

    @NotBlank(message = "Email is required")
    @Size(max = 255, message = "Email cannot exceed {max} characters")
    private String email;

    @NotBlank(message = "Specialty is required")
    private String specialty;
}

