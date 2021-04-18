/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirclient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;


/**
 *
 * @author borisgr04
 */
public class ByAFhirCliente {
    public String encodedPat;
    
    public MethodOutcome Enviar(String EndPoint,IResource r ){
        FhirContext ctx= FhirContext.forDstu2() ;
        IGenericClient client = ctx.newRestfulGenericClient(EndPoint);
        MethodOutcome outcome = client.create().resource(r).execute();
        //encodedPat = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(r);
        return outcome;
    }
}

