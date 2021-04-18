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
public class EPS {
    private String Codigo;
    private String Nombre;
    private String EndPoint;

    public EPS(String Codigo, String Nombre, String EndPoint) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.EndPoint = EndPoint;
    }

    
    public String getEndPoint() {
        return EndPoint;
    }

    public void setEndPoint(String EndPoint) {
        this.EndPoint = EndPoint;
    }


    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
}
