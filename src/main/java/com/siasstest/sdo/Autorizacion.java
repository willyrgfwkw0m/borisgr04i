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
public class Autorizacion extends RptaSolicitud {
    
    private List<ServiciosCUPS> lServiciosCUPS= new ArrayList<ServiciosCUPS>();
  
    public List<ServiciosCUPS> getlServiciosCUPS() {
        return lServiciosCUPS;
    }

    public void setlServiciosCUPS(List<ServiciosCUPS> lServiciosCUPS) {
        this.lServiciosCUPS = lServiciosCUPS;
    }
    
    
}
