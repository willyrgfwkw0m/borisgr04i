/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.bussines;

import com.siasstest.fhirclient.ByAFhirClientARecibo;
import com.siasstest.fhirclient.ByAFhirClientAutorizacion;
import com.siasstest.fhirclient.ByAFhirClientNegacion;
import com.siasstest.repositorio.RepDocumentos;
import com.siasstest.repositorio.RepEPS;
import com.siasstest.repositorio.RepIPS;
import com.siasstest.repositorio.RepPacientes;
import com.siasstest.sdo.AcuseRecibo;
import com.siasstest.sdo.Autorizacion;
import com.siasstest.sdo.Clasificacion;
import com.siasstest.sdo.Documentos;
import com.siasstest.sdo.Negacion;
import com.siasstest.sdo.Solicitud;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author borisgr04
 */
public class GestionEPS {
    //1.Server
    public String NuevaSolicitud(Solicitud sol){
        sol.setEstado("SOLICITUD");
        //Se Agregan Objetos Relacionados//Deben Existir Previamente
        sol.setIps(RepIPS.getInstance().get(sol.getIps_ide()));
        sol.setEps(RepEPS.getInstance().get(sol.getEps_ide()));
        sol.setPaciente(RepPacientes.getInstance().get(sol.getPac_ide()));
        RepDocumentos.getInstance().add(sol);
        return sol.getIdDocumento();
    }
        
    //2.Web
    public String ARSolicitud(AcuseRecibo ar){
        ar.setTipoDocumento("ARSOAU");
        Solicitud doc=(Solicitud)RepDocumentos.getInstance().get(ar.getIdDocRecibido());
        //ar.setDocRecibido(doc);
        doc.setEstado("ARSOLICITUD");
        
        ar.setIps_ide(doc.getIps_ide());
        ar.setEps_ide(doc.getEps_ide());
        ar.setPac_ide(doc.getPac_ide());
        
        doc.setAcuseRecibo(ar);

        Long c=RepDocumentos.getInstance().ObtenerConsecutivo(ar.getTipoDocumento());
        ar.setIdDocumento(doc.getEps_ide()+ar.getTipoDocumento()+"-"+c);
        
        
        System.out.println(ar.getIdDocumento()+ doc.getIps().getEndPoint());
        
        Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();    
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        String date1 = format1.format(date); 
        
        SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
        String time = format2.format(date);
        
        ar.setHoraDocumento(time);
        ar.setFechaDocumento(date1);
        
        RepDocumentos.getInstance().add(ar);
        
        ByAFhirClientARecibo fhir= new ByAFhirClientARecibo(doc.getIps().getEndPoint());
        String Respuesta = fhir.Enviar(ar);
        return ar.getIdDocumento() + " Resultado Remoto:"+Respuesta;// 
    }
    
    //3.1. a Web
    public String Autorizar(Autorizacion doc){
        
        doc.setEstado("AUTORIZACION");
        //Se Agregan Objetos Relacionados//Deben Existir Previamente
        doc.setTipoDocumento("AUTSER");
        
        Solicitud sol=(Solicitud) RepDocumentos.getInstance().get(doc.getIdSol());
        
        
        doc.setEps_ide(sol.getEps_ide());
        doc.setPac_ide(sol.getPac_ide());
        
        Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();    
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        String date1 = format1.format(date); 
        
        SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
        String time = format2.format(date);
        
        Long c=RepDocumentos.getInstance().ObtenerConsecutivo(doc.getTipoDocumento());
        
        
        doc.setFechaDocumento(date1);
        doc.setHoraDocumento(time);
        doc.setIdDocumento(doc.getEps_ide()+doc.getTipoDocumento()+"-"+c);

        doc.setIps(RepIPS.getInstance().get(doc.getIps_ide()));
        doc.setEps(RepEPS.getInstance().get(doc.getEps_ide()));
        doc.setPaciente(RepPacientes.getInstance().get(doc.getPac_ide()));
        
        
        //doc.setSolicitud();
        sol.setEstado("AUTORIZACION");
        sol.setRptaSolicitud(doc);
        RepDocumentos.getInstance().add(doc);
        System.out.println(doc.getIdDocumento()+ sol.getIps().getEndPoint());
        //return "Sol"+doc.getSolicitud().getIps().getEndPoint()+"Aut:"+doc.getIps().getEndPoint();
        ByAFhirClientAutorizacion fhir= new ByAFhirClientAutorizacion(sol.getIps().getEndPoint());
        String Respuesta = fhir.Enviar(doc);
        return doc.getIdDocumento()+ " Resultado Remoto:"+Respuesta;// 
    }
   
