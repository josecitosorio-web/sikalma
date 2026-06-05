package com.example.demo.HistorialCita;

import java.util.List;

public interface HistorialCitaService {
    
    void registrarHistorial(HistorialCita historial);

    List<HistorialCita> obtenerPorCita(Long citaId);

}
