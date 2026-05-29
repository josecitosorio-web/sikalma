package com.example.demo.Servicio;

public class Servicio {

    private Long id;
    private String nombre;
    private String descripcion;
    private double costo;
    private Boolean estado;

    public Servicio() {}

    public Servicio(String nombre, String descripcion, double costo, Boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public Boolean getEstado() { return estado;}
    public void setEstado(Boolean estado) { this.estado = estado;}
}