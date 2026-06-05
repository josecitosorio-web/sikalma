package com.example.demo.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

import com.example.demo.Servicio.Servicio;

public class Doctor {

    private Long id;
    private String nombre;
    private String dni;
    private String telefono;
    private String correo;
    private LocalDate fechaNacimiento;
    private LocalTime hora_atencion_inicio;
    private LocalTime hora_atencion_fin;
    private Servicio servicio;
    private Boolean estado;

    public Doctor() {
    }

    public Doctor(String nombre, String dni, String telefono, String correo,
            LocalDate fechaNacimiento, LocalTime hora_atencion_inicio, LocalTime hora_atencion_fin, Servicio servicio, Boolean estado) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.hora_atencion_inicio = hora_atencion_inicio;
        this.hora_atencion_fin = hora_atencion_fin;
        this.servicio = servicio;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalTime getHoraAtencionInicio() {
        return hora_atencion_inicio;
    }

    public void setHoraAtencionInicio(LocalTime hora_atencion_inicio) {
        this.hora_atencion_inicio = hora_atencion_inicio;
    }

    public LocalTime getHoraAtencionFin() {
        return hora_atencion_fin;
    }

    public void setHoraAtencionFin(LocalTime hora_atencion_fin) {
        this.hora_atencion_fin = hora_atencion_fin;
    }

    public int getEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    public Servicio getServicio () {
        return servicio;
    }

    public void setServicio ( Servicio servicio) {
        this.servicio = servicio;
    }

    public Boolean getEstado () {
        return estado;
    }

    public void setEstado ( Boolean estado) {
        this.estado = estado;
    }
}