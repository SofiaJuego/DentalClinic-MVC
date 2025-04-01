package com.dh.DentalClinicMVC.controller;


import com.dh.DentalClinicMVC.model.Patient;
import com.dh.DentalClinicMVC.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //ENDPOINT: Agregar paciente
    @PostMapping
    public Patient save(@RequestBody Patient patient){
        return patientService.save(patient);
    }

    //ENDPOINT: Actualizar paciente
    @PutMapping
    public void update(@RequestBody Patient patient){
        patientService.update(patient);

    }


}
