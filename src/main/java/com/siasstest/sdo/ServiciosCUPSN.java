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
public class ServiciosCUPSN extends ServiciosCUPS{
    public String justificacion;
    public String fundamentoLegal;
    public String alternativa;

    public ServiciosCUPSN(String justificacion, String fundamentoLegal, String alternativa, String CodigoCUPS, String Cantidad, String Descripcion) {
        super(CodigoCUPS, Cantidad, Descripcion);
        this.justificacion = justificacion;
        this.fundamentoLegal = fundamentoLegal;
        this.alternativa = alternativa;
    }

    
    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    

        
    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getFundamentoLegal() {
        return fundamentoLegal;
    }

    public void setFundamentoLegal(String fundamentoLegal) {
        this.fundamentoLegal = fundamentoLegal;
    }

 
    
    
    
}
