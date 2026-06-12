package com.example.demo.DoctorDIa;

import java.time.DayOfWeek;

import com.example.demo.Doctor.DoctorEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "doctor_dia")
public class DoctorDiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DayOfWeek diaSemana;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    public DoctorDiaEntity() {}

    public DoctorDiaEntity(DayOfWeek diaSemana){
        this.diaSemana = diaSemana;

    }

    public Long getId() {return id;}
    public void setID(Long id) {this.id = id;}

    public DayOfWeek getDiaSemana(){return diaSemana;}
    public void setDiaSemana(DayOfWeek diaSemana){this.diaSemana = diaSemana;}

    public DoctorEntity getDoctor() {return doctor;}
    public void setDoctor(DoctorEntity doctor) {this.doctor = doctor;}
    

    
}
