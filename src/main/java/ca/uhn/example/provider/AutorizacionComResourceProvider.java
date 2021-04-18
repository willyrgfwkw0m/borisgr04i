/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uhn.example.provider;

import ca.uhn.example.processcomm.ProcessorComm;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.valueset.CommunicationStatusEnum;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.siasstest.fhirprofile.AutorizacionCommunication;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author borisgr04
 */
public class AutorizacionComResourceProvider implements IResourceProvider{
    private final Map<Long, Deque<AutorizacionCommunication>> myComm = new HashMap<Long, Deque<AutorizacionCommunication>>();
    private Long myNextId = 1L;
    
     /**
     * Constructor
     */
    public AutorizacionComResourceProvider() {
        AutorizacionCommunication c1 = new AutorizacionCommunication();
        long resourceId = myNextId++;
        c1.setId(Long.toString(resourceId));
        c1.addIdentifier().setSystem("http://eps.com/comm").setValue("12345");
        c1.setStatus(CommunicationStatusEnum.COMPLETED);
        //myComm.put(myNextId++, c1);
        LinkedList<AutorizacionCommunication> list = new LinkedList<AutorizacionCommunication>();   
        list.add(c1);
	myComm.put(resourceId, list);
    }

    /**
     * All Resource Providers must implement this method
     * @return Tipo de Clase
     */
    @Override
    public Class<? extends IResource> getResourceType() {
        return AutorizacionCommunication.class;
    }

    /**
     * Simple implementation of the "read" method
     *
     * @param theId
     * @return
     */
    @Read()
    public AutorizacionCommunication read(@IdParam IdDt theId) {
       /* Communication retVal = myComm.get(theId.getIdPartAsLong());
        if (retVal == null) {
            throw new ResourceNotFoundException(theId);
        }
        return retVal;
        */
        Deque<AutorizacionCommunication> retVal;
	try {
            retVal = myComm.get(theId.getIdPartAsLong());
	} catch (NumberFormatException e) {
            /*
                * If we can't parse the ID as a long, it's not valid so this is an unknown resource
            */
            throw new ResourceNotFoundException(theId);
	}
		if (theId.hasVersionIdPart() == false) {
			return retVal.getLast();
		} else {
			for (AutorizacionCommunication nextVersion : retVal) {
				String nextVersionId = nextVersion.getId().getVersionIdPart();
				if (theId.getVersionIdPart().equals(nextVersionId)) {
					return nextVersion;
				}
			}
			// No matching version
			throw new ResourceNotFoundException("Unknown version: " + theId.getValue());
		}
    }

    /**
     * Create/save a new resource
     *
     * @param theComm
     * @return
     */
    @Create
    public MethodOutcome create(@ResourceParam AutorizacionCommunication theComm) {
        long id = myNextId++;
        addNewVersion(theComm, id);
        
        ProcessorComm pc = new ProcessorComm();
        pc.Procesar(theComm);
        
        // Let the caller know the ID of the newly created resource
        return new MethodOutcome(new IdDt(id));
    }

    /**
     * Simple "search" implementation // * @return  *
     * @return Lista de Communications Resources
     */
    @Search
    public List<AutorizacionCommunication> search() {
       LinkedList<AutorizacionCommunication> retVal = new LinkedList<AutorizacionCommunication>();
        for (Deque<AutorizacionCommunication> nextList : myComm.values()) {
			AutorizacionCommunication next = nextList.getLast();
			retVal.add(next);
	}
	return retVal;
    }
    
    @Update()
    public MethodOutcome update(@IdParam IdDt theId, @ResourceParam AutorizacionCommunication theCom) {
            //validateResource(thePatient);
             Long id;
		try {
			id = theId.getIdPartAsLong();
		} catch (DataFormatException e) {
			throw new InvalidRequestException("Invalid ID " + theId.getValue() + " - Must be numeric");
		}
		/*
		 * Throw an exception (HTTP 404) if the ID is not known
		 */
		if (!myComm.containsKey(id)) {
			throw new ResourceNotFoundException(theId);
		}
		addNewVersion(theCom, id);
		return new MethodOutcome();
	}
    /**
	 * Stores a new version of the patient in memory so that it can be retrieved later.
	 * 
	 * @param thePatient
	 *            The patient resource to store
	 * @param theId
	 *            The ID of the patient to retrieve    /**
	 * Stores a new version of the patient in memory so that it can be retrieved later.
	 * 
	 * @param thePatient
	 *            The patient resource to store
	 * @param theId
	 *            The ID of the patient to retrieve
	 */
    
    private void addNewVersion(AutorizacionCommunication theComm, Long theId) {
		InstantDt publishedDate;
		if (!myComm.containsKey(theId)) {
			myComm.put(theId, new LinkedList<AutorizacionCommunication>());
			publishedDate = InstantDt.withCurrentTime();
		} else {
			AutorizacionCommunication currentPatitne = myComm.get(theId).getLast();
			Map<ResourceMetadataKeyEnum<?>, Object> resourceMetadata = currentPatitne.getResourceMetadata();
			publishedDate = (InstantDt) resourceMetadata.get(ResourceMetadataKeyEnum.PUBLISHED);
		}
		/*
		 * PUBLISHED time will always be set to the time that the first version was stored. UPDATED time is set to the time that the new version was stored.
		 */
		theComm.getResourceMetadata().put(ResourceMetadataKeyEnum.PUBLISHED, publishedDate);
		theComm.getResourceMetadata().put(ResourceMetadataKeyEnum.UPDATED, InstantDt.withCurrentTime());

		Deque<AutorizacionCommunication> existingVersions = myComm.get(theId);

		// We just use the current number of versions as the next version number
		String newVersion = Integer.toString(existingVersions.size());
		
		// Create an ID with the new version and assign it back to the resource
		IdDt newId = new IdDt("Communication", Long.toString(theId), newVersion);
		theComm.setId(newId);
		
		existingVersions.add(theComm);
        }	
    /*
    @Operation(name="$acusederecibo")
    public MethodOutcome acuseReciboOperation(
           @IdParam IdDt theId,
           @OperationParam(name="fechaRecibido") DateTimeDt fechaRecibido
           ) {
           Deque<Communication> retVal;
           retVal = myComm.get(theId.getIdPartAsLong());
           Communication c = retVal.getFirst();
           c.setReceived(fechaRecibido);
           Long id;
           id = theId.getIdPartAsLong();
           addNewVersion(c, id);
           return new MethodOutcome(new IdDt(id));
    }*/
}
