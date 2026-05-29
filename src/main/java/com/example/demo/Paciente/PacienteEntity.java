package com.example.demo.Paciente;

import com.example.demo.Cita.CitaEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "paciente")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_pac")
    private String nombres;
    @Column(name = "dni_pac")
    private String dni;
    @Column(name = "telefono_pac")
    private String telefono;
    @Column(name = "fecha_pac")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @OneToMany(mappedBy = "paciente" ,cascade = CascadeType.ALL)
    private List<CitaEntity> citas = new ArrayList<>();

    public PacienteEntity () {}

    public PacienteEntity (String nombres, String dni, String telefono, LocalDate fechaNacimiento){

        this.nombres = nombres;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;

    }

    public Long getId () {
        return this.id;
    }

    public String getNombres(){
        return this.nombres;
    }

    public String getDni(){
        return this.dni;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public int getEdad(){

        return Period.between(this.fechaNacimiento , LocalDate.now()).getYears() ;

    }

    public LocalDate getFechaNacimiento () {
        return  this.fechaNacimiento;
    }

    public List<CitaEntity> getCitas() {
        return citas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCitas(List<CitaEntity> citas) {
        this.citas = citas;
    }

}
