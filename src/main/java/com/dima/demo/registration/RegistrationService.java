package com.dima.demo.registration;

import com.dima.demo.authentication.Provider;
import com.dima.demo.doctor.Doctor;
import com.dima.demo.doctor.DoctorService;
import com.dima.demo.exception.ApiRequestException;
import com.dima.demo.patient.PatientService;
import com.dima.demo.registration.token.ConfirmationToken;
import com.dima.demo.registration.token.ConfirmationTokenService;
import com.dima.demo.security.config.JwtUtils;
import com.dima.demo.user.User;
import com.dima.demo.user.UserRole;
import com.dima.demo.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PatientService patientService;
    private final ConfirmationTokenService confirmationTokenService;
    private final DoctorService doctorService;
    private final EmailValidator emailValidator;

    public String registerDoctor(RegistrationRequestDoctor request) {
        boolean isEmailValid = emailValidator.test(request.getEmail());
        if (!isEmailValid) {
            throw new ApiRequestException("Invalid email");
        }

        Doctor doctorInfo = doctorService.getDoctorById(request.getDoctorInfoId());

        User savedUser = userService.signUpUser(new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                UserRole.DOCTOR,
                Provider.LOCAL,
                false,
                true,
                request.getPhoneNo(),
                request.getAddress(),
                request.getCnp(),
                doctorInfo
        ));

        return jwtUtils.generateToken(savedUser);
    }

    public String registerPatient(RegistrationRequestPatient request) {
        boolean isEmailValid = emailValidator.test(request.getEmail());
        if (!isEmailValid) {
            throw new ApiRequestException("Invalid email");
        }

        User savedUser = userService.signUpUser(new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                UserRole.PATIENT,
                Provider.LOCAL,
                false,
                true,
                request.getPhoneNo(),
                request.getAddress(),
                request.getCnp(),
                null
        ));

        patientService.saveNewPatient(savedUser.getId(), request.getDoctorId());

        return jwtUtils.generateToken(savedUser);
    }

    @Transactional
    public String confirmRegistrationToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationToken(token).orElseThrow(() -> new ApiRequestException("Token not found"));
        if (confirmationToken.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new ApiRequestException("Token is expired");
        if (confirmationToken.getConfirmedAt() != null) throw new ApiRequestException("Email already confirmed");
        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
