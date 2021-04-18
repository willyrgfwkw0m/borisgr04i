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
public class Negacion extends RptaSolicitud {
    private List<ServiciosCUPSN> lServiciosCUPS= new ArrayList<ServiciosCUPSN>();

    public List<ServiciosCUPSN> getlServiciosCUPS() {
        return lServiciosCUPS;
    }

    public void setlServiciosCUPS(List<ServiciosCUPSN> lServiciosCUPS) {
        this.lServiciosCUPS = lServiciosCUPS;
    }
    
}
