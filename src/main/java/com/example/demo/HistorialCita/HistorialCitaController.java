package com.example.demo.HistorialCita;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Usuario.UsuarioService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/historial")
public class HistorialCitaController {

    private final HistorialCitaService historialCitaService;
    private final UsuarioService usuarioService;

    public HistorialCitaController(HistorialCitaService historialCitaService, UsuarioService usuarioService) {
        this.historialCitaService = historialCitaService;
        this.usuarioService = usuarioService;
    }
    
    @GetMapping("/g-historial")
    public String mostrarHistorial(@RequestParam Long id , Model model) {

        model.addAttribute("cambios" , historialCitaService.obtenerPorCita(id));
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        model.addAttribute("paginaActiva", "citas");

        return "Gestion-Historial";

    }

    

}
