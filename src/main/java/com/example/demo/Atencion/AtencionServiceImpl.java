package com.example.demo.Atencion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Cita.CitaService;
import com.example.demo.Cita.Cita;


import java.time.LocalTime;
import java.util.List;

@Service
public class AtencionServiceImpl implements AtencionService {


    @Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private CitaService citaService;

    @Override
    public List<Atencion> obtenerTodos() {
        return atencionRepository.findAll();
    }

    @Override
    public void agregar(Long CitaId , LocalTime horaInicio, LocalTime horaFin , String diagnostico, String tratamiento , String estado) {

        Cita c = citaService.buscarPorId(CitaId);

        Atencion a = new Atencion(c, horaInicio,horaFin,diagnostico,tratamiento,estado);

        atencionRepository.save(a);
    }

    @Override
    public Atencion buscarPorId(Long id) {
        return atencionRepository.findById(id).orElse(null);
    }

    @Override
    public void actualizar(Long id, Long citaId , LocalTime horaInicio, LocalTime horaFin , String diagnostico, String tratamiento , String estado) {

        Cita c = citaService.buscarPorId(citaId);

        Atencion a = new Atencion(c, horaInicio,horaFin,diagnostico,tratamiento,estado);
        a.setId(id);

        atencionRepository.save(a);
    }

    @Override
    public void eliminar(Long id) {
        atencionRepository.deleteById(id);
    }


     //validaciones 
    @Override
    public String validarDatosRegistro(Atencion atencion) {

        String error = validacionesGenerales(atencion);

        if(error != null ){

            return error;

        }
        
        return null;
    }


    @Override
    public String validarDatosEdicion(Atencion atencion) {

        String error = validacionesGenerales(atencion);

        if(error != null ){

            return error;
            
        }

        return null;
    }

     public String validacionesGenerales(Atencion atencion) {

        if(atencion.getDiagnostico() == null || atencion.getDiagnostico().trim().isEmpty()){
            
            return "El diagnostico es obligatorio";

        }else if(atencion.getTratamiento() == null || atencion.getTratamiento().trim().isEmpty()){

            return "El tratamiento es obligatorio";

        }else if(!atencion.getHoraFin().isAfter(atencion.getHoraInicio())){

            return "La hora fin debe ser mayor a la hora de inicio";

        }

        return null;

    }

}
