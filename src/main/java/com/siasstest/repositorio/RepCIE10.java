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
public class RepCIE10 {

    private final Map<String, CIE10> Org = new HashMap<String, CIE10>();
    private static RepCIE10 instance= new RepCIE10();

    public static RepCIE10 getInstance() {
        return instance;
    }
    
    private RepCIE10() {
        add(new CIE10("A01.0","FIEBRE TIFOIDEA"));
        add(new CIE10("A06.7","AMEBIASIS CUTANEA"));
        add(new CIE10("A20.0","PESTE BUBONICA"));
        add(new CIE10("A20.2","PESTE NEUMONICA"));
    }
    public void add(CIE10 o){
        Org.put(o.getCodigo(),o);
    }
    
    public CIE10 get(String Codigo){
        return Org.get(Codigo);
    }
    
    public List<CIE10> getValues() {
        List<CIE10> list = new ArrayList<CIE10>(Org.values());
        return list;
    }
}
