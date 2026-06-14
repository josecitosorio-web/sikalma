package com.example.demo.Cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaService {

    List<Cita> listar();

    void guardar(Long pacienteId, Long doctorId, LocalDate fecha, LocalTime hora, String estado);

    Cita buscarPorId(Long id);

    void actualizar(Long id ,Long pacienteId, Long doctorId, LocalDate fecha, LocalTime hora, String estado);

    List<Cita> buscarCitaPorPaciente( Long idPaciente);

    List<Cita> buscarCitasHoy(LocalDate fecha);

    long contarPorEstado(String estado);


    // validaciones 
    String validarDatosRegistro(Long pacienteId, Long doctorId,Long servicioId, LocalDate fecha, LocalTime hora);

    String validarDatosEdicion(Long id, Long pacienteId, Long doctorId,Long servicioId, LocalDate fecha, LocalTime hora);

    String validarDatosHorario(Long idPaciente, Long idServicio, Long idDoctor);

    void cambiarEstado(Long id, String estado);

    boolean existeCitaDoctor(Long doctorId, LocalDate fecha, LocalTime hora);



    String validarCitasExistentesPaciente(Long idPaciente);

    String validarCitasExistentesDoctor(Long idDoctor);


    // METRICAS

    List<String> obtenerCitasPorFecha();
    List<Long> obtenerCantidadPorFecha();

    List<String> obtenerEstadoPorCantidad();
    List<Long> obtenerCantidadPorEstado();

    List<String> obtenerIngresosPorFecha();
    List<Double> obtenerSumaDeIngresos();

    List<String> obtenerServicioPorCantidad();
    List<Long> obtenerCantidadPorServicio();

    List<String> obtenerDiaPorCantidad();
    List<Long> obtenerCantidadPorDia();
}