package com.dima.demo.patient;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientTransferBodyData {
    private final Long newDoctorId;
    private final Long oldDoctorId;
    private final Long patientId;
    private final boolean removeFromOldDoctor;
}
