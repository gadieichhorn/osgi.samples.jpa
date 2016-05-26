package osgi.examples.jpa.data.provider;

import junit.framework.TestCase;

/*
 * 
 */
public class DataImplTest extends TestCase {
	
	/*
	 * 
	 */
	public void testGetName () {
		assertEquals(new DataImpl().getName(), "JPA");
	}
	
}
