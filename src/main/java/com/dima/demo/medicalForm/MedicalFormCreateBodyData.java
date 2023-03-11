package com.dima.demo.medicalForm;

import com.dima.demo.user.User;
import jakarta.persistence.ManyToOne;
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
public class MedicalFormCreateBodyData {
    private final String patientDescription;
    private final String patientTreatment;
    private final Long patientId;
    private final Long doctorId;
    private final LocalDateTime time;
    private final List<Long> documents;
}
