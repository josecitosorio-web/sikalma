package com.example.demo.Doctor;

import java.util.List;

public interface DoctorDAO {
    void save(DoctorEntity d);
    List<DoctorEntity> findAll();
    DoctorEntity findById(int id);
    DoctorEntity findDoctor(String dni);
    void update(DoctorEntity d);
    void delete(int id);
    List<DoctorEntity> findByDni(String dni);
    List<DoctorEntity> findByCorreo(String correo);
}