package com.example.demo.Atencion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Cita.CitaService;
import com.example.demo.Usuario.Usuario;
import com.example.demo.Usuario.UsuarioService;
import com.example.demo.Cita.Cita;

import java.time.LocalTime;
import java.util.List;

@Service
public class AtencionServiceImpl implements AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private CitaService citaService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<Atencion> obtenerTodos() {

        Usuario usuario = usuarioService.obtenerUsuarioActual();

        if (usuario.getRol().equals("DOCTOR")) {

            return AtencionAdapter.toModelList(atencionRepository.findByCitaDoctorId(usuario.getDoctor().getId()));

        }

        return AtencionAdapter.toModelList(atencionRepository.findAll());
    }

    @Override
    public void agregar(Long CitaId, LocalTime horaInicio, LocalTime horaFin, String diagnostico, String tratamiento,
            String estado) {

        Cita c = citaService.buscarPorId(CitaId);

        Atencion a = new Atencion(c, horaInicio, horaFin, diagnostico, tratamiento, estado);

        atencionRepository.save(AtencionAdapter.toEntity(a));
    }

    @Override
    public Atencion buscarPorId(Long id) {
        return AtencionAdapter.toModel(atencionRepository.findById(id).orElse(null));
    }

    @Override
    public void actualizar(Long id, Long citaId, LocalTime horaInicio, LocalTime horaFin, String diagnostico,
            String tratamiento, String estado) {

        Cita c = citaService.buscarPorId(citaId);

        Atencion a = new Atencion(c, horaInicio, horaFin, diagnostico, tratamiento, estado);
        a.setId(id);

        atencionRepository.save(AtencionAdapter.toEntity(a));
    }

    // validaciones
    @Override
    public String validarDatosRegistro(Atencion atencion) {

        String error = validacionesGenerales(atencion);

        if (error != null) {

            return error;

        }

        return null;
    }

    @Override
    public String validarDatosEdicion(Atencion atencion) {

        String error = validacionesGenerales(atencion);

        if (error != null) {

            return error;

        }

        return null;
    }

    public String validacionesGenerales(Atencion atencion) {

        if (atencion.getDiagnostico() == null || atencion.getDiagnostico().trim().isEmpty()) {

            return "El diagnostico es obligatorio";

        } else if (atencion.getHoraInicio() == null) {

            return "La hora de inicio es obligatoria";

        } else if (atencion.getHoraFin() == null) {

            return "La hora de fin es obligatoria";

        } else if (atencion.getTratamiento() == null || atencion.getTratamiento().trim().isEmpty()) {

            return "El tratamiento es obligatorio";

        } else if (!atencion.getHoraFin().isAfter(atencion.getHoraInicio())) {

            return "La hora fin debe ser mayor a la hora de inicio";

        }

        return null;

    }

}
