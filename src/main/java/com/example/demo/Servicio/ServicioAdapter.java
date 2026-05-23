package com.example.demo.Servicio;

import java.util.List;
import java.util.stream.Collectors;

public class ServicioAdapter {

    // Entity → Model
    public static Servicio toModel(ServicioEntity entity) {
        if (entity == null) return null;

        Servicio model = new Servicio();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());
        model.setDescripcion(entity.getDescripcion());
        model.setCosto(entity.getCosto());

        return model;
    }

    // Model → Entity
    public static ServicioEntity toEntity(Servicio model) {
        if (model == null) return null;

        ServicioEntity entity = new ServicioEntity();
        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setDescripcion(model.getDescripcion());
        entity.setCosto(model.getCosto());

        return entity;
    }

    // Lista Entity → Lista Model
    public static List<Servicio> toModelList(List<ServicioEntity> entities) {
        return entities.stream()
                .map(ServicioAdapter::toModel)
                .collect(Collectors.toList());
    }
}