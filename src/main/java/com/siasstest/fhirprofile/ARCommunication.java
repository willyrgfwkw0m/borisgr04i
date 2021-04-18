/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirprofile;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.dstu2.resource.Communication;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.StringDt;

/**
 *
 * @author borisgr04
 */
@ResourceDef(name = "Communication")
public class ARCommunication extends Communication {

    @Description(shortDefinition = "N° Documento al que se le aplica acuse de recibo")
    @Child(name = "numDocumento")
    @Extension(url = "http://siass.org/acuserecibo.html/#numdoc", definedLocally = false, isModifier = false)
    private StringDt numDocumento;

    @Description(shortDefinition = "Tipo Documento al que se le realiza Acuse de Recibo")
    @Child(name = "tipDocumento")
    @Extension(url = "http://siass.org/acuserecibo.html/#tipdoc", definedLocally = false, isModifier = false)
    private StringDt tipDocumento;

    @Description(shortDefinition = "Hora de Acuse")
    @Child(name = "hora")
    @Extension(url = "http://siass.org/Acuse.html#horaAcuse", definedLocally = false, isModifier = false)
    private StringDt hora;
    /* *****************************
     * Getters and setters
     * *****************************/

    public StringDt getHora() {
        if (hora == null) {
            hora = new StringDt();
        }
        return hora;
    }

    public void setHora(StringDt thehora) {
        hora = thehora;
    }

    public StringDt getTipDocumento() {
        if (tipDocumento == null) {
            tipDocumento = new StringDt();
        }
        return tipDocumento;
    }

    public void setTipDocumento(StringDt tipDocumento) {
        this.tipDocumento = tipDocumento;
    }

    public StringDt getNumDocumento() {
        if (numDocumento == null) {
            numDocumento = new StringDt();
        }
        return numDocumento;
    }

    public void setNumDocumento(StringDt numDocumento) {
        this.numDocumento = numDocumento;
    }

}
