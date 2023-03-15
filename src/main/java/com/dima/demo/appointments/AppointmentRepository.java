package com.dima.demo.appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> getAllByPatientId(Long patientId);
    List<Appointment> getAllByDoctorId(Long patientId);
}
