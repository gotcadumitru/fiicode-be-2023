package com.dima.demo.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequestPatient {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNo;
    private final String cnp;
    private final String address;
    private final String password;
    private final Long doctorId;
}
