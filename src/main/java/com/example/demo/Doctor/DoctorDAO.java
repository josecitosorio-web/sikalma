package com.example.demo.Doctor;

import java.util.List;

public interface DoctorDAO {
    void save(Doctor d);
    List<Doctor> findAll();
    Doctor findById(int id);
    Doctor findDoctor(String dni);
    void update(Doctor d);
    void delete(int id);
    List<Doctor> findByDni(String dni);
    List<Doctor> findByCorreo(String correo);
}