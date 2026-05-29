package com.example.demo.Doctor;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.Servicio.ServicioAdapter;

public class DoctorAdapter {

    // Entity → Model
    public static Doctor toModel(DoctorEntity entity) {
        if (entity == null) return null;

        Doctor model = new Doctor();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());
        model.setDni(entity.getDni());
        model.setTelefono(entity.getTelefono());
        model.setCorreo(entity.getCorreo());
        model.setFechaNacimiento(entity.getFechaNacimiento());
        model.setHoraAtencionInicio(entity.getHoraAtencionInicio());
        model.setHoraAtencionFin(entity.getHoraAtencionFin());
        model.setServicio(ServicioAdapter.toModel(entity.getServicio()));

        return model;
    }

    // Model → Entity
    public static DoctorEntity toEntity(Doctor model) {
        if (model == null) return null;

        DoctorEntity entity = new DoctorEntity();
        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setDni(model.getDni());
        entity.setTelefono(model.getTelefono());
        entity.setCorreo(model.getCorreo());
        entity.setFechaNacimiento(model.getFechaNacimiento());
        entity.setHoraAtencionInicio(model.getHoraAtencionInicio());
        entity.setHoraAtencionFin(model.getHoraAtencionFin());
        entity.setServicio(ServicioAdapter.toEntity(model.getServicio()));

        return entity;
    }

    // Lista Entity → Lista Model
    public static List<Doctor> toModelList(List<DoctorEntity> entities) {
        return entities.stream()
                .map(DoctorAdapter::toModel)
                .collect(Collectors.toList());
    }
}