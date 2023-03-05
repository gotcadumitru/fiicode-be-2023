package com.dima.demo.appointments;

import com.dima.demo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private LocalDateTime time;

    private AppointmentImportance importance;

    public Appointment(String cause, User patient, User doctor, LocalDateTime time, AppointmentImportance importance) {
        this.cause = cause;
        this.patient = patient;
        this.doctor = doctor;
        this.time = time;
        this.importance = importance;
    }
}
