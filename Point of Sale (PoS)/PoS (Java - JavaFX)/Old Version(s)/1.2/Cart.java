package application;

import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Cart Class
 * This class contains the product list, quantity of those products in the cart, and a product table GUI to display the cart graphically.
 * @author Thomas Yamakaitis
 */
class Cart {
	ObservableList<CartItem> products = FXCollections.observableArrayList();
	Double total = 0.00;
	DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	
	/**
	 * Default Constructor
	 * Creates an empty cart (empty ObservableList<CartItem>).
	 */
	Cart() { }
	
	/**
	 * Add Item
	 * Add a CartItem `c` to the cart (the ObservableList<CartItem>).
	 * @param c the CartItem to add the cart
	 */
	void addItem(CartItem c) {
		int i = this.indexOf(c);
		this.total += c.getItemPrice();
		if(i == -1) {
			this.products.add(c);
		} else {
			int q = this.products.get(i).getItemQuantity();
			this.products.get(i).setItemQuantity(++q);
			this.products.get(i).setItemTotal(q * this.products.get(i).getItemPrice());
			this.products.set(i, this.products.get(i));
		}
	}
	
	/**
	 * Last Item
	 * Returns the last CartItem that was added in the cart
	 * @return the last item in the cart
	 */
	CartItem lastItem() {
		return this.products.get(products.size() - 1);
	}
	
	/**
	 * Index of (CartItem)
	 * @param c the CartItem to search for
	 * @return the index of the CartItem in the cart, if not in the cart, return -1
	 */
	int indexOf(CartItem c) {
		for(int i = 0; i < this.products.size(); i++) {
			if(c.getItemName().equals(this.products.get(i).getItemName())) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Clear All
	 * Clears all items from the cart and sets total to 0
	 */
	public void clearAll() {
		this.products.clear();
		this.total = 0.00;
	}

	/**
	 * Get String Total
	 * @return a formatted String version ($#.##) of the total
	 */
	public String getStringTotal() {
		return fmt.format(this.getTotal());
	}
	
	/**
	 * Get Total
	 * @return the actual value of total as a Double
	 */
	public Double getTotal() {
		return this.total;
	}
	
	/**
	 * To String
	 * @param s status code
	 * @return string version of table
	 */
	public String toString(String s) {
		StringBuilder sb = new StringBuilder();
		String plusLine = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n";
		sb.append(plusLine + String.format("+ %89s +\r\n", "(" + s + ") " + "Cart") +
				plusLine + String.format("+ %20s + %20s + %20s + %20s +\r\n", "Description", "Price", "Quantity", "Total") + plusLine);
		
		for(int i = 0; i < products.size(); i++) {
			sb.append(String.format("+ %20s + %20s + %20s + %20s +\r\n", products.get(i).getItemName(), fmt.format(products.get(i).getItemPrice()), products.get(i).getItemQuantity(), fmt.format(products.get(i).getItemPrice() * products.get(i).getItemQuantity())));
		}
		
		sb.append(plusLine + String.format("+ %89s +\r\n", "Total: " + this.getStringTotal()) + plusLine);
		return sb.toString();
	}
}
