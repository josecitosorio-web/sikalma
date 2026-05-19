package com.example.demo.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> obtenerTodos();
    void agregar(Doctor doctor);
    Doctor buscarPorId(int id);
    Doctor buscarDoctor(String dni);
    void actualizar(Doctor doctor);
    void eliminar(int id);
    List<Doctor> buscarPorDni(String dni);


    String validarDatosRegistro(Doctor doctor);
    String validarDatosEdicion(Doctor doctor);
}