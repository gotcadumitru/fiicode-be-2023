package com.dima.demo.appointments;

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
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cause;

    @ManyToOne
    private User patient;

    @ManyToOne
    private User doctor;

    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    private AppointmentImportance importance;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Storage> documents;

    public Appointment(String cause, User patient, User doctor, LocalDateTime timeStart, LocalDateTime timeEnd, AppointmentImportance importance, List<Storage> documents) {
        this.cause = cause;
        this.patient = patient;
        this.doctor = doctor;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.importance = importance;
        this.documents = documents;
    }
}
