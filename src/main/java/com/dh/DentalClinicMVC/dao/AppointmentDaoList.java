package com.dh.DentalClinicMVC.dao;

import com.dh.DentalClinicMVC.model.Appointment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentDaoList implements IDao<Appointment>{

    private final List<Appointment> appointments;

    public AppointmentDaoList(){
        appointments = new ArrayList<>();
    }

    @Override
    public Appointment save(Appointment appointment) {
        //Guardamos en la lista el turno que recibimos por parametro
        appointments.add(appointment);
        return appointment;
    }

    @Override
    public Appointment findById(Integer id) {
        Appointment appointmentToLookFor = null;
        //recorremos la lista
        for (Appointment a: appointments) {
            //vemos que el id del turno coincida con el id que buscamos
            if (a.getId() == id){
                appointmentToLookFor = a;
                return appointmentToLookFor;
            }

        }
        return appointmentToLookFor;
    }

    @Override
    public void update(Appointment appointment) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Appointment> findAll() {
        return appointments;
    }

    @Override
    public Appointment findByString(String value) {
        return null;
    }
}
