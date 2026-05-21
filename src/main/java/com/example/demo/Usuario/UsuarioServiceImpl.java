package com.example.demo.Usuario;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Doctor.Doctor;
import com.example.demo.Doctor.DoctorRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    private Usuario usuarioActual;
    

    @Override
    public void agregar(String correo, String contrasena, String rol, Long idDoctor) {

        Doctor doctor = doctorRepository.findById(idDoctor).orElse(null);

        Usuario usuario = new Usuario(correo,contrasena,rol,doctor);

        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> Listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(usuarioActual);
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).orElse(usuarioActual);
    }

    @Override
    public void actualizar(Long idUsuario, String correo, String contrasena, String rol, Long idDoctor) {

        Doctor doctor = doctorRepository.findById(idDoctor).orElse(null);

        Usuario usuario = new Usuario(correo, contrasena,rol,doctor);
        usuario.setId(idUsuario);

        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public Doctor buscarPorDoctor(Long idDoctor) {
        return doctorRepository.findById(idDoctor).orElse(null);
    }

    @Override
    public List<Usuario> buscarPorDni(String dni){
        return usuarioRepository.findByDoctorDni(dni);
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
    public String validarDatosRegistro(String correo, String contrasena, String rol, Long idDoctor) {

        Doctor doctor = doctorRepository.findById(idDoctor).orElse(null);
        Usuario usuario = new Usuario(correo,contrasena,rol,doctor);

        String error = validacionesGenerales(usuario);
        if(error != null) {
            return error;
        } else if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()){

            return "Ya hay un usuario registrado con ese correo";

        }

        return null;
    }

    @Override
    public String validarDatosEdicion(String correo, String contrasena, String rol, Long idDoctor){

        Doctor doctor = doctorRepository.findById(idDoctor).orElse(null);
        Usuario usuario = new Usuario(correo,contrasena,rol,doctor);

        String error = validacionesGenerales(usuario);
        if(error != null) {
            return error;
        }

        return null;
    }

    @Override
    public String validarUsuario( String correo, String contrasena) {

        Usuario user = usuarioRepository.findByCorreo(correo).orElse(usuarioActual);
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
