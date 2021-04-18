/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.old;

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
public class AutorizacionSDO {

    //private String ips_ide;
    private String id;
    private String fechaAutorizacion;

   
    private String horaAutorizacion;
    private String fechaRecibido;
    private String horaRecibido;
    
    private String estado;//SOLICITUD, ARSOLICITUD,  AUTORIZACION, ARAUTORIZACION, NEGACION, ARNEGACION 
    
    private String id_solicitud;
    private SolicitudesSDO solicitud;
    
    private String ips_ide;
    private IPS ips;
   
    private String eps_ide;
    private EPS eps;
    
    private String pac_ide;
    private Pacientes paciente;
    
    private List<ServiciosCUPS> lServiciosCUPS= new ArrayList<ServiciosCUPS>();
    
    
    public String getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(String id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public SolicitudesSDO getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudesSDO solicitud) {
        this.solicitud = solicitud;
    }

    public String getIps_ide() {
        return ips_ide;
    }

    public void setIps_ide(String ips_ide) {
        this.ips_ide = ips_ide;
    }

    public IPS getIps() {
        return ips;
    }

    public void setIps(IPS ips) {
        this.ips = ips;
    }

    public String getEps_ide() {
        return eps_ide;
    }

    public void setEps_ide(String eps_ide) {
        this.eps_ide = eps_ide;
    }

    public EPS getEps() {
        return eps;
    }

    public void setEps(EPS eps) {
        this.eps = eps;
    }

    public String getPac_ide() {
        return pac_ide;
    }

    public void setPac_ide(String pac_ide) {
        this.pac_ide = pac_ide;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public List<ServiciosCUPS> getlServiciosCUPS() {
        return lServiciosCUPS;
    }

    public void setlServiciosCUPS(List<ServiciosCUPS> lServiciosCUPS) {
        this.lServiciosCUPS = lServiciosCUPS;
    }
  public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(String fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getHoraAutorizacion() {
        return horaAutorizacion;
    }

    public void setHoraAutorizacion(String horaAutorizacion) {
        this.horaAutorizacion = horaAutorizacion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
