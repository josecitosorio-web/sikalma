package com.example.demo.Paciente;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

   
    Optional<Paciente> findByDni(String dni);

    List<Paciente> findAllByDni(String dni);

}
