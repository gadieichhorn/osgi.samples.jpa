package osgi.examples.jpa.data.test;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import osgi.examples.jpa.data.api.Data;

/**
 * 
 */

public class DataTest {

    private final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
    
    private     <T> T getService(Class<T> clazz) throws InterruptedException {
    	ServiceTracker<T,T> st = new ServiceTracker<>(context, clazz, null);
    	st.open();
    	return st.waitForService(1000);
    }    
    
    /*
     * 
     */
    @Test
    public void testData() throws Exception {
    	Assert.assertNotNull(getService(Data.class));
   	}

}
