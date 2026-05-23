package com.example.demo.Usuario;

import java.util.List;

public interface UsuarioDAO {
    
    void save(UsuarioEntity usuario);

    List<UsuarioEntity> findAll();

    UsuarioEntity findById(int id);

    void update(UsuarioEntity usuario);

    void delete(int id);

    List<UsuarioEntity> findByDni(String dni);

    UsuarioEntity findByCorreo(String correo);

}
