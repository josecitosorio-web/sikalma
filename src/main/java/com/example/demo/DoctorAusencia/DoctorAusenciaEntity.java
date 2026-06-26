package com.example.demo.DoctorAusencia;

import java.time.LocalDate;

import com.example.demo.Doctor.DoctorEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "doctor_ausencia")
public class DoctorAusenciaEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    private LocalDate fecha;

    private String motivo;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    public DoctorAusenciaEntity () {}

    public DoctorAusenciaEntity (LocalDate fecha, String motivo){

        this.fecha = fecha;
        this.motivo = motivo;

    }

    public Long getId() {return this.id;}
    public void setId(Long id) {this.id=id;}

    public LocalDate getFecha() {return this.fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public String getMotivo() {return this.motivo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}

    public DoctorEntity getDoctor() {return this.doctor;}
    public void setDoctor(DoctorEntity doctor) {this.doctor = doctor;}
}
