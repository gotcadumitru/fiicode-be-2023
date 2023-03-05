package com.dima.demo.appointments;

import com.dima.demo.exception.ApiRequestException;
import com.dima.demo.patient.Patient;
import com.dima.demo.user.User;
import com.dima.demo.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment saveNewAppointment(String cause, Long patientId, Long doctorId, LocalDateTime time, AppointmentImportance importance) {
        User userPatient = userService.getUserById(patientId);
        User userDoctor = userService.getUserById(doctorId);
        Appointment appointment = new Appointment(cause, userPatient, userDoctor, time, importance);
        return appointmentRepository.save(appointment);
    }

    public Appointment editAppointment(Long appointmentId, String cause, Long patientId, Long doctorId, LocalDateTime time, AppointmentImportance importance) {
        User userPatient = userService.getUserById(patientId);
        User userDoctor = userService.getUserById(doctorId);
        Appointment appointment = new Appointment(appointmentId,cause, userPatient, userDoctor, time, importance);
        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(()->new ApiRequestException("nifiga nu am gasit, sigur id-ul e bun?"));
    }
}
