/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.repositorio;

import com.siasstest.sdo.EPS;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author borisgr04
 */
public class RepEPS {
    
    private final Map<String, EPS> Org = new HashMap<String, EPS>();
    private static RepEPS instance= new RepEPS();

    public static RepEPS getInstance() {
        return instance;
    }
    
    private RepEPS() {
        add(new EPS("EPS01","EPS 1","http://190.109.185.138:9091/hapi-fhir-eps/fhir/"));
    }
    public void add(EPS o){
        Org.put(o.getCodigo(),o);
    }
    public EPS get(String Codigo){
        return Org.get(Codigo);
    }
}
