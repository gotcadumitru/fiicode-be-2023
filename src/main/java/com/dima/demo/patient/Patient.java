package com.dima.demo.patient;

import com.dima.demo.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Nullable
    private User patient;

    @OneToOne
    private User doctor;


    public Patient(User patient, User doctor) {
        this.patient = patient;
        this.doctor = doctor;
    }
}
