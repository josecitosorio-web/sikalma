package com.example.demo.DoctorAusencia;

import java.time.LocalDate;
import java.util.List;


public interface DoctorAusenciaService {
    
    void agregar(LocalDate fecha, String motivo, Long idDoctor);
    void actualizar(LocalDate fecha, String motivo, Long idDoctor, Long ausenciaId);
    List<DoctorAusencia> buscarPorIdDoctor(Long idDoctor);
    DoctorAusencia buscarAusencia(Long id);
    void eliminar(Long id);
    DoctorAusencia ausenciaTemporal(LocalDate fecha, String motivo, Long idDoctor);
    boolean existeAusencia(Long doctorId, LocalDate fecha);


    String validaciones(LocalDate fecha, String motivo, Long idDoctor);
    String validacionesEdicion(LocalDate fecha, String motivo, Long idDoctor);


}
