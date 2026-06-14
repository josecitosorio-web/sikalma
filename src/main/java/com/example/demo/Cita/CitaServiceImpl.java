package com.example.demo.Cita;

import com.example.demo.Doctor.Doctor;
import com.example.demo.Doctor.DoctorService;
import com.example.demo.DoctorDIa.DoctorDiaService;
import com.example.demo.HistorialCita.HistorialCita;
import com.example.demo.HistorialCita.HistorialCitaService;
import com.example.demo.Paciente.Paciente;
import com.example.demo.Paciente.PacienteService;
import com.example.demo.Usuario.Usuario;
import com.example.demo.Usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private UsuarioService usuarioService;

    @Autowired
    private HistorialCitaService historialCitaService;

    @Autowired
    private DoctorDiaService doctorDiaService;

    @Override
    public List<Cita> listar() {

        Usuario usuario = usuarioService.obtenerUsuarioActual();

        if (usuario.getRol().equals("DOCTOR")) {

            return CitaAdapter.toModelList(citaRepository.findByDoctorId(usuario.getDoctor().getId()));

        }

        return CitaAdapter.toModelList(citaRepository.findAll());
    }

    @Override
    public void guardar(Long pacienteId, Long doctorId, LocalDate fecha, LocalTime hora,
            String estado) {

        Paciente p = pacienteService.buscarPorId(pacienteId);
        Doctor d = doctorService.buscarPorId(doctorId);

        Cita c = new Cita(p, d, fecha, hora, estado);

        citaRepository.save(CitaAdapter.toEntity(c));
    }

    @Override
    public Cita buscarPorId(Long id) {
        return CitaAdapter.toModel(citaRepository.findById(id).orElse(null));
    }

    @Override
    public void actualizar(Long id, Long pacienteId, Long doctorId, LocalDate fecha, LocalTime hora,
            String estado) {

        Cita citaAnterior = buscarPorId(id);
        Doctor doctor = doctorService.buscarPorId(doctorId);

        if (!citaAnterior.getHora().equals(hora) || !citaAnterior.getFecha().equals(fecha)) {

            HistorialCita historialNuevo = new HistorialCita(citaAnterior, citaAnterior.getFecha(),
                    fecha, citaAnterior.getHora(), hora, citaAnterior.getDoctor().getNombre(),
                    doctor.getNombre(), citaAnterior.getEstado(),
                    estado, LocalDateTime.now(), "Administrador", "REAGENDAMIENTO");

            historialCitaService.registrarHistorial(historialNuevo);

        }

        if (!citaAnterior.getDoctor().getId().equals(doctorId)) {

            HistorialCita historialNuevo = new HistorialCita(citaAnterior, citaAnterior.getFecha(),
                    fecha, citaAnterior.getHora(), hora, citaAnterior.getDoctor().getNombre(),
                    doctor.getNombre(), citaAnterior.getEstado(),
                    estado, LocalDateTime.now(), "Administrador", "CAMBIO DE DOCTOR");

            historialCitaService.registrarHistorial(historialNuevo);

        }

        Paciente p = pacienteService.buscarPorId(pacienteId);
        Doctor d = doctorService.buscarPorId(doctorId);

        Cita c = new Cita(p, d, fecha, hora, estado);
        c.setId(id);

        citaRepository.save(CitaAdapter.toEntity(c));
    }

    @Override
    public List<Cita> buscarCitaPorPaciente(Long idPaciente) {
        return CitaAdapter.toModelList(citaRepository.findByPacienteId(idPaciente));
    }

    @Override
    public List<Cita> buscarCitasHoy(LocalDate fecha) {
        return CitaAdapter.toModelList(citaRepository.findByFecha(fecha));
    }

    @Override
    public long contarPorEstado(String estado){

        return citaRepository.countByEstado(estado);

    }

    // validaciones
    @Override
    public String validarDatosRegistro(Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha,
            LocalTime hora) {

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
    public String validarDatosEdicion(Long id, Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha,
            LocalTime hora) {

        String error = validacionesGeneralesEdicion(id, pacienteId, doctorId, servicioId, fecha, hora);
        if (error != null)
            return error;

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

        Cita citaAnterior = CitaAdapter.toModel(citaRepository.findById(id).orElse(null));

        if (!citaAnterior.getEstado().equals(estado)) {

            String responsable;

            if (estado.equals("Atendido")) {

                responsable = citaAnterior.getDoctor().getNombre();

            } else {

                responsable = "Administrador";

            }

            HistorialCita historialNuevo = new HistorialCita(citaAnterior, citaAnterior.getFecha(),
                    null, citaAnterior.getHora(), null, citaAnterior.getDoctor().getNombre(),
                    null, citaAnterior.getEstado(),
                    estado, LocalDateTime.now(), responsable, "CAMBIO DE ESTADO");

            historialCitaService.registrarHistorial(historialNuevo);

        }

        citaRepository.cambiarEstado(id, estado);

    }

    @Override
    public boolean existeCitaDoctor(Long doctorId, LocalDate fecha, LocalTime hora) {

        return citaRepository.existsByDoctorIdAndFechaAndHora(doctorId, fecha, hora);

    }

    @Override
    public String validarDatosHorario(Long idPaciente, Long idServicio, Long idDoctor) {

        if(idPaciente == null || idPaciente == 0) {

            return "Debe seleccionar un paciente";

        }else if (idServicio == null || idServicio == 0) {

            return "Debe seleccionar un servicio";

        } else if (idDoctor == null || idDoctor == 0){

            return "Debe seleccionar un doctor";

        }

        

        return null;

    }


    // METRICAS

    @Override
    public List<String> obtenerCitasPorFecha() {

        List<Object[]> resultados = citaRepository.obtenerCitasPorFecha();
        List<String> fechas = new ArrayList<>();

        for(Object[] fila : resultados) {

            fechas.add(fila[0].toString());

        }

        return fechas;

    }

    @Override
    public List<Long> obtenerCantidadPorFecha() {

        List<Object[]> resultados = citaRepository.obtenerCitasPorFecha();
        List<Long> cantidades = new ArrayList<>();

        for(Object[] fila : resultados) {

            cantidades.add((Long) fila[1]);

        }

        return cantidades;

    }

    @Override
    public List<String> obtenerEstadoPorCantidad(){

        List<Object[]> resultados = citaRepository.contarPorEstado();
        List<String> servicios = new ArrayList<>();

        for(Object[] fila : resultados) {

            servicios.add(fila[0].toString());

        }

        return servicios;

    }

    @Override
    public List<Long> obtenerCantidadPorEstado() {

        List<Object[]> resultados = citaRepository.contarPorEstado();
        List<Long> cantidad = new ArrayList<>();

        for(Object[] fila : resultados) {

            cantidad.add((Long) fila[1]);

        }

        return cantidad;

    }

    @Override
    public List<String> obtenerIngresosPorFecha() {

        List<Object[]> resultados = citaRepository.ingresosPorDia();
        List<String> fechas = new ArrayList<>();

        for(Object[] fila : resultados) {

            fechas.add(fila[0].toString());

        }

        return fechas;
    }

    @Override
    public List<Double> obtenerSumaDeIngresos() {

        List<Object[]> resultados = citaRepository.ingresosPorDia();
        List<Double> ingresos = new ArrayList<>();

        for(Object[] fila : resultados) {

            ingresos.add((Double) fila[1]);

        }

        return ingresos;


    }

    @Override
    public List<String> obtenerServicioPorCantidad() {


        List<Object[]> resultados = citaRepository.contarPorServicio();
        List<String> servicios = new ArrayList<>();

        for(Object[] fila : resultados) {

            servicios.add(fila[0].toString());
        }

        return servicios;

    }

    @Override
    public List<Long> obtenerCantidadPorServicio() {

        List<Object[]> resultados = citaRepository.contarPorServicio();
        List<Long> cantidad = new ArrayList<>();

        for(Object[] fila : resultados) {

            cantidad.add((Long) fila[1]);

        }

        return cantidad;


    }

    @Override
    public List<String> obtenerDiaPorCantidad() {

        List<Object[]> resultados = citaRepository.contarPorDiaSemana();
        List<String> dias = new ArrayList<>();

        for(Object[] fila : resultados) {

            dias.add(fila[0].toString());

        }

        return dias;

    }

    @Override
    public List<Long> obtenerCantidadPorDia() {


        List<Object[]> resultados = citaRepository.contarPorDiaSemana();
        List<Long> cantidad = new ArrayList<>();
        
        for(Object[] fila : resultados) {

            cantidad.add((Long) fila[1]);

        }


        return cantidad;

    }

    public String validacionesGenerales(Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha,
            LocalTime hora) {

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

        } else if (fecha.isEqual(LocalDate.now()) && hora.isBefore(LocalTime.now())) {

            return "La hora de la cita no puede ser anterior a la hora actual";

        } else if (fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
            

            return "No se pueden registrar citas los domingos";

        } else {

            List<Cita> citasPaciente = CitaAdapter.toModelList(citaRepository.findByPacienteId(pacienteId));

            for (Cita c : citasPaciente) {

                if (c.getFecha().isEqual(fecha)) {

                    LocalTime inicioExistente = c.getHora();
                    LocalTime finExistente = c.getHora().plusHours(1);
                    LocalTime inicioNueva = hora;
                    LocalTime finNueva = hora.plusHours(1);

                    boolean hayCruce = inicioNueva.isBefore(finExistente) && finNueva.isAfter(inicioExistente);

                    if (hayCruce) {

                        return "El paciente ya tiene una cita reservada, cada cita dura 1 hora";

                    }

                }

            }

            Doctor d = doctorService.buscarPorId(doctorId);

            if (hora.isBefore(d.getHoraAtencionInicio()) || hora.isAfter(d.getHoraAtencionFin())
                    || hora.equals(d.getHoraAtencionFin())) {

                return "La hora de la cita está fuera del horario del doctor";

            }

            if (!d.getServicio().getId().equals(servicioId)) {

                return "El doctor seleccionado no pertenece a este servicio";

            }

            List<Cita> citasDoctor = CitaAdapter.toModelList(citaRepository.findByDoctorId(doctorId));

            for (Cita c : citasDoctor) {

                if (c.getFecha().isEqual(fecha)) {

                    LocalTime inicioExistente = c.getHora();
                    LocalTime finExistente = c.getHora().plusHours(1);
                    LocalTime inicioNueva = hora;
                    LocalTime finNueva = hora.plusHours(1);

                    boolean hayCruce = inicioNueva.isBefore(finExistente) && finNueva.isAfter(inicioExistente);

                    if (hayCruce) {

                        return "El doctor ya tiene una cita en ese horario";
                    }
                }

            }

            List<DayOfWeek> diasDoctor = doctorDiaService.obtenerDiasPorDoctor(doctorId);
            if(!diasDoctor.contains(fecha.getDayOfWeek())) {

                return "El doctor no atiende esos dias, escoga otro día";

            }

        }

        return null;

    }

    public String validacionesGeneralesEdicion(Long id, Long pacienteId, Long doctorId, Long servicioId,
            LocalDate fecha, LocalTime hora) {

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

        } else if (fecha.isEqual(LocalDate.now()) && hora.isBefore(LocalTime.now())) {

            return "La hora de la cita no puede ser anterior a la hora actual";

        } else if (fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {

            return "No se pueden registrar citas los domingos";

        } else {

            Doctor d = doctorService.buscarPorId(doctorId);

            if (hora.isBefore(d.getHoraAtencionInicio()) || hora.isAfter(d.getHoraAtencionFin())) {

                return "La hora de la cita está fuera del horario del doctor";

            }

            if (!d.getServicio().getId().equals(servicioId)) {

                return "El doctor seleccionado no pertenece a este servicio";

            }

            List<Cita> citasDoctor = CitaAdapter.toModelList(citaRepository.findByDoctorId(doctorId));

            for (Cita c : citasDoctor) {

                if (c.getId().equals(id)) {
                    continue;
                }

                if (c.getFecha().isEqual(fecha)) {

                    LocalTime inicioExistente = c.getHora();
                    LocalTime finExistente = c.getHora().plusHours(1);
                    LocalTime inicioNueva = hora;
                    LocalTime finNueva = hora.plusHours(1);

                    boolean hayCruce = inicioNueva.isBefore(finExistente) && finNueva.isAfter(inicioExistente);

                    if (hayCruce) {

                        return "El doctor ya tiene una cita en ese horario";
                    }
                }
            }

            List<DayOfWeek> diasDoctor = doctorDiaService.obtenerDiasPorDoctor(doctorId);
            if(!diasDoctor.contains(fecha.getDayOfWeek())) {

                return "El doctor no atiende esos dias, escoga otro día";

            }

            List<Cita> citasPaciente = CitaAdapter.toModelList(citaRepository.findByPacienteId(pacienteId));

            for (Cita c : citasPaciente) {

                if (c.getId().equals(id)) {
                    continue;
                }

                if (c.getFecha().isEqual(fecha)) {

                    LocalTime inicioExistente = c.getHora();
                    LocalTime finExistente = c.getHora().plusHours(1);
                    LocalTime inicioNueva = hora;
                    LocalTime finNueva = hora.plusHours(1);

                    boolean hayCruce = inicioNueva.isBefore(finExistente) && finNueva.isAfter(inicioExistente);

                    if (hayCruce) {

                        return "El paciente ya tiene una cita reservada, cada cita dura 1 hora";

                    }
                }
            }

        }

        return null;

    }

    

}