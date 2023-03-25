package com.dima.demo.appointments;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/appointment")
@AllArgsConstructor
public class AppointmentController {
    public final AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllPatients() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping(path = "{appointmentId}")
    public Appointment getAppointmentById(@PathVariable @NonNull Long appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping(path = "user/{userId}")
    public List<Appointment> getAppointmentByDoctorId(@PathVariable @NonNull Long userId) {
        return appointmentService.getAppointmentByUserId(userId);
    }

    @PostMapping
    public Appointment createNewAppointment(@RequestBody AppointmentCreateBodyData request) {
        return appointmentService.saveNewAppointment(request.getCause(), request.getPatientId(), request.getDoctorId(), request.getTimeStart(), request.getTimeEnd(), request.getImportance(), request.getDocuments());
    }

    @PutMapping(path = "{appointmentId}")
    public Appointment editAppointment(@PathVariable @NonNull Long appointmentId, @RequestBody AppointmentCreateBodyData request) {
        return appointmentService.editAppointment(appointmentId, request.getCause(), request.getPatientId(), request.getDoctorId(), request.getTimeStart(), request.getTimeEnd(), request.getImportance(), request.getDocuments());
    }
}
