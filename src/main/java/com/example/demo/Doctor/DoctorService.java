package com.example.demo.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> obtenerTodos();
    Long agregar(Doctor doctor);
    Doctor buscarPorId(Long id);
    Doctor buscarDoctor(String dni);
    void actualizar(Doctor doctor);
    void eliminar(Long id);
    List<Doctor> buscarPorDni(String dni);
    void cambiarEstado(Long id, Boolean estado);
    List<Doctor> buscarPorEstado(Boolean estado);

    String validarDatosRegistro(Doctor doctor);
    String validarDatosEdicion(Doctor doctor);
}