package com.MomenBazzar.Hosbital.service;

import com.MomenBazzar.Hosbital.dto.MedicationAddDto;
import com.MomenBazzar.Hosbital.dto.MedicationReadDto;
import com.MomenBazzar.Hosbital.dto.MedicationUpdateDto;
import com.MomenBazzar.Hosbital.entity.Medication;
import com.MomenBazzar.Hosbital.repository.MedicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final ModelMapper modelMapper;

    public MedicationService(MedicationRepository medicationRepository, ModelMapper modelMapper) {
        this.medicationRepository = medicationRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<MedicationReadDto> getMedicationById(Long id) {
        return medicationRepository.findById(id)
                .map(this::convertToMedicationReadDto);
    }

    public Optional<MedicationReadDto> addMedication(MedicationAddDto addDto) {
        Medication medication = convertToMedicationEntity(addDto);
        Medication savedMedication = medicationRepository.save(medication);
        return Optional.ofNullable(convertToMedicationReadDto(savedMedication));
    }

    public Optional<MedicationReadDto> updateMedication(Long id, MedicationUpdateDto updateDto) {
        return medicationRepository.findById(id)
                .map(existingMedication -> {
                    updateMedicationFromUpdateDto(updateDto, existingMedication);
                    Medication updatedMedication = medicationRepository.save(existingMedication);
                    return convertToMedicationReadDto(updatedMedication);
                });
    }

    public boolean deleteMedication(Long id) {
        if (medicationRepository.existsById(id)) {
            medicationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<MedicationReadDto> getAllMedications() {
        List<Medication> medications = medicationRepository.findAll();
        return medications.stream()
                .map(this::convertToMedicationReadDto)
                .collect(Collectors.toList());
    }

    public MedicationReadDto convertToMedicationReadDto(Medication medication) {
        return modelMapper.map(medication, MedicationReadDto.class);
    }

    public Medication convertToMedicationEntity(MedicationAddDto addDto) {
        return modelMapper.map(addDto, Medication.class);
    }

    public void updateMedicationFromUpdateDto(MedicationUpdateDto updateDto, Medication medication) {
        modelMapper.map(updateDto, medication);
    }
}

