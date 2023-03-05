package com.dima.demo.patient;

import com.dima.demo.registration.RegistrationRequestPatient;
import com.dima.demo.registration.token.ConfirmationToken;
import com.dima.demo.registration.token.ConfirmationTokenService;
import com.dima.demo.security.config.JwtUtils;
import com.dima.demo.user.User;
import com.dima.demo.user.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
@AllArgsConstructor
public class PatientController {
    public final PatientService patientService;
    public final UserService userService;
    public final JwtUtils jwtUtils;

    @GetMapping(path = "doctor/{doctorId}")
    public List<Patient> getAuthUser(@PathVariable @NonNull Long doctorId) {
        return patientService.getDoctorPatients(doctorId);
    }

    @PostMapping(path = "transfer")
    public Patient transferPatientToNewDoctor(@RequestBody PatientTransferBodyData request) {
        return patientService.transferPatientToNewDoctor(request);
    }

    @PostMapping(path = "invite")
    public Boolean inviteNewPatient(@RequestBody PatientInviteBodyData request) {
        return patientService.inviteNewPatient(request);
    }
}
