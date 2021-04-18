/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.fhirprofile;

import ca.uhn.fhir.model.api.BaseIdentifiableElement;
import ca.uhn.fhir.model.api.IElement;
import ca.uhn.fhir.model.api.IExtension;
import ca.uhn.fhir.model.api.annotation.Block;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.resource.Communication;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.util.ElementUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author borisgr04
 */
@ResourceDef(name="Communication")
public class NegacionCommunication extends Communication{
    
        @Description(shortDefinition="Solicitud de Autorización Inicial")
        @Child(name = "solicitudId")
	@Extension(url="http://siass.org/negacion.html/#solicitudId", definedLocally = false, isModifier = false)
	private StringDt solicitudId;

         /* *****************************
	 * Getters and setters
	 * *****************************/
	 
	public StringDt getIdSolicitud() {
		if (solicitudId == null) {
			solicitudId = new StringDt();
		}
		return solicitudId;
	}

	public void setIdSolicitud(StringDt theSolicitudId) {
		solicitudId = theSolicitudId;
	}
        
         @Description(shortDefinition="Acuse de Recibo de la Autorización")
        @Child(name = "IdAcuseRecibo")
	@Extension(url="http://siass.org/autorizacion.html/#IdAcuseRecibo", definedLocally = false, isModifier = false)
	private StringDt IdAcuseRecibo;

         /* *****************************
	 * Getters and setters
	 * *****************************/
	public StringDt getIdAcuseRecibo() {
		if (IdAcuseRecibo == null) {
			IdAcuseRecibo = new StringDt();
		}
		return IdAcuseRecibo;
	}

	public void setIdAcuseRecibo(StringDt theIdAcuseRecibo) {
		IdAcuseRecibo = theIdAcuseRecibo;
	}
        
        
        @Description(shortDefinition="Hora de Negacion")
        @Child(name = "hora")
	@Extension(url="http://siass.org/negacion.html#horanegacion", definedLocally = false, isModifier = false)
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
        
        /**
	 * This is a composite extension, containing further extensions instead of
	 * a value. The class "EmergencyContact" is defined at the bottom
	 * of this file.
	 */
	@Description(shortDefinition="Lista de Servicios Negados CUPS")
	@Extension(url = "http://siass.org/solicitud.html#serviciosNegados", isModifier = false, definedLocally = true)
	@Child(name = "serviciosNegados", min=0, max=Child.MAX_UNLIMITED)
	private List<ServiciosNCUPS> myServiciosCUPS;
        
         /* *****************************
	 * Getters and setters
	 * *****************************/

	public List<ServiciosNCUPS> getServiciosCUPS() {
		if (myServiciosCUPS==null) {
			myServiciosCUPS=new ArrayList<ServiciosNCUPS>();
		}
		return myServiciosCUPS;
	}

	public void setServiciosNCUPS(List<ServiciosNCUPS> theServiciosCUPS) {
		myServiciosCUPS = theServiciosCUPS;
	}
                
        @Block
	public static class ServiciosNCUPS extends BaseIdentifiableElement implements IExtension
	{
		/* *****************************
		 * Fields
		 * *****************************/	
		
                /**
		 * This is a primitive datatype extension
		 */
		@Description(shortDefinition = "Establezca la Fundamento Legal de la Negación")
		@Extension(url = "http://siass.org/negacion.html#fundamentocups", isModifier = false, definedLocally = true)
		@Child(name = "fundamento")
		private StringDt myFundamento;
                
                /**
		 * This is a primitive datatype extension
		 */
		@Description(shortDefinition = "Establezca la Justificación de la Negación")
		@Extension(url = "http://siass.org/negacion.html#justificacioncups", isModifier = false, definedLocally = true)
		@Child(name = "justificacion")
		private StringDt myJustificacion;
                
                /**
		 * This is a primitive datatype extension
		 */
		@Description(shortDefinition = "Establezca la Alternativas de la Negación")
		@Extension(url = "http://siass.org/negacion.html#alternativascups", isModifier = false, definedLocally = true)
		@Child(name = "alternativas")
		private StringDt myAlternativa;
				
		/**
		 * This is a primitive datatype extension
		 */
		@Description(shortDefinition = "Establezca la Cantidad de Servicios/Procedimientos Requeridos")
		@Extension(url = "http://siass.org/negacion.html#cantidadcups", isModifier = false, definedLocally = true)
		@Child(name = "cantidad")
		private QuantityDt myCantidad;
				
		/**
		 * This is a composite datatype extension
		 */
		@Description(shortDefinition = "Contiene los CUPS")
		@Extension(url = "http://siass.org/negacion.html#servicioscups", isModifier = false, definedLocally = true)
		@Child(name = "cups")
		private CodingDt myCUPS;

		/* *****************************
		 * Getters and setters
		 * *****************************/

                public StringDt getFundamento() {
			if (myFundamento == null) {
				myFundamento = new StringDt();
			}
			return myFundamento;
		}

		public void setFundamento(StringDt theCantidad) {
			myFundamento = theCantidad;
		}
                public StringDt getAlternativa() {
			if (myAlternativa == null) {
				myAlternativa = new StringDt();
			}
			return myAlternativa;
		}

		public void setAlternativa(StringDt theAlternativa) {
			myAlternativa = theAlternativa;
		}
               
                public StringDt getJustificacion() {
			if (myJustificacion == null) {
				myJustificacion = new StringDt();
			}
			return myJustificacion;
		}

		public void setJustificacion(StringDt theJustificacion) {
			myJustificacion = theJustificacion;
		}
                
                
		public QuantityDt getCantidad() {
			if (myCantidad == null) {
				myCantidad = new QuantityDt();
			}
			return myCantidad;
		}

		public void setCantidad(QuantityDt theCantidad) {
			myCantidad = theCantidad;
		}

		public CodingDt getCUPS() {
			if (myCUPS == null) {
				myCUPS = new CodingDt();
			}
			return myCUPS;
		}

		public void setCUPS(CodingDt theCUPS) {
			myCUPS = theCUPS;
		}
                
                


		/* *****************************
		 * Boilerplate methods- Hopefully these will be removed or made optional
		 * in a future version of HAPI but for now they need to be added to all block
		 * types. These two methods follow a simple pattern where a utility method from
		 * ElementUtil is called and all fields are passed in.
		 * *****************************/
		
		@Override
		public <T extends IElement> List<T> getAllPopulatedChildElementsOfType(Class<T> theType) {
			return ElementUtil.allPopulatedChildElements(theType, myCantidad, myCUPS);
		}

		@Override
		public boolean isEmpty() {
			return ElementUtil.isEmpty(myCantidad, myCUPS);
		}

       
		
	}
	
}
