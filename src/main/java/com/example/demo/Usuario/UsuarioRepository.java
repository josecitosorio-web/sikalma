package com.example.demo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;


import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByCorreo(String correo);

    List<UsuarioEntity> findByDoctorDni(String dni);
    
}
