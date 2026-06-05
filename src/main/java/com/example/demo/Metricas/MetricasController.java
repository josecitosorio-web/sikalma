package com.example.demo.Metricas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Cita.CitaService;
import com.example.demo.Usuario.UsuarioService;

@Controller
@RequestMapping("/metricas")
public class MetricasController {

    private final UsuarioService usuarioService;
    private final CitaService citaService;

    public MetricasController (UsuarioService usuarioService, CitaService citaService){
        this.usuarioService = usuarioService;
        this.citaService = citaService;
    }

    @GetMapping("/g-metricas")
    public String mostrarMetricas(Model model){

        model.addAttribute("paginaActiva" , "metricas");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        model.addAttribute("cantidades", citaService.obtenerCantidadPorFecha());
        model.addAttribute("fechas", citaService.obtenerCitasPorFecha());

        model.addAttribute("estados",citaService.obtenerEstadoPorCantidad());
        model.addAttribute("cantidadEstado", citaService.obtenerCantidadPorEstado());

        model.addAttribute("fechasIngresos" , citaService.obtenerCitasPorFecha());
        model.addAttribute("ingresos", citaService.obtenerSumaDeIngresos());

        model.addAttribute("servicios", citaService.obtenerServicioPorCantidad());
        model.addAttribute("cantidadServicio", citaService.obtenerCantidadPorServicio());
        
        model.addAttribute("diasCitas" , citaService.obtenerDiaPorCantidad());
        model.addAttribute("cantidadCitas", citaService.obtenerCantidadPorDia());
        

        return "Metricas";
    }
}
