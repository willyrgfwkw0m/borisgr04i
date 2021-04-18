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
public class ServiciosCUPS {
    private String CodigoCUPS;
    private String Cantidad;
    private String Descripcion;

    public ServiciosCUPS(String CodigoCUPS, String Cantidad, String Descripcion) {
        this.CodigoCUPS = CodigoCUPS;
        this.Cantidad = Cantidad;
        this.Descripcion = Descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the CodigoCUPS
     */
    public String getCodigoCUPS() {
        return CodigoCUPS;
    }

    /**
     * @param CodigoCUPS the CodigoCUPS to set
     */
    public void setCodigoCUPS(String CodigoCUPS) {
        this.CodigoCUPS = CodigoCUPS;
    }

    /**
     * @return the Cantidad
     */
    public String getCantidad() {
        return Cantidad;
    }

    /**
     * @param Cantidad the Cantidad to set
     */
    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }
}