    //4. Server
    public String Rec_ARAutorizacion(AcuseRecibo ar){
        //ar.setDocRecibido( RepDocumentos.getInstance().get(ar.getIdDocRecibido()) );
        Autorizacion aut=(Autorizacion)RepDocumentos.getInstance().get(ar.getIdDocRecibido());//Busca la Autorizacion
        Solicitud sol=(Solicitud)RepDocumentos.getInstance().get(aut.getIdSol());//Busca la Solicitud
        sol.setEstado("ARAUTORIZACION");
        aut.setAcuseRecibo(ar);
        RepDocumentos.getInstance().add(ar);
        return ar.getIdDocumento();
    }
    
    //3.b Web
    public String Negar(Negacion doc){
        
        doc.setEstado("NEGACION");
        //Se Agregan Objetos Relacionados//Deben Existir Previamente
        doc.setTipoDocumento("NEGSER");
        
        Solicitud sol=(Solicitud) RepDocumentos.getInstance().get(doc.getIdSol());
        
        doc.setEps_ide(sol.getEps_ide());
        doc.setPac_ide(sol.getPac_ide());
        doc.setIps_ide(sol.getIps_ide());
        
        Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();    
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        String date1 = format1.format(date); 
        
        SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
        String time = format2.format(date);
        
        Long c=RepDocumentos.getInstance().ObtenerConsecutivo(doc.getTipoDocumento());
        
        doc.setFechaDocumento(date1);
        doc.setHoraDocumento(time);
        doc.setIdDocumento(doc.getEps_ide()+doc.getTipoDocumento()+"-"+c);
        
        doc.setIps(RepIPS.getInstance().get(doc.getIps_ide()));
        doc.setEps(RepEPS.getInstance().get(doc.getEps_ide()));
        doc.setPaciente(RepPacientes.getInstance().get(doc.getPac_ide()));
        
        //doc.setSolicitud((Solicitud) RepDocumentos.getInstance().get(doc.getIdSol()));
        //doc.getSolicitud().setEstado("NEGACION");
        //doc.getSolicitud().setRptaSolicitud(doc);
        
        
        //doc.setSolicitud();
        sol.setEstado("NEGACION");
        sol.setRptaSolicitud(doc);
        
        System.out.println(doc.getIdDocumento()+ sol.getIps().getEndPoint());
        
        RepDocumentos.getInstance().add(doc);
        
        ByAFhirClientNegacion fhir= new ByAFhirClientNegacion(doc.getIps().getEndPoint());
        String Respuesta = fhir.Enviar(doc);
        return doc.getIdDocumento()+ " Resultado Remoto:"+Respuesta;// 
    }
 
    //4. Server
    public String Rec_ARNegacion(AcuseRecibo ar){
        Negacion neg=(Negacion)RepDocumentos.getInstance().get(ar.getIdDocRecibido());//Busca la Autorizacion
        //ar.setDocRecibido( RepDocumentos.getInstance().get(ar.getIdDocRecibido()) );
        Solicitud sol=(Solicitud)RepDocumentos.getInstance().get(neg.getIdSol());//Busca la Solicitud
        sol.setEstado("ARNEGACION");
        neg.setAcuseRecibo(ar);
        RepDocumentos.getInstance().add(ar);
        return ar.getIdDocumento();
    }
    
    // Web Consulta
    public List<Clasificacion> GetSolicitudesxE(){
        List<Clasificacion> lst=  new ArrayList<Clasificacion>();
        //Solicitudes Sin Recibir
        int SOLICITUD=0;
        int ARSOLICITUD=0;
        int ARAUTORIZACION=0;
        int AUTORIZACION=0;
        int ARNEGACION=0;
        int NEGACION=0;
        for(Documentos d : RepDocumentos.getInstance().getValues()){
            if(d instanceof Solicitud){
                Solicitud s= (Solicitud) d;
                if("SOLICITUD".equals(s.getEstado()))SOLICITUD++;
                if("ARSOLICITUD".equals(s.getEstado()))ARSOLICITUD++;
                if("AUTORIZACION".equals(s.getEstado()))AUTORIZACION++;
                if("ARAUTORIZACION".equals(s.getEstado()))ARAUTORIZACION++;
                if("NEGACION".equals(s.getEstado()))NEGACION++;
                if("ARNEGACION".equals(s.getEstado()))ARNEGACION++;
            }
        }
        lst.add(new Clasificacion("SOL", "Solicitud", SOLICITUD));
        lst.add(new Clasificacion("ARS", "AR Solicitud", ARSOLICITUD));
        lst.add(new Clasificacion("AUT", "Autorización", AUTORIZACION));
        lst.add(new Clasificacion("ARA", "AR Autorizacón", ARAUTORIZACION));
        lst.add(new Clasificacion("NEG", "Negación", NEGACION));
        lst.add(new Clasificacion("ARN", "AR Negación", ARNEGACION));
        return lst;
    }
    
    public List<Documentos> GetSolicitudesxPaciente(String PacienteId){
        return RepDocumentos.getInstance().getValuesPac(PacienteId);
    }
}
