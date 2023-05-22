package com.MomenBazzar.Hosbital.dto;

import com.MomenBazzar.Hosbital.entity.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientReadDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String email;
}
