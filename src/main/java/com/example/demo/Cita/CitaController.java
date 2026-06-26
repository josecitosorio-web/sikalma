package com.example.demo.Cita;

import com.example.demo.Doctor.Doctor;
import com.example.demo.Doctor.DoctorService;
import com.example.demo.DoctorDIa.DoctorDiaService;
import com.example.demo.Paciente.Paciente;
import com.example.demo.Paciente.PacienteService;
import com.example.demo.Servicio.ServicioService;
import com.example.demo.Usuario.UsuarioService;

import org.springframework.ui.Model;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/cita")
public class CitaController {

    private final CitaService citaService;
    private final PacienteService pacienteService;
    private final DoctorService doctorService;
    private final ServicioService servicioService;
    private final UsuarioService usuarioService;
    private final DoctorDiaService doctorDiaService;

    public CitaController(CitaService citaService, PacienteService pacienteService, DoctorService doctorService,
            ServicioService servicioService, UsuarioService usuarioService, DoctorDiaService doctorDiaService) {
        this.citaService = citaService;
        this.pacienteService = pacienteService;
        this.doctorService = doctorService;
        this.servicioService = servicioService;
        this.usuarioService = usuarioService;
        this.doctorDiaService = doctorDiaService;
    }

    @GetMapping("/g-citas")
    public String mostrarCitas(Model model) {

        model.addAttribute("paginaActiva", "citas");
        model.addAttribute("citas", citaService.listar());
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("citasPendientes", citaService.contarPorEstado("Pendiente"));
        model.addAttribute("citasConfirmados", citaService.contarPorEstado("Confirmado"));
        model.addAttribute("citasHoy", citaService.buscarCitasHoy(LocalDate.now()));
        return "Gestion-citas";

    }

    @GetMapping("/r-citas")
    public String registrarCita(Model model) {
        model.addAttribute("paginaActiva", "r-citas");
        model.addAttribute("pacientes", null);
        model.addAttribute("doctores", doctorService.buscarPorEstado(true));
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

        return "Registrar-cita";
    }

    @PostMapping("/guardar")
    public String guardar(

            @RequestParam Long paciente,
            @RequestParam Long doctor,
            @RequestParam Long servicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime hora,
            @RequestParam String estado,
            Model model) {

        String error = citaService.validarDatosRegistro(paciente, doctor, servicio, fecha, hora);
        if (error != null) {

            model.addAttribute("error", error);
            model.addAttribute("paginaActiva", "r-citas");
            model.addAttribute("pacientes", pacienteService.listar());
            model.addAttribute("doctores", doctorService.buscarPorEstado(true));
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
            model.addAttribute("servicioSeleccionado", servicio);
            model.addAttribute("doctorSeleccionado", doctor);

            return "Registrar-cita";

        }

        citaService.guardar(paciente, doctor, fecha, hora, estado);
        return "redirect:/cita/g-citas";
    }

    @GetMapping("/buscar")
    public String buscarPac(@RequestParam String numeroDocumento, Model model) {

        model.addAttribute("paciente", pacienteService.buscarPaciente(numeroDocumento));
        model.addAttribute("paginaActiva", "r-citas");
        model.addAttribute("doctores", doctorService.buscarPorEstado(true));
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

        return "Registrar-cita";
    }

    @GetMapping("/ver-horario")
    public String verHorario(@RequestParam Long paciente, @RequestParam Long servicio, @RequestParam Long doctor,
            Model model) {

        Paciente pacienteEncontrado = pacienteService.buscarPorId(paciente);
        List<String> horario = doctorDiaService.obtenerDiasEspanol(doctor);
        Doctor doctorEncontrado = doctorService.buscarPorId(doctor);

        String error = citaService.validarDatosHorario(paciente, servicio, doctor);

        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("paciente", pacienteEncontrado);
            model.addAttribute("servicioid", servicio);
            model.addAttribute("doctorid", doctor);
            model.addAttribute("paginaActiva", "r-citas");
            model.addAttribute("doctores", doctorService.buscarPorEstado(true));
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

            return "Registrar-cita";
        }

