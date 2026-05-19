package com.example.demo.Paciente;

import java.util.List;

public interface PacienteDAO {

    void save(Paciente p);

    List<Paciente> findAll();

    Paciente findById(int id);

    Paciente findPaciente(String dni);

    void update(Paciente p);

    void delete(int id);

    List<Paciente> findByDni(String dni);

}
