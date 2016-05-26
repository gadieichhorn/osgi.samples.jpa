package osgi.examples.jpa.data.api;

/*
 * Session encapsulate an EM and declare the interface to use as in get, update, create, delete
 */
public interface Session {

	Object get(String key);

	void create(Object entity);
	
	void delete(String key);
	
	void update(Object entity);
}
