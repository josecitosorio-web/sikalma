package com.example.demo.Atencion;

import java.time.LocalTime;
import com.example.demo.Cita.Cita;

public class Atencion {

    private Long id;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String diagnostico;
    private String tratamiento;
    private String estado;
    private Cita cita;

    public Atencion() {}

    public Atencion(Cita cita, LocalTime horaInicio, LocalTime horaFin, String diagnostico, String tratamiento, String estado) {
        this.cita = cita;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cita getCita() { return cita; }
    public void setCita(Cita cita) { this.cita = cita; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}