package com.example.demo.Doctor;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorAdapter {

    // Entity → Model
    public static Doctor toModel(DoctorEntity entity) {
        if (entity == null) return null;

        Doctor model = new Doctor();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());
        model.setDni(entity.getDni());
        model.setEspecialidad(entity.getEspecialidad());
        model.setTelefono(entity.getTelefono());
        model.setCorreo(entity.getCorreo());
        model.setFechaNacimiento(entity.getFechaNacimiento());

        return model;
    }

    // Model → Entity
    public static DoctorEntity toEntity(Doctor model) {
        if (model == null) return null;

        DoctorEntity entity = new DoctorEntity();
        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setDni(model.getDni());
        entity.setEspecialidad(model.getEspecialidad());
        entity.setTelefono(model.getTelefono());
        entity.setCorreo(model.getCorreo());
        entity.setFechaNacimiento(model.getFechaNacimiento());

        return entity;
    }

    // Lista Entity → Lista Model
    public static List<Doctor> toModelList(List<DoctorEntity> entities) {
        return entities.stream()
                .map(DoctorAdapter::toModel)
                .collect(Collectors.toList());
    }
}