package com.example.demo.Cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaDAO {

    List<CitaEntity> listar();

    void guardar(CitaEntity cita);

    CitaEntity buscarPorId(int id);

    void eliminar(int id);

    void actualizar(CitaEntity cita);

    List<CitaEntity> buscarPorPaciente(int idPaciente);

    List<CitaEntity> buscarPorDoctor(int idDoctor);

    List<CitaEntity> buscarPorServicio(int idServicio);

    void cambiarEstado(int id, String estado);

    boolean existeCitaDoctor(int doctorId, LocalDate fecha, LocalTime hora);

    boolean existeCitaDoctorExcluyendo(int doctorId, LocalDate fecha, LocalTime hora, int citaId);

}