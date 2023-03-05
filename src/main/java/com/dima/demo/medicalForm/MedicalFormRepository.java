package com.dima.demo.medicalForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalFormRepository extends JpaRepository<MedicalForm, Long> {
    List<MedicalForm> getAllByPatientId(Long patientId);
}
