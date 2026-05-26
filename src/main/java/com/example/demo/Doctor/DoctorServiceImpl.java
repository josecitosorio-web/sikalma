package com.example.demo.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Servicio.ServicioEntity;
import com.example.demo.Servicio.ServicioRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public void agregar(Doctor doctor) {
        DoctorEntity entity = DoctorAdapter.toEntity(doctor);

        if (doctor.getServicio() != null) {
            ServicioEntity servicioGestionado = servicioRepository.findById(doctor.getServicio().getId()).orElse(null);
            entity.setServicio(servicioGestionado);
        }

        doctorRepository.save(entity);
    }

    @Override
    public List<Doctor> obtenerTodos() {
        return DoctorAdapter.toModelList(doctorRepository.findAll());
    }

    @Override
    public Doctor buscarPorId(Long id) {
        return DoctorAdapter.toModel(doctorRepository.findById(id).orElse(null));
    }

    @Override
    public Doctor buscarDoctor(String dni) {
        return DoctorAdapter.toModel(doctorRepository.findByDni(dni).orElse(null));
    }

    @Override
    public void actualizar(Doctor doctor) {
        DoctorEntity entity = DoctorAdapter.toEntity(doctor);

        if (doctor.getServicio() != null) {
            ServicioEntity servicioGestionado = servicioRepository.findById(doctor.getServicio().getId()).orElse(null);
            entity.setServicio(servicioGestionado);
        }

        doctorRepository.save(entity);
    }

    @Override
    public void eliminar(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> buscarPorDni(String dni) {

        return DoctorAdapter.toModelList(doctorRepository.findAllByDni(dni));
    }

    // VALIDACIONES

    @Override
    public String validarDatosRegistro(Doctor doctor) {
        String error = validacionesGenerales(doctor);

        if (error != null) {
            return error;
        } else if (doctorRepository.findByDni(doctor.getDni()).isPresent()) {

            return "Ya existe un doctor registrado con ese DNI";

        } else if (doctorRepository.findByCorreo(doctor.getCorreo()).isPresent()) {
            return "Ya existe un doctor registrado con ese correo";
        }

        return null;
    }

    @Override
    public String validarDatosEdicion(Doctor doctor) {
        String error = validacionesGenerales(doctor);

        if (error != null) {

            return error;

        }

        return null;
    }

    public String validacionesGenerales(Doctor doctor) {

        if (doctor.getNombre() == null || doctor.getNombre().trim().isEmpty()) {

            return "El nombre del doctor es obligatorio";

        } else if (doctor.getTelefono() == null || doctor.getTelefono().trim().isEmpty()) {

            return "El teléfono del doctor es obligatorio";

        } else if (doctor.getServicio() == null || doctor.getServicio().getId() == null) {

            return "La especialidad es obligatoria";

        } else if (doctor.getHoraAtencionInicio() == null) {

            return "El horario de inicio de atención es obligatorio";

        } else if (doctor.getHoraAtencionFin() == null) {

            return "El horario de fin de atención es obligatorio";

        } else if (!doctor.getHoraAtencionFin().isAfter(doctor.getHoraAtencionInicio())) {

            return "El horario de fin debe ser mayor al horario de inicio";

        } else if (doctor.getHoraAtencionInicio().isBefore(LocalTime.of(7, 0))) {

            return "El horario de inicio no puede ser antes de las 7:00 am";

        } else if (doctor.getHoraAtencionFin().isAfter(LocalTime.of(20, 0))) {

            return "El horario de fin no puede ser después de las 8:00 pm";

        } else if (doctor.getFechaNacimiento() == null) {

            return "La fecha de nacimiento es obligatoria";

        } else if (!doctor.getDni().matches("\\d{8}")) {

            return "El DNI debe tener exactamente 8 dígitos numéricos";

        } else if (!doctor.getTelefono().matches("9\\d{8}")) {

            return "El teléfono solo debe contener números";

        } else if (doctor.getEdad() < 24 || doctor.getEdad() > 75 || doctor.getEdad() == 0) {

            return "La fecha de nacimiento no es válida";

        } else if (doctor.getFechaNacimiento().isAfter(LocalDate.now())) {

            return "La fecha de nacimiento no debería ser futura";

        } else if (doctor.getHoraAtencionInicio().plusHours(6).isAfter(doctor.getHoraAtencionFin())) {

            return "El doctor debe trabajar mínimo 6 horas al día";
            
        }

        return null;
    }
}