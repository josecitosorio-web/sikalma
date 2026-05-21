package com.example.demo.Servicio;

import com.example.demo.Cita.Cita;

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
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_servi")
    private String nombre;
    @Column(name = "descripcion_servi")
    private String descripcion;
    @Column(name = "costo_servi")
    private double costo;
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<Cita> citas = new ArrayList<>();

    public Servicio () {}

    public Servicio(String nombre, String descripcion, double costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
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

    public List<Cita> getCitas() {
        return citas;
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

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
