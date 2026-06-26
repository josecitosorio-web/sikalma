package com.example.demo.DoctorAusencia;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Doctor.DoctorService;
import com.example.demo.DoctorDIa.DoctorDiaService;
import com.example.demo.Usuario.UsuarioService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/ausencia")
public class DoctorAusenciaController {

    private final DoctorAusenciaService doctorAusenciaService;
    private final UsuarioService usuarioService;
    private final DoctorService doctorService;
    private final DoctorDiaService doctorDiaService;

    public DoctorAusenciaController(DoctorAusenciaService doctorAusenciaService, UsuarioService usuarioService,
            DoctorService doctorService, DoctorDiaService doctorDiaService) {

        this.doctorAusenciaService = doctorAusenciaService;
        this.usuarioService = usuarioService;
        this.doctorService = doctorService;
        this.doctorDiaService = doctorDiaService;
    }

    @GetMapping("/gestion")
    public String listarAusencias(@RequestParam Long id, Model model) {

        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("faltas", doctorAusenciaService.buscarPorIdDoctor(id));
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        model.addAttribute("idDoctor", id);
        return "Gestion-ausencias";
    }

    @GetMapping("/nuevo")
    public String nuevaAusencia(@RequestParam Long id, Model model) {

        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        model.addAttribute("doctor", doctorService.buscarPorId(id));
        model.addAttribute("diaLaborales", doctorDiaService.obtenerDiasEspanol(id));
        model.addAttribute("ausencia", new DoctorAusencia());

        return "Registrar-Ausencia";
    }

    @PostMapping("/registrar")
    public String guardarAusencia(@RequestParam LocalDate fecha,@RequestParam String motivo, @RequestParam Long doctorId, Model model) {

        String error = doctorAusenciaService.validaciones(fecha,motivo,doctorId);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("ausencia", doctorAusenciaService.ausenciaTemporal(fecha, motivo, doctorId));
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
            model.addAttribute("doctor", doctorService.buscarPorId(doctorId));
            model.addAttribute("diaLaborales", doctorDiaService.obtenerDiasEspanol(doctorId));
            model.addAttribute("paginaActiva", "personal");
            return "Registrar-Ausencia";
        }

        doctorAusenciaService.agregar(fecha,motivo,doctorId);

        return "redirect:/ausencia/gestion?id=" + doctorId;

    }

    @GetMapping("/editar")
    public String editarAusencia(@RequestParam Long id, Model model) {

        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        model.addAttribute("doctor", doctorService.buscarPorId(id));
        model.addAttribute("diaLaborales", doctorDiaService.obtenerDiasEspanol(id));
        model.addAttribute("ausencia", doctorAusenciaService.buscarAusencia(id));

        return "Editar-Ausencia";
    }

    @PostMapping("/actualizar")
    public String guardarCambios(@RequestParam LocalDate fecha,@RequestParam String motivo, @RequestParam Long doctorId, @RequestParam Long ausenciaId, Model model) {

        String error = doctorAusenciaService.validacionesEdicion(fecha,motivo,doctorId);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("ausencia", doctorAusenciaService.buscarAusencia(ausenciaId));
            model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
            model.addAttribute("doctor", doctorService.buscarPorId(doctorId));
            model.addAttribute("diaLaborales", doctorDiaService.obtenerDiasEspanol(doctorId));
            model.addAttribute("paginaActiva", "personal");
            return "Editar-Ausencia";
        }

        doctorAusenciaService.actualizar(fecha,motivo,doctorId,ausenciaId);

        return "redirect:/ausencia/gestion?id=" + doctorId;

    }

}
