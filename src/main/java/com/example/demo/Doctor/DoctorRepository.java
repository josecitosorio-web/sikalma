package com.example.demo.Doctor;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByDni (String dni);

    Optional<DoctorEntity> findByCorreo(String correo);

    List<DoctorEntity> findAllByDni(String dni);

    List<DoctorEntity> findByEstado(Boolean estado);
}