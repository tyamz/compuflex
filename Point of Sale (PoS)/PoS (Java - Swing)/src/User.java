package posSwing;

public class User {
	String name;
	
	/**
	 * Default Constuctor
	 */
	User() { }
	
	/**
	 * User Constructor
	 * @param n the name of the User.
	 */
	User(String n) {
		this.name = n;
	}
	
	/**
	 * Get Name
	 * @return the name String of the User.
	 */
	String getName() {
		return name;
	}
	
	/**
	 * Set Name
	 * @param name the name of the User.
	 */
	void setName(String name) {
		this.name = name;
	}
}
