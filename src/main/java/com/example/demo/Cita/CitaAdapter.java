package com.example.demo.Cita;


import com.example.demo.Doctor.DoctorAdapter;
import com.example.demo.Paciente.PacienteAdapter;
import com.example.demo.Servicio.ServicioAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class CitaAdapter {

    // Entity → Model
    public static Cita toModel(CitaEntity entity) {
        if (entity == null) return null;

        Cita model = new Cita();
        model.setId(entity.getId());
        model.setFecha(entity.getFecha());
        model.setHora(entity.getHora());
        model.setEstado(entity.getEstado());

        model.setPaciente(PacienteAdapter.toModel(entity.getPaciente()));
        model.setDoctor(DoctorAdapter.toModel(entity.getDoctor()));
        model.setServicio(ServicioAdapter.toModel(entity.getServicio()));

        // Atencion la omitimos para evitar referencia circular
        // Cita → Atencion → Cita → ...

        return model;
    }

    // Model → Entity
    public static CitaEntity toEntity(Cita model) {
        if (model == null) return null;

        CitaEntity entity = new CitaEntity();
        entity.setId(model.getId());
        entity.setFecha(model.getFecha());
        entity.setHora(model.getHora());
        entity.setEstado(model.getEstado());

        entity.setPaciente(PacienteAdapter.toEntity(model.getPaciente()));
        entity.setDoctor(DoctorAdapter.toEntity(model.getDoctor()));
        entity.setServicio(ServicioAdapter.toEntity(model.getServicio()));

        return entity;
    }

    // Lista Entity → Lista Model
    public static List<Cita> toModelList(List<CitaEntity> entities) {
        return entities.stream()
                .map(CitaAdapter::toModel)
                .collect(Collectors.toList());
    }
}