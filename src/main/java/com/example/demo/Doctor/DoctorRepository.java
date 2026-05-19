package com.example.demo.Doctor;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DoctorRepository implements DoctorDAO {

    private List<Doctor> lista = new ArrayList<>();
    private int contador = 1;

    @Override
    public void save(Doctor d) {
        d.setId(contador++);
        lista.add(d);
    }

    @Override
    public List<Doctor> findAll() {
        return lista;
    }

    @Override
    public Doctor findById(int id) {
        return lista.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Doctor findDoctor(String dni) {
        return lista.stream()
                .filter(d -> d.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Doctor d) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == d.getId()) {
                lista.set(i, d);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        lista.removeIf(d -> d.getId() == id);
    }

    @Override
    public List<Doctor> findByDni(String dni) {
        return lista.stream()
                .filter(d -> d.getDni().equals(dni))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findByCorreo(String correo){
        return lista.stream()
                .filter(d -> d.getCorreo().equals(correo))
                .collect(Collectors.toList());
    }
}