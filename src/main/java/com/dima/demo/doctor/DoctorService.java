package com.dima.demo.doctor;

import com.dima.demo.exception.ApiRequestException;
import com.dima.demo.storage.Storage;
import com.dima.demo.storage.StorageService;
import com.dima.demo.user.User;
import com.dima.demo.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final StorageService storageService;

    private final UserService userService;



    public Doctor saveNewDoctor(DoctorCreateBodyData requestData) {
        List<Storage> documents = requestData.getDocuments().stream().map(storageService::getFileById).toList();
        Doctor doctor = new Doctor(requestData.getTimeStart(),requestData.getTimeEnd(),requestData.getVisitTimeStart(),requestData.getVisitTimeEnd(),requestData.getCabinetNo(),documents, requestData.getLongitude(), requestData.getLatitude());
        return doctorRepository.save(doctor);
    }

    public Doctor editDoctor(Long doctorId,DoctorCreateBodyData requestData) {
        List<Storage> documents = requestData.getDocuments().stream().map(storageService::getFileById).toList();
        Doctor doctor = new Doctor(doctorId,requestData.getTimeStart(),requestData.getTimeEnd(),requestData.getVisitTimeStart(),requestData.getVisitTimeEnd(),requestData.getCabinetNo(),documents, requestData.getLongitude(), requestData.getLatitude());
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long appointmentId) {
        return doctorRepository.findById(appointmentId).orElseThrow(()->new ApiRequestException("nifiga nu am gasit, sigur id-ul e bun?"));
    }
}
