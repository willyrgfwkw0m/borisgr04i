/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.old;

/**
 *
 * @author borisgr04
 */
public class ARecibo {
    
    private String tipo;
    private String idDoc;
    private String fechaRecibido;
    private int i;
    private SolicitudesSDO  doc;

    public SolicitudesSDO getDoc() {
        return doc;
    }

    public void setDoc(SolicitudesSDO doc) {
        this.doc = doc;
    }
    
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(String fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }
    
    
    
}
