package com.example.demo.Servicio;

import com.example.demo.Cita.CitaEntity;
import com.example.demo.Doctor.DoctorEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "servicio")
public class ServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_servi")
    private String nombre;
    @Column(name = "descripcion_servi")
    private String descripcion;
    @Column(name = "costo_servi")
    private double costo;
    @Column(name = "estado_servi")
    private Boolean estado; 
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<DoctorEntity> doctores = new ArrayList<>();

    public ServicioEntity () {}

    public ServicioEntity(String nombre, String descripcion, double costo, Boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public List<DoctorEntity> getDoctores(){
        return doctores;
    }

    public Boolean getEstado () {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setDoctores(List<DoctorEntity> doctores){
        this.doctores = doctores;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
