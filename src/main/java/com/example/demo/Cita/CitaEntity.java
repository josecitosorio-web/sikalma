package com.example.demo.Cita;

import com.example.demo.Atencion.AtencionEntity;
import com.example.demo.Doctor.DoctorEntity;
import com.example.demo.Paciente.PacienteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "cita")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @OneToOne(mappedBy = "cita")
    private AtencionEntity atencion;

    @Column(name = "fecha_cita")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column(name = "hora_cita")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora;

    @Column(name = "estado_cita")
    private String estado;



    public CitaEntity() {

    }

    // Constructor con parámetros
    public CitaEntity(PacienteEntity paciente, DoctorEntity doctor, LocalDate fecha, LocalTime hora, String estado){
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public AtencionEntity getAtencion() {
        return atencion;
    }

    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setAtencion(AtencionEntity atencion) {
        this.atencion = atencion;
    }
}