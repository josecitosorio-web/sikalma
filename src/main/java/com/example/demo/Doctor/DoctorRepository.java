package com.example.demo.Doctor;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByDni (String dni);

    Optional<Doctor> findByCorreo(String correo);

    List<Doctor> findAllByDni(String dni);
}