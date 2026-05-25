package com.example.demo.Doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Cita.CitaService;
import com.example.demo.Servicio.ServicioService;
import com.example.demo.Usuario.UsuarioService;


@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final CitaService citaService;
    private final ServicioService servicioService;
    private final UsuarioService usuarioService;

    public DoctorController(DoctorService doctorService , CitaService citaService, ServicioService servicioService, UsuarioService usuarioService) {
        this.doctorService = doctorService;
        this.citaService = citaService;
        this.servicioService = servicioService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/gestion")
    public String listar(Model model) {
        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("doctores", doctorService.obtenerTodos());
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Gestion-doctores";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String dni, Model model) {
        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("doctores", doctorService.buscarPorDni(dni));
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Gestion-doctores";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        model.addAttribute("doctor", new Doctor());
        return "Registrar-doctor";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Doctor doctor, Model model) {


        String error = doctorService.validarDatosRegistro(doctor);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("doctor", doctor); // Devuelve los datos para que no se borren
            model.addAttribute("paginaActiva", "personal");
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            return "Registrar-doctor";
        }

        doctorService.agregar(doctor);
        return "redirect:/doctor/gestion";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {
        model.addAttribute("doctor", doctorService.buscarPorId(id));
        model.addAttribute("servicios" , servicioService.listar());
        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Editar-doctor";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Doctor doctor, Model model) {


        String error = doctorService.validarDatosEdicion(doctor);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("doctor", doctor); // Devuelve los datos
            model.addAttribute("servicios" , servicioService.listar());
            model.addAttribute("paginaActiva", "personal");
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            return "Editar-doctor";
        }

        doctorService.actualizar(doctor);
        return "redirect:/doctor/gestion";
    }

    @GetMapping("/advertir")
    public String advertir(@RequestParam Long id, Model model) {

        String error = citaService.validarCitasExistentesDoctor(id);
        if(error != null){

            model.addAttribute("error" , error);
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

            return "Eliminar-doctor-error-message";

        }

        model.addAttribute("doctor", doctorService.buscarPorId(id));
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Eliminar-doctor";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {
        doctorService.eliminar(id);
        return "redirect:/doctor/gestion";
    }
}