package com.dima.demo.doctor;

import com.dima.demo.storage.Storage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
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
public class DoctorCreateBodyData {
    private final Long timeStart;
    private final Long timeEnd;
    private final Long visitTimeStart;
    private final Long visitTimeEnd;
    private final String cabinetNo;
    private final double longitude;
    private final double latitude;
    private final String workAddress;
    private final List<Long> documents;
}
