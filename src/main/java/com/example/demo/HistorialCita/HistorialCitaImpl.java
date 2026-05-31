package com.example.demo.HistorialCita;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialCitaImpl implements HistorialCitaService {

    @Autowired
    private HistorialCitaRepository historialCitaRepository;

    @Override
    public void registrarHistorial(HistorialCita historial) {

        historial.setFechaRegistro(LocalDateTime.now());

        historialCitaRepository.save(HistorialCitaAdapter.toEntity(historial));

    }

    @Override
    public List<HistorialCita> obtenerPorCita(Long citaId) {

        return HistorialCitaAdapter.toModelList(historialCitaRepository.findByCitaEntityId(citaId)) ;
        
    }

    
    
}
