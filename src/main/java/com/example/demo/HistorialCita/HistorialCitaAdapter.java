package com.example.demo.HistorialCita;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.Cita.CitaAdapter;

public class HistorialCitaAdapter {
    

    public static HistorialCita toModel(HistorialCitaEntity entity) {
        if(entity == null) return null;

        HistorialCita model = new HistorialCita();
        model.setId(entity.getId());
        model.setCita(CitaAdapter.toModel(entity.getCitaEntity()));
        model.setFechaAnterior(entity.getFechaAnterior());
        model.setFechaNueva(entity.getFechaNueva());
        model.setHoraAnterior(entity.getHoraAnterior());
        model.setHoraNueva(entity.getHoraNueva());
        model.setDoctorAnterior(entity.getDoctorAnterior());
        model.setDoctorNuevo(entity.getDoctorNuevo());
        model.setEstadoAnterior(entity.getEstadoAnterior());
        model.setEstadoNuevo(entity.getEstadoNuevo());
        model.setFechaRegistro(entity.getFechaRegistro());
        model.setUsuarioResponsable(entity.getUsuarioResponsable());
        model.setTipoCambio(entity.getTipoCambio());

        return model;

    }

    public static HistorialCitaEntity toEntity(HistorialCita model) {
        if(model == null) return null;

        HistorialCitaEntity entity = new HistorialCitaEntity();
        entity.setId(model.getId());
        entity.setCitaEntity(CitaAdapter.toEntity(model.getCita()));
        entity.setFechaAnterior(model.getFechaAnterior());
        entity.setFechaNueva(model.getFechaNueva());
        entity.setHoraAnterior(model.getHoraAnterior());
        entity.setHoraNueva(model.getHoraNueva());
        entity.setDoctorAnterior(model.getDoctorAnterior());
        entity.setDoctorNuevo(model.getDoctorNuevo());
        entity.setEstadoAnterior(model.getEstadoAnterior());
        entity.setEstadoNuevo(model.getEstadoNuevo());
        entity.setFechaRegistro(model.getFechaRegistro());
        entity.setUsuarioResponsable(model.getUsuarioResponsable());
        entity.setTipoCambio(model.getTipoCambio());


        return entity;

    }

    public static List<HistorialCita> toModelList(List<HistorialCitaEntity> entities) {
        return entities.stream()
                .map(HistorialCitaAdapter::toModel)
                .collect(Collectors.toList());
    }

}
