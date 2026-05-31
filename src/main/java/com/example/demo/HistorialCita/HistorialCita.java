package com.example.demo.HistorialCita;

import com.example.demo.Cita.Cita;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class HistorialCita {

    private Long id;
    private Cita cita;
    private LocalDate fechaAnterior;
    private LocalDate fechaNueva;
    private LocalTime horaAnterior;
    private LocalTime horaNueva;
    private String doctorAnterior;
    private String doctorNuevo;
    private String estadoAnterior;
    private String estadoNuevo;
    private LocalDateTime fechaRegistro;
    private String usuarioResponsable;
    private String tipoCambio;

    public HistorialCita() {}

    public HistorialCita( Cita cita, LocalDate fechaAnterior, 
        LocalDate fechaNueva, LocalTime horaAnterior, 
        LocalTime horaNueva, String doctorAnterior, 
        String doctorNuevo,String estadoAnterior, 
        String estadoNuevo, LocalDateTime fechaRegistro, 
        String usuarioResponsable,String tipoCambio){

        this.cita = cita;
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

    public Cita getCita() {return cita;}
    public void setCita(Cita cita) {this.cita = cita;}

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
