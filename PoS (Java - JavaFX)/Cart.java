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
	
	Cart() { }
	
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

	CartItem lastItem() {
		return this.products.get(products.size() - 1);
	}
	
	int indexOf(CartItem c) {
		for(int i = 0; i < this.products.size(); i++) {
			if(c.getItemName().equals(this.products.get(i).getItemName())) {
				return i;
			}
		}
		
		return -1;
	}

	public void clearAll() {
		this.products.clear();
		this.total = 0.00;
	}

	public void removeItem(Object indices) {
		this.products.remove(indices);
	}

	public String getStringTotal() {
		return fmt.format(this.getTotal());
	}

	public Double getTotal() {
		return this.total;
	}
}
