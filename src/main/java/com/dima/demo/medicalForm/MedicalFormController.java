package com.dima.demo.medicalForm;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicalform")
@AllArgsConstructor
public class MedicalFormController {
    public final MedicalFormService medicalFormService;

    @GetMapping(path = "user/{userId}")
    public List<MedicalForm> getMedicalFormsByUserId(@PathVariable @NonNull Long userId) {
        return medicalFormService.getAllMedicalFormsByUserId(userId);
    }

    @PostMapping
    public MedicalForm createNewMedicalForm(@RequestBody MedicalFormCreateBodyData request) {
        return medicalFormService.saveNewMedicalForm(request.getPatientId(), request.getDoctorId(), request.getPatientDescription(), request.getPatientTreatment(), request.getTime());
    }

    @PutMapping(path = "{medicalFormId}")
    public MedicalForm editMedicalForm(@PathVariable @NonNull Long medicalFormId, @RequestBody MedicalFormCreateBodyData request) {
        return medicalFormService.editMedicalForm(medicalFormId, request.getPatientId(), request.getDoctorId(), request.getPatientDescription(), request.getPatientTreatment(), request.getTime());
    }
}
