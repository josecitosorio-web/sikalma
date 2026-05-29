package com.example.demo.Cita;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<CitaEntity, Long> {

    List<CitaEntity> findByPacienteId(Long idPaciente);

    List<CitaEntity> findByDoctorId(Long idDoctor);

    List<CitaEntity> findByServicioId(Long idServicio);

    boolean existsByDoctorIdAndFechaAndHora(Long doctorId, LocalDate fecha, LocalTime hora);

    @Modifying
    @Transactional
    @Query("UPDATE cita c SET c.estado = :estado WHERE c.id = :id")
    void cambiarEstado(@Param("id") Long id, @Param("estado") String estado);


    @Query("SELECT COUNT(c) > 0 FROM cita c WHERE c.doctor.id = :doctorId " +
           "AND c.fecha = :fecha AND c.hora = :hora AND c.id <> :citaId")
           
    boolean existeCitaDoctorExcluyendo(
        @Param("doctorId") Long doctorId,
        @Param("fecha") LocalDate fecha,
        @Param("hora") LocalTime hora,
        @Param("citaId") Long citaId
    );
}