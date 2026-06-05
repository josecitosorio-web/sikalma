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

        List<CitaEntity> findByFecha(LocalDate fecha);

        long countByEstado(String estado);

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
                        @Param("citaId") Long citaId);

        // PARA MÉTRICAS
        @Query("""
                            SELECT c.fecha, COUNT(c)
                            FROM cita c
                            GROUP BY c.fecha
                            ORDER BY c.fecha
                        """)
        List<Object[]> obtenerCitasPorFecha();

        @Query("SELECT c.estado AS estadoCita, COUNT(c) AS total " +
                        "FROM cita c " +
                        "GROUP BY c.estado " +
                        "ORDER BY total DESC")
        List<Object[]> contarPorEstado();

        @Query("SELECT c.fecha AS fechaCita, SUM(s.costo) AS ingresos " +
                        "FROM cita c " +
                        "JOIN c.doctor d " +
                        "JOIN d.servicio s " +
                        "WHERE c.estado = 'Atendido' " +
                        "GROUP BY c.fecha " +
                        "ORDER BY c.fecha")
        List<Object[]> ingresosPorDia();

        @Query("SELECT s.nombre AS servicio, COUNT(c) AS total " +
                        "FROM cita c " +
                        "JOIN c.doctor d " +
                        "JOIN d.servicio s " +
                        "GROUP BY s.nombre " +
                        "ORDER BY total DESC")
        List<Object[]> contarPorServicio();

        @Query("SELECT FUNCTION('DAYNAME', c.fecha) AS dia, COUNT(c) AS total " +
                        "FROM cita c " +
                        "GROUP BY FUNCTION('DAYNAME', c.fecha) " +
                        "ORDER BY total DESC")
        List<Object[]> contarPorDiaSemana();
}