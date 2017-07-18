package application;

import javafx.beans.property.*;

/**
 * Product Class
 * This class contains the name, quantity and price of a product, and methods for accessing those values.
 * @author Thomas Yamakaitis
 */
class Product {
	StringProperty name;
	DoubleProperty price;
	String[] itemList = {"Macadamia", "Hazelnut", "Almond", "Peanut", "Walnut", "Pistachio", "Pecan", "Brazil"};
	Double[] priceList = {2.00, 1.90, 1.31, 0.85, 1.12, 1.53, 1.25, 1.75};
	
	/**
	 * Default Constructor
	 */
	Product() {
		int random = (int) (Math.random() * itemList.length);
		this.name = new SimpleStringProperty(itemList[random]);
		this.price = new SimpleDoubleProperty(priceList[random]);
	}
	
	/**
	 * Product Constructor
	 * There is no default constructor because the product must have a name and a price.
	 * Products with no price should have a `p` value of 0.0
	 * @param n the product's name
	 * @param p the product's price
	 */
	Product(String n, Double p) {
		this.name = new SimpleStringProperty(n);
		this.price = new SimpleDoubleProperty(p);
	}
	
	/**
	 * Get Name Method
	 * @return the name of the product
	 */
	String getName() {
		return this.name.get();
	}
	
	/**
	 * Get Name Property
	 * @return Name Property
	 */
	StringProperty nameProperty() {
		return this.name;
	}
	
	/**
	 * Get Price Method
	 * @return the price of the product
	 */
	Double getPrice() {
		return this.price.get();
	}
	
	/**
	 * Get Price Property
	 * @return Price Property
	 */
	DoubleProperty priceProperty() {
		return this.price;
	}
	
	/**
	 * Set Name Method
	 * @param n the name that you would like to change the product's name to
	 */
	void setName(String n) {
		this.name.set(n);;
	}
	
	/**
	 * Set Price Method
	 * @param p the price that you would like to change the product's price to
	 */
	void setPrice(Double p) {
		this.price.set(p);;
	}
}