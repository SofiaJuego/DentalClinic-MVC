package com.dh.DentalClinicMVC.controller;


import com.dh.DentalClinicMVC.entity.Patient;
import com.dh.DentalClinicMVC.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    private IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
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
