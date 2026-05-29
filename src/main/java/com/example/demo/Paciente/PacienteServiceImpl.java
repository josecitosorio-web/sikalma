package com.example.demo.Paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    
    @Override
    public void agregar(Paciente p){

        pacienteRepository.save(PacienteAdapter.toEntity(p));
    }

    @Override
    public List<Paciente> listar() {
        return PacienteAdapter.toModelList(pacienteRepository.findAll());
    }

    @Override
    public Paciente buscarPorId(Long id){
        return PacienteAdapter.toModel(pacienteRepository.findById(id).orElse(null));
    }

    @Override
    public Paciente buscarPaciente(String dni){
        return PacienteAdapter.toModel(pacienteRepository.findByDni(dni).orElse(null));
    }

    @Override
    public void actualizar(Paciente p){
        pacienteRepository.save(PacienteAdapter.toEntity(p));
    }

    @Override
    public List<Paciente> buscarPorDni(String dni){
        return PacienteAdapter.toModelList(pacienteRepository.findAllByDni(dni)) ;
    }

    //validaciones 
    @Override
    public String validarDatosRegistro(Paciente paciente) {

        String error = validacionesGenerales(paciente);

        if(error != null ){

            return error;

        }else if(pacienteRepository.findByDni(paciente.getDni()).isPresent()){

            return "Ya existe un paciente con ese DNI";

        }
        
        return null;
    }


    @Override
    public String validarDatosEdicion(Paciente paciente) {

        String error = validacionesGenerales(paciente);

        if(error != null ){

            return error;
            
        }

        return null;
    }


    public String validacionesGenerales(Paciente paciente) {

        if(paciente.getNombres() == null || paciente.getNombres().trim().isEmpty()){
            
            return "El nombre del paciente es obligatorio";

        }else if(paciente.getDni() == null || paciente.getDni().trim().isEmpty()){

            return "El DNI del paciente es obligatorio";

        }else if(paciente.getTelefono() == null || paciente.getTelefono().trim().isEmpty()){

            return "El telefono del paciente es obligatorio";

        }else if(paciente.getFechaNacimiento() == null){

            return "La fecha de nacimiento es obligatorio";

        }else if(!paciente.getDni().matches("\\d{8}")){

            return "El DNI debe contener exactamente 8 dígitos numéricos";

        }else if(paciente.getDni().equals("00000000")){

            return "El DNI no puede ser 00000000";

        }else if(!paciente.getTelefono().matches("9\\d{8}")) {

            return "El número de teléfono debe comenzar con 9 y debe tener 9 dígitos";

        }else if(paciente.getEdad() < 0 || paciente.getEdad() > 120) {

            return "La fecha de nacimiento no es válida";

        }else if( paciente.getFechaNacimiento().isAfter(LocalDate.now())){

            return "La fecha de nacimiento no debería ser futura";

        }

        return null;

    }

}
