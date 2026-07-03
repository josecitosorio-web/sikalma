package com.example.demo.HistorialCita;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;


import com.example.demo.Cita.CitaEntity;

@Service
public class HistorialCitaImpl implements HistorialCitaService {


    private HistorialCitaRepository historialCitaRepository;

    public HistorialCitaImpl (HistorialCitaRepository historialCitaRepository) {
        this.historialCitaRepository = historialCitaRepository;
    }

    @Override
    public void registrarHistorial(HistorialCita historial) {

        historial.setFechaRegistro(LocalDateTime.now());

        historialCitaRepository.save(HistorialCitaAdapter.toEntity(historial));

    }

    @Override
    public List<HistorialCita> obtenerPorCita(Long citaId) {

        return HistorialCitaAdapter.toModelList(historialCitaRepository.findByCitaEntityId(citaId)) ;
        
    }

    @Override
    public List<CitaEntity> obtenerCitasCanceladasFecha(Long doctorId, LocalDate fecha) {

        return historialCitaRepository.findCitasCanceladasPorIndisponibilidad(doctorId, fecha);

    }

    
    
}
