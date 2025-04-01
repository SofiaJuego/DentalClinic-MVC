package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Dentist;
import com.dh.DentalClinicMVC.service.DentistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class DentistController {

    private final DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    //ENDPOINT: Consultamos odontologo por id
    @GetMapping("/{id}")
    public Dentist findById(@PathVariable Integer id){
        return dentistService.findById(id);

    }

    //ENDPOINT: Guardamos odontologo
    @PostMapping
    public Dentist save(@RequestBody Dentist dentist){
        return dentistService.save(dentist);
    }

    //ENDPOINT: Actualizamos odontologos
    @PutMapping
    public void update(@RequestBody Dentist dentist){
        dentistService.updateDentist(dentist);

    }

    //ENDPOINT: Eliminamos odontologo por id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        dentistService.deleteDentist(id);
    }

    //ENDPOINT: Listamos todos los odontologos
    @GetMapping
     public List<Dentist> findAll(){
        return dentistService.findAll();
     }






}
