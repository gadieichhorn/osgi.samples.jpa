package osgi.examples.jpa.scheduler.provider;

import java.io.Closeable;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import osgi.enroute.scheduler.api.Scheduler;
import osgi.examples.jpa.data.api.Data;

/**
 * this is a sample scheduler that will use the data session provider 
 * to demonstrate how OSGi provides a mechanisem to connect independent 
 * capabilities on demand e.i. dependency injection
 * 
 * no interface is exposed, this is purely a client component
 */
@Component(
		name = "osgi.examples.jpa.scheduler",
		immediate=true)
public class SchedulerImpl {

	static final Logger		logger	= LoggerFactory.getLogger(SchedulerImpl.class);

	private Data data;
	private Scheduler scheduler;
	private Closeable	schedule;
	  		
	@Reference(cardinality=ReferenceCardinality.MANDATORY)
	public void setData(Data data) {
		logger.info("Data: {}", data);
		this.data = data;
	}

	@Reference
	  void setScheduler(Scheduler scheduler) {
	    this.scheduler = scheduler;
	  }
	
	@Activate
	void 	activate() throws Exception {
		// ping the service every 1 sec
		schedule = scheduler.schedule( () -> {
				logger.info("Data Service: {}", data.getName());
		},1000, 1000);

		// close the scheduler after 10 sec (about 10 tests)
		scheduler.after( schedule::close, 10000);
	}
	
}
