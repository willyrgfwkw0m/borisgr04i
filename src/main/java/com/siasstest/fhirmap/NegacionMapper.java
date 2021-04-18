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
import com.siasstest.fhirprofile.NegacionCommunication;
import com.siasstest.fhirprofile.NegacionCommunication.ServiciosNCUPS;
import com.siasstest.sdo.Negacion;
import com.siasstest.sdo.ServiciosCUPSN;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author borisgr04
 */
public class NegacionMapper extends DocumentosMapper{
   

    public NegacionMapper() {
        super("NEGSER");
    }
     
     
    public Negacion Mapper(NegacionCommunication c){
        Negacion o = new Negacion();
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
        List<ServiciosCUPSN> lstCUPS= new ArrayList<ServiciosCUPSN>();
        for(ServiciosNCUPS cups : c.getServiciosCUPS())
        {
           lstCUPS.add(new ServiciosCUPSN(cups.getJustificacion().getValue(),
                   cups.getFundamento().getValue(),cups.getAlternativa().getValue(),
                   cups.getCUPS().getCode(), cups.getCantidad().getUnits(),
                   cups.getCUPS().getDisplay()  ));
        }
        o.setlServiciosCUPS(lstCUPS);
        return o;
    }
     public NegacionCommunication Mapper(Negacion o){
         NegacionCommunication c = new NegacionCommunication();
        
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
        c.getIdentifier().add(new IdentifierDt(o.getTipoDocumento(), o.getIdDocumento()));
        
       c.getSender().setResource(eps);
        c.getRecipient().add(new ResourceReferenceDt(ips));
        
        c.setId( new IdDt(o.getIdDocumento()));        
        
        c.setStatus(CommunicationStatusEnum.IN_PROGRESS);
        //c.setSent(new DateTimeDt( solicitud.getFecha() ));//Fecha de Envio //"2014-01-01"
        c.setSent(new DateTimeDt( o.getFechaDocumento() ));//Fecha de Envio //"2014-01-01"
        c.getSubject().setResource(pat);//Paciente
        c.setIdAcuseRecibo(new StringDt(o.getIdAcuseRecibo()));
        c.setIdSolicitud(new StringDt( o.getIdSol()) );
        
        c.setHora( new StringDt( o.getHoraDocumento() ) ); //Hora del Documento
        
        List<ServiciosNCUPS> lsc= new ArrayList<ServiciosNCUPS>();
        for(ServiciosCUPSN serv:o.getlServiciosCUPS()){
            ServiciosNCUPS sc= new ServiciosNCUPS();
            CodingDt cups = new CodingDt();
            cups.setSystem("http://siass.org/codificacion#cups").setCode(serv.getCodigoCUPS()).setDisplay(serv.getDescripcion());
            sc.setCUPS(cups);
            QuantityDt cantidad = new QuantityDt();
            cantidad.setValue(1).setSystem("http://siass.org/codificacion#eps.unidadMedididas").setUnits("Unidad");
            sc.setCantidad(cantidad);
            sc.setAlternativa(new StringDt(serv.getAlternativa()));
            sc.setFundamento(new StringDt(serv.getFundamentoLegal()));
            sc.setJustificacion(new StringDt(serv.getJustificacion()));
            lsc.add(sc);
        }       
        c.setServiciosNCUPS(lsc);
        return c;
     }
}
