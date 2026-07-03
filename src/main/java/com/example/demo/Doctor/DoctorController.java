package com.example.demo.Doctor;

import java.time.DayOfWeek;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DoctorDIa.DoctorDiaService;
import com.example.demo.Servicio.ServicioService;
import com.example.demo.Usuario.UsuarioService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final ServicioService servicioService;
    private final UsuarioService usuarioService;
    private final DoctorDiaService doctorDiaService;

    public DoctorController(DoctorService doctorService, ServicioService servicioService, UsuarioService usuarioService,
            DoctorDiaService doctorDiaService) {
        this.doctorService = doctorService;
        this.servicioService = servicioService;
        this.usuarioService = usuarioService;
        this.doctorDiaService = doctorDiaService;
    }

    @GetMapping("/gestion")
    public String listar(Model model) {
        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("doctores", doctorService.obtenerTodos());
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        return "Gestion-doctores";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String dni, Model model) {
        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("doctores", doctorService.buscarPorDni(dni));
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        return "Gestion-doctores";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        model.addAttribute("doctor", new Doctor());
        return "Registrar-doctor";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Doctor doctor, @RequestParam(required = false) List<DayOfWeek> diasLaborales, Model model) {

        String error = doctorService.validarDatosRegistro(doctor);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("doctor", doctor);
            model.addAttribute("paginaActiva", "personal");
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
            return "Registrar-doctor";
        }

        error = doctorDiaService.validarDatos(diasLaborales);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("doctor", doctor);
            model.addAttribute("paginaActiva", "personal");
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
            return "Registrar-doctor";
        }

        doctor.setEstado(true);

        Long id = doctorService.agregar(doctor);
        doctorDiaService.agregar(id, diasLaborales);

        return "redirect:/doctor/gestion";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {

        model.addAttribute("doctor", doctorService.buscarPorId(id));
        model.addAttribute("servicios", servicioService.listar());
        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        model.addAttribute("diasSeleccionados", doctorDiaService.obtenerDiasPorDoctor(id));
        return "Editar-doctor";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Doctor doctor, Model model) {

        String error = doctorService.validarDatosEdicion(doctor);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("doctor", doctor);
            model.addAttribute("servicios", servicioService.listar());
            model.addAttribute("paginaActiva", "personal");
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
            return "Editar-doctor";
        }

        doctorService.actualizar(doctor);
        return "redirect:/doctor/gestion";
    }

    @GetMapping("/activar")
    public String activarDoctor(@RequestParam Long id, Model model) {

        Doctor doctor = doctorService.buscarPorId(id);

        if (doctor != null && !doctor.getEstado()) {

            doctorService.cambiarEstado(id, true);

        }

        return "redirect:/doctor/gestion";

    }

    @GetMapping("/desactivar")
    public String desactivarDoctor(@RequestParam Long id, Model model) {

        Doctor doctor = doctorService.buscarPorId(id);

        if (doctor != null && doctor.getEstado()) {

            doctorService.cambiarEstado(id, false);

        }

        return "redirect:/doctor/gestion";

    }
}