package com.dima.demo.appointments;

import com.dima.demo.patient.Patient;
import com.dima.demo.patient.PatientTransferBodyData;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointment")
@AllArgsConstructor
public class AppointmentController {
    public final AppointmentService appointmentService;
    @GetMapping
    public List<Appointment> getAllPatients(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping(path = "{appointmentId}")
    public Appointment getAppointmentById(@PathVariable @NonNull Long appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }

    @PostMapping
    public Appointment createNewAppointment(@RequestBody AppointmentCreateBodyData request) {
        return appointmentService.saveNewAppointment(request.getCause(),request.getPatientId(),request.getDoctorId(),request.getTime(),request.getImportance());
    }
    @PutMapping(path = "{appointmentId}")
    public Appointment editAppointment(@PathVariable @NonNull Long appointmentId,@RequestBody AppointmentCreateBodyData request) {
        return appointmentService.editAppointment(appointmentId,request.getCause(),request.getPatientId(),request.getDoctorId(),request.getTime(),request.getImportance());
    }
}
