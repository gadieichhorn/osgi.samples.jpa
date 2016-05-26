<h1><img src="http://enroute.osgi.org/img/enroute-logo-64.png" witdh=40px style="float:left;margin: 0 1em 1em 0;width:40px">
OSGi JPA sample project</h1>

This repo is a sample application using enRoute and bndtools. see the google group list for details 
link -> https://groups.google.com/forum/#!topic/bndtools-users/Ssw_iPr1VhI

the intentions are:
1. use bndtools
2. use enRoute templates
3. use JPA
	a. use Derby/H2 in memory database for tests
	b. use mysql as release prod
4. use eclipse link as provider

other design patterns
1. abstract the JPA implementation with a session interface
2. use a service factory (OSGi) for creating many datasource connection. EntityManagerFactory
3. implement integration unit tests (enRoute) black box
4. unit tests

