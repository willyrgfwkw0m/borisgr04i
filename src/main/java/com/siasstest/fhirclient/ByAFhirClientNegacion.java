/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirclient;

import ca.uhn.fhir.rest.api.MethodOutcome;
import com.siasstest.fhirmap.NegacionMapper;
import com.siasstest.fhirprofile.NegacionCommunication;
import com.siasstest.sdo.Negacion;

/**
 *
 * @author borisgr04
 */
public class ByAFhirClientNegacion {
    String serverBaseUrl; 
    public ByAFhirClientNegacion(String serverBaseUrl) {
        this.serverBaseUrl = serverBaseUrl;
    }
    public String Enviar(Negacion sol){
        NegacionMapper sm = new  NegacionMapper();
        NegacionCommunication c= sm.Mapper(sol);
        ByAFhirCliente client= new ByAFhirCliente();
        MethodOutcome outcome = client.Enviar(serverBaseUrl, c);
        return outcome.getId().toString();
    }
}
