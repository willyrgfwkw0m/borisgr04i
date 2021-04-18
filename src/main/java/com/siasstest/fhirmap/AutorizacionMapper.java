/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirmap;

import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.CommunicationStatusEnum;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.StringDt;
import com.siasstest.fhirprofile.AutorizacionCommunication;
import com.siasstest.fhirprofile.AutorizacionCommunication.ServiciosACUPS;
import com.siasstest.sdo.Autorizacion;
import com.siasstest.sdo.ServiciosCUPS;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author borisgr04
 */
public class AutorizacionMapper extends DocumentosMapper{
   

    public AutorizacionMapper() {
        super("AUTSER");
    }
     
     
    public Autorizacion Mapper(AutorizacionCommunication c){
        Autorizacion o = new Autorizacion();
        o.setTipoDocumento(this.getCodigo());
        o.setIps_ide( c.getRecipient().get(0).getReference().getIdPart() );
        o.setEps_ide( c.getSender().getReference().getIdPart() );
        o.setIdDocumento(c.getIdentifier().get(0).getValue());
        o.setIdAcuseRecibo(c.getIdAcuseRecibo().getValue());
        o.setIdSol(c.getIdSolicitud().getValue());
        //Cambia esta linea
        //Por las Siguientes para ver como nos va.        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        String date1 = format1.format(c.getSent()); 
        o.setFechaDocumento(date1);
        o.setHoraDocumento(c.getHora().getValue()); //Hora del Documento
        List<ServiciosCUPS> lstCUPS= new ArrayList<ServiciosCUPS>();
        for(ServiciosACUPS cups : c.getServiciosCUPS())
        {
           lstCUPS.add(new ServiciosCUPS(cups.getCUPS().getCode(), cups.getCantidad().getUnits(),   cups.getCUPS().getDisplay()  ));
        }
        o.setlServiciosCUPS(lstCUPS);
        return o;
    }
     public AutorizacionCommunication Mapper(Autorizacion o){
         AutorizacionCommunication c = new AutorizacionCommunication();
        
        Patient pat =  new Patient();
        pat.setId("Pacient/"+o.getIps_ide());
        Organization ips = new Organization();
        ips.setId("Organization/"+ o.getIps_ide() );
        Organization eps = new Organization();
        eps.setId("Organization/"+o.getEps_ide() );
        CodingDt catDt= c.getCategory().addCoding();
        catDt.setCode(this.getCodigo())
                .setSystem(this.getSistemaCodigo())
                .setDisplay(this.getTitulo());
        //Crea la Comunicación
        c.getIdentifier().add(new IdentifierDt("AUTSER", o.getIdDocumento()));
        c.setId( new IdDt(o.getIdDocumento()));   
        c.getSender().setResource(eps);
        c.getRecipient().add(new ResourceReferenceDt(ips));
        c.setStatus(CommunicationStatusEnum.IN_PROGRESS);
        //c.setSent(new DateTimeDt( solicitud.getFecha() ));//Fecha de Envio //"2014-01-01"
        c.setSent(new DateTimeDt( o.getFechaDocumento() ));//Fecha de Envio //"2014-01-01"
        c.getSubject().setResource(pat);//Paciente
        c.setIdAcuseRecibo(new StringDt(o.getIdAcuseRecibo()));
        c.setIdSolicitud( new StringDt( o.getIdSol()) );
        
        c.setHora( new StringDt( o.getHoraDocumento() ) ); //Hora del Documento
        
        List<ServiciosACUPS> lsc= new ArrayList<ServiciosACUPS>();
        for(ServiciosCUPS serv:o.getlServiciosCUPS()){
            ServiciosACUPS sc= new ServiciosACUPS();
            CodingDt cups = new CodingDt();
            cups.setSystem("http://siass.org/codificacion#cups").setCode(serv.getCodigoCUPS()).setDisplay(serv.getDescripcion());
            sc.setCUPS(cups);
            QuantityDt cantidad = new QuantityDt();
            cantidad.setValue(1).setSystem("http://siass.org/codificacion#eps.unidadMedididas").setUnits("Unidad");
            sc.setCantidad(cantidad);
            lsc.add(sc);
        }       
        c.setServiciosCUPS(lsc);
        return c;
     }
}
