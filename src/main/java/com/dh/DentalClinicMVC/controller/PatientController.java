package com.dh.DentalClinicMVC.controller;


import com.dh.DentalClinicMVC.model.Patient;
import com.dh.DentalClinicMVC.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    private final PatientService patientService;

    @Autowired
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

    @GetMapping
    public List<Patient> findAll(){
        return patientService.findAll();
    }


}
