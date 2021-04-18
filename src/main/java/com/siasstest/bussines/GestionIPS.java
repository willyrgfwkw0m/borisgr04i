/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.bussines;

import com.siasstest.fhirclient.ByAFhirClientARecibo;
import com.siasstest.fhirclient.ByAFhirClientSolicitud;
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
public class GestionIPS {
    //Metodos Invocados por el Cliente IPS
    public String Crear(Solicitud sol){
        sol.setEstado("SOLICITUD");
        //Se Agregan Objetos Relacionados//Deben Existir Previamente
        sol.setTipoDocumento("SOLAUT");
        
        Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();    
        
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        String date1 = format1.format(date); 
        
        SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
        String time = format2.format(date);
        
        Long c=RepDocumentos.getInstance().ObtenerConsecutivo(sol.getTipoDocumento());
        
        sol.setFechaDocumento(date1);
        sol.setHoraDocumento(time);
        
        sol.setIdDocumento(sol.getIps_ide()+sol.getTipoDocumento()+"-"+c);
        
        sol.setIps(RepIPS.getInstance().get(sol.getIps_ide()));
        sol.setEps(RepEPS.getInstance().get(sol.getEps_ide()));
        sol.setPaciente(RepPacientes.getInstance().get(sol.getPac_ide()));
        sol.setAcuseRecibo((AcuseRecibo) RepDocumentos.getInstance().get(sol.getIdAcuseRecibo()));
        RepDocumentos.getInstance().add(sol);
        ByAFhirClientSolicitud fhir= new ByAFhirClientSolicitud();
        String Respuesta = fhir.Enviar(sol);
        return sol.getIdDocumento()+ " Resultado Remoto:"+Respuesta;// + Remoto+" Remoto A" +RemotoA;
    }
    
    public String ARNegacion(AcuseRecibo ar){
        ar.setTipoDocumento("ARNESE");
        Negacion neg= (Negacion) RepDocumentos.getInstance().get(ar.getIdDocRecibido()) ;
        Solicitud sol= (Solicitud) RepDocumentos.getInstance().get(neg.getIdSol()) ;
        //ar.setDocRecibido(doc);
        neg.setEstado("ARNEGACION");
        sol.setEstado(neg.getEstado());
        
        ar.setIps_ide(sol.getIps_ide());
        ar.setEps_ide(sol.getEps_ide());
        ar.setPac_ide(sol.getPac_ide());
        
        neg.setAcuseRecibo(ar);
        
        Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();    
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        String date1 = format1.format(date); 
        
        SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
        String time = format2.format(date);
        
        ar.setHoraDocumento(time);
        ar.setFechaDocumento(date1);
                
        Long c=RepDocumentos.getInstance().ObtenerConsecutivo(ar.getTipoDocumento());
        ar.setIdDocumento(neg.getIps_ide()+ar.getTipoDocumento()+"-"+c);
        //ar.setIdDocumento(doc.getIps_ide()+"-"+c);
        neg.setIps(RepIPS.getInstance().get(neg.getIps_ide()));
        neg.setEps(RepEPS.getInstance().get(neg.getEps_ide()));
        
        RepDocumentos.getInstance().add(ar);
        ByAFhirClientARecibo fhir= new ByAFhirClientARecibo(neg.getEps().getEndPoint());
        String Respuesta = fhir.Enviar(ar);
        
        return ar.getIdDocumento()+ " Resultado Remoto:"+Respuesta;
    }
    
    public String ARAutorizacion(AcuseRecibo ar){
        ar.setTipoDocumento("ARAUSE");
        Autorizacion aut= (Autorizacion) RepDocumentos.getInstance().get(ar.getIdDocRecibido()) ;
        
        Solicitud sol= (Solicitud) RepDocumentos.getInstance().get(aut.getIdSol()) ;
        
        aut.setEstado("ARAUTORIZACION");
        //ar.setDocRecibido(doc);
        sol.setEstado(aut.getEstado());
        
        aut.setAcuseRecibo(ar);
        
        ar.setIps_ide(sol.getIps_ide());
        ar.setEps_ide(sol.getEps_ide());
        ar.setPac_ide(sol.getPac_ide());
        
        Long c=RepDocumentos.getInstance().ObtenerConsecutivo(ar.getTipoDocumento());
        
        Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();    
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        String date1 = format1.format(date); 
        
        SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
        String time = format2.format(date);
        
        ar.setFechaDocumento(date1);
        ar.setHoraDocumento(time);
        
        aut.setIps(RepIPS.getInstance().get(aut.getIps_ide()));
        aut.setEps(RepEPS.getInstance().get(aut.getEps_ide()));
        
        ar.setIdDocumento(aut.getIps_ide()+ar.getTipoDocumento()+"-"+c);
        
        RepDocumentos.getInstance().add(ar);
        //return doc.getEps().getEndPoint();
        
        ByAFhirClientARecibo fhir= new ByAFhirClientARecibo(aut.getEps().getEndPoint());
        String Respuesta = fhir.Enviar(ar);
        return ar.getIdDocumento()+ " Resultado Remoto:"+Respuesta;
        
    }
    
    //Metodos Invocados por el Server IPS
    public String Rec_ARSolicitud(AcuseRecibo ar){
        Solicitud sol= (Solicitud) RepDocumentos.getInstance().get(ar.getIdDocRecibido()) ;
        //ar.setDocRecibido( RepDocumentos.getInstance().get(ar.getIdDocRecibido()) );
        sol.setEstado("ARSOLICITUD");
        sol.setAcuseRecibo(ar);
        RepDocumentos.getInstance().add(ar);
        return sol.getIdDocumento();
    }
    
    public String Rec_Autorizacion(Autorizacion aut){
        Solicitud sol= (Solicitud) RepDocumentos.getInstance().get(aut.getIdSol()) ;
        //doc.setSolicitud((Solicitud) RepDocumentos.getInstance().get(doc.getIdSol() ));
        sol.setRptaSolicitud(aut);
        sol.setEstado("AUTORIZACION");
        RepDocumentos.getInstance().add(aut);
        return aut.getIdDocumento();
    }
    
    public String Rec_Negacion(Negacion neg){
        Solicitud sol= (Solicitud) RepDocumentos.getInstance().get(neg.getIdSol()) ;
        //doc.setSolicitud((Solicitud) RepDocumentos.getInstance().get(doc.getIdSol()));
        sol.setEstado("NEGACION");
        sol.setRptaSolicitud(neg);
        RepDocumentos.getInstance().add(neg);
        return neg.getIdDocumento();
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
    
}
