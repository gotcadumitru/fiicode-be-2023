package com.dima.demo.appointments;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AppointmentCreateBodyData {
    private final String cause;
    private final Long patientId;
    private final Long doctorId;
    private final LocalDateTime time;
    private final AppointmentImportance importance;
}
