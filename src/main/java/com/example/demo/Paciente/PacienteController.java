package com.example.demo.Paciente;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Cita.CitaService;
import com.example.demo.Usuario.UsuarioService;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;
    private final CitaService citaService;
    private final UsuarioService usuarioService;

    public PacienteController(PacienteService pacienteService, CitaService citaService, UsuarioService usuarioService){
        this.pacienteService = pacienteService;
        this.citaService = citaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/gestion")
    public String gestionPaciente(Model model){

        List<Paciente> pacientes = pacienteService.listar();
        model.addAttribute("pacientes" , pacientes);
        model.addAttribute("paginaActiva", "paciente");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Gestion-pacientes";
    }

    @GetMapping("/nuevo")
    public String nuevoPaciente(Model model){

        model.addAttribute("paginaActiva" , "paciente");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Registrar-paciente";
    }

    @PostMapping("/registrar")
    public String registrarPaciente(@ModelAttribute Paciente p, Model model){

        String error = pacienteService.validarDatosRegistro(p);
        if (error != null) {
    
            model.addAttribute("error", error);
            model.addAttribute("paciente" , p);
            model.addAttribute("paginaActiva" , "paciente");
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            return "Registrar-paciente";

        }

        pacienteService.agregar(p);

        return "redirect:/paciente/gestion";
    }

    @GetMapping("/editar")
    public String editarPaciente(@RequestParam Long id , Model model){

        model.addAttribute("paciente" , pacienteService.buscarPorId(id));
        model.addAttribute("paginaActiva" , "paciente");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Editar-paciente";

    }

    @PostMapping("/editar")
    public String cambiarPaciente(@ModelAttribute Paciente p, Model model){

        String error = pacienteService.validarDatosEdicion(p);
        if (error != null) {
    
            model.addAttribute("error", error);
            model.addAttribute("paciente" , p);
            model.addAttribute("paginaActiva" , "paciente");
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            return "Editar-paciente";

        }

        pacienteService.actualizar(p);

        return "redirect:/paciente/gestion";
    }
    
    @GetMapping("/advertir")
    public String advertir(@RequestParam Long id, Model model){

        String error = citaService.validarCitasExistentesPaciente(id);
        if(error != null){

            model.addAttribute("error" ,error);
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            
            return "Eliminar-paciente-error-message";
        }

        model.addAttribute("paciente" , pacienteService.buscarPorId(id));
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Eliminar-paciente";
    }

    @GetMapping("/eliminar")
    public String eliminarPaciente(@RequestParam Long id){

        pacienteService.eliminar(id);

        return "redirect:/paciente/gestion";
    }

    @GetMapping("/buscar")
    public String buscarPaciente(@RequestParam String dni,Model model){

        model.addAttribute("pacientes" , pacienteService.buscarPorDni(dni));
        model.addAttribute("paginaActiva" , "paciente");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Gestion-pacientes";
    }


}
