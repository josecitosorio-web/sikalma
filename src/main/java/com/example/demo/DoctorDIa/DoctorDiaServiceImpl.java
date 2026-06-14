package com.example.demo.DoctorDIa;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Doctor.DoctorEntity;
import com.example.demo.Doctor.DoctorRepository;

@Service
public class DoctorDiaServiceImpl implements DoctorDiaService {

    @Autowired
    private DoctorDiaRepository doctorDiaRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<DayOfWeek> obtenerDiasPorDoctor(Long idDoctor) {

        return doctorDiaRepository.findByDoctorId(idDoctor)
                .stream()
                .map(DoctorDiaEntity::getDiaSemana)
                .toList();

    }

    @Override
    public List<String> obtenerDiasEspanol(Long idDoctor) {

        return doctorDiaRepository.findByDoctorId(idDoctor)
                .stream()
                .map(DoctorDiaEntity::getDiaSemana)
                .map(dia -> dia.getDisplayName(
                        TextStyle.FULL,
                        new Locale("es", "PE")))
                .toList();

    }

    @Override
    public void agregar(Long idDoctor, List<DayOfWeek> diasLaborales) {

        if (diasLaborales == null || diasLaborales.isEmpty()) {
            return;
        }

        DoctorEntity doctor = doctorRepository.findById(idDoctor).orElse(null);

        if (doctor == null) {
            return;
        }

        for (DayOfWeek dialaboral : diasLaborales) {

            DoctorDiaEntity entity = new DoctorDiaEntity();

            entity.setDiaSemana(dialaboral);
            entity.setDoctor(doctor);

            doctorDiaRepository.save(entity);

        }

    }

    @Override
    public void editar(Long idDoctor, List<DayOfWeek> diasLaborales) {

        if (diasLaborales == null || diasLaborales.isEmpty()) {

            return;

        }

        doctorDiaRepository.deleteByDoctorId(idDoctor);

        agregar(idDoctor, diasLaborales);

    }

    @Override
    public String validarDatos(List<DayOfWeek> diasLaborales) {

        if (diasLaborales == null || diasLaborales.isEmpty()) {

            return "Debe escoger mínimo 1 día de la semana";

        }

        return null;
    }
}
