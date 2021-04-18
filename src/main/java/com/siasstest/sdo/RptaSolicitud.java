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
public class RptaSolicitud extends Documentos {
    private String idSol;
    //private Solicitud solicitud;
    
    private String IdAcuseRecibo;
    private AcuseRecibo acuseRecibo;

    public String getIdSol() {
        return idSol;
    }

    public void setIdSol(String idSol) {
        this.idSol = idSol;
    }

//    public Solicitud getSolicitud() {
//        return solicitud;
//    }
//
//    public void setSolicitud(Solicitud solicitud) {
//        this.solicitud = solicitud;
//    }

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
    
    
    
}
