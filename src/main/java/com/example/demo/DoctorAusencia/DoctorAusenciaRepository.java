package com.example.demo.DoctorAusencia;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAusenciaRepository extends JpaRepository<DoctorAusenciaEntity, Long> {

    List<DoctorAusenciaEntity> findByDoctorId(Long id);

    boolean existsByDoctorIdAndFecha(Long doctorId, LocalDate fecha);

    @Query("SELECT COUNT(a) FROM doctor_ausencia a WHERE a.doctor.id = :doctorId AND MONTH(a.fecha) = :mes AND YEAR(a.fecha) = :anio")
    int contarPorDoctorYMes(@Param("doctorId") Long doctorId, @Param("mes") int mes, @Param("anio") int anio);

}
