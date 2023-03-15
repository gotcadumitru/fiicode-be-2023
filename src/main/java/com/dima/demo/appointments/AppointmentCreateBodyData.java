package com.dima.demo.appointments;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AppointmentCreateBodyData {
    private final String cause;
    private final Long patientId;
    private final Long doctorId;
    private final LocalDateTime timeStart;
    private final LocalDateTime timeEnd;
    private final AppointmentImportance importance;
    private final List<Long> documents;
}
