package posSwing;

public class Item {
	private String name;
	private Double price;
	private int quantity;
	private Double total;

	public Item(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = 1;
		this.total = this.price * this.quantity;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * Increment the Quantity
	 */
	public void incrementQuantity() {
		this.quantity++;
	}
}
