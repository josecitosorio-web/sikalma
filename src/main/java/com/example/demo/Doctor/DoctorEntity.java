package com.example.demo.Doctor;

import com.example.demo.Cita.CitaEntity;
import com.example.demo.Servicio.ServicioEntity;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_doc")
    private String nombre;
    @Column(name = "dni_doc")
    private String dni;
    @Column(name = "telefono_doc")
    private String telefono;
    @Column(name = "correo_doc")
    private String correo;
    @Column(name = "fecha_doc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Column(name = "hora_atencion_inicio")
    private LocalTime hora_atencion_inicio;
    @Column(name = "hora_atencion_fin")
    private LocalTime hora_atencion_fin;
    @OneToMany(mappedBy = "doctor" , cascade = CascadeType.ALL)
    private List<CitaEntity> citas = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private ServicioEntity servicio;


    public DoctorEntity() {
    }

    public DoctorEntity(String nombre, String dni,String telefono, String correo, LocalDate fechaNacimiento , LocalTime hora_atencion_inicio, LocalTime hora_atencion_fin) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.hora_atencion_inicio = hora_atencion_inicio;
        this.hora_atencion_fin = hora_atencion_fin;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getEdad(){return Period.between(this.fechaNacimiento , LocalDate.now()).getYears() ;}

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public LocalTime getHoraAtencionInicio() { return hora_atencion_inicio; }
    public void setHoraAtencionInicio(LocalTime hora_atencion_inicio) { this.hora_atencion_inicio = hora_atencion_inicio ;}

    public LocalTime getHoraAtencionFin() { return hora_atencion_fin; }
    public void setHoraAtencionFin(LocalTime hora_atencion_fin) { this.hora_atencion_fin = hora_atencion_fin ;}

    public List<CitaEntity> getCitas () {return citas;}
    public void setCitas (List<CitaEntity> citas) {this.citas = citas;}

    public ServicioEntity getServicio () { return servicio;}
    public void setServicio (ServicioEntity servicio) { this.servicio = servicio ;}
}