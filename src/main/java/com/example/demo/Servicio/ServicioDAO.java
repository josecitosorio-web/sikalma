package com.example.demo.Servicio;

import java.util.List;

public interface ServicioDAO {

    void save(ServicioEntity s);

    List<ServicioEntity> findAll();

    ServicioEntity findById(int id);

    void update(ServicioEntity s);

    void delete(int id);

    List<ServicioEntity> findByNombre(String nombre);

}
