/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.old;

import com.siasstest.sdo.EPS;
import com.siasstest.sdo.IPS;
import com.siasstest.sdo.Pacientes;
import com.siasstest.sdo.ServiciosCUPSN;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author borisgr04
 */
public class NegacionSDO {
    
    //private String ips_ide;
    private String id_solicitud;
    private SolicitudesSDO solicitud;
    
    private String ips_ide;
    private IPS ips;
   
    private String eps_ide;
    private EPS eps;
    
    private String pac_ide;
    private Pacientes paciente;
    
    private List<ServiciosCUPSN> lServiciosCUPS= new ArrayList<ServiciosCUPSN>();
    
    
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

    public List<ServiciosCUPSN> getlServiciosCUPS() {
        return lServiciosCUPS;
    }

    public void setlServiciosCUPS(List<ServiciosCUPSN> lServiciosCUPS) {
        this.lServiciosCUPS = lServiciosCUPS;
    }
 
}
