package com.example.demo.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public void agregar(Servicio s) {
        servicioRepository.save(s);
    }

    @Override
    public List<Servicio> listar() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio buscarPorId(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public void actualizar(Servicio s) {
        servicioRepository.save(s);
    }

    @Override
    public void eliminar(Long id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public List<Servicio> buscarPorNombre(String nombre) {
        return servicioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // validaciones

    @Override
    public String validarDatosRegistro(Servicio servicio){

        String error = validacionesGenerales(servicio);

        if(error != null ){

            return error;

        }else if(!servicioRepository.findByNombreContainingIgnoreCase(servicio.getNombre()).isEmpty()){

            return "Ya existe un servicio con ese nombre";

        }
        
        return null;
        
    }

    @Override
    public String validarDatosEdicion(Servicio servicio){

        String error = validacionesGenerales(servicio);

        if(error != null ){

            return error;

        }
        
        return null;
    }
 
    public String validacionesGenerales(Servicio servicio) {

        if(servicio.getNombre() == null || servicio.getNombre().trim().isEmpty()){
            
            return "El nombre del servicio es obligatorio";

        }else if(servicio.getDescripcion() == null || servicio.getDescripcion().trim().isEmpty()){

            return "La descripcion del servicio es obligatorio";

        }else if(servicio.getCosto() <= 0){

            return "El costo debe ser mayor a S/ 0.00";

        }

        return null;

    }

}
