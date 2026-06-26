package com.example.demo.Usuario;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Doctor.Doctor;
import com.example.demo.Doctor.DoctorAdapter;
import com.example.demo.Doctor.DoctorEntity;
import com.example.demo.Doctor.DoctorRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    

    private UsuarioRepository usuarioRepository;
    private DoctorRepository doctorRepository;

    public UsuarioServiceImpl (UsuarioRepository usuarioRepository, DoctorRepository doctorRepository){

        this.usuarioRepository = usuarioRepository;
        this.doctorRepository = doctorRepository;

    }

    private Usuario usuarioActual;
    

    @Override
    public void agregar(String correo, String contrasena, String rol, Long idDoctor) {

        DoctorEntity doctor = doctorRepository.findById(idDoctor).orElse(null);

        UsuarioEntity usuario = new UsuarioEntity(correo,contrasena,rol,doctor);

        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> Listar() {
        return UsuarioAdapter.toModelList(usuarioRepository.findAll()) ;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return UsuarioAdapter.toModel(usuarioRepository.findById(id).orElse(null)) ;
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return UsuarioAdapter.toModel(usuarioRepository.findByCorreo(correo).orElse(null)) ;
    }

    @Override
    public void actualizar(Long idUsuario, String correo, String contrasena, String rol, Long idDoctor) {

        Doctor doctor = DoctorAdapter.toModel(doctorRepository.findById(idDoctor).orElse(null)) ;

        Usuario usuario = new Usuario(correo, contrasena,rol,doctor);
        usuario.setId(idUsuario);

        usuarioRepository.save(UsuarioAdapter.toEntity(usuario));
    }

    @Override
    public void eliminar(Long id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public Doctor buscarPorDoctor(Long idDoctor) {
        return DoctorAdapter.toModel(doctorRepository.findById(idDoctor).orElse(null));
    }

    @Override
    public List<Usuario> buscarPorDni(String dni){
        return UsuarioAdapter.toModelList(usuarioRepository.findByDoctorDni(dni));
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

        Doctor doctor = DoctorAdapter.toModel(doctorRepository.findById(idDoctor).orElse(null));
        Usuario usuario = new Usuario(correo,contrasena,rol,doctor);

        String error = validacionesGenerales(usuario);

        if(error != null) {

            return error;

        } else if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()){

            return "Ya hay un usuario registrado con ese correo";

        } else if (usuarioRepository.findByDoctorId(idDoctor) != null){

            return "Este doctor ya tiene un usuario ascociado";

        }

        return null;
    }

    @Override
    public String validarDatosEdicion(String correo, String contrasena, String rol, Long idDoctor){

        Doctor doctor = DoctorAdapter.toModel(doctorRepository.findById(idDoctor).orElse(null));
        Usuario usuario = new Usuario(correo,contrasena,rol,doctor);

        String error = validacionesGenerales(usuario);
        if(error != null) {
            return error;
        }

        return null;
    }

    @Override
    public String validarUsuario( String correo, String contrasena) {

        Usuario user = UsuarioAdapter.toModel(usuarioRepository.findByCorreo(correo).orElse(null));
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
