package com.example.demo.Atencion;

import java.time.LocalTime;
import java.util.List;


public interface AtencionService {

    List<Atencion> obtenerTodos();

    void agregar(Long CitaId , LocalTime horaInicio, LocalTime horaFin , String diagnostico, String tratamiento , String estado );

    Atencion buscarPorId(Long id);

    void actualizar(Long id , Long citaId , LocalTime horaInicio, LocalTime horaFin , String diagnostico, String tratamiento , String estado);

    //validaciones

     String validarDatosRegistro(Atencion atencion);

    String validarDatosEdicion(Atencion atencion);
}
