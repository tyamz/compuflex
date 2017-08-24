package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import tyamz.FileFunctions;

public class CartItem {
	StringProperty itemName;
	StringProperty itemPrice;
	Double price;
	IntegerProperty itemQuantity;
	StringProperty itemTotal;
	Double total;
	ArrayList<String> itemList = new ArrayList<>(); // = {"Macadamia", "Hazelnut", "Almond", "Peanut", "Walnut", "Pistachio", "Pecan", "Brazil"};
	ArrayList<Double> priceList = new ArrayList<>(); // = {2.00, 1.90, 1.31, 0.85, 1.12, 1.53, 1.25, 1.75};
	DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	private Settings settings;

	/**
	 * Default CartItem Constructor
	 */
	CartItem(Settings s) {
		this.settings = s;
		if(itemList.isEmpty()) {
			initInventory();
		}
		int random = (int) (Math.random() * itemList.size());
		this.itemName = new SimpleStringProperty(itemList.get(random));
		this.price = priceList.get(random);
		this.itemPrice = new SimpleStringProperty(fmt.format(this.price));
		this.itemQuantity = new SimpleIntegerProperty(1);
		this.total = this.price * this.itemQuantity.get();
		this.itemTotal = new SimpleStringProperty(fmt.format(this.total));
	}
	
	private void initInventory() {
		ArrayList<JSONObject> inv = FileFunctions.parseJson(this.settings.getProductFile(), "items");
		
		for(JSONObject j : inv) {
			String item = j.get("name").toString();
			Double price = Double.parseDouble(j.get("price").toString());
			
			itemList.add(item);
			priceList.add(price);
		}
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
