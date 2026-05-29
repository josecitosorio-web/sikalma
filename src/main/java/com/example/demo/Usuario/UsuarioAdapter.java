package com.example.demo.Usuario;

import com.example.demo.Doctor.DoctorAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioAdapter {

    // Entity → Model
    public static Usuario toModel(UsuarioEntity entity) {
        if (entity == null) return null;

        Usuario model = new Usuario();
        model.setId(entity.getId());
        model.setCorreo(entity.getCorreo());
        model.setContrasena(entity.getContrasena());
        model.setRol(entity.getRol());
        model.setDoctor(DoctorAdapter.toModel(entity.getDoctor()));

        return model;
    }

    // Model → Entity
    public static UsuarioEntity toEntity(Usuario model) {
        if (model == null) return null;

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(model.getId());
        entity.setCorreo(model.getCorreo());
        entity.setContrasena(model.getContrasena());
        entity.setRol(model.getRol());
        entity.setDoctor(DoctorAdapter.toEntity(model.getDoctor()));

        return entity;
    }

    // Lista Entity → Lista Model
    public static List<Usuario> toModelList(List<UsuarioEntity> entities) {
        return entities.stream()
                .map(UsuarioAdapter::toModel)
                .collect(Collectors.toList());
    }
}