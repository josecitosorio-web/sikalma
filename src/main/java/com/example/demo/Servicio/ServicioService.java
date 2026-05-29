package com.example.demo.Servicio;

import java.util.List;

public interface ServicioService {

    void agregar(Servicio servicio);

    List<Servicio> listar();

    Servicio buscarPorId(Long id);

    void actualizar(Servicio servicio);

    List<Servicio> buscarPorNombre(String nombre);

    void cambiarEstado(Long id, Boolean estado);

    //validaciones

    String validarDatosRegistro(Servicio servicio);

    String validarDatosEdicion(Servicio servicio);

}
