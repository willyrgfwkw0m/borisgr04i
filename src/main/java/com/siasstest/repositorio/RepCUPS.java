/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.repositorio;

import com.siasstest.sdo.CIE10;
import com.siasstest.sdo.CUPS;
import com.siasstest.sdo.EPS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author borisgr04
 */
public class RepCUPS {
    
    private final Map<String, CUPS> Org = new HashMap<String, CUPS>();
    private static RepCUPS instance= new RepCUPS();

    public static RepCUPS getInstance() {
        return instance;
    }
    
    private RepCUPS() {
        add(new CUPS("010901","PUNCION SUBDURAL"));
        add(new CUPS("011100","BIOPSIA DE CRANEO SOD"));
        add(new CUPS("014100","TALAMOTOMIA SOD"));
        add(new CUPS("597104","CISTOURETROPEXIA VAGINAL"));
        add(new CUPS("777000","TOMA DE INJERTO OSEO SOD"));
        add(new CUPS("778923","HEMIPELVECTOMIA"));
    }
    public void add(CUPS o){
        Org.put(o.getCodigo(),o);
    }
    
    public CUPS get(String Codigo){
        return Org.get(Codigo);
    }
    public List<CUPS> getValues() {
        List<CUPS> list = new ArrayList<CUPS>(Org.values());
        return list;
    }
}
