package com.dima.demo.doctor;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/doctor")
@AllArgsConstructor
public class DoctorController {
    public final DoctorService doctorService;


    @GetMapping(path = "{doctorId}")
    public Doctor getDoctorById(@PathVariable @NonNull Long doctorId){
        return doctorService.getDoctorById(doctorId);
    }

    @PostMapping
    public Doctor createNewDoctor(@RequestBody DoctorCreateBodyData request) {
        return doctorService.saveNewDoctor(request);
    }
    @PutMapping(path = "{doctorId}")
    public Doctor editDoctor(@PathVariable @NonNull Long doctorId, @RequestBody DoctorCreateBodyData request) {
        return doctorService.editDoctor(doctorId,request);
    }
}
