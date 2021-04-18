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
public class Pacientes {
    private String id;
    private String apellidos1;
    private String apellidos2;
    private String nombre1;
    private String nombre2;
    private String tipodocumento;
    private String fechanacimiento;
    private String direccion_pas;
    private String telefono_pas;
    private String departamento_pas;
    private String municipio_pas;
    private String correo_pas;
    private String cobertura_pas;
    private String nombrecompleto;
    private String nombrecobertura;

    public Pacientes(String id, String apellidos1, String apellidos2, String nombre1, String nombre2, String tipodocumento, String fechanacimiento, String direccion_pas, String telefono_pas, String departamento_pas, String municipio_pas, String correo_pas, String cobertura_pas) {
        this.id = id;
        this.apellidos1 = apellidos1;
        this.apellidos2 = apellidos2;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.tipodocumento = tipodocumento;
        this.fechanacimiento = fechanacimiento;
        this.direccion_pas = direccion_pas;
        this.telefono_pas = telefono_pas;
        this.departamento_pas = departamento_pas;
        this.municipio_pas = municipio_pas;
        this.correo_pas = correo_pas;
        this.cobertura_pas = cobertura_pas;
        this.nombrecompleto = nombrecompleto;
        this.nombrecobertura = nombrecobertura;
        this.nombrecompleto = this.nombre1 + " " + this.nombre2 + " " + this.apellidos1 + " " + this.apellidos2;        
        if("RC".equals(this.cobertura_pas)) this.nombrecobertura = "Regimen Contribuido";
        if("RSP".equals(this.cobertura_pas)) this.nombrecobertura = "egimen Subsidiado Parcial";
        if("RST".equals(this.cobertura_pas)) this.nombrecobertura = "Regimen Subsidiado Total";
        if("PPNACS".equals(this.cobertura_pas)) this.nombrecobertura = "Poblacion Pobre No Asegurada Con Sisben";
        if("PPNASS".equals(this.cobertura_pas)) this.nombrecobertura = "Poblacion Pobre No Asegurada Sin Sisben";
        if("DE".equals(this.cobertura_pas)) this.nombrecobertura = "Desplazado";
        if("PAS".equals(this.cobertura_pas)) this.nombrecobertura = "Plan Adicional de Salud";
        if("OTR".equals(this.cobertura_pas)) this.nombrecobertura = "Otro";  
    }   
    
    public String getDireccion_pas() {
        return direccion_pas;
    }

    public void setDireccion_pas(String direccion_pas) {
        this.direccion_pas = direccion_pas;
    }

    public String getTelefono_pas() {
        return telefono_pas;
    }

    public void setTelefono_pas(String telefono_pas) {
        this.telefono_pas = telefono_pas;
    }

    public String getDepartamento_pas() {
        return departamento_pas;
    }

    public void setDepartamento_pas(String departamento_pas) {
        this.departamento_pas = departamento_pas;
    }

    public String getMunicipio_pas() {
        return municipio_pas;
    }

    public void setMunicipio_pas(String municipio_pas) {
        this.municipio_pas = municipio_pas;
    }

    public String getCorreo_pas() {
        return correo_pas;
    }

    public void setCorreo_pas(String correo_pas) {
        this.correo_pas = correo_pas;
    }

    public String getCobertura_pas() {
        return cobertura_pas;
    }

    public void setCobertura_pas(String cobertura_pas) {
        this.cobertura_pas = cobertura_pas;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getNombrecobertura() {
        return nombrecobertura;
    }

    public void setNombrecobertura(String nombrecobertura) {
        this.nombrecobertura = nombrecobertura;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApellidos1() {
        return apellidos1;
    }

    public void setApellidos1(String apellidos1) {
        this.apellidos1 = apellidos1;
    }

    public String getApellidos2() {
        return apellidos2;
    }

    public void setApellidos2(String apellidos2) {
        this.apellidos2 = apellidos2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    
    
}
