package com.example.demo.Paciente;

import java.util.List;

public interface PacienteService {

    void agregar(Paciente paciente);

    List<Paciente> listar();

    Paciente buscarPorId(int id);

    Paciente buscarPaciente(String dni);

    void actualizar(Paciente paciente);

    void eliminar(int id);

    List<Paciente> buscarPorDni(String dni);

    //validaciones

    String validarDatosRegistro(Paciente paciente);

    String validarDatosEdicion(Paciente paciente);


}
