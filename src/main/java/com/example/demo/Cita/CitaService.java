package com.example.demo.Cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaService {

    List<Cita> listar();

    void guardar(Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha, LocalTime hora, String estado);

    Cita buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(Long id ,Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha, LocalTime hora, String estado);

    List<Cita> buscarCitaPorPaciente( Long idPaciente);


    // validaciones 
    String validarDatosRegistro(Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha, LocalTime hora);

    String validarDatosEdicion(Long id, Long pacienteId, Long doctorId, Long servicioId, LocalDate fecha, LocalTime hora);

    void cambiarEstado(Long id, String estado);

    boolean existeCitaDoctor(Long doctorId, LocalDate fecha, LocalTime hora);



    String validarCitasExistentesPaciente(Long idPaciente);

    String validarCitasExistentesDoctor(Long idDoctor);

    String validarCitasExistentesServicio(Long idServicio);
}