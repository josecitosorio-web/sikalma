package com.example.demo.Usuario;

import java.util.List;

import com.example.demo.Doctor.Doctor;

public interface UsuarioService {
    
    void agregar(String correo, String contrasena, String rol, Long idDoctor);

    List<Usuario> Listar();

    Usuario buscarPorId(Long id);

    Usuario buscarPorCorreo(String correo);

    void actualizar(Long idUsuario, String correo, String contrasena, String rol, Long idDoctor);

    void eliminar(Long id);

    Doctor buscarPorDoctor(Long idDoctor);

    List<Usuario> buscarPorDni(String dni);

    void guardarUsuarioActual(Usuario usaurio);

    Usuario obtenerUsuarioActual();

    void cerrarSesion();

    String validarDatosRegistro(String correo, String contrasena, String rol, Long idDoctor);
    String validarDatosEdicion(String correo, String contrasena, String rol, Long idDoctor);
    String validarUsuario(String correo, String contrasena);
}
