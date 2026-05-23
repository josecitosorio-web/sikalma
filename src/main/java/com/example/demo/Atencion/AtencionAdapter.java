package com.example.demo.Atencion;

import java.util.List;
import java.util.stream.Collectors;

public class AtencionAdapter {

    public static Atencion toModel(AtencionEntity entity) {
        if (entity == null) return null;
        
        Atencion model = new Atencion();
        model.setId(entity.getId());
        model.setHoraInicio(entity.getHoraInicio());
        model.setHoraFin(entity.getHoraFin());
        model.setDiagnostico(entity.getDiagnostico());
        model.setTratamiento(entity.getTratamiento());
        model.setEstado(entity.getEstado());
        // Cita omitida para evitar referencia circular

        return model;
    }

    public static AtencionEntity toEntity(Atencion model) {
        if (model == null) return null;

        AtencionEntity entity = new AtencionEntity();
        entity.setId(model.getId());
        entity.setHoraInicio(model.getHoraInicio());
        entity.setHoraFin(model.getHoraFin());
        entity.setDiagnostico(model.getDiagnostico());
        entity.setTratamiento(model.getTratamiento());
        entity.setEstado(model.getEstado());
        // Cita omitida para evitar referencia circular

        return entity;
    }

    public static List<Atencion> toModelList(List<AtencionEntity> entities) {
        return entities.stream()
                .map(AtencionAdapter::toModel)
                .collect(Collectors.toList());
    }
}