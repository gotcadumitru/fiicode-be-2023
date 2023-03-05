package com.dima.demo.patient;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientInviteBodyData {
    private final Long doctorId;
    private final String patientName;
    @Nullable
    private final String phoneNumber;
    @Nullable
    private final String email;
}
