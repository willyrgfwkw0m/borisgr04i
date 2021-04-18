/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirclient;

import ca.uhn.fhir.rest.api.MethodOutcome;
import com.siasstest.fhirmap.AutorizacionMapper;
import com.siasstest.fhirprofile.AutorizacionCommunication;
import com.siasstest.sdo.Autorizacion;

/**
 *
 * @author borisgr04
 */
public class ByAFhirClientAutorizacion {
    String serverBaseUrl; //= "http://localhost:9090/hapi-fhir-eps/fhir/";

    public ByAFhirClientAutorizacion(String serverBaseUrl) {
        this.serverBaseUrl = serverBaseUrl;
    }
    public String Enviar(Autorizacion aut){
        AutorizacionMapper sm = new  AutorizacionMapper();
        AutorizacionCommunication c= sm.Mapper(aut);
        ByAFhirCliente client= new ByAFhirCliente();
        MethodOutcome outcome = client.Enviar(serverBaseUrl, c);
        
        return outcome.getId().toString();
    }
   
}
