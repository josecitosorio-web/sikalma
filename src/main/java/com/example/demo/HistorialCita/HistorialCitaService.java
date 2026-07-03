package com.example.demo.HistorialCita;

import java.time.LocalDate;
import java.util.List;


import com.example.demo.Cita.CitaEntity;

public interface HistorialCitaService {
    
    void registrarHistorial(HistorialCita historial);

    List<HistorialCita> obtenerPorCita(Long citaId);

    List<CitaEntity> obtenerCitasCanceladasFecha(Long doctorId, LocalDate fecha);

}
