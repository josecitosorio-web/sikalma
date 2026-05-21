package com.example.demo.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void agregar(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> obtenerTodos() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor buscarPorId(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor buscarDoctor(String dni){
        return doctorRepository.findByDni(dni).orElse(null);
    }

    @Override
    public void actualizar(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public void eliminar(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> buscarPorDni(String dni) {
        
        return doctorRepository.findAllByDni(dni);
    }


    // VALIDACIONES


    @Override
    public String validarDatosRegistro(Doctor doctor) {
        String error = validacionesGenerales(doctor);

        if (error != null) {
            return error;
        } else if (!doctorRepository.findByDni(doctor.getDni()).isEmpty()) {

            return "Ya existe un doctor registrado con ese DNI";

        } else if (!doctorRepository.findByCorreo(doctor.getCorreo()).isEmpty()) {
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

        } else if (doctor.getEspecialidad() == null || doctor.getEspecialidad().trim().isEmpty()) {

            return "La especialidad es obligatoria";

        } else if (doctor.getTelefono() == null || doctor.getTelefono().trim().isEmpty()) {

            return "El teléfono del doctor es obligatorio";

        } else if (doctor.getFechaNacimiento() == null) {

            return "La fecha de nacimiento es obligatoria";

        } else if (!doctor.getDni().matches("\\d{8}")) {

            return "El DNI debe tener exactamente 8 dígitos numéricos";

        } else if (!doctor.getTelefono().matches("9\\d{8}")) {

            return "El teléfono solo debe contener números";

        }   else if(doctor.getEdad() < 24 || doctor.getEdad() > 75 || doctor.getEdad() == 0){

            return "La fecha de nacimiento no es válida";

        }   else if( doctor.getFechaNacimiento().isAfter(LocalDate.now())){

            return "La fecha de nacimiento no debería ser futura";

        }

        return null;
    }
}