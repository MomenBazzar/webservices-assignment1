package com.MomenBazzar.Hosbital.repository;

import com.MomenBazzar.Hosbital.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

}
