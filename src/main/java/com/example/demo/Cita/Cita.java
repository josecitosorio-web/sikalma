package com.example.demo.Cita;

import com.example.demo.Atencion.Atencion;
import com.example.demo.Doctor.Doctor;
import com.example.demo.Paciente.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private Long id;
    private Paciente paciente;
    private Doctor doctor;
    private Atencion atencion;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    public Cita() {}

    public Cita(Paciente paciente, Doctor doctor, LocalDate fecha, LocalTime hora, String estado) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Atencion getAtencion() { return atencion; }
    public void setAtencion(Atencion atencion) { this.atencion = atencion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}