package com.example.demo.HistorialCita;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface HistorialCitaRepository extends JpaRepository<HistorialCitaEntity,Long> {

    List<HistorialCitaEntity> findByCitaEntityId(Long citaId);
    
}
