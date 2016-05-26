package osgi.examples.jpa.data.api;

import org.osgi.annotation.versioning.ProviderType;

/**
 * 
 */
@ProviderType
public interface Data {
	
	/**
	 * get the name of the provider type (jpa, elastic, mongo, ...)
	 */
	String getName();
}
