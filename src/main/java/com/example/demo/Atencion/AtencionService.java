package com.example.demo.Atencion;

import java.time.LocalTime;
import java.util.List;


public interface AtencionService {

    List<Atencion> obtenerTodos();

    void agregar(int CitaId , LocalTime horaInicio, LocalTime horaFin , String diagnostico, String tratamiento , String estado );

    Atencion buscarPorId(int id);

    void actualizar(int id , int citaId , LocalTime horaInicio, LocalTime horaFin , String diagnostico, String tratamiento , String estado);
    
    void eliminar(int id);

    //validaciones

     String validarDatosRegistro(Atencion atencion);

    String validarDatosEdicion(Atencion atencion);
}
