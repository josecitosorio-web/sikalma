package com.example.demo.Paciente;

import java.util.List;
import java.util.stream.Collectors;

public class PacienteAdapter {

    // Entity → Model
    public static Paciente toModel(PacienteEntity entity) {
        if (entity == null) return null;

        Paciente model = new Paciente();
        model.setId(entity.getId());
        model.setNombres(entity.getNombres());
        model.setTipoDocumento(entity.getTipoDocumento());
        model.setNumeroDocumento(entity.getNumeroDocumento());
        model.setTelefono(entity.getTelefono());
        model.setFechaNacimiento(entity.getFechaNacimiento());

        return model;
    }

    // Model → Entity
    public static PacienteEntity toEntity(Paciente model) {
        if (model == null) return null;

        PacienteEntity entity = new PacienteEntity();
        entity.setId(model.getId());
        entity.setNombres(model.getNombres());
        entity.setTipoDocumento(model.getTipoDocumento());
        entity.setNumeroDocumento(model.getNumeroDocumento());
        entity.setTelefono(model.getTelefono());
        entity.setFechaNacimiento(model.getFechaNacimiento());

        return entity;
    }

    // Lista Entity → Lista Model
    public static List<Paciente> toModelList(List<PacienteEntity> entities) {
        return entities.stream()
                .map(PacienteAdapter::toModel)
                .collect(Collectors.toList());
    }
}