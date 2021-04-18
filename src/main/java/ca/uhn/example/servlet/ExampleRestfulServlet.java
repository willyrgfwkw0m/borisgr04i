package ca.uhn.example.servlet;

import ca.uhn.example.provider.CommunicationResourceProvider;
import java.util.ArrayList;
import java.util.List;

import ca.uhn.example.provider.OrganizationResourceProvider;
import ca.uhn.example.provider.PatientResourceProvider;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.INarrativeGenerator;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import javax.servlet.annotation.WebServlet;

/**
 * This servlet is the actual FHIR server itself
 */
@WebServlet("/fhirbase/*")
public class ExampleRestfulServlet extends RestfulServer {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is called automatically when the
	 * servlet is initializing.
	 */
	@Override
	public void initialize() {
		//Se Defini el Tipo de Borrador
                setFhirContext (FhirContext.forDstu2());
                
		/*
		 * Two resource providers are defined. Each one handles a specific
		 * type of resource.
		 */
		List<IResourceProvider> providers = new ArrayList<IResourceProvider>();
		providers.add(new PatientResourceProvider());
		providers.add(new OrganizationResourceProvider());
                providers.add(new CommunicationResourceProvider());
                //providers.add(new  SolicitudesComResourceProvider());
                //providers.add(new  AutorizacionComResourceProvider());

		setResourceProviders(providers);
                setInterceptors(new Interceptor());
                	
		/*
		 * Use a narrative generator. This is a completely optional step, 
		 * but can be useful as it causes HAPI to generate narratives for
		 * resources which don't otherwise have one.
		 */
		INarrativeGenerator narrativeGen = new DefaultThymeleafNarrativeGenerator();
		getFhirContext().setNarrativeGenerator(narrativeGen);

		/*
		 * Tells HAPI to use content types which are not technically FHIR compliant when a browser is detected as the
		 * requesting client. This prevents browsers from trying to download resource responses instead of displaying them
		 * inline which can be handy for troubleshooting.
		 */
		setUseBrowserFriendlyContentTypes(true);
		
	}

}
