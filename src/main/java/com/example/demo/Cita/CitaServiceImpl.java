package com.example.demo.Cita;

import com.example.demo.Doctor.Doctor;
import com.example.demo.Doctor.DoctorService;
import com.example.demo.Paciente.Paciente;
import com.example.demo.Paciente.PacienteService;
import com.example.demo.Servicio.Servicio;
import com.example.demo.Servicio.ServicioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ServicioService servicioService;


    @Override
    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    @Override
    public void guardar(Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha, LocalTime hora, String estado) {

        Paciente p = pacienteService.buscarPorId(pacienteId);
        Doctor d = doctorService.buscarPorId(doctorId);
        Servicio s = servicioService.buscarPorId(servicioId);

        Cita c = new Cita(p, d, s, fecha, hora, estado);

        citaRepository.save(c);
    }

    @Override
    public Cita buscarPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }

    @Override
    public void actualizar(Long id, Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha, LocalTime hora,
            String estado) {

        Paciente p = pacienteService.buscarPorId(pacienteId);
        Doctor d = doctorService.buscarPorId(doctorId);
        Servicio s = servicioService.buscarPorId(servicioId);

        Cita c = new Cita(p, d, s, fecha, hora, estado);
        c.setId(id);

        citaRepository.save(c);
    }

    @Override
    public List<Cita> buscarCitaPorPaciente(Long idPaciente) {
        return citaRepository.findByPacienteId(idPaciente);
    }

    // validaciones
    @Override
    public String validarDatosRegistro(Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha, LocalTime hora) {

        String error = validacionesGenerales(pacienteId, doctorId, servicioId, fecha, hora);

        if (error != null) {

            return error;

        }

        if (citaRepository.existsByDoctorIdAndFechaAndHora(doctorId, fecha, hora)) {
            return "El doctor ya tiene una cita registrada en esa fecha y hora";
        }

        return null;

    }

    @Override
    public String validarDatosEdicion(Long id, Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha, LocalTime hora) {

        String error = validacionesGenerales(pacienteId, doctorId, servicioId, fecha, hora);
        if (error != null) return error;

        if (citaRepository.existeCitaDoctorExcluyendo(doctorId, fecha, hora, id)) {
            return "El doctor ya tiene una cita registrada en esa fecha y hora";
        }

        return null;
    }

    @Override
    public String validarCitasExistentesPaciente(Long idPaciente) {

        if (!citaRepository.findByPacienteId(idPaciente).isEmpty()) {

            return "El paciente tiene citas registradas";
        }

        return null;

    }

    @Override
    public String validarCitasExistentesDoctor(Long idDoctor) {

        if (!citaRepository.findByDoctorId(idDoctor).isEmpty()) {

            return "El doctor tiene citas registradas";
        }

        return null;

    }

    @Override
    public void cambiarEstado(Long id, String estado) {

        citaRepository.cambiarEstado(id, estado);

    }

    @Override
    public boolean existeCitaDoctor(Long doctorId, LocalDate fecha, LocalTime hora) {

        return citaRepository.existsByDoctorIdAndFechaAndHora(doctorId, fecha, hora);

    }

    

    public String validacionesGenerales(Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha, LocalTime hora) {

        if (pacienteId <= 0) {

            return "Debe de seleccionar un paciente";

        } else if (doctorId <= 0) {

            return "Debe de seleccionar un doctor";

        } else if (servicioId <= 0l) {

            return "Debe de seleccionar un servicio";

        } else if (fecha == null) {

            return "La fecha de reserva es obligatorio";

        } else if (hora == null) {

            return "La hora de reserva es obligatorio";

        } else if (fecha.isBefore(LocalDate.now())) {

            return "La fecha de la cita no puede ser anterior a hoy";

        }

        return null;

    }

    @Override
    public String validarCitasExistentesServicio(Long idServicio) {

        if(!citaRepository.findByServicioId(idServicio).isEmpty()) {

            return "El servicio tiene citas registradas";

        }

        return null;

    }

}