/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirclient;

import ca.uhn.fhir.rest.api.MethodOutcome;
import com.siasstest.fhirmap.SolicitudMapper;
import com.siasstest.fhirprofile.SolicitudCommunication;
import com.siasstest.sdo.Solicitud;

/**
 *
 * @author borisgr04
 */
public class ByAFhirClientSolicitud {
    
    String serverBaseUrl = "http://localhost:9090/hapi-fhir-eps/fhir/";
    public String Enviar(Solicitud sol){
        SolicitudMapper sm = new  SolicitudMapper();
        SolicitudCommunication c= sm.Mapper(sol);
        ByAFhirCliente client= new ByAFhirCliente();
        MethodOutcome outcome = client.Enviar(serverBaseUrl, c);
       return outcome.getId().toString();
    }

}