        model.addAttribute("horarios", horario);
        model.addAttribute("doctorEncontrado", doctorEncontrado);
        model.addAttribute("paciente", pacienteEncontrado);
        model.addAttribute("servicioid", servicio);
        model.addAttribute("doctorid", doctor);
        model.addAttribute("paginaActiva", "r-citas");
        model.addAttribute("doctores", doctorService.buscarPorEstado(true));
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

        return "Registrar-cita";

    }

    @GetMapping("/ver-horario-edicion")
    public String verHorarioEdicion(@RequestParam Long CitaId,@RequestParam Long paciente, @RequestParam Long servicio, @RequestParam Long doctor,
            Model model) {

        Paciente pacienteEncontrado = pacienteService.buscarPorId(paciente);
        List<String> horario = doctorDiaService.obtenerDiasEspanol(doctor);
        Doctor doctorEncontrado = doctorService.buscarPorId(doctor);

        String error = citaService.validarDatosHorario(paciente, servicio, doctor);

        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("paciente", pacienteEncontrado);
            model.addAttribute("servicioid", servicio);
            model.addAttribute("doctorid", doctor);
            model.addAttribute("cita", citaService.buscarPorId(CitaId));
            model.addAttribute("paginaActiva", "r-citas");
            model.addAttribute("doctores", doctorService.buscarPorEstado(true));
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

            return "Editar-cita";
        }

        model.addAttribute("cita", citaService.buscarPorId(CitaId));
        model.addAttribute("horarios", horario);
        model.addAttribute("doctorEncontrado", doctorEncontrado);
        model.addAttribute("paciente", pacienteEncontrado);
        model.addAttribute("servicioid", servicio);
        model.addAttribute("doctorid", doctor);
        model.addAttribute("paginaActiva", "r-citas");
        model.addAttribute("doctores", doctorService.buscarPorEstado(true));
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

        return "Editar-cita";

    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {

        model.addAttribute("cita", citaService.buscarPorId(id));
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("doctores", doctorService.buscarPorEstado(true));
        model.addAttribute("paginaActiva", "r-citas");
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

        return "Editar-cita";
    }

    @PostMapping("/actualizar")
    public String actualizar(@RequestParam Long id, @RequestParam Long paciente, @RequestParam Long doctor,
            @RequestParam Long servicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime hora, @RequestParam String estado, Model model) {

        String error = citaService.validarDatosEdicion(id, paciente, doctor, servicio, fecha, hora);
        if (error != null) {

            model.addAttribute("error", error);
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("doctores", doctorService.buscarPorEstado(true));
            model.addAttribute("paginaActiva", "citas");
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
            model.addAttribute("cita", citaService.buscarPorId(id));
            return "Editar-cita";

        }

        citaService.actualizar(id, paciente, doctor, fecha, hora, estado);
        return "redirect:/cita/g-citas";

    }

    @GetMapping("/atender")
    public String atenderCita(@RequestParam Long id, Model model) {

        Cita cita = citaService.buscarPorId(id);

        LocalTime horafin = cita.getHora().plusHours(1);

        model.addAttribute("cita", cita);
        model.addAttribute("horafin", horafin);
        model.addAttribute("paginaActiva", "citas");
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

        return "Registrar-atencion";
    }

    @GetMapping("/cancelar")
    public String cancelar(@RequestParam Long id, Model model) {

        Cita cita = citaService.buscarPorId(id);

        if (cita != null && cita.getEstado().equalsIgnoreCase("Pendiente")) {
            citaService.cambiarEstado(id, "Cancelado");
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        }
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

        return "redirect:/cita/g-citas";
    }

    @GetMapping("/confirmar")
    public String confirmarCita(@RequestParam Long id, Model model) {

        Cita cita = citaService.buscarPorId(id);

        if (cita != null && cita.getEstado().equalsIgnoreCase("Pendiente")) {
            citaService.cambiarEstado(id, "Confirmado");
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        }

        return "redirect:/cita/g-citas";
    }

    @GetMapping("/no-asistio")
    public String marcarNoAsistio(@RequestParam Long id, Model model) {
        Cita cita = citaService.buscarPorId(id);

        if (cita != null && cita.getEstado().equalsIgnoreCase("Pendiente")) {
            citaService.cambiarEstado(id, "No asistió");
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        }
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());

        return "redirect:/cita/g-citas";
    }
}