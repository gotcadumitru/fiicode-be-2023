package com.dima.demo.patient;

import com.dima.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByDoctor(User doctor);
    Patient findByPatientId(Long patientId);
    Patient findByPatientIdAndDoctorId(Long patientId,Long doctorId);
}
