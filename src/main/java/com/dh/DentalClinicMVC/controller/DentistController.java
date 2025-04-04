package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.entity.Dentist;
import com.dh.DentalClinicMVC.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class DentistController {

    private IDentistService iDentistService;

    @Autowired
    public DentistController(IDentistService iDentistService) {
        this.iDentistService = iDentistService;
    }

    //ENDPOINT: Consultamos odontologo por id
    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable Long id){
       Optional<Dentist> dentist = iDentistService.findById(id);

       if (dentist.isPresent()){
           return ResponseEntity.ok(dentist.get());
       } else {
           return ResponseEntity.notFound().build();
       }

    }

    //ENDPOINT: Guardamos odontologo
    @PostMapping
    public ResponseEntity<Dentist> save(@RequestBody Dentist dentist){
        return ResponseEntity.ok(iDentistService.save(dentist));
    }

    //ENDPOINT: Actualizamos odontologos
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Dentist dentist){
        ResponseEntity<String> response;
        Optional<Dentist> dentistToLookFor = iDentistService.findById(dentist.getId());
        if (dentistToLookFor.isPresent()){
            iDentistService.update(dentist);
            response = ResponseEntity.ok("Se actualizo el odontólogo con nombre: " + dentist.getName());

        } else {
            response = ResponseEntity.ok().body("No se puede actualizar un odontólogo que no existe en la base de datos  ");
        }
        return response;

    }

    //ENDPOINT: Eliminamos odontologo por id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        ResponseEntity<String> response;
        Optional<Dentist> dentist = iDentistService.findById(id);
        if (dentist.isPresent()){
            iDentistService.delete(id);
            response = ResponseEntity.ok("Se elimino el odontólogo con el id: "+ id);
        } else {
            response = ResponseEntity.ok().body("No se puede eliminar un odontólogo que no existe en la base de datos");
        }
        return response;
    }

    //ENDPOINT: Listamos todos los odontologos
    @GetMapping
     public ResponseEntity<List<Dentist>> findAll(){
        return ResponseEntity.ok(iDentistService.findAll());
     }






}
