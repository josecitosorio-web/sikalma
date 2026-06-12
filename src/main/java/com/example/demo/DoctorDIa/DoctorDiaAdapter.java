package com.example.demo.DoctorDIa;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.Doctor.DoctorAdapter;

public class DoctorDiaAdapter {

    public static DoctorDia toModel(DoctorDiaEntity entity) {
        if (entity == null) return null;

        DoctorDia model = new DoctorDia();
        model.setID(entity.getId());
        model.setDiaSemana(entity.getDiaSemana());
        model.setDoctor(DoctorAdapter.toModel(entity.getDoctor()));
        

        return model;

    }

    public static DoctorDiaEntity toEntity(DoctorDia model) {
        if(model == null) return null;

        DoctorDiaEntity entity = new DoctorDiaEntity();
        entity.setID(model.getId());
        entity.setDiaSemana(model.getDiaSemana());
        entity.setDoctor(DoctorAdapter.toEntity(model.getDoctor()));

        return entity;
    }

    public static List<DoctorDia> toModelList(List<DoctorDiaEntity> entities){
        return entities.stream()
                .map(DoctorDiaAdapter::toModel)
                .collect(Collectors.toList());
    }

    public static List<DoctorDiaEntity> toEntityList(List<DoctorDia> modeList){
        return modeList.stream()
                .map(DoctorDiaAdapter::toEntity)
                .collect(Collectors.toList());
    }
    
}
