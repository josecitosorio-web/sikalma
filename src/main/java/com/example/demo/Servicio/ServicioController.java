package com.example.demo.Servicio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Usuario.UsuarioService;

import java.util.List;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    private final ServicioService servicioService;
    private final UsuarioService usuarioService;

    public ServicioController(ServicioService servicioService , UsuarioService usuarioService) {
        this.servicioService = servicioService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/gestion")
    public String gestionServicio(Model model) {
        List<Servicio> servicios = servicioService.listar();
        model.addAttribute("servicios", servicios);
        model.addAttribute("paginaActiva", "servicios");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Gestion-servicios";
    }

    @GetMapping("/nuevo")
    public String nuevoServicio(Model model) {
        model.addAttribute("paginaActiva", "servicios");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Registrar-servicio";
    }

    @PostMapping("/registrar")
    public String registrarServicio(@ModelAttribute Servicio s, Model model) {

        String error = servicioService.validarDatosRegistro(s);
        if(error != null){

            model.addAttribute("error", error);
            model.addAttribute("servicio" , s);
            model.addAttribute("paginaActiva" , "servicios");
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            return "Registrar-servicio";

        } 

        s.setEstado(true);

        servicioService.agregar(s);

        return "redirect:/servicio/gestion";
    }

    @GetMapping("/editar")
    public String editarServicio(@RequestParam Long id, Model model) {
        model.addAttribute("servicio", servicioService.buscarPorId(id));
        model.addAttribute("paginaActiva", "servicios");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Editar-servicio";
    }

    @PostMapping("/editar")
    public String cambiarServicio(@ModelAttribute Servicio s, Model model) {

        String error = servicioService.validarDatosEdicion(s);
        if (error != null) {
    
            model.addAttribute("error", error);
            model.addAttribute("servicio" , s);
            model.addAttribute("paginaActiva" , "servicios");
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            return "Editar-servicio";

        }

        servicioService.actualizar(s);
        return "redirect:/servicio/gestion";
    }

    @GetMapping("/buscar")
    public String buscarServicio(@RequestParam String nombre, Model model) {
        model.addAttribute("servicios", servicioService.buscarPorNombre(nombre));
        model.addAttribute("paginaActiva", "servicios");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Gestion-servicios";
    }

    @GetMapping("/activar")
    public String activarServicio(@RequestParam Long id, Model model) {

        Servicio servicio = servicioService.buscarPorId(id);

        if(servicio != null && !servicio.getEstado()) {
            servicioService.cambiarEstado(id, true);
            
        }
        

        return "redirect:/servicio/gestion";
    }

    @GetMapping("/desactivar")
    public String desactivarServicio(@RequestParam Long id, Model model) {

        Servicio servicio = servicioService.buscarPorId(id);

        if(servicio != null && servicio.getEstado()) {
            servicioService.cambiarEstado(id, false);
           
        }
        

        return "redirect:/servicio/gestion";
    }

}