package com.example.demo.Servicio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


@Repository
public interface ServicioRepository extends JpaRepository<ServicioEntity, Long> {

    List<ServicioEntity> findByNombreContainingIgnoreCase(String nombre);


}
