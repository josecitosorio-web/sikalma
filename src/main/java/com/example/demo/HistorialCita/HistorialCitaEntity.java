package com.example.demo.HistorialCita;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.demo.Cita.CitaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "HistorialCita")
public class HistorialCitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cita_id")
    private CitaEntity citaEntity;

    @Column(name = "fecha_anterior")
    private LocalDate fechaAnterior;

    @Column(name = "fecha_nueva")
    private LocalDate fechaNueva;

    @Column(name = "hora_anterior")
    private LocalTime horaAnterior;

    @Column(name = "hora_nueva")
    private LocalTime horaNueva;

    @Column(name = "doctor_anterior")
    private String doctorAnterior;

    @Column(name = "doctor_nuevo")
    private String doctorNuevo;

    @Column(name = "estado_anterior")
    private String estadoAnterior;

    @Column(name = "estado_nuevo")
    private String estadoNuevo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "usuario_responsable")
    private String usuarioResponsable;

    @Column(name = "tipo_cambio")
    private String tipoCambio;

    public HistorialCitaEntity() {}

    public HistorialCitaEntity( CitaEntity citaEntity, LocalDate fechaAnterior, LocalDate fechaNueva, LocalTime horaAnterior, LocalTime horaNueva, String doctorAnterior, String doctorNuevo,String estadoAnterior, String estadoNuevo, LocalDateTime fechaRegistro, String usuarioResponsable,String tipoCambio){

        this.citaEntity = citaEntity;
        this.fechaAnterior = fechaAnterior;
        this.fechaNueva = fechaNueva;
        this.horaAnterior = horaAnterior;
        this.horaNueva = horaNueva;
        this.doctorAnterior = doctorAnterior;
        this.doctorNuevo = doctorNuevo;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.fechaRegistro = fechaRegistro;
        this.usuarioResponsable = usuarioResponsable;
        this.tipoCambio = tipoCambio;

    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public CitaEntity getCitaEntity() {return citaEntity;}
    public void setCitaEntity(CitaEntity citaEntity) {this.citaEntity = citaEntity;}

    public LocalDate getFechaAnterior() {return fechaAnterior;}
    public void setFechaAnterior(LocalDate fechaAnterior) {this.fechaAnterior = fechaAnterior;}

    public LocalDate getFechaNueva() {return fechaNueva;}
    public void setFechaNueva(LocalDate fechaNueva) {this.fechaNueva = fechaNueva;}

    public LocalTime getHoraAnterior() {return horaAnterior;}
    public void setHoraAnterior(LocalTime horaAnterior){this.horaAnterior = horaAnterior;}
    
    public LocalTime getHoraNueva() {return horaNueva;}
    public void setHoraNueva(LocalTime horaNueva){this.horaNueva = horaNueva;}

    public String getDoctorAnterior(){return doctorAnterior;}
    public void setDoctorAnterior(String doctorAnterior) {this.doctorAnterior = doctorAnterior;}

    public String getDoctorNuevo(){return doctorNuevo;}
    public void setDoctorNuevo(String doctorNuevo) {this.doctorNuevo = doctorNuevo;}

    public String getEstadoAnterior(){return estadoAnterior;}
    public void setEstadoAnterior(String estadoAnterior) {this.estadoAnterior = estadoAnterior;}

    public String getEstadoNuevo(){return estadoNuevo;}
    public void setEstadoNuevo(String estadoNuevo) {this.estadoNuevo = estadoNuevo;}

    public LocalDateTime getFechaRegistro () {return fechaRegistro;}
    public void setFechaRegistro( LocalDateTime fechaRegistro) {this.fechaRegistro = fechaRegistro;}

    public String getUsuarioResponsable(){return usuarioResponsable;}
    public void setUsuarioResponsable(String usuarioResponsable) {this.usuarioResponsable = usuarioResponsable;}

    public String getTipoCambio(){return tipoCambio;}
    public void setTipoCambio (String tipoCambio) {this.tipoCambio = tipoCambio;}


}
