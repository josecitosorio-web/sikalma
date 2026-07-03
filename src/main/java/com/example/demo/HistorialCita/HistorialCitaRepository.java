package com.example.demo.HistorialCita;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Cita.CitaEntity;

@Repository
public interface HistorialCitaRepository extends JpaRepository<HistorialCitaEntity, Long> {

    List<HistorialCitaEntity> findByCitaEntityId(Long citaId);

    @Query("SELECT c FROM HistorialCita h JOIN h.citaEntity c WHERE c.doctor.id = :doctorId AND h.estadoNuevo = 'Cancelado' AND h.fechaAnterior = :fecha AND h.tipoCambio = 'INDISPONIBILIDAD DEL DOCTOR'")
    List<CitaEntity> findCitasCanceladasPorIndisponibilidad(
            @Param("doctorId") Long doctorId,
            @Param("fecha") LocalDate fecha);

}
