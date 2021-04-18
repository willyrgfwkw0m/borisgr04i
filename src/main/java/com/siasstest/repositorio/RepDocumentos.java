/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.repositorio;

import com.siasstest.sdo.Documentos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author borisgr04
 */
public class RepDocumentos {
    private final Map<String, Long> Consecutivos = new HashMap<String, Long>();
    private final Map<String, Documentos> Org = new HashMap<String, Documentos>();
    private static RepDocumentos instance= new RepDocumentos();

    public static RepDocumentos getInstance() {
        return instance;
    }
    public Long ObtenerConsecutivo(String Tipo){
        Long c= Consecutivos.get(Tipo)+1;
        Consecutivos.put(Tipo, c);
        return c;
    }
    private RepDocumentos() {
            Consecutivos.put("SOLAUT", 0L);
            Consecutivos.put("AUTSER", 0L);
            Consecutivos.put("NEGSER", 0L);
            Consecutivos.put("ARSOAU", 0L);
            Consecutivos.put("ARNESE", 0L);
            Consecutivos.put("ARAUSE", 0L);
    }
    public void add(Documentos s){
        
        Org.put(s.getIdDocumento(),s);
    }
    public void update(Documentos s){
        Documentos ss=get(s.getIdDocumento());
        Org.put(ss.getIdDocumento(),ss);
    }
    
    public Documentos get(String id){
        return Org.get(id);
    }
    
    public List<Documentos> getValues() {
        List<Documentos> list = new ArrayList<Documentos>(Org.values());
        return list;
    }
    
    public List<Documentos> getValuesPac(String idPaciente) {
        List<Documentos> list = new ArrayList<Documentos>(Org.values());
        List<Documentos> list2 = new ArrayList<Documentos>();
        for(int i = 0;i<list.size();i++){
            if((list.get(i).getPac_ide().equals(idPaciente))&&(list.get(i).getTipoDocumento().equals("SOLAUT"))){
                list2.add(list.get(i));
            }
        }             
        return list2;
    }
}
