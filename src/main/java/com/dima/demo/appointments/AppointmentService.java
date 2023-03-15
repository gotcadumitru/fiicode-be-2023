package com.dima.demo.appointments;

import com.dima.demo.exception.ApiRequestException;
import com.dima.demo.storage.Storage;
import com.dima.demo.storage.StorageService;
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
    private final StorageService storageService;


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment saveNewAppointment(String cause, Long patientId, Long doctorId, LocalDateTime timeStart, LocalDateTime timeEnd, AppointmentImportance importance, List<Long> documents) {

        List<Storage> documentsFiles = documents.stream().map(storageService::getFileById).toList();
        User userPatient = userService.getUserById(patientId);
        User userDoctor = userService.getUserById(doctorId);
        Appointment appointment = new Appointment(cause, userPatient, userDoctor, timeStart, timeEnd, importance, documentsFiles);
        return appointmentRepository.save(appointment);
    }

    public Appointment editAppointment(Long appointmentId, String cause, Long patientId, Long doctorId, LocalDateTime timeStart, LocalDateTime timeEnd, AppointmentImportance importance, List<Long> documents) {
        List<Storage> documentsFiles = documents.stream().map(storageService::getFileById).toList();
        User userPatient = userService.getUserById(patientId);
        User userDoctor = userService.getUserById(doctorId);
        Appointment appointment = new Appointment(appointmentId, cause, userPatient, userDoctor, timeStart, timeEnd, importance, documentsFiles);
        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new ApiRequestException("nifiga nu am gasit, sigur id-ul e bun?"));
    }

    public List<Appointment> getAppointmentByDoctorId(Long doctorId) {
        return appointmentRepository.getAllByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentByPatientId(Long patientId) {
        return appointmentRepository.getAllByPatientId(patientId);
    }
}
