package com.example.demo.Usuario;

import java.util.List;

public interface UsuarioDAO {
    
    void save(Usuario usuario);

    List<Usuario> findAll();

    Usuario findById(int id);

    void update(Usuario usuario);

    void delete(int id);

    List<Usuario> findByDni(String dni);

    Usuario findByCorreo(String correo);

}
