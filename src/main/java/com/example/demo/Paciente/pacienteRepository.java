package com.example.demo.Paciente;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class pacienteRepository implements PacienteDAO {

    private List<Paciente> lista = new ArrayList<>();
    private int contador =1;

    @Override
    public void save(Paciente p){
        p.setId(contador ++);
        lista.add(p);
    }

    @Override
    public List<Paciente> findAll() {
        return lista;
    }

    @Override
    public Paciente findById(int id) {
        return lista.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Paciente findPaciente(String dni) {
        return lista.stream()
                .filter(p -> p.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Paciente p) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == p.getId()) {
                lista.set(i, p);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Paciente> findByDni(String dni) {
        return lista.stream()
                .filter(p -> p.getDni().equals(dni))
                .collect(Collectors.toList());
    }



}
