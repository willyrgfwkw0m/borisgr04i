/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirclient;

import ca.uhn.fhir.rest.api.MethodOutcome;
import com.siasstest.fhirmap.ARMapper;
import com.siasstest.fhirprofile.ARCommunication;
import com.siasstest.sdo.AcuseRecibo;

/**
 *
 * @author borisgr04
 */
public class ByAFhirClientARecibo {
    String serverBaseUrl; //= "http://localhost:9090/hapi-fhir-eps/fhir/";

    public ByAFhirClientARecibo(String serverBaseUrl) {
        this.serverBaseUrl = serverBaseUrl;
    }
    
    public String Enviar(AcuseRecibo ar){
        ARMapper arm = new  ARMapper(ar.getTipoDocumento());
        ARCommunication c= arm.Mapper(ar);
        ByAFhirCliente client= new ByAFhirCliente();
        MethodOutcome outcome = client.Enviar(serverBaseUrl, c);
        
        return outcome.getId().toString();//+client.encodedPat;
    }
 
}
