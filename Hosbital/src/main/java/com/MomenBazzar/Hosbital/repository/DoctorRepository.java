package com.MomenBazzar.Hosbital.repository;

import com.MomenBazzar.Hosbital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}

