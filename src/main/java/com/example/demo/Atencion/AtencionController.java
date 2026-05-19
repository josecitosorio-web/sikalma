package com.example.demo.Atencion;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Cita.CitaService;
import com.example.demo.Usuario.UsuarioService;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/atencion")
public class AtencionController {

    private final AtencionService atencionService;
    private final CitaService citaService;
    private final UsuarioService usuarioService;

    public AtencionController(AtencionService atencionService, CitaService citaService, UsuarioService usuarioService) {
        this.atencionService = atencionService;
        this.citaService = citaService;
        this.usuarioService = usuarioService;
    }

    
    @GetMapping("/gestion")
    public String listar(Model model) {
        List<Atencion> atenciones = atencionService.obtenerTodos();
        model.addAttribute("atenciones", atenciones);
        model.addAttribute("paginaActiva", "atencion");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Gestion-atenciones";
    }

    @PostMapping("/nuevo")
    public String registrarAtencion(@RequestParam int citaId,
        @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horaInicio,
        @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horaFin,
        @RequestParam String diagnostico,
        @RequestParam String tratamiento,
        @RequestParam String estado,
        Model model) {

        Atencion atencion = new Atencion(null, horaInicio, horaFin, diagnostico, tratamiento, estado);

        String error = atencionService.validarDatosRegistro(atencion);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("cita", citaService.buscarPorId(citaId)); 
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            return "Registrar-atencion";
        }

        atencionService.agregar(citaId,horaInicio,horaFin,diagnostico,tratamiento,estado);

        return "redirect:/atencion/gestion";
    }


    @GetMapping("/ver")
    public String ver(@RequestParam int id, Model model) {
        model.addAttribute("atencion", atencionService.buscarPorId(id));
        model.addAttribute("paginaActiva", "atencion");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Ver-atencion";
    }

    
    @GetMapping("/editar")
    public String editar(@RequestParam int id, Model model) {
        model.addAttribute("atencion", atencionService.buscarPorId(id));
        model.addAttribute("paginaActiva", "atencion");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Editar-atencion";
    }

    
    @PostMapping("/actualizar")
    public String actualizar(@RequestParam int id,
        @RequestParam int citaId,
        @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horaInicio,
        @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horaFin,
        @RequestParam String diagnostico,
        @RequestParam String tratamiento,
        @RequestParam String estado,
        Model model) {

        Atencion atencion = new Atencion(null, horaInicio, horaFin, diagnostico, tratamiento, estado);

        String error = atencionService.validarDatosEdicion(atencion);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("atencion", atencionService.buscarPorId(id));
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            return "Editar-atencion";
        }

        atencionService.actualizar(id, citaId, horaInicio, horaFin, diagnostico, tratamiento, estado);
        return "redirect:/atencion/gestion";
        }

    
}
