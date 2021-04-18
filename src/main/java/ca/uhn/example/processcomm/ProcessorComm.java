/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uhn.example.processcomm;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Communication;
import com.siasstest.bussines.GestionEPS;
import com.siasstest.bussines.GestionIPS;
import com.siasstest.fhirmap.ARMapper;
import com.siasstest.fhirmap.AutorizacionMapper;
import com.siasstest.fhirmap.NegacionMapper;
import com.siasstest.fhirmap.SolicitudMapper;
import com.siasstest.fhirprofile.ARCommunication;
import com.siasstest.fhirprofile.AutorizacionCommunication;
import com.siasstest.fhirprofile.NegacionCommunication;
import com.siasstest.fhirprofile.SolicitudCommunication;
import com.siasstest.sdo.AcuseRecibo;
import com.siasstest.sdo.Autorizacion;
import com.siasstest.sdo.Documentos;
import com.siasstest.sdo.Negacion;
import com.siasstest.sdo.Solicitud;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author borisgr04
 */
public class ProcessorComm {
    private Communication com;
    private String encodedComm;
    FhirContext ctx = FhirContext.forDstu2();
    
     //Procesador
    public void Procesar(Communication c){
        com=c;
        encodedComm = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(c);
        //-------------------------EPS
        if ("SOLAUT".equals(c.getCategory().getCoding().get(0).getCode())){
            ProcesarSolicitud();//EPS
        }
        if ("ARAUSE".equals(c.getCategory().getCoding().get(0).getCode())){
            ProcesarARAutorizacion();//IPS
        }
        if ("ARNESE".equals(c.getCategory().getCoding().get(0).getCode())){
            ProcesarARNegacion();
        }
        //-------------------------IPS
        if ("ARSOAU".equals(c.getCategory().getCoding().get(0).getCode())){
            ProcesarARSolicitud();//IPS
        }
        if ("AUTSER".equals(c.getCategory().getCoding().get(0).getCode())){
            ProcesarAutorizacion();
        }
        if ("NEGSER".equals(c.getCategory().getCoding().get(0).getCode())){
            ProcesarNegacion();
        }

        
     }
    //-------------------------EPS
    //Eps Recibir Solicitud
    private void ProcesarSolicitud(){
            SolicitudMapper mapper = new SolicitudMapper();
            SolicitudCommunication Comm = ctx.newJsonParser().parseResource(SolicitudCommunication.class, encodedComm);
            Solicitud sol;
            sol = mapper.Mapper(Comm);
            GestionEPS bll= new GestionEPS();
            bll.NuevaSolicitud(sol);
    }
    
    //Eps Recibe Acuse de Recibo Autorizacion
    private void ProcesarARAutorizacion(){
            ARMapper mapper = new ARMapper("ARAUSE");
            ARCommunication Comm = ctx.newJsonParser().parseResource(ARCommunication.class, encodedComm);
            AcuseRecibo ar = mapper.Mapper(Comm);
            GestionEPS sbll= new GestionEPS();
            sbll.Rec_ARAutorizacion(ar);
    }
    
     //Eps Recibe Acuse de Recibo Negacion
    private void ProcesarARNegacion(){
            ARMapper mapper = new ARMapper("ARNESE");
            ARCommunication Comm = ctx.newJsonParser().parseResource(ARCommunication.class, encodedComm);
            AcuseRecibo ar = mapper.Mapper(Comm);
            GestionEPS sbll= new GestionEPS();
            sbll.Rec_ARNegacion(ar);
    }
    
    //-------------------------IPS
    //Ips Recibe Acuse de Recibo
    private void ProcesarARSolicitud(){
            ARMapper mapper = new ARMapper("ARSOAU");
            ARCommunication Comm = ctx.newJsonParser().parseResource(ARCommunication.class, encodedComm);
            AcuseRecibo ar = mapper.Mapper(Comm);
            GestionIPS sbll= new GestionIPS();
            sbll.Rec_ARSolicitud(ar);
    }
    
    //Ips Recibe Autorizacion
    private void ProcesarAutorizacion(){
            AutorizacionMapper mapper = new AutorizacionMapper();
            AutorizacionCommunication Comm = ctx.newJsonParser().parseResource(AutorizacionCommunication.class, encodedComm);
            Autorizacion aut = mapper.Mapper(Comm);
            GestionIPS sbll= new GestionIPS();
            sbll.Rec_Autorizacion(aut);
    }
    
    //Ips Recibe Negacion
    private void ProcesarNegacion(){
            NegacionMapper mapper = new NegacionMapper();
            NegacionCommunication Comm = ctx.newJsonParser().parseResource(NegacionCommunication.class, encodedComm);
            //3. Pasar a ByAAutorizaciones
            Negacion neg = mapper.Mapper(Comm);
            GestionIPS sbll= new GestionIPS();
            sbll.Rec_Negacion(neg);
    }

    public List<Communication> ObtenerSolicitudes(String PacienteId) {
        GestionEPS sbll= new GestionEPS();
        List<Communication> lst= new ArrayList<Communication>();
        SolicitudMapper mapper= new SolicitudMapper();
        for(Documentos d: sbll.GetSolicitudesxPaciente(PacienteId)){
            lst.add( (SolicitudCommunication) mapper.Mapper((Solicitud) d) );
        }
        return lst;
    }
   
   
   
}
