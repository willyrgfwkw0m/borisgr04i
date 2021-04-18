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
public class AutorizacionCommunication extends Communication{
    
        @Description(shortDefinition="Solicitud de Autorización")
        @Child(name = "solicitudId")
	@Extension(url="http://siass.org/autorizacion.html/#solicitudId", definedLocally = false, isModifier = false)
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
        
        @Description(shortDefinition="Hora de Autorizacion")
        @Child(name = "hora")
	@Extension(url="http://siass.org/autorizacion.html#horaautorizacion", definedLocally = false, isModifier = false)
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
	@Description(shortDefinition="Lista de Servicios Autorizados CUPS")
	@Extension(url = "http://siass.org/autorizacion.html#serviciosAutorizados", isModifier = false, definedLocally = true)
	@Child(name = "servicio", min=0, max=Child.MAX_UNLIMITED)
	private List<ServiciosACUPS> myServiciosCUPS;
        
         /* *****************************
	 * Getters and setters
	 * *****************************/

	public List<ServiciosACUPS> getServiciosCUPS() {
		if (myServiciosCUPS==null) {
			myServiciosCUPS=new ArrayList<ServiciosACUPS>();
		}
		return myServiciosCUPS;
	}

	public void setServiciosCUPS(List<ServiciosACUPS> theServiciosCUPS) {
		myServiciosCUPS = theServiciosCUPS;
	}
                
        @Block
	public static class ServiciosACUPS extends BaseIdentifiableElement implements IExtension
	{
		/* *****************************
		 * Fields
		 * *****************************/	
		
		/**
		 * This is a primitive datatype extension
		 */
		@Description(shortDefinition = "Establezca la Cantidad de Servicios/Procedimientos Requeridos")
		@Extension(url = "http://siass.org/autorizacion.html#autorizacion-cantidadcups", isModifier = false, definedLocally = true)
		@Child(name = "cantidad")
		private QuantityDt myCantidad;
				
		/**
		 * This is a composite datatype extension
		 */
		@Description(shortDefinition = "Contiene los CUPS Autorizados")
		@Extension(url = "http://siass.org/autorizacion.html#autorizacion-servicioscups", isModifier = false, definedLocally = true)
		@Child(name = "cups")
		private CodingDt myCUPS;

		/* *****************************
		 * Getters and setters
		 * *****************************/

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
