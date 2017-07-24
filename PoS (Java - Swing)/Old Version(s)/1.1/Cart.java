package compuflexPosSwing;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

/**
 * Cart Class
 * This class contains the product list, quantity of those products in the cart, and a product table GUI to display the cart graphically.
 * @author Thomas Yamakaitis
 */
class Cart extends TableBuilder {
	ArrayList<Product> products = new ArrayList<>(); // Holds the products themselves
	ArrayList<Integer> quantities = new ArrayList<>(); // Holds the quantities themselves
	Object[] columns = {"Description","Price","Quantity","Total"}; // Column Identifiers
	Double total;
	int cartId = 1;
	
	/**
	 * Default Cart Constructor
	 * Runs the setTableStyle method to initialize Table Styles and Columns
	 */
	Cart() {
		setTableStyle(this.columns);
	}

	/**
	 * Add Product
	 * Searches for the product in the product list, if found, it will increase the quantity, otherwise it will add the product to the products list.
	 * No duplicate items in the cart, one item with a variable quantity. Afterwards, it re-renders the table.
	 * @param p the product to be added
	 */
	void addProduct(Product p) {
		Boolean prodFound = false; // Flag (default: false)
		
		// Runs through the product list to search for the product by product name, if it's found, flag will be set to true.
		for(int i = 0; i < products.size(); i++) {
			if(this.products.get(i).getName().equals(p.getName())) {
				this.quantities.set(i, this.quantities.get(i) + 1);
				this.tableModel.setValueAt(this.quantities.get(i), i, 2);
				prodFound = true;
				break;
			}
		}
		
		// Runs only if the specified product in the argument is not found in the products list
		if(!prodFound) {
			this.products.add(p);
			this.quantities.add(1);
		}
	}
	
	/**
	 * Remove Product
	 * 
	 * Stores the selected (highlighted) rows' indices from the GUI Product Table in rows array
	 * Removes all entries at those indices in the products and quantities which are the same across the board
	 * products[i] <=> quantities[i] <=> rows[i] (the values are not the same, but they're mapped to eachother indirectly by index number)
	 * Re-renders the table, afterwards.
	 */
	void removeProduct() {
		int[] rows = this.table.getSelectedRows();
		
		// Remove all rows and entries that were selected (highlighted) in the GUI Product Table
		for(int i = rows.length - 1; i >= 0; i--) {
			products.remove(rows[i]);
			quantities.remove(rows[i]);
		}
	}
	
	/**
	 * Clear All
	 * Clears the products and quantities list and re-renders the table.
	 */
	void clearAll(String status) {
		System.out.println(this.toString(status));
		products.clear(); // Clear Products
		quantities.clear(); // Clear Quantities
	}
	
	/**
	 * Render the Table
	 * Creates rows from the products and quantities lists.
	 */
	void renderTable() {		
		// Re-initialize the Table Model
		this.tableModel = new DefaultTableModel();
		
		// Set the Table Style
		setTableStyle(this.columns);
		
		// Create a row from each list entry for product and quantity and add it to the Table Model
		for(int i = 0; i < products.size(); i++) {
			Object[] row = new Object[4];
			
			row[0] = products.get(i).getName();
			row[1] = fmt.format(products.get(i).getPrice());
			row[2] = quantities.get(i);
			row[3] = fmt.format(products.get(i).getPrice() * quantities.get(i));
			
			this.tableModel.addRow(row);
		}
		
		this.table.setModel(this.tableModel);
	}
	
	/**
	 * Get Total
	 * @return total cost of all product(s) in cart
	 */
	Double getTotal() {
		this.total = 0.00;
		
		for(int i = 0; i < products.size(); i++) {
			total += (products.get(i).getPrice() * quantities.get(i));
		}
		
		return total;
	}
	
	/**
	 * Get ID
	 * @return ID of Cart / Transaction
	 */
	int getId() {
		return this.cartId;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String plusLine = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n";
		sb.append(plusLine + String.format("+ %89s +\r\n", "Cart ID#: " + this.cartId) +
				plusLine + String.format("+ %20s + %20s + %20s + %20s +\r\n", "Description", "Price", "Quantity", "Total") + plusLine);
		
		for(int i = 0; i < products.size(); i++) {
			sb.append(String.format("+ %20s + %20s + %20s + %20s +\r\n", products.get(i).getName(), fmt.format(products.get(i).getPrice()), quantities.get(i), fmt.format(products.get(i).getPrice() * quantities.get(i))));
		}
		
		sb.append(plusLine + String.format("+ %89s +\r\n", "Total: " + fmt.format(this.getTotal())) + plusLine);
		return sb.toString();
	}
	
	public String toString(String s) {
		StringBuilder sb = new StringBuilder();
		String plusLine = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n";
		sb.append(plusLine + String.format("+ %89s +\r\n", "(" + s + ") " + "Cart ID#: " + this.cartId) +
				plusLine + String.format("+ %20s + %20s + %20s + %20s +\r\n", "Description", "Price", "Quantity", "Total") + plusLine);
		
		for(int i = 0; i < products.size(); i++) {
			sb.append(String.format("+ %20s + %20s + %20s + %20s +\r\n", products.get(i).getName(), fmt.format(products.get(i).getPrice()), quantities.get(i), fmt.format(products.get(i).getPrice() * quantities.get(i))));
		}
		
		sb.append(plusLine + String.format("+ %89s +\r\n", "Total: " + fmt.format(this.getTotal())) + plusLine);
		return sb.toString();
	}
}
