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
public class IPS {
    private String Codigo;
    private String Nombre;
    private String Documento;
    private String Telefono;
    private String Direccion;
    private String EndPoint;
    private String Tipo;

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEndPoint() {
        return EndPoint;
    }

    public void setEndPoint(String EndPoint) {
        this.EndPoint = EndPoint;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
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
    
    public IPS(String Codigo, String Nombre, String Documento, String Telefono, String Direccion, String EndPoint, String Tipo) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Documento = Documento;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.EndPoint = EndPoint;
        this.Tipo = Tipo;
    }
    
}
