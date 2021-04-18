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
import com.siasstest.fhirprofile.SolicitudCommunication;
import com.siasstest.fhirprofile.SolicitudCommunication.Diagnostico;
import com.siasstest.fhirprofile.SolicitudCommunication.ServiciosPCUPS;
import com.siasstest.sdo.DiagnosticoCIE10;
import com.siasstest.sdo.ServiciosCUPS;
import com.siasstest.sdo.Solicitud;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author borisgr04
 */
public class SolicitudMapper extends DocumentosMapper{

    public SolicitudMapper() {
        super("SOLAUT");
    }
     
    public Solicitud Mapper(SolicitudCommunication c){
        
        Solicitud s = new Solicitud();
        
        s.setTipoDocumento(this.getCodigo());
        s.setEps_ide( c.getRecipient().get(0).getReference().getIdPart() );
        s.setIps_ide( c.getSender().getReference().getIdPart() );
        s.setPac_ide(c.getSubject().getReference().getIdPart() );
        s.setIdDocumento(c.getIdentifier().get(0).getValue());
        
        s.setCama(c.getCama().getValue());
        s.setPrioridadAtencion(c.getPrioridadAtencion().getCode());
        s.setUbicacionPac(c.getUbicacionPaciente().getCode());
        s.setEstado( c.getEstado().getCode() );
        s.setOrigenAtencion(c.getOrigenAtencion().getCode());
        s.setServicioactual(c.getServicio().getValue());
        
        //Cambia esta linea
        //Por las Siguientes para ver como nos va.        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        String date1 = format1.format(c.getSent()); 
        s.setFechaDocumento(date1);
        
        s.setHoraDocumento(c.getHoraSolicitud().getValue());
        
        s.setIdAcuseRecibo(c.getIdAcuseRecibo().getValue());
        
        List<DiagnosticoCIE10> lstCIE10= new ArrayList<DiagnosticoCIE10>();
        for(Diagnostico cie10 : c.getDiagnostico() )
        {
           lstCIE10.add(new DiagnosticoCIE10(cie10.getDiagnostico().getCode(), cie10.getDiagnostico().getDisplay()));
        }
        s.setlDiagnosticoCIE10(lstCIE10);
        
        List<ServiciosCUPS> lstCUPS= new ArrayList<ServiciosCUPS>();
        for(ServiciosPCUPS cups : c.getServiciosCUPS())
        {
           lstCUPS.add(new ServiciosCUPS(cups.getCUPS().getCode(), cups.getCantidad().getValue().toString(),   cups.getCUPS().getDisplay()  ));
        }
        s.setlServiciosCUPS(lstCUPS);
        return s;
    }
     public SolicitudCommunication Mapper(Solicitud solicitud){
         SolicitudCommunication c = new SolicitudCommunication();
                 Patient pat =  new Patient();
        pat.setId("Pacient/"+solicitud.getPac_ide() );
        Organization ips = new Organization();
        ips.setId("Organization/"+ solicitud.getIps_ide() );
        Organization eps = new Organization();
        eps.setId("Organization/"+solicitud.getEps_ide() );
        CodingDt catDt= c.getCategory().addCoding();
        catDt.setCode(this.getCodigo())
                .setSystem(this.getSistemaCodigo())
                .setDisplay(this.getTitulo());
        //Crea la Comunicación
        c.setId( new IdDt(solicitud.getIdDocumento()));
        c.getIdentifier().add(new IdentifierDt(this.getCodigo(), solicitud.getIdDocumento()));
        //c.getId().setValue(solicitud.getId());
        c.getSender().setResource(ips);//Establecer IPS
        c.getRecipient().add(new ResourceReferenceDt(eps));//Establecer EPS
        c.setStatus(CommunicationStatusEnum.IN_PROGRESS);
        c.setSent(new DateTimeDt( solicitud.getFechaDocumento() ));//Fecha de Envio //"2014-01-01"
        //c.setSent(new DateTimeDt( "2014-01-01" ));//Fecha de Envio //"2014-01-01"
        c.getSubject().setResource(pat);//Paciente
            
        c.setHoraSolicitud( new StringDt( solicitud.getHoraDocumento() ) ); //Hora del Documento
        
//
        
        CodingDt prioridad = new CodingDt();
        prioridad.setSystem("http://siass.org/codificacion#tiposprioridad").setCode( solicitud.getPrioridadAtencion()) ;
        c.setPrioridadAtencion( prioridad);
        
        CodingDt ubicacion = new CodingDt();
        ubicacion.setSystem("http://siass.org/codificacion#tiposubicacion").setCode( solicitud.getUbicacionPac()) ;
        c.setUbicacionPaciente(ubicacion);
        
        CodingDt estadoSol = new CodingDt();
        estadoSol.setSystem("http://siass.org/codificacion#estadoSol").setCode( solicitud.getEstado() ) ;
        c.setEstado(estadoSol);
        
        CodingDt origenAtencion = new CodingDt();
        origenAtencion.setSystem("http://siass.org/codificacion#origenatencion").setCode( solicitud.getOrigenAtencion()) ;
        c.setOrigenAtencion(origenAtencion);
        
        c.setServicio( new StringDt(solicitud.getServicioactual()) );
        
        c.setCama( new StringDt(solicitud.getCama()));
        
        c.setHoraSolicitud(new StringDt( solicitud.getHoraDocumento() ) );
        //c.setHoraRecibido( new StringDt( solicitud.gets  ) );
        
        List<Diagnostico> lem= new ArrayList<Diagnostico>();
        Diagnostico ec;
        for(DiagnosticoCIE10 diag:solicitud.getlDiagnosticoCIE10()){
            
            ec= new Diagnostico();
            CodingDt cie10;
            cie10 = new CodingDt();
            cie10.setSystem("http://hl7.org/fhir/sid/icd-10").setCode(diag.getCodigo()).setDisplay(diag.getDescripcion());
            ec.setDiagnostico(cie10);
            lem.add(ec);
        }       
        c.setDiagnostico(lem);
        List<ServiciosPCUPS> lsc= new ArrayList<ServiciosPCUPS>();
        for(ServiciosCUPS serv:solicitud.getlServiciosCUPS()){
            ServiciosPCUPS sc= new ServiciosPCUPS();
            CodingDt cups = new CodingDt();
            cups.setSystem("http://siass.org/codificacion#cups").setCode(serv.getCodigoCUPS()).setDisplay(serv.getDescripcion());
            sc.setCUPS(cups);
            QuantityDt cantidad = new QuantityDt();
            cantidad.setValue(Long.parseLong(serv.getCantidad())).setSystem("http://siass.org/codificacion#eps.unidadMedididas").setUnits("Unidad");
            sc.setCantidad(cantidad);
            lsc.add(sc);
        }       
        c.setServiciosCUPS(lsc);
        return c;
     }
}
