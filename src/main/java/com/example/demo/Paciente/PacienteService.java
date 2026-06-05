package com.example.demo.Paciente;

import java.util.List;

public interface PacienteService {

    void agregar(Paciente paciente);

    List<Paciente> listar();

    Paciente buscarPorId(Long id);

    Paciente buscarPaciente(String numeroDocumento);

    void actualizar(Paciente paciente);

    List<Paciente> buscarPorNumeroDocumento(String numeroDocumento);

    //validaciones

    String validarDatosRegistro(Paciente paciente);

    String validarDatosEdicion(Paciente paciente);


}
