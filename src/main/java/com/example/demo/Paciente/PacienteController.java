package com.example.demo.Paciente;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Usuario.UsuarioService;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;
    private final UsuarioService usuarioService;

    public PacienteController(PacienteService pacienteService, UsuarioService usuarioService){
        this.pacienteService = pacienteService;
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
        model.addAttribute("paciente", new Paciente());

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
    
    @GetMapping("/buscar")
    public String buscarPaciente(@RequestParam String dni,Model model){

        model.addAttribute("pacientes" , pacienteService.buscarPorDni(dni));
        model.addAttribute("paginaActiva" , "paciente");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Gestion-pacientes";
    }


}
