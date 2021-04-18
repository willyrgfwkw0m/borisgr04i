/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.repositorio;

import com.siasstest.sdo.Pacientes;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author borisgr04
 */
public class RepPacientes {
    private final Map<String, Pacientes> Org = new HashMap<String, Pacientes>();
    private static RepPacientes instance= new RepPacientes();
    
    public static RepPacientes getInstance() {
        return instance;
    }

    private RepPacientes() {
        add(new Pacientes("12345","TIRADO","PABON","CARLOS","ORLEY","CC","26-01-2000","Calle 9 No. 22-75","453563","Cesar","Valledupar","orley333@gmail.com","RC"));
        add(new Pacientes("54321","LOPEZ","PABON","JUAN","CARLOS","CC","26-01-2000","Calle 9 No. 22-75","453563","Cesar","Valledupar","orley333@gmail.com","RC"));
        add(new Pacientes("67890","MUSA","ARIAS","CAMILA","ANDREA","CC","26-01-2000","Calle 9 No. 22-75","453563","Cesar","Valledupar","orley333@gmail.com","RC"));
    }
    public void add(Pacientes o){
        Org.put(o.getId(),o);
    }
    public Pacientes get(String Id){
        return Org.get(Id);
    }
}
