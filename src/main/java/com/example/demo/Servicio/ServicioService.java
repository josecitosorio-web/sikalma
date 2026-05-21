package com.example.demo.Servicio;

import java.util.List;

public interface ServicioService {

    void agregar(Servicio servicio);

    List<Servicio> listar();

    Servicio buscarPorId(Long id);

    void actualizar(Servicio servicio);

    void eliminar(Long id);

    List<Servicio> buscarPorNombre(String nombre);

    //validaciones

    String validarDatosRegistro(Servicio servicio);

    String validarDatosEdicion(Servicio servicio);

}
