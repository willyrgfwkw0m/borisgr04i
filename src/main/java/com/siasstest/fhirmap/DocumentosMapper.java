/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirmap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author borisgr04
 */
public class DocumentosMapper {
    private String Codigo;
    private String SistemaCodigo;
    private String Titulo;
    private final Map<String, String> docsAR = new HashMap<String, String>();

    public DocumentosMapper(String Codigo) {
        Inicializar();
        SetCategoria(Codigo);
    }

    private void Inicializar() {
        docsAR.put("SOLAUT", "SOLICITUD SERVICIOS DE SALUD");
        docsAR.put("AUTSER", "AUTORIZACIÓN DE SERVICIOS DE SALUD");
        docsAR.put("NEGSER", "NEGACIÓN DE SERVICIOS DE SALUD");
        
        docsAR.put("ARSOAU", "ACUSE DE RECIBO DE SOLICITUD SERVICIOS DE SALUD");
        docsAR.put("ARAUSE", "ACUSE DE RECIBO DE AUTORIZACIÓN DE SERVICIOS DE SALUD");
        docsAR.put("ARNESE", "ACUSE DE RECIBO DE NEGACIÓN DE SERVICIOS DE SALUD");
    }

    private void SetCategoria(String Codigo){
        this.setCodigo(Codigo);
        this.setTitulo(docsAR.get(Codigo));
        this.setSistemaCodigo("http://colombia.com/formatosRes3047_ext");
    }
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getSistemaCodigo() {
        return SistemaCodigo;
    }

    public void setSistemaCodigo(String SistemaCodigo) {
        this.SistemaCodigo = SistemaCodigo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    

}
