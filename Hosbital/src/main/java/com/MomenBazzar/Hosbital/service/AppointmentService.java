package com.MomenBazzar.Hosbital.service;

import com.MomenBazzar.Hosbital.dto.*;
import com.MomenBazzar.Hosbital.entity.Appointment;
import com.MomenBazzar.Hosbital.entity.Doctor;
import com.MomenBazzar.Hosbital.entity.Medication;
import com.MomenBazzar.Hosbital.entity.Patient;
import com.MomenBazzar.Hosbital.repository.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final MedicationService medicationService;
    private final ModelMapper modelMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorService doctorService,
                              PatientService patientService, MedicationService medicationService, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.medicationService = medicationService;
        this.modelMapper = modelMapper;
    }

    public Optional<AppointmentReadDto> getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(this::convertToAppointmentReadDto);
    }

    public Optional<AppointmentReadDto> addAppointment(AppointmentAddDto addDto) {
        Optional<DoctorReadDto> doctorDto = doctorService.getDoctorById(addDto.getDoctorId());
        Optional<PatientReadDto> patientDto = patientService.getPatientById(addDto.getPatientId());
        Optional<MedicationReadDto> medicationDto = medicationService.getMedicationById(addDto.getMedicationId());

        if (doctorDto.isEmpty()) {
            throw new IllegalArgumentException("Invalid Doctor ID");
        }

        if (patientDto.isEmpty()) {
            throw new IllegalArgumentException("Invalid Patient ID");
        }

        if (medicationDto.isEmpty()) {
            throw new IllegalArgumentException("Invalid Medication ID");
        }

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(addDto.getAppointmentDate());
        appointment.setDoctor(modelMapper.map(doctorDto.get(), Doctor.class));
        appointment.setPatient(modelMapper.map(patientDto.get(), Patient.class));
        appointment.setMedication(modelMapper.map(medicationDto.get(), Medication.class));

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return Optional.ofNullable(modelMapper.map(savedAppointment, AppointmentReadDto.class));
    }


    public boolean deleteAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<AppointmentReadDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(this::convertToAppointmentReadDto)
                .collect(Collectors.toList());
    }

    private AppointmentReadDto convertToAppointmentReadDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentReadDto.class);
    }

    private Appointment convertToAppointmentEntity(AppointmentAddDto addDto, DoctorReadDto doctorDto,
                                                   PatientReadDto patientDto, MedicationReadDto medicationDto) {
        Appointment appointment = modelMapper.map(addDto, Appointment.class);
        appointment.setDoctor(modelMapper.map(doctorDto, Doctor.class));
        appointment.setPatient(modelMapper.map(patientDto, Patient.class));
        appointment.setMedication(modelMapper.map(medicationDto, Medication.class));
        return appointment;
    }
}
