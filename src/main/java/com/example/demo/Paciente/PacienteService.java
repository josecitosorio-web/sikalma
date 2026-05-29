package com.example.demo.Paciente;

import java.util.List;

public interface PacienteService {

    void agregar(Paciente paciente);

    List<Paciente> listar();

    Paciente buscarPorId(Long id);

    Paciente buscarPaciente(String dni);

    void actualizar(Paciente paciente);

    List<Paciente> buscarPorDni(String dni);

    //validaciones

    String validarDatosRegistro(Paciente paciente);

    String validarDatosEdicion(Paciente paciente);


}
