package osgi.examples.jpa.data.provider;

import org.osgi.service.component.annotations.Component;

import osgi.examples.jpa.data.api.Data;

/**
 * 
 */
@Component(name = "osgi.examples.jpa.data")
public class DataImpl implements  Data {
	
	@Override
	public String getName() {
		return "JPA";
	}
	
}
