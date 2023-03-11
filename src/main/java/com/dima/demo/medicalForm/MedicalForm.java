package com.dima.demo.medicalForm;

import com.dima.demo.storage.Storage;
import com.dima.demo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User patient;
    @ManyToOne
    private User doctor;
    private String patientDescription;
    private String patientTreatment;
    private LocalDateTime time;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Storage> documents;

    public MedicalForm(User patient, User doctor, String patientDescription, String patientTreatment, LocalDateTime time,List<Storage> documents) {
        this.patient = patient;
        this.doctor = doctor;
        this.patientDescription = patientDescription;
        this.patientTreatment = patientTreatment;
        this.time = time;
        this.documents = documents;
    }
}
