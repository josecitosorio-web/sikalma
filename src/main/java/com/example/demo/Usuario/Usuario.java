package com.example.demo.Usuario;

import com.example.demo.Doctor.Doctor;

public class Usuario {

    private Long id;
    private String correo;
    private String contrasena;
    private String rol;
    private Doctor doctor;

    public Usuario() {}

    public Usuario(String correo, String contrasena, String rol, Doctor doctor) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.doctor = doctor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}