package com.example.demo.DoctorAusencia;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Cita.CitaService;
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
    private final CitaService citaService;

    public DoctorAusenciaController(DoctorAusenciaService doctorAusenciaService, UsuarioService usuarioService,
            DoctorService doctorService, DoctorDiaService doctorDiaService, CitaService citaService) {

        this.doctorAusenciaService = doctorAusenciaService;
        this.usuarioService = usuarioService;
        this.doctorService = doctorService;
        this.doctorDiaService = doctorDiaService;
        this.citaService = citaService;
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
        citaService.cancelarCitasPorAusencia(doctorId, fecha);
        

        return "redirect:/ausencia/gestion?id=" + doctorId;

    }

    @GetMapping("/editar")
    public String editarAusencia(@RequestParam Long idAusencia, @RequestParam Long idDoctor, Model model) {


        model.addAttribute("paginaActiva", "personal");
        model.addAttribute("usuario", usuarioService.obtenerUsuarioActual());
        model.addAttribute("doctor", doctorService.buscarPorId(idDoctor));
        model.addAttribute("diaLaborales", doctorDiaService.obtenerDiasEspanol(idDoctor));
        model.addAttribute("ausencia", doctorAusenciaService.buscarAusencia(idAusencia));

        return "Editar-Ausencia";
    }

    @PostMapping("/actualizar")
    public String guardarCambios(@RequestParam LocalDate fecha,@RequestParam String motivo, @RequestParam Long doctorId, @RequestParam Long ausenciaId,@RequestParam LocalDate fechaAnterior, Model model) {

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

        citaService.actualizarCitasEdicionAusencia(doctorId, fechaAnterior);
        doctorAusenciaService.actualizar(fecha,motivo,doctorId,ausenciaId);
        citaService.cancelarCitasPorAusencia(doctorId, fecha);

        return "redirect:/ausencia/gestion?id=" + doctorId;

    }

}
