package com.example.demo.Paciente;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

   
    Optional<PacienteEntity> findByDni(String dni);

    List<PacienteEntity> findAllByDni(String dni);

}
