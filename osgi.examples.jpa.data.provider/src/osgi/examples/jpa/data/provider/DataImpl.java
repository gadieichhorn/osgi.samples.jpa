package osgi.examples.jpa.data.provider;

import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;
import org.osgi.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
//		immediate=true,
	property = {			
			"service.pid=osgi.examples.jpa.data.factory", 
			Debug.COMMAND_SCOPE + "=jpa", 
			Debug.COMMAND_FUNCTION + "=getName" 
		}
)
public class DataImpl implements  Data  {

	static final Logger		logger	= LoggerFactory.getLogger(Data.class);

    @Reference(cardinality = ReferenceCardinality.OPTIONAL, target = "(osgi.unit.name=data.jpa.factory)")
    protected EntityManagerFactoryBuilder emfb;

    private EntityManagerFactory emf; // injected from OSGi DS

	public DataImpl() {
//		System.out.println("Create DataImpl");			
		logger.info("Create");
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
		logger.info("Activate");
//		System.out.println("Activate DataImpl: " + config);			
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
		logger.info("Deactivate");
//		System.out.println("Deactivate DataImpl: " + config);			
	}
	
	@Modified
	void modified(Map<String,Object> config) {
		logger.info("Modified");
//		System.out.println("Modified DataImpl");			
        if (emf != null && emf.isOpen()) {
        	emf.close();
        }		
		config(config);				
	}

	@Reference
	void setLogService(LogService log) {
		logger.info("LogService: {}", log);
//		System.out.println("SetServiceLoger");			
//		this.log = log;
	}

    private void config(Map<String, Object> properties) {
//        this.emf = this.emfb.createEntityManagerFactory(properties);
    }
	
	
}
