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
public class SolicitudCommunication extends Communication{
    
   
    
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
        
        @Description(shortDefinition="Ubicación del Paciente al momento del Servicio")
        @Child(name = "ubicacionPaciente")
	@Extension(url="http://siass.org/solicitud.html#tiposubicacion", definedLocally = false, isModifier = false)
	private CodingDt ubicacionPaciente;
        /* *****************************
	 * Getters and setters
	 * *****************************/
        
        public CodingDt getUbicacionPaciente() {
		if (this.ubicacionPaciente == null) {
			this.ubicacionPaciente = new CodingDt();
		}
		return this.ubicacionPaciente;
	}

	public void setUbicacionPaciente(CodingDt theUbicacionPaciente) {
		this.ubicacionPaciente = theUbicacionPaciente;
	}
    
     
        @Description(shortDefinition="Tipo de Servicio Solicitado")
        @Child(name = "tipoServicio")
	@Extension(url="http://siass.org/solicitud.html#tipoServicio", definedLocally = false, isModifier = false)
        private CodingDt tipoServicio;
        /* *****************************
	 * Getters and setters
	 * *****************************/
        
        public CodingDt getTipoServicio() {
		if (this.tipoServicio == null) {
			this.tipoServicio = new CodingDt();
		}
		return this.tipoServicio;
	}

	public void seTipoServicio(CodingDt thetipoServicio) {
		this.tipoServicio = thetipoServicio;
	}
        
        @Description(shortDefinition="Origen de la Atención")
        @Child(name = "origenAtencion")
	@Extension(url="http://siass.org/solicitud.html#origenatencion", definedLocally = false, isModifier = false)
        private CodingDt origenAtencion;
        /* *****************************
	 * Getters and setters
	 * *****************************/
        
        public CodingDt getOrigenAtencion() {
		if (this.origenAtencion == null) {
			this.origenAtencion = new CodingDt();
		}
		return this.origenAtencion;
	}

	public void setOrigenAtencion(CodingDt theOrigenAtencion) {
		this.origenAtencion = theOrigenAtencion;
	}
    
        
        @Description(shortDefinition="Prioridad de la Atención")
        @Child(name = "prioridadAtencion")
	@Extension(url="http://siass.org/solicitud.html#tiposprioridad", definedLocally = false, isModifier = false)
        private CodingDt prioridadAtencion;
        /* *****************************
	 * Getters and setters
	 * *****************************/
        
        public CodingDt getPrioridadAtencion() {
		if (prioridadAtencion == null) {
			this.prioridadAtencion = new CodingDt();
		}
		return this.prioridadAtencion;
	}

	public void setPrioridadAtencion(CodingDt thePrioridadAtencion) {
		prioridadAtencion = thePrioridadAtencion;
	}
        
        @Description(shortDefinition="Servicio de Hospitalización")
        @Child(name = "servicio")
	@Extension(url="http://siass.org/solicitud.html#serviciohospitalizacion", definedLocally = false, isModifier = false)
        private StringDt servicio;
        /* *****************************
	 * Getters and setters
	 * *****************************/
        public StringDt getServicio() {
		if (servicio == null) {
			servicio = new StringDt();
		}
		return servicio;
	}

	public void setServicio(StringDt theServicio) {
		servicio = theServicio;
	}
        
        @Description(shortDefinition="N° de Cama Asignada en la IPS Solicitante en Hospitalizacion")
        @Child(name = "cama")
	@Extension(url="http://siass.org/solicitud.html#cama", definedLocally = false, isModifier = false)
        private StringDt cama;
        /* *****************************
	 * Getters and setters
	 * *****************************/
        public StringDt getCama() {
		if (cama == null) {
			cama = new StringDt();
		}
		return cama;
	}

	public void setCama(StringDt theCama) {
		cama = theCama;
	}
        
        @Description(shortDefinition="Hora de Solicitud")
        @Child(name = "horaSolicitud")
	@Extension(url="http://siass.org/solicitud.html#horasolicitud", definedLocally = false, isModifier = false)
        private StringDt horaSolicitud;
        /* *****************************
	 * Getters and setters
	 * *****************************/
        public StringDt getHoraSolicitud() {
		if (horaSolicitud == null) {
			horaSolicitud = new StringDt();
		}
		return horaSolicitud;
	}

	public void setHoraSolicitud(StringDt thehoraSolicitud) {
		horaSolicitud = thehoraSolicitud;
	}
        
        @Description(shortDefinition="Hora de Recibido")
        @Child(name = "horaRecibido")
	@Extension(url="http://siass.org/solicitud.html#horaRecibido", definedLocally = false, isModifier = false)
        private StringDt horaRecibido;
        /* *****************************
	 * Getters and setters
	 * *****************************/
        public StringDt getHoraRecibido() {
		if (horaSolicitud == null) {
			horaSolicitud = new StringDt();
		}
		return horaSolicitud;
	}

