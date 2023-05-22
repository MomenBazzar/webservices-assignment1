package com.MomenBazzar.Hosbital.dto;

import com.MomenBazzar.Hosbital.entity.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorReadDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String email;
    private Gender gender;
}
