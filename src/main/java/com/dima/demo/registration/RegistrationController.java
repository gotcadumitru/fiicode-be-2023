package com.dima.demo.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    @PostMapping(path="doctor")
    public String register(@RequestBody RegistrationRequestDoctor request){
        return registrationService.registerDoctor(request);
    }

    @PostMapping(path="patient")
    public String register(@RequestBody RegistrationRequestPatient request){
        return registrationService.registerPatient(request);
    }

    @GetMapping(path = "confirm")
    public String confirmRegistrationToken(@RequestParam("token") String token){
        return registrationService.confirmRegistrationToken(token);
    }
}
