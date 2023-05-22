package com.MomenBazzar.Hosbital.repository;

import com.MomenBazzar.Hosbital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}

