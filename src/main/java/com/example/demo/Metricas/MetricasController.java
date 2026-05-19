package com.example.demo.Metricas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Usuario.UsuarioService;

@Controller
@RequestMapping("/metricas")
public class MetricasController {

    private final UsuarioService usuarioService;

    public MetricasController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/g-metricas")
    public String mostrarMetricas(Model model){

        model.addAttribute("paginaActiva" , "metricas");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Metricas";
    }
}
