package com.dh.DentalClinicMVC.service.impl;

import com.dh.DentalClinicMVC.dto.AppointmentDTO;
import com.dh.DentalClinicMVC.entity.Appointment;
import com.dh.DentalClinicMVC.entity.Dentist;
import com.dh.DentalClinicMVC.entity.Patient;
import com.dh.DentalClinicMVC.exception.ResourceNotFoundException;
import com.dh.DentalClinicMVC.repository.IAppointmentRepository;
import com.dh.DentalClinicMVC.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        //Mapeo las entidades como DTO a mano
        //instacia una entidad de turno
        Appointment appointmentEntity = new Appointment();

        //instaciar un paciente
        Patient patientEntity = new Patient();
        patientEntity.setId(appointmentDTO.getPatient_id());

        //instaciar un odontólogo
        Dentist dentistEntity = new Dentist();
        dentistEntity.setId(appointmentDTO.getDentist_id());

        //seteamos el paciente y el odontólogo a nuestra entidad turno
        appointmentEntity.setPatient(patientEntity);
        appointmentEntity.setDentist(dentistEntity);

        //convierto el string del turnodto que es la fecha a localdate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate date = LocalDate.parse(appointmentDTO.getDate(),formatter);

        //setear la fecha
        appointmentEntity.setDate(date);

        //persistir en BD
        appointmentRepository.save(appointmentEntity);

        //DTO que debemos devolver
        AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();

        appointmentDTOToReturn.setId(appointmentEntity.getId());
        appointmentDTOToReturn.setDate(appointmentEntity.getDate().toString());
        appointmentDTOToReturn.setDentist_id(appointmentEntity.getDentist().getId());
        appointmentDTOToReturn.setPatient_id(appointmentEntity.getPatient().getId());

        return appointmentDTOToReturn;

    }

    @Override
    public Optional<AppointmentDTO> findById(Long id) throws ResourceNotFoundException {

        Optional<Appointment> appointmentToLookFor = appointmentRepository.findById(id);

        Optional<AppointmentDTO> appointmentDTO = null;
        if (appointmentToLookFor.isPresent()) {
            Appointment appointment = appointmentToLookFor.get();

            AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
            appointmentDTOToReturn.setId(appointment.getId());
            appointmentDTOToReturn.setPatient_id(appointment.getPatient().getId());
            appointmentDTOToReturn.setDentist_id(appointment.getDentist().getId());
            appointmentDTOToReturn.setDate(appointment.getDate().toString());

            appointmentDTO = Optional.of(appointmentDTOToReturn);
            return appointmentDTO;

        } else {
            throw new ResourceNotFoundException("No se encontro el turno con id: " + id);
        }


    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) throws Exception {

        //chequeo que el turno a actualizar exista
        if (appointmentRepository.findById(appointmentDTO.getId()).isPresent()){

            //busco la entidad en la BD
            Optional<Appointment> appointmentEntity = appointmentRepository.findById(appointmentDTO.getId());

            //instaciar un paciente
            Patient patientEntity = new Patient();
            patientEntity.setId(appointmentDTO.getPatient_id());

            //instaciar un odontólogo
            Dentist dentistEntity = new Dentist();
            dentistEntity.setId(appointmentDTO.getDentist_id());

            //seteamos el paciente y el odontólogo a nuestra entidad turno
            appointmentEntity.get().setPatient(patientEntity);
            appointmentEntity.get().setDentist(dentistEntity);

            //convierto el string del turnodto que es la fecha a localdate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
            LocalDate date = LocalDate.parse(appointmentDTO.getDate(),formatter);

            //setear la fecha
            appointmentEntity.get().setDate(date);

            //persistir en BD
            appointmentRepository.save(appointmentEntity.get());

            //Respuesta dto que vamos a devolver
            AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
            appointmentDTOToReturn.setId(appointmentEntity.get().getId());
            appointmentDTOToReturn.setPatient_id(appointmentEntity.get().getPatient().getId());
            appointmentDTOToReturn.setDentist_id(appointmentEntity.get().getDentist().getId());
            appointmentDTOToReturn.setDate(appointmentEntity.get().getDate().toString());

            return appointmentDTOToReturn;

        } else {
            throw new Exception("No se pudo actualizar el turno");
        }

    }

    @Override
    public Optional<AppointmentDTO> delete(Long id) throws ResourceNotFoundException {
        //Buscamos la entidad por id en BD y la guardo en un Optional
        Optional<Appointment> appointmentToLookFor = appointmentRepository.findById(id);

        Optional<AppointmentDTO> appointmentDTO;

        if (appointmentToLookFor.isPresent()) {
            //recupero el turno que se encontro y lo guardo en una var
            Appointment appointment = appointmentToLookFor.get();

            //devuelvo dto
            AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
            appointmentDTOToReturn.setId(appointment.getId());
            appointmentDTOToReturn.setDentist_id(appointment.getDentist().getId());
            appointmentDTOToReturn.setPatient_id(appointment.getPatient().getId());
            appointmentDTOToReturn.setDate(appointment.getDate().toString());

            appointmentDTO = Optional.of(appointmentDTOToReturn);

            return appointmentDTO;

        } else {
            //Exception
            throw new ResourceNotFoundException("No se encontro el turno con id: " + id);
        }


    }

    @Override
    public List<AppointmentDTO> findAll() {
        //Traemos la entidades de la BD para guardarla en una lista
        List<Appointment> appointments = appointmentRepository.findAll();

        //creo una lista vacia de turnos dto
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();

        //recorremos turnos para guardarlas en la lista de turnos dto
        for (Appointment appointment:appointments){
            appointmentDTOS.add(new AppointmentDTO(appointment.getId(),
                    appointment.getPatient().getId(),appointment.getDentist().getId(),
                    appointment.getDate().toString()));
        }
        return appointmentDTOS;
    }
}
