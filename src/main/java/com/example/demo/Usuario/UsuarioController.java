package com.example.demo.Usuario;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Cita.CitaService;
import com.example.demo.Doctor.DoctorService;


import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    private final DoctorService doctorService;
    private final CitaService citaService;

    public UsuarioController(UsuarioService usuarioService, DoctorService doctorService, CitaService citaService) {
        this.usuarioService = usuarioService;
        this.doctorService = doctorService;
        this.citaService = citaService;
    }

    @GetMapping("/gestion")
    public String gestionUsuario(Model model){
        List<Usuario> usuarios = usuarioService.Listar();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("paginaActiva", "usuario");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        return "Gestion-usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevoUusario(Model model) {

        model.addAttribute("paginaActiva", "usuario");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
        model.addAttribute("usuarioForm", new Usuario());

        return "Registrar-usuario";

    }

    @GetMapping("/buscar")
    public String buscarDoctor(@RequestParam String dni, Model model) {
        model.addAttribute("paginaActiva", "usuario");
        model.addAttribute("doctor", doctorService.buscarDoctor(dni));
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        
        return "Registrar-usuario";
    }

    @PostMapping("/registrar")
    public String registrarUusario(@RequestParam String correo,
        @RequestParam String contrasena,
        @RequestParam String rol,
        @RequestParam Long idDoctor,
        Model model){

        String error = usuarioService.validarDatosRegistro(correo,contrasena,rol,idDoctor);
        if(error != null) {
            model.addAttribute("error", error);
            model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());
            
            return "Registrar-usuario";
        }

        usuarioService.agregar(correo, contrasena, rol, idDoctor);    

        
        return "redirect:/usuario/gestion";

    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {

        model.addAttribute("usuarioEditar", usuarioService.buscarPorId(id));
        model.addAttribute("paginaActiva", "usuario");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Editar-usuario";
    }

    @PostMapping("/editar")
    public String editarUsuario(@RequestParam Long idUsuario, @RequestParam String correo,
        @RequestParam String contrasena,
        @RequestParam String rol,
        @RequestParam Long idDoctor,
        Model model){

        String error = usuarioService.validarDatosEdicion(correo, contrasena, rol, idDoctor);

        if(error != null) {
            model.addAttribute("error" , error);
            
            return "redirect:/usuario/editar?id=" + idUsuario;
        }

        usuarioService.actualizar(idUsuario ,correo, contrasena, rol, idDoctor);    

        
        return "redirect:/usuario/gestion";

    }


    @GetMapping("/buscar-usuario")
    public String buscar(@RequestParam String dni, Model model){

        model.addAttribute("usuarios", usuarioService.buscarPorDni(dni) );
        model.addAttribute("paginaActiva" , "usuario");
        model.addAttribute("usuario" , usuarioService.obtenerUsuarioActual());

        return "Gestion-usuarios";

    }

    @GetMapping("/cerrar-sesion")
    public String cerrarLogin(){
        usuarioService.cerrarSesion();
        return "login";
    }

    @PostMapping("/entrar")
    public String ingresar(@RequestParam String correo,@RequestParam String contrasena, Model model){

        String error = usuarioService.validarUsuario(correo, contrasena);

        if(error != null){

            model.addAttribute("error",error);
            return "login";

        }
        Usuario usuario = usuarioService.buscarPorCorreo(correo);

        usuarioService.guardarUsuarioActual(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("paginaActiva","metricas");

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
