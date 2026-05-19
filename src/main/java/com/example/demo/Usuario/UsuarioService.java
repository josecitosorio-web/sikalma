package com.example.demo.Usuario;

import java.util.List;

import com.example.demo.Doctor.Doctor;

public interface UsuarioService {
    
    void agregar(String correo, String contrasena, String rol, int idDoctor);

    List<Usuario> Listar();

    Usuario buscarPorId(int id);

    Usuario buscarPorCorreo(String correo);

    void actualizar(int idUsuario, String correo, String contrasena, String rol, int idDoctor);

    void eliminar(int id);

    Doctor buscarPorDoctor(int idDoctor);

    List<Usuario> buscarPorDni(String dni);

    void guardarUsuarioActual(Usuario usaurio);

    Usuario obtenerUsuarioActual();

    void cerrarSesion();

    String validarDatosRegistro(String correo, String contrasena, String rol, int idDoctor);
    String validarDatosEdicion(String correo, String contrasena, String rol, int idDoctor);
    String validarUsuario(String correo, String contrasena);
}
