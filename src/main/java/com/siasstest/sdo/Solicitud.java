/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.sdo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author borisgr04
 */
public class Solicitud extends Documentos {
    private String origenAtencion;
    private String prioridadAtencion;
    private String ubicacionPac;
    private String servicioactual;    
    private String cama;
    private String IdAcuseRecibo;
    private AcuseRecibo acuseRecibo;
    private RptaSolicitud rptaSolicitud;
    private String NombreUbicacion;
    private String servicio;
    
    private List<ServiciosCUPS> lServiciosCUPS= new ArrayList<ServiciosCUPS>();
    private List<DiagnosticoCIE10> lDiagnosticoCIE10= new ArrayList<DiagnosticoCIE10>();

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    
    
    
    public String getNombreUbicacion() {
        return NombreUbicacion;
    }

    public void setNombreUbicacion(String NombreUbicacion) {
        this.NombreUbicacion = NombreUbicacion;
    }    
    
    public RptaSolicitud getRptaSolicitud() {
        return rptaSolicitud;
    }

    public void setRptaSolicitud(RptaSolicitud rptaSolicitud) {
        this.rptaSolicitud = rptaSolicitud;
    }
    public String getIdAcuseRecibo() {
        return IdAcuseRecibo;
    }

    public void setIdAcuseRecibo(String IdAcuseRecibo) {
        this.IdAcuseRecibo = IdAcuseRecibo;
    }

    public AcuseRecibo getAcuseRecibo() {
        return acuseRecibo;
    }

    public void setAcuseRecibo(AcuseRecibo acuseRecibo) {
        this.acuseRecibo = acuseRecibo;
    }

    
    public String getOrigenAtencion() {
        return origenAtencion;
    }

    public void setOrigenAtencion(String origenAtencion) {
        this.origenAtencion = origenAtencion;
    }

    public String getPrioridadAtencion() {
        return prioridadAtencion;
    }

    public void setPrioridadAtencion(String prioridadAtencion) {
        this.prioridadAtencion = prioridadAtencion;
    }

    public String getUbicacionPac() {
        return ubicacionPac;
    }

    public void setUbicacionPac(String ubicacionPac) {
        this.ubicacionPac = ubicacionPac;
    }

    public String getServicioactual() {
        return servicioactual;
    }

    public void setServicioactual(String servicioactual) {
        this.servicioactual = servicioactual;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public List<ServiciosCUPS> getlServiciosCUPS() {
        return lServiciosCUPS;
    }

    public void setlServiciosCUPS(List<ServiciosCUPS> lServiciosCUPS) {
        this.lServiciosCUPS = lServiciosCUPS;
    }

    public List<DiagnosticoCIE10> getlDiagnosticoCIE10() {
        return lDiagnosticoCIE10;
    }

    public void setlDiagnosticoCIE10(List<DiagnosticoCIE10> lDiagnosticoCIE10) {
        this.lDiagnosticoCIE10 = lDiagnosticoCIE10;
    }

}
