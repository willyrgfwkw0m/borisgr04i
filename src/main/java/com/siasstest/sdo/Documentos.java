/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.sdo;

/**
 *
 * @author borisgr04
 */
public class Documentos {
    
    private String idDocumento;
    private String fechaDocumento;
    private String horaDocumento;    
    private String tipoDocumento; 
    private String estado;//SOLICITUD, ARSOLICITUD,  AUTORIZACION, ARAUTORIZACION, NEGACION, ARNEGACION 
    private String ips_ide;
    private IPS ips;
    private String eps_ide;
    private EPS eps;
    private String pac_ide;
    private Pacientes paciente;

    
    public Documentos(){
        
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
 
    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getHoraDocumento() {
        return horaDocumento;
    }

    public void setHoraDocumento(String horaDocumento) {
        this.horaDocumento = horaDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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
    
    
}
