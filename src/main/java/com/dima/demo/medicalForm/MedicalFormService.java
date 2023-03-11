package com.dima.demo.medicalForm;

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
public class MedicalFormService {

    private final MedicalFormRepository medicalFormRepository;
    private final UserService userService;
    private final StorageService storageService;

    public MedicalForm saveNewMedicalForm(Long patientId, Long doctorId, String patientDescription, String patientTreatment, LocalDateTime time, List<Long> documents) {
        List<Storage> documentsFiles = documents.stream().map(storageService::getFileById).toList();
        User userPatient = userService.getUserById(patientId);
        User userDoctor = userService.getUserById(doctorId);
        MedicalForm medicalForm = new MedicalForm(userPatient, userDoctor, patientDescription, patientTreatment, time, documentsFiles);
        return medicalFormRepository.save(medicalForm);
    }

    public MedicalForm editMedicalForm(Long medicalFormId, Long patientId, Long doctorId, String patientDescription, String patientTreatment, LocalDateTime time, List<Long> documents) {
        List<Storage> documentsFiles = documents.stream().map(storageService::getFileById).toList();
        User userPatient = userService.getUserById(patientId);
        User userDoctor = userService.getUserById(doctorId);
        MedicalForm medicalForm = new MedicalForm(medicalFormId, userPatient, userDoctor, patientDescription, patientTreatment, time, documentsFiles);
        return medicalFormRepository.save(medicalForm);
    }

    public List<MedicalForm> getAllMedicalFormsByUserId(Long patientId) {
        return medicalFormRepository.getAllByPatientId(patientId);
    }
}
