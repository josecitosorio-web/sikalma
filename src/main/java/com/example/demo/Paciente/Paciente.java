package com.example.demo.Paciente;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Paciente {

    private Long id;
    private String nombres;
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private LocalDate fechaNacimiento;
    private List<Paciente> citas = new ArrayList<>();

    public Paciente() {}

    public Paciente(String nombres, String tipoDocumento, String numeroDocumento, String telefono, LocalDate fechaNacimiento){

        this.nombres = nombres;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;

    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento;}

    public String getNumeroDocumento() {return numeroDocumento;}
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento;}

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public int getEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    public List<Paciente> getCitas() { return citas; }
    public void setCitas(List<Paciente> citas) { this.citas = citas; }
}