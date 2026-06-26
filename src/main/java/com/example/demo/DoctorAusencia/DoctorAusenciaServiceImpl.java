package com.example.demo.DoctorAusencia;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Doctor.Doctor;
import com.example.demo.Doctor.DoctorService;
import com.example.demo.DoctorDIa.DoctorDiaService;

@Service
public class DoctorAusenciaServiceImpl implements DoctorAusenciaService {

    private final DoctorAusenciaRepository doctorAusenciaRepository;
    private final DoctorService doctorService;
    private final DoctorDiaService doctorDiaService;

    public DoctorAusenciaServiceImpl(DoctorAusenciaRepository doctorAusenciaRepository, DoctorService doctorService, DoctorDiaService doctorDiaService) {
        this.doctorAusenciaRepository = doctorAusenciaRepository;
        this.doctorService = doctorService;
        this.doctorDiaService = doctorDiaService;
    }

    @Override
    public void agregar(LocalDate fecha, String motivo, Long idDoctor) {

        Doctor doctor = doctorService.buscarPorId(idDoctor);

        DoctorAusencia doctorAusencia = new DoctorAusencia(fecha,motivo,doctor);



        doctorAusenciaRepository.save(DoctorAusenciaAdapter.toEntity(doctorAusencia));

    }

    @Override
    public void actualizar(LocalDate fecha, String motivo, Long idDoctor, Long ausenciaId) {

        DoctorAusencia model = DoctorAusenciaAdapter.toModel(doctorAusenciaRepository.findById(ausenciaId).orElse(null));
        Doctor doctor = doctorService.buscarPorId(idDoctor);

        model.setFecha(fecha);
        model.setMotivo(motivo);
        model.setDoctor(doctor);


        doctorAusenciaRepository.save(DoctorAusenciaAdapter.toEntity(model));

    }

    @Override
    public List<DoctorAusencia> buscarPorIdDoctor(Long id) {

        return DoctorAusenciaAdapter.toModelList(doctorAusenciaRepository.findByDoctorId(id));

    }

    @Override 
    public DoctorAusencia buscarAusencia(Long id) {

        return DoctorAusenciaAdapter.toModel(doctorAusenciaRepository.findById(id).orElse(null));

    }

    @Override
    public void eliminar(Long id) {

        doctorAusenciaRepository.deleteById(id);

    }

    @Override
    public String validaciones(LocalDate fecha, String motivo, Long idDoctor) {

        String error = validacionesGenerales(fecha,motivo,idDoctor);

        if(error != null) {

            return error;

        }

        return null;
    }

    @Override
    public String validacionesEdicion(LocalDate fecha, String motivo, Long idDoctor) {

        String error = validacionesGenerales(fecha,motivo,idDoctor);

        if(error != null) {

            return error;

        }else if (!fecha.isAfter(LocalDate.now())) {

            return "No se puede editar una ausencia de una fecha trancurrida";

        }

        return null;

    }

    @Override
    public DoctorAusencia ausenciaTemporal(LocalDate fecha, String motivo, Long idDoctor) {

        Doctor doctor = doctorService.buscarPorId(idDoctor);

        DoctorAusencia ausenciaTemporal = new DoctorAusencia(fecha,motivo,doctor);

        return ausenciaTemporal;

    }

    @Override
    public boolean existeAusencia(Long doctorId, LocalDate fecha) {

        return doctorAusenciaRepository.existsByDoctorIdAndFecha(doctorId, fecha);

    }

  

    public String validacionesGenerales(LocalDate fecha, String motivo, Long idDoctor) {

        List<DayOfWeek> diasDoctor = doctorDiaService.obtenerDiasPorDoctor(idDoctor);

        int numeroAusencias = doctorAusenciaRepository.contarPorDoctorYMes(idDoctor, fecha.getMonthValue(), fecha.getYear());

        if (fecha == null) {

            return "La fecha de falta del doctor es obligatoria";

        }
        else if (motivo == null || motivo.isEmpty()) {

            return "El motivo es obligatorio";

        }else if(idDoctor == null) {

            return "El doctor no esta relacionado";

        }else if(!diasDoctor.contains(fecha.getDayOfWeek())){

            return "Solo se pueden registrar ausencias en base a los dias de atención";
            
        }else if(!fecha.isAfter(LocalDate.now())){

            return "La fecha de ausencia debe ser posterior a la fecha actual";

        }else if(doctorAusenciaRepository.existsByDoctorIdAndFecha(idDoctor, fecha)) {

            return "Ya existe una ausencia registrada para ese doctor en esa fecha";

        }else if(numeroAusencias >= 3){

            return "El doctor solo tiene permitido faltar 3 veces al mes";

        }
        

        return null;

    }
}
