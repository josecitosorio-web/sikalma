package com.example.demo.Usuario;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Doctor.Doctor;
import com.example.demo.Doctor.DoctorDAO;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final DoctorDAO doctorDAO;
    private Usuario usuarioActual;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO, DoctorDAO doctorDAO) {
        this.usuarioDAO = usuarioDAO;
        this.doctorDAO = doctorDAO;
    }
    
    @Override
    public void agregar(String correo, String contrasena, String rol, int idDoctor) {

        Doctor doctor = doctorDAO.findById(idDoctor);

        Usuario usuario = new Usuario(correo,contrasena,rol,doctor);

        usuarioDAO.save(usuario);
    }

    @Override
    public List<Usuario> Listar() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario buscarPorId(int id) {
        return usuarioDAO.findById(id);
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return usuarioDAO.findByCorreo(correo);
    }

    @Override
    public void actualizar(int idUsuario, String correo, String contrasena, String rol, int idDoctor) {

        Doctor doctor = doctorDAO.findById(idDoctor);

        Usuario usuario = new Usuario(correo, contrasena,rol,doctor);
        usuario.setId(idUsuario);

        usuarioDAO.update(usuario);
    }

    @Override
    public void eliminar(int id){
        usuarioDAO.delete(id);
    }

    @Override
    public Doctor buscarPorDoctor(int idDoctor) {
        return doctorDAO.findById(idDoctor);
    }

    @Override
    public List<Usuario> buscarPorDni(String dni){
        return usuarioDAO.findByDni(dni);
    }

    @Override
    public void guardarUsuarioActual(Usuario usuario){
        
        this.usuarioActual = usuario;

    }

    @Override
    public Usuario obtenerUsuarioActual(){

        return this.usuarioActual;

    }

    @Override
    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    // VALIDACIONES

    @Override
    public String validarDatosRegistro(String correo, String contrasena, String rol, int idDoctor) {

        Doctor doctor = doctorDAO.findById(idDoctor);
        Usuario usuario = new Usuario(correo,contrasena,rol,doctor);

        String error = validacionesGenerales(usuario);
        if(error != null) {
            return error;
        } else if (!usuarioDAO.findByDni(usuario.getCorreo()).isEmpty()){

            return "Ya hay un usuario registrado con ese correo";

        }

        return null;
    }

    @Override
    public String validarDatosEdicion(String correo, String contrasena, String rol, int idDoctor){

        Doctor doctor = doctorDAO.findById(idDoctor);
        Usuario usuario = new Usuario(correo,contrasena,rol,doctor);

        String error = validacionesGenerales(usuario);
        if(error != null) {
            return error;
        }

        return null;
    }

    @Override
    public String validarUsuario( String correo, String contrasena) {

        Usuario user = usuarioDAO.findByCorreo(correo);
        if(user != null){

            if(user.getContrasena().equals(contrasena)){
                return null;
            }
           
            return "La contraseña es incorrecta";

        }

        return "El correo es incorrecto";
    }

    public String validacionesGenerales(Usuario usuario) {

        if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {

            return "El correo del usuario es obligatorio";

        } else if (usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()) {

            return "La contraseña es obligatoria";

        } else if (usuario.getDoctor() == null ) {

            return "El id del doctor es obligatorio";

        } 
        
        return null;
    }
}
