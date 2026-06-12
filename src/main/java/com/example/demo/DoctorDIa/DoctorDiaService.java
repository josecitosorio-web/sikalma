package com.example.demo.DoctorDIa;

import java.time.DayOfWeek;
import java.util.List;

public interface DoctorDiaService {

    List<DayOfWeek> obtenerDiasPorDoctor(Long idDOctor);

    void agregar(Long idDoctor, List<DayOfWeek> diasLaborales);

    void editar(Long idDoctor, List<DayOfWeek> diasLaborales);

    String validarDatos(List<DayOfWeek> diasLaborales);
   

}
