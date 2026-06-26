package com.example.demo.DoctorAusencia;

import java.time.LocalDate;

import com.example.demo.Doctor.Doctor;

public class DoctorAusencia {

    private Long id;
    
    private LocalDate fecha;

    private String motivo;

    private Doctor doctor;

    public DoctorAusencia () {}

    public DoctorAusencia ( LocalDate fecha, String motivo, Doctor doctor) {

        this.fecha = fecha;
        this.motivo = motivo;
        this.doctor = doctor;

    }

    public Long getId() {return this.id;}
    public void setId(Long id) {this.id=id;}

    public LocalDate getFecha() {return this.fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public String getMotivo() {return this.motivo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}

    public Doctor getDoctor() {return this.doctor;}
    public void setDoctor(Doctor doctor) {this.doctor = doctor;}

}
