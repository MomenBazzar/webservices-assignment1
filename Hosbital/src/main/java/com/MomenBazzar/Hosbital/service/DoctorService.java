package com.MomenBazzar.Hosbital.service;

import com.MomenBazzar.Hosbital.dto.*;
import com.MomenBazzar.Hosbital.entity.Doctor;
import com.MomenBazzar.Hosbital.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<DoctorReadDto> getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .map(this::convertToDoctorReadDto);
    }

    public Optional<DoctorReadDto> addDoctor(DoctorAddDto addDto) {
        Doctor doctor = convertToDoctorEntity(addDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return Optional.ofNullable(convertToDoctorReadDto(savedDoctor));
    }

    public Optional<DoctorReadDto> updateDoctor(Long id, DoctorUpdateDto updateDto) {
        return doctorRepository.findById(id)
                .map(existingDoctor -> {
                    updateDoctorFromUpdateDto(updateDto, existingDoctor);
                    Doctor updatedDoctor = doctorRepository.save(existingDoctor);
                    return convertToDoctorReadDto(updatedDoctor);
                });
    }

    public boolean deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DoctorReadDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(this::convertToDoctorReadDto)
                .collect(Collectors.toList());
    }

    public DoctorReadDto convertToDoctorReadDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorReadDto.class);
    }

    public Doctor convertToDoctorEntity(DoctorAddDto addDto) {
        return modelMapper.map(addDto, Doctor.class);
    }

    public void updateDoctorFromUpdateDto(DoctorUpdateDto updateDto, Doctor doctor) {
        modelMapper.map(updateDto, doctor);
    }
}


