package com.example.demo.Doctor;

import com.example.demo.Cita.Cita;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_doc")
    private String nombre;
    @Column(name = "dni_doc")
    private String dni;
    @Column(name = "especialidad_doc")
    private String especialidad;
    @Column(name = "telefono_doc")
    private String telefono;
    @Column(name = "correo_doc")
    private String correo;
    @Column(name = "fecha_doc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @OneToMany(mappedBy = "doctor" , cascade = CascadeType.ALL)
    private List<Cita> citas = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String nombre, String dni, String especialidad, String telefono, String correo, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getEdad(){return Period.between(this.fechaNacimiento , LocalDate.now()).getYears() ;}

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public List<Cita> getCitas () {return citas;}
    public void setCitas (List<Cita> citas) {this.citas = citas;}
}