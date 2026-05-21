package com.example.demo.Cita;

import com.example.demo.Atencion.Atencion;
import com.example.demo.Doctor.Doctor;
import com.example.demo.Paciente.Paciente;
import com.example.demo.Servicio.Servicio;

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
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @OneToOne(mappedBy = "cita")
    private Atencion atencion;

    @Column(name = "fecha_cita")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column(name = "hora_cita")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora;

    @Column(name = "estado_cita")
    private String estado;



    public Cita() {

    }

    // Constructor con parámetros
    public Cita(Paciente paciente, Doctor doctor,Servicio servicio, LocalDate fecha, LocalTime hora, String estado){
        this.paciente = paciente;
        this.doctor = doctor;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Servicio getServicio() {
        return servicio;
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

    public Atencion getAtencion() {
        return atencion;
    }

    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
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

    public void setAtencion(Atencion atencion) {
        this.atencion = atencion;
    }
}