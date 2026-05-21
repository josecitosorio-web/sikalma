package com.example.demo.Atencion;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.Cita.Cita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalTime;

@Entity(name = "atencion")
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "hr_inicio")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaInicio;
    @Column(name = "hr_fin")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaFin;
    @Column(name = "diagnostico")
    private String diagnostico;
    @Column(name = "tratamiento")
    private String tratamiento;
    @Column(name = "estado")
    private String estado; 
    @OneToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;      

    public Atencion() {}

    // Constructor completo (útil al crear desde una Cita)
    public Atencion(Cita cita, LocalTime horaInicio, LocalTime horaFin , String diagnostico, String tratamiento , String estado) {
        this.cita         = cita;
        this.horaInicio     = horaInicio;
        this.horaFin        = horaFin;
        this.diagnostico    = diagnostico;
        this.tratamiento    = tratamiento;
        this.estado         = estado;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public Long getId()                  { return id; }
    public Cita getCita ()               { return cita;}
    public LocalTime getHoraInicio()    { return horaInicio;}
    public LocalTime getHoraFin()       { return horaFin;}
    public String getDiagnostico()      { return diagnostico;}
    public String getTratamiento()      { return tratamiento;}
    public String getEstado()           { return estado;}

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setId(Long id)                          { this.id = id;}
    public void setCita(Cita cita)                     {this.cita = cita;}
    public void setHoraInicio(LocalTime horaInicio)    { this.horaInicio = horaInicio; }
    public void setHoraFin(LocalTime horaFin)          { this.horaFin = horaFin; }
    public void setDiagnostico(String diagnostico)     { this.diagnostico = diagnostico; }
    public void setTratamiento(String tratamiento)     { this.tratamiento = tratamiento; }
    public void setEstado(String estado)               { this.estado = estado; }
}