	public void setHoraRecibido(StringDt thehoraSolicitud) {
		horaSolicitud = thehoraSolicitud;
	}
        
        
        @Description(shortDefinition="Estado de la Solicitud")
        @Child(name = "estado")
	@Extension(url="http://siass.org/solicitud.html#estado", definedLocally = false, isModifier = false)
        private CodingDt estado;
        /* *****************************
	 * Getters and setters
	 * *****************************/
        
        public CodingDt getEstado() {
		if (estado == null) {
			this.estado = new CodingDt();
		}
		return this.estado;
	}

	public void setEstado(CodingDt theEstado) {
		estado = theEstado;
	}
        
        /**
	 * This is a composite extension, containing further extensions instead of
	 * a value. The class "EmergencyContact" is defined at the bottom
	 * of this file.
	 */
	@Description(shortDefinition="Listado Diagnosticos CIE10")
	@Extension(url = "http://siass.org/solicitud.html#diagnostico", isModifier = false, definedLocally = true)
	@Child(name = "diagnostico", min=0, max=Child.MAX_UNLIMITED)
	private List<Diagnostico> myDiagnostico;
        
        /* *****************************
	 * Getters and setters
	 * *****************************/

	public List<Diagnostico> getDiagnostico() {
		if (myDiagnostico==null) {
			myDiagnostico=new ArrayList<Diagnostico>();
		}
		return myDiagnostico;
	}

	public void setDiagnostico(List<Diagnostico> theDiagnostico) {
		myDiagnostico = theDiagnostico;
	}
        /**
	 * This is a composite extension, containing further extensions instead of
	 * a value. The class "EmergencyContact" is defined at the bottom
	 * of this file.
	 */
	@Description(shortDefinition="Listado Servicios CUPS")
	@Extension(url = "http://siass.org/solicitud.html#serviciosCUPS", isModifier = false, definedLocally = true)
	@Child(name = "serviciocups", min=0, max=Child.MAX_UNLIMITED)
	private List<ServiciosPCUPS> myServiciosCUPS;
        
         /* *****************************
	 * Getters and setters
	 * *****************************/

	public List<ServiciosPCUPS> getServiciosCUPS() {
		if (myServiciosCUPS==null) {
			myServiciosCUPS=new ArrayList<ServiciosPCUPS>();
		}
		return myServiciosCUPS;
	}

	public void setServiciosCUPS(List<ServiciosPCUPS> theServiciosCUPS) {
		myServiciosCUPS = theServiciosCUPS;
	}
        
        /**
	 * This "block definition" defines an extension type with multiple child extensions.
	 * It is referenced by the field Diagnostico above.
	 */
	@Block
	public static class Diagnostico extends BaseIdentifiableElement implements IExtension
	{
		/* *****************************
		 * Fields
		 * *****************************/	
				
		/**
		 * This is a composite datatype extension
		 */
            
		@Description(shortDefinition = "Diagnostico CIE10")
		@Extension(url = "http://siass.org/solicitud.html#diagnosticoCIE10", isModifier = false, definedLocally = true)
		@Child(name = "diagnostico")
		private CodingDt myDiagnostico;

		/* *****************************
		 * Getters and setters
		 * *****************************/


		public CodingDt getDiagnostico() {
			if (myDiagnostico == null) {
				myDiagnostico = new CodingDt();
			}
			return myDiagnostico;
		}

		public void setDiagnostico(CodingDt theDiagnostico) {
			myDiagnostico = theDiagnostico;
		}
                
                


		/* *****************************
		 * Boilerplate methods- Hopefully these will be removed or made optional
		 * in a future version of HAPI but for now they need to be added to all block
		 * types. These two methods follow a simple pattern where a utility method from
		 * ElementUtil is called and all fields are passed in.
		 * *****************************/
		
		@Override
		public <T extends IElement> List<T> getAllPopulatedChildElementsOfType(Class<T> theType) {
			return ElementUtil.allPopulatedChildElements(theType,  myDiagnostico);
		}

		@Override
		public boolean isEmpty() {
			return ElementUtil.isEmpty( myDiagnostico);
		}

       
		
	}
	
        /**
	 * This "block definition" defines an extension type with multiple child extensions.
	 * It is referenced by the field ServiciosCUPS above.
	 */
        @Block
	public static class ServiciosPCUPS extends BaseIdentifiableElement implements IExtension
	{
		/* *****************************
		 * Fields
		 * *****************************/	
		
		/**
		 * This is a primitive datatype extension
		 */
		@Description(shortDefinition = "Establezca la Cantidad de Servicios/Procedimientos Requeridos")
		@Extension(url = "http://siass.org/solicitud.html#cantidadcups", isModifier = false, definedLocally = true)
		@Child(name = "cantidad")
		private QuantityDt myCantidad;
				
		/**
		 * This is a composite datatype extension
		 */
		@Description(shortDefinition = "Contiene los CUPS")
		@Extension(url = "http://siass.org/solicitud.html#cups", isModifier = false, definedLocally = true)
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
