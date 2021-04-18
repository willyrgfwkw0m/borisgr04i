/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.old;

import com.siasstest.sdo.DiagnosticoCIE10;
import com.siasstest.sdo.EPS;
import com.siasstest.sdo.IPS;
import com.siasstest.sdo.Pacientes;
import com.siasstest.sdo.ServiciosCUPS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author borisgr04
 */
public class SolicitudesSDO {
    private String id;
    private String FechaSolicitud;
    private String hora;    
    private String fechaRecibido;
    private String horaRecibido;
    private String estado;//SOLICITUD, ARSOLICITUD,  AUTORIZACION, ARAUTORIZACION, NEGACION, ARNEGACION 
    private String origen_atencion;
    private String prioridad_atencion;
    private String ubicacion_pac;
    private String servicio;
    private String cama;
            
    private List<ServiciosCUPS> lServiciosCUPS= new ArrayList<ServiciosCUPS>();
    private List<DiagnosticoCIE10> lDiagnosticoCIE10= new ArrayList<DiagnosticoCIE10>();

    
    private String ips_ide;
    private IPS ips;
   
    private String eps_ide;
    private EPS eps;
    
    private String pac_ide;
    private Pacientes paciente;

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getOrigen_atencion() {
        return origen_atencion;
    }

    public void setOrigen_atencion(String origen_atencion) {
        this.origen_atencion = origen_atencion;
    }

    public String getPrioridad_atencion() {
        return prioridad_atencion;
    }

    public void setPrioridad_atencion(String prioridad_atencion) {
        this.prioridad_atencion = prioridad_atencion;
    }

    public String getUbicacion_pac() {
        return ubicacion_pac;
    }

    public void setUbicacion_pac(String ubicacion_pac) {
        this.ubicacion_pac = ubicacion_pac;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }


    public IPS getIps() {
        return ips;
    }

    public void setIps(IPS ips) {
        this.ips = ips;
    }

    public EPS getEps() {
        return eps;
    }

    public void setEps(EPS eps) {
        this.eps = eps;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaSolicitud() {
        return FechaSolicitud;
    }

    public void setFechaSolicitud(String FechaSolicitud) {
        this.FechaSolicitud = FechaSolicitud;
    }

    public List<DiagnosticoCIE10> getlDiagnosticoCIE10() {
        return lDiagnosticoCIE10;
    }

    public void setlDiagnosticoCIE10(List<DiagnosticoCIE10> lDiagnosticoCIE10) {
        this.lDiagnosticoCIE10 = lDiagnosticoCIE10;
    }

    public List<ServiciosCUPS> getlServiciosCUPS() {
        return lServiciosCUPS;
    }

    public void setlServiciosCUPS(List<ServiciosCUPS> lServiciosCUPS) {
        this.lServiciosCUPS = lServiciosCUPS;
    }
    
            
    public String getIps_ide() {
        return ips_ide;
    }

    public void setIps_ide(String ips_ide) {
        this.ips_ide = ips_ide;
    }

    public String getEps_ide() {
        return eps_ide;
    }

    public void setEps_ide(String eps_ide) {
        this.eps_ide = eps_ide;
    }

    public String getPac_ide() {
        return pac_ide;
    }

    public void setPac_ide(String pac_ide) {
        this.pac_ide = pac_ide;
    }
    
    public String getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(String fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public String getHoraRecibido() {
        return horaRecibido;
    }

    public void setHoraRecibido(String horaRecibido) {
        this.horaRecibido = horaRecibido;
    }
    

    
}
