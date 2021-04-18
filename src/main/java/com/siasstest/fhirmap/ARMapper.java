/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirmap;

import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.CommunicationStatusEnum;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.StringDt;
import com.siasstest.fhirprofile.ARCommunication;
import com.siasstest.sdo.AcuseRecibo;
import java.text.SimpleDateFormat;

/**
 *
 * @author borisgr04
 */
public class ARMapper extends  DocumentosMapper{

    public ARMapper(String Codigo) {
        super(Codigo);
    }
        
    public AcuseRecibo Mapper(ARCommunication c){
        AcuseRecibo o = new AcuseRecibo();
        
        //o.setTipoDocumento(c.getTipDocumento().getValue()); //Tipo de Documento
        o.setIdDocumento(c.getIdentifier().get(0).getValue()); //Número del Documento
        o.setIdDocRecibido(c.getNumDocumento().getValue());//Numero de Documento Recibido
        //o.setFechaDocumento( c.getSent().toString() );//Fecha del Documento
        o.setHoraDocumento(c.getHora().getValue()); //Hora del Documento
        //Cambia esta linea
        //Por las Siguientes para ver como nos va.        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        String date1 = format1.format(c.getSent()); 
        o.setFechaDocumento(date1);
        c.setId( new IdDt(o.getIdDocumento()));   
        
        if("ARSOLAUT".equals(this.getCodigo())){
            o.setIps_ide( c.getRecipient().get(0).getReference().getIdPart() );
            o.setEps_ide( c.getSender().getReference().getIdPart() );
        }
        if("ARAUTSER".equals(this.getCodigo())){
            o.setEps_ide( c.getRecipient().get(0).getReference().getIdPart() );
            o.setIps_ide( c.getSender().getReference().getIdPart() );
        }
        if("ARNEGSER".equals(this.getCodigo())){
            o.setEps_ide( c.getRecipient().get(0).getReference().getIdPart() );
            o.setIps_ide( c.getSender().getReference().getIdPart() );
        }
        
        return o;
    }
    public ARCommunication  Mapper(AcuseRecibo ar){
        
        ARCommunication c = new ARCommunication();
        Patient pat =  new Patient();
        pat.setId("Pacient/"+ ar.getPac_ide() );
        Organization ips = new Organization();
        ips.setId("Organization/"+ ar.getIps_ide() );
        Organization eps = new Organization();
        eps.setId("Organization/"+ar.getEps_ide() );
        CodingDt catDt= c.getCategory().addCoding();
        catDt.setCode(this.getCodigo())
                .setSystem(this.getSistemaCodigo())
                .setDisplay(this.getTitulo());
        
        c.getIdentifier().add(new IdentifierDt(this.getCodigo(), ar.getIdDocumento()));
        
        c.setSent(new DateTimeDt( ar.getFechaDocumento() ));//Fecha de Envio //"2014-01-01"
        
        c.getSubject().setResource(pat);//Paciente
        //Crea la Comunicación
        if("ARSOLAUT".equals(this.getCodigo())){
            c.getSender().setResource(eps);
            c.getRecipient().add(new ResourceReferenceDt(ips));
        }
        if("ARAUTSER".equals(this.getCodigo())){
            c.getSender().setResource(ips);
            c.getRecipient().add(new ResourceReferenceDt(eps));
        }
        if("ARNEGSER".equals(this.getCodigo())){
            c.getSender().setResource(ips);
            c.getRecipient().add(new ResourceReferenceDt(eps));
        }
        c.setHora( new StringDt( ar.getHoraDocumento() ) ); //Hora del Documento
        c.setStatus(CommunicationStatusEnum.IN_PROGRESS);
        c.setNumDocumento( new StringDt(ar.getIdDocRecibido()));
        //c.setTipDocumento( new StringDt(ar.getDocRecibido().getTipoDocumento()));
        
        return c;
    }
}
