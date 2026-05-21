package com.example.demo.Usuario;

import com.example.demo.Doctor.Doctor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "correo_user")
    private String correo;
    @Column(name = "contrasena_user")
    private String contrasena;
    @Column(name = "rol_user")
    private String rol;
    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Usuario() {}

    public Usuario (String correo , String contrasena , String rol, Doctor doctor) {
        
        this.correo = correo;
        this.contrasena = contrasena;
        this.doctor = doctor;
        this.rol = rol;
    } 

    // GETTERS

    public Long getId() {
        return this.id;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public String getRol() {
        return this.rol;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
}
