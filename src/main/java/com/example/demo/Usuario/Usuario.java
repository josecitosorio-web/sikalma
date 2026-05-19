package com.example.demo.Usuario;

import com.example.demo.Doctor.Doctor;

public class Usuario {
    
    private int id;
    private String correo;
    private String contrasena;
    private String rol;
    private Doctor doctor;

    public Usuario() {}

    public Usuario (String correo , String contrasena , String rol, Doctor doctor) {
        
        this.correo = correo;
        this.contrasena = contrasena;
        this.doctor = doctor;
        this.rol = rol;
    } 

    // GETTERS

    public int getId() {
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

    public void setId(int id) {
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
