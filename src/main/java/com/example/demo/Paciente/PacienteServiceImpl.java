package com.example.demo.Paciente;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteDAO PacienteDAO;

    public PacienteServiceImpl(PacienteDAO PacienteDAO){
        this.PacienteDAO = PacienteDAO;
    }

    @Override
    public void agregar(Paciente p){
        PacienteDAO.save(p);
    }

    @Override
    public List<Paciente> listar() {
        return PacienteDAO.findAll();
    }

    @Override
    public Paciente buscarPorId(int id){
        return PacienteDAO.findById(id);
    }

    @Override
    public Paciente buscarPaciente(String dni){
        return PacienteDAO.findPaciente(dni);
    }

    @Override
    public void actualizar(Paciente p){
        PacienteDAO.update(p);
    }

    @Override
    public void eliminar(int id) {
        PacienteDAO.delete(id);
    }

    @Override
    public List<Paciente> buscarPorDni(String dni){
        return PacienteDAO.findByDni(dni);
    }

    //validaciones 
    @Override
    public String validarDatosRegistro(Paciente paciente) {

        String error = validacionesGenerales(paciente);

        if(error != null ){

            return error;

        }else if(!PacienteDAO.findByDni(paciente.getDni()).isEmpty()){

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

        }else if(paciente.getEdad() < 0 || paciente.getEdad() > 100) {

            return "La fecha de nacimiento no es válida";

        }else if( paciente.getFechaNacimiento().isAfter(LocalDate.now())){

            return "La fecha de nacimiento no debería ser futura";

        }

        return null;

    }

}
