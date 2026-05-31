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
    public void agregar(Paciente p) {

        pacienteRepository.save(PacienteAdapter.toEntity(p));
    }

    @Override
    public List<Paciente> listar() {
        return PacienteAdapter.toModelList(pacienteRepository.findAll());
    }

    @Override
    public Paciente buscarPorId(Long id) {
        return PacienteAdapter.toModel(pacienteRepository.findById(id).orElse(null));
    }

    @Override
    public Paciente buscarPaciente(String numeroDocumento) {
        return PacienteAdapter.toModel(pacienteRepository.findByNumeroDocumento(numeroDocumento).orElse(null));
    }

    @Override
    public void actualizar(Paciente p) {
        pacienteRepository.save(PacienteAdapter.toEntity(p));
    }

    @Override
    public List<Paciente> buscarPorNumeroDocumento(String numeroDocumento) {
        return PacienteAdapter.toModelList(pacienteRepository.findAllByNumeroDocumento(numeroDocumento));
    }

    // validaciones
    @Override
    public String validarDatosRegistro(Paciente paciente) {

        String error = validacionesGenerales(paciente);

        if (error != null) {

            return error;

        }

        

        return null;
    }

    @Override
    public String validarDatosEdicion(Paciente paciente) {

        String error = validacionesGenerales(paciente);

        if (error != null) {

            return error;

        }

        return null;
    }

    public String validacionesGenerales(Paciente paciente) {

        if (paciente.getNombres() == null || paciente.getNombres().trim().isEmpty()) {

            return "El nombre del paciente es obligatorio";

        } else if (paciente.getTelefono() == null || paciente.getTelefono().trim().isEmpty()) {

            return "El telefono del paciente es obligatorio";

        } else if(paciente.getTipoDocumento().equals("DNI")) {

            String dni = paciente.getNumeroDocumento();

            if (dni == null || dni.length() != 8) {

                return "DNI Inválido : debe tener solo 8 dígitos";

            } else if (!dni.matches("\\d{8}")) {

                return "DNI Inválido : solo debe tener número permitidos";

            } else if (dni.equals("00000000")) {

                return "DNI Inválido : no puede ser 00000000";

            }
        } else if(paciente.getTipoDocumento().equals("CE")) {

            String ce = paciente.getNumeroDocumento();

            if (ce == null || ce.isBlank()) {

                return "CE Inválido : no puede estar vacío";

            }else if(!ce.matches("[a-zA-Z0-9]+")) {

                return "CE Inálido : solo caracteres alfanuméricos";

            } else if(ce.length() < 9 || ce.length() > 12) {

                return "CE Inválido : debe tener entre 9 y 12 carateres";

            }

        }else if(paciente.getTipoDocumento().equals("PASAPORTE")) {

            String P = paciente.getNumeroDocumento();

            if (P == null || P.isBlank()) {

                return "CE Inválido : no puede estar vacío";

            }else if(!P.matches("[a-zA-Z0-9]+")) {

                return "CE Inálido : solo caracteres alfanuméricos";

            } else if(P.length() < 6 || P.length() > 9) {

                return "CE Inválido : debe tener entre 6 y 9 carateres";

            }


        } else if (paciente.getFechaNacimiento() == null) {

            return "La fecha de nacimiento es obligatorio";

        } else if (!paciente.getTelefono().matches("9\\d{8}")) {

            return "El número de teléfono debe comenzar con 9 y debe tener 9 dígitos";

        } else if (paciente.getEdad() < 0 || paciente.getEdad() > 120) {

            return "La fecha de nacimiento no es válida";

        } else if (paciente.getFechaNacimiento().isAfter(LocalDate.now())) {

            return "La fecha de nacimiento no debería ser futura";

        }

        return null;

    }

}
