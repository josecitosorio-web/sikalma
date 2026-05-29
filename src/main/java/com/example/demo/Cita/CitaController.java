package com.example.demo.Cita;

import com.example.demo.Doctor.DoctorService;
import com.example.demo.Paciente.PacienteService;
import com.example.demo.Servicio.ServicioService;
import com.example.demo.Usuario.UsuarioService;

import org.springframework.ui.Model;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/cita")
public class CitaController {

    private final CitaService citaService;
    private final PacienteService pacienteService;
    private final DoctorService doctorService;
    private final ServicioService servicioService;
    private final UsuarioService usuarioService;

    public CitaController(CitaService citaService, PacienteService pacienteService, DoctorService doctorService,
            ServicioService servicioService, UsuarioService usuarioService) {
        this.citaService = citaService;
        this.pacienteService = pacienteService;
        this.doctorService = doctorService;
        this.servicioService = servicioService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/g-citas")
    public String mostrarCitas(Model model) {

        model.addAttribute("paginaActiva", "citas");
        model.addAttribute("citas", citaService.listar());
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Gestion-citas";

    }

    @GetMapping("/r-citas")
    public String registrarCita(Model model) {
        model.addAttribute("paginaActiva", "r-citas");
        model.addAttribute("pacientes", null);
        model.addAttribute("doctores", doctorService.obtenerTodos());
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

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
            model.addAttribute("doctores", doctorService.obtenerTodos());
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            return "Registrar-cita";

        }

        citaService.guardar(paciente, doctor, servicio, fecha, hora, estado);
        return "redirect:/cita/g-citas";
    }

    @GetMapping("/buscar")
    public String buscarPac(@RequestParam String dni, Model model){

        model.addAttribute("paciente" , pacienteService.buscarPaciente(dni));
        model.addAttribute("paginaActiva" , "r-citas");
        model.addAttribute("doctores", doctorService.obtenerTodos());
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Registrar-cita";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {

        model.addAttribute("cita", citaService.buscarPorId(id));
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("doctores", doctorService.obtenerTodos());
        model.addAttribute("paginaActiva", "r-citas");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Editar-cita";
    }

    @PostMapping("/actualizar")
    public String actualizar(@RequestParam Long id, @RequestParam Long paciente, @RequestParam Long doctor,
            @RequestParam Long servicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime hora, @RequestParam String estado, Model model) {

        String error = citaService.validarDatosEdicion(id,paciente, doctor, servicio, fecha, hora);
        if (error != null) {

            model.addAttribute("error", error);
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("doctores", doctorService.obtenerTodos());
            model.addAttribute("paginaActiva", "citas");
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            model.addAttribute("cita", citaService.buscarPorId(id));
            return "Editar-cita";

        }

        citaService.actualizar(id, paciente, doctor, servicio, fecha, hora, estado);
        return "redirect:/cita/g-citas";

    }

    @GetMapping("/atender")
    public String atenderCita(@RequestParam Long id, Model model) {

        Cita cita = citaService.buscarPorId(id);

        if(cita != null && cita.getEstado().equalsIgnoreCase("Confirmado")){
            
            citaService.cambiarEstado(id, "Atendido");

        }

        model.addAttribute("cita", citaService.buscarPorId(id));
        model.addAttribute("paginaActiva", "citas");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Registrar-atencion";
    }

    @GetMapping("/cancelar")
    public String cancelar(@RequestParam Long id, Model model) {

        Cita cita = citaService.buscarPorId(id);

        if (cita != null && cita.getEstado().equalsIgnoreCase("Pendiente")) {
            citaService.cambiarEstado(id, "Cancelado");
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        }
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "redirect:/cita/g-citas";
    }

    @GetMapping("/confirmar")
    public String confirmarCita(@RequestParam Long id, Model model) {

        Cita cita = citaService.buscarPorId(id);

        if(cita != null && cita.getEstado().equalsIgnoreCase("Pendiente")) {
            citaService.cambiarEstado(id, "Confirmado");
            model.addAttribute("usuario",usuarioService.obtenerUsuarioActual());
        }

        return "redirect:/cita/g-citas";
    }


    @GetMapping("/no-asistio")
    public String marcarNoAsistio(@RequestParam Long id, Model model) {
        Cita cita = citaService.buscarPorId(id);

        if (cita != null && cita.getEstado().equalsIgnoreCase("Confirmado")) {
            citaService.cambiarEstado(id, "No asistió");
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        }
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "redirect:/cita/g-citas";
    }
}