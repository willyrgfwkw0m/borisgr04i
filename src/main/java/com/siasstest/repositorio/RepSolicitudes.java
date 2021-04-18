/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.repositorio;

import com.siasstest.old.SolicitudesSDO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author borisgr04
 */
public class RepSolicitudes {
    private final Map<String, SolicitudesSDO> Org = new HashMap<String, SolicitudesSDO>();
    private static RepSolicitudes instance= new RepSolicitudes();

    public static RepSolicitudes getInstance() {
        return instance;
    }
    
    private RepSolicitudes() {
       // add(new SolicitudesSDO("J09","GRIPA"));
       // add(new SolicitudesSDO("J0901","RESFRIADO"));
    }
    public void add(SolicitudesSDO s){
        Org.put(s.getId(),s);
    }
    public void update(SolicitudesSDO s){
        SolicitudesSDO ss=get(s.getId());
        s.setFechaRecibido(s.getFechaRecibido());
        Org.put(ss.getId(),ss);
    }
    
    public SolicitudesSDO get(String id){
        return Org.get(id);
    }
    
    public List<SolicitudesSDO> getValues() {
        List<SolicitudesSDO> list = new ArrayList<SolicitudesSDO>(Org.values());
        return list;
    } 
}
