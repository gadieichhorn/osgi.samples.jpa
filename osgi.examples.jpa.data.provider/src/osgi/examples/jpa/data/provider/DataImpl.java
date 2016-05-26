package osgi.examples.jpa.data.provider;

import javax.persistence.EntityManagerFactory;

import org.osgi.service.component.annotations.Component;

import osgi.examples.jpa.data.api.Data;
import osgi.examples.jpa.data.api.Session;
import osgi.examples.jpa.data.provider.impl.SessionJpaImpl;

/**
 * 
 */
@Component(name = "osgi.examples.jpa.data")
public class DataImpl implements  Data {

	EntityManagerFactory emf; // injected from OSGi DS
	
	@Override
	public String getName() {
		return "JPA";
	}
	
	@Override
	public Session getSession() {
		return new SessionJpaImpl(this.emf.createEntityManager());
	}
}
