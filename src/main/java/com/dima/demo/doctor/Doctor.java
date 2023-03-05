package com.dima.demo.doctor;

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
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long timeStart;
    private Long timeEnd;

    private Long visitTimeStart;
    private Long visitTimeEnd;
    private String cabinetNo;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Storage> documents;

    private String longitude;
    private String latitude;

    public Doctor(Long timeStart, Long timeEnd, Long visitTimeStart, Long visitTimeEnd, String cabinetNo, List<Storage> documents, String longitude, String latitude) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.visitTimeStart = visitTimeStart;
        this.visitTimeEnd = visitTimeEnd;
        this.cabinetNo = cabinetNo;
        this.documents = documents;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
