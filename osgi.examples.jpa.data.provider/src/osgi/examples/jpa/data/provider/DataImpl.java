package osgi.examples.jpa.data.provider;

import java.util.Dictionary;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
//import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;
import org.osgi.service.log.LogService;

import osgi.enroute.debug.api.Debug;
import osgi.examples.jpa.data.api.Data;
import osgi.examples.jpa.data.api.Session;
import osgi.examples.jpa.data.provider.impl.SessionJpaImpl;

/**
 * 
 */
@Component(
		name = "osgi.examples.jpa.data.factory",
		configurationPolicy=ConfigurationPolicy.OPTIONAL,
	property = {			
			"service.pid=osgi.examples.jpa.data.factory", 
			Debug.COMMAND_SCOPE + "=jpa", 
			Debug.COMMAND_FUNCTION + "=getName" 
		}
)
public class DataImpl implements  Data, ManagedServiceFactory  {

    @Reference(cardinality = ReferenceCardinality.OPTIONAL, target = "(osgi.unit.name=data.jpa.factory)")
    protected EntityManagerFactoryBuilder emfb;

    private EntityManagerFactory emf; // injected from OSGi DS

	public DataImpl() {
		System.out.println("Create DataImpl");			
	}
	
	@Override
	public String getName() {
		return "JPA";
	}
	
	@Override
	public Session getSession() {
		return new SessionJpaImpl(this.emf.createEntityManager());
	}

	@Activate
	void activate(
		ComponentContext cc, 
		BundleContext bc, 
		Map<String,Object> config) {
		System.out.println("Activate DataImpl: " + config);			
        if (emf != null && emf.isOpen()) {
        	emf.close();
        }		
		config(config);				
	}

	@Deactivate
	void deactivate(
		ComponentContext cc, 
		BundleContext bc, 
		Map<String,Object> config) {
        if (emf != null && emf.isOpen()) {
        	emf.close();
        }		
		System.out.println("Deactivate DataImpl: " + config);			
	}
	
	@Modified
	void modified(Map<String,Object> config) {
		System.out.println("Modified DataImpl");			
        if (emf != null && emf.isOpen()) {
        	emf.close();
        }		
		config(config);				
	}

	@Reference
	void setLogService(LogService log) {
		System.out.println("SetServiceLoger");			
//		this.log = log;
	}
	
	@Override
	public void updated(String pid, Dictionary<String, ?> properties) throws ConfigurationException {
		System.out.println("Updated factory " + pid + " -> " + properties);			
	}
	
	@Override
	public void deleted(String pid) {
		System.out.println("Deleted factory " + pid );		
	}

    private void config(Map<String, Object> properties) {
//        this.emf = this.emfb.createEntityManagerFactory(properties);
    }
	
	
}
