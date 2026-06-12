package com.example.demo.DoctorDIa;

import java.time.DayOfWeek;

import com.example.demo.Doctor.Doctor;

public class DoctorDia {

    private Long id;
    private DayOfWeek diaSemana;
    private Doctor doctor;
    

    public DoctorDia() {}

    public DoctorDia (DayOfWeek diaSemana, Doctor doctor) {

        this.diaSemana = diaSemana;
        this.doctor = doctor;

    }

    public Long getId() {return id;}
    public void setID(Long id) {this.id = id;}

    public DayOfWeek getDiaSemana(){return diaSemana;}
    public void setDiaSemana(DayOfWeek diaSemana){this.diaSemana = diaSemana;}

    public Doctor getDoctor() {return doctor;}
    public void setDoctor(Doctor doctor) {this.doctor = doctor;}
    
}
