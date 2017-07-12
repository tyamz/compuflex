package compuflexPosSwing;

/**
 * Product Class
 * This class contains the name, quantity and price of a product, and methods for accessing those values.
 * @author Thomas Yamakaitis
 */
class Product {
	String name;
	Double price;
	
	/**
	 * Product Constructor
	 * There is no default constructor because the product must have a name and a price.
	 * Products with no price should have a `p` value of 0.0
	 * @param n the product's name
	 * @param p the product's price
	 */
	Product(String n, Double p) {
		this.name = n;
		this.price = p;
	}
	
	/**
	 * Get Name Method
	 * @return the name of the product
	 */
	String getName() {
		return this.name;
	}
	
	/**
	 * Get Price Method
	 * @return the price of the product
	 */
	Double getPrice() {
		return this.price;
	}
	
	/**
	 * Set Name Method
	 * @param n the name that you would like to change the product's name to
	 */
	void setName(String n) {
		this.name = n;
	}
	
	/**
	 * Set Price Method
	 * @param p the price that you would like to change the product's price to
	 */
	void setPrice(Double p) {
		this.price = p;
	}
}