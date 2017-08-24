package application;

import java.text.DecimalFormat;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CartItem {
	StringProperty itemName;
	StringProperty itemPrice;
	Double price;
	IntegerProperty itemQuantity;
	StringProperty itemTotal;
	Double total;
	String[] itemList = {"Macadamia", "Hazelnut", "Almond", "Peanut", "Walnut", "Pistachio", "Pecan", "Brazil"};
	Double[] priceList = {2.00, 1.90, 1.31, 0.85, 1.12, 1.53, 1.25, 1.75};
	DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");

	/**
	 * Default CartItem Constructor
	 */
	CartItem() {
		int random = (int) (Math.random() * itemList.length);
		this.itemName = new SimpleStringProperty(itemList[random]);
		this.price = priceList[random];
		this.itemPrice = new SimpleStringProperty(fmt.format(this.price));
		this.itemQuantity = new SimpleIntegerProperty(1);
		this.total = this.price * this.itemQuantity.get();
		this.itemTotal = new SimpleStringProperty(fmt.format(this.total));
	}
	
	/**
	 * Get Item Name
	 * @return item name
	 */
	String getItemName() {
		return this.itemName.get();
	}
	StringProperty itemNameProperty() {
		return this.itemName;
	}
	
	/**
	 * Get Item Price
	 * @return item price
	 */
	Double getItemPrice() {
		return this.price;
	}
	StringProperty itemPriceProperty() {
		return new SimpleStringProperty(fmt.format(this.price));
	}
	
	/**
	 * Get Item Quantity
	 * @return item quantity
	 */
	int getItemQuantity() {
		return this.itemQuantity.get();
	}
	IntegerProperty itemQuantityProperty() {
		return this.itemQuantity;
	}
	
	/**
	 * Get Item Total
	 * @return item total
	 */
	Double getItemTotal() {
		return this.total;
	}
	StringProperty itemTotalProperty() {
		return new SimpleStringProperty(fmt.format(this.getItemTotal()));
	}
	
	/**
	 * Set Item Name
	 * @param n item name
	 */
	void setItemName(String n) {
		this.itemName.set(n);
	}
	
	/**
	 * Set Item Price
	 * @param p item price
	 */
	void setItemPrice(Double p) {
		this.price = p;
		this.itemPrice = new SimpleStringProperty(fmt.format(this.price));
	}
	
	/**
	 * Set Item Quantity
	 * @param q item quantity
	 */
	void setItemQuantity(int q) {
		this.itemQuantity.set(q);
	}
	
	/**
	 * Set Item Total
	 * @param t item total
	 */
	void setItemTotal(Double t) {
		this.total = t;
		this.itemTotal = new SimpleStringProperty(fmt.format(this.total));
	}
}
