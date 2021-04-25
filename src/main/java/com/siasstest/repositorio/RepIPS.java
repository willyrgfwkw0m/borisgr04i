/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.repositorio;

import com.siasstest.sdo.CUPS;
import com.siasstest.sdo.IPS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author borisgr04
 */
public class RepIPS {
    
    private final Map<String, IPS> Org = new HashMap<String, IPS>();
    private static RepIPS instance= new RepIPS();

    public static RepIPS getInstance() {
        return instance;
    }
    
    private RepIPS() {
        add(new IPS("IPS01","HOSPITAL PRIMER NIVEL","12345","0912347898","Calle 9 No. 22-75","http://190.109.185.138:9091/hapi-fhir-ips/fhir/","S"));
        add(new IPS("IPS02","LABORATORIO CLÍNICO","54321","0912347898","Calle 12 No. 54-75","http://190.109.185.138:9091/hapi-fhir-ips/fhir/","R"));
        add(new IPS("IPS03","CLINICA MEDIANA Y ALTA COMPLEJIDAD","67890","0912347898","Calle 54 No. 23-75","http://190.109.185.138:9091/hapi-fhir-ips/fhir/","RS"));
    }
    public void add(IPS o){
        Org.put(o.getCodigo(),o);
    }
    public IPS get(String Codigo){
        return Org.get(Codigo);
    }
    public List<IPS> getValues() {
        List<IPS> list = new ArrayList<IPS>(Org.values());
        return list;
    }
}
