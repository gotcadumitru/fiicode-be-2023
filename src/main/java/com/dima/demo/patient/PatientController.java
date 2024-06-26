package com.dima.demo.patient;

import com.dima.demo.security.config.JwtUtils;
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

    @GetMapping(path = "user/{userId}")
    public List<Patient> getPatientsListByUserId(@PathVariable @NonNull Long userId) {
        return patientService.getPatientsListByUserId(userId);
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
