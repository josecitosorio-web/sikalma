package com.example.demo.DoctorAusencia;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.Doctor.DoctorAdapter;

public class DoctorAusenciaAdapter {
    

    public static DoctorAusencia toModel(DoctorAusenciaEntity entity) {
        if(entity == null) return null;

        DoctorAusencia model = new DoctorAusencia();
        model.setId(entity.getId());
        model.setFecha(entity.getFecha());
        model.setMotivo(entity.getMotivo());
        model.setDoctor(DoctorAdapter.toModel(entity.getDoctor()));


        return model;

    }   
    
    public static DoctorAusenciaEntity toEntity(DoctorAusencia model) {
        if(model == null) return null;

        DoctorAusenciaEntity entity = new DoctorAusenciaEntity();
        entity.setId(model.getId());
        entity.setFecha(model.getFecha());
        entity.setMotivo(model.getMotivo());
        entity.setDoctor(DoctorAdapter.toEntity(model.getDoctor()));

        return entity;
    }


    public static List<DoctorAusencia> toModelList(List<DoctorAusenciaEntity> entities) {

        return entities.stream()
                .map(DoctorAusenciaAdapter::toModel)
                .collect(Collectors.toList());

    }

}
