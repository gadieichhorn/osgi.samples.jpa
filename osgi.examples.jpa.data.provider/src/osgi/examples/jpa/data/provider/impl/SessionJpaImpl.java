package osgi.examples.jpa.data.provider.impl;

import javax.persistence.EntityManager;

import osgi.examples.jpa.data.api.Session;

public class SessionJpaImpl implements Session {
	private EntityManager em;
	
	public SessionJpaImpl(final EntityManager em) {
		this.em = em;
	}

	@Override
	public Object get(String key) {
		System.out.println(this.em.isOpen());
		return null;
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void update(Object entity) {
			// TODO Auto-generated method stub
			
	}
	
	@Override
	public void create(Object entity) {
				// TODO Auto-generated method stub
				
	}
}
