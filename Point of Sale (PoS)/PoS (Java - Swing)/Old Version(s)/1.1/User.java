package compuflexPosSwing;

/**
 * User Class
 * Defines the user(s) viewing and processing the transaction(s) in the cart.
 * @author Thomas Yamakaitis
 */
class User {
	String name = "Tommy";
	
	User() {
		pickName();
	}
	
	/**
	 * Get Name
	 * @return user's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Pick Name
	 * Picks a new random name for the user
	 */
	void pickName() {
		String[] names = {"Spider-Man","Captain America","Thor","The Incredible Hulk","Black Widow","Iron Man","Deadpool"};
		int choice = (int) (Math.random() * names.length);
		
		this.name = names[choice];
	}
}
