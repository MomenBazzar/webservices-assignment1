package com.MomenBazzar.Hosbital.service;

import com.MomenBazzar.Hosbital.dto.*;
import com.MomenBazzar.Hosbital.entity.Patient;
import com.MomenBazzar.Hosbital.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<PatientReadDto> getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(this::convertToPatientReadDto);
    }

    public Optional<PatientReadDto> addPatient(PatientAddDto addDto) {
        Patient patient = convertToPatientEntity(addDto);
        Patient savedPatient = patientRepository.save(patient);
        return Optional.ofNullable(convertToPatientReadDto(savedPatient));
    }

    public Optional<PatientReadDto> updatePatient(Long id, PatientUpdateDto updateDto) {
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    updatePatientFromUpdateDto(updateDto, existingPatient);
                    Patient updatedPatient = patientRepository.save(existingPatient);
                    return convertToPatientReadDto(updatedPatient);
                });
    }

    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<PatientReadDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(this::convertToPatientReadDto)
                .collect(Collectors.toList());
    }

    public PatientReadDto convertToPatientReadDto(Patient patient) {
        return modelMapper.map(patient, PatientReadDto.class);
    }

    public Patient convertToPatientEntity(PatientAddDto addDto) {
        return modelMapper.map(addDto, Patient.class);
    }

    public void updatePatientFromUpdateDto(PatientUpdateDto updateDto, Patient patient) {
        modelMapper.map(updateDto, patient);
    }
}


