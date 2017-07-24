package posSwing;

import java.util.Date;

class Transaction {
	private Date stamp;
	private User user;
	private Double total;
	private Double tendered;
	private Double change;
	
	/**
	 * Transaction Constructor
	 * @param stamp
	 * @param user
	 * @param total
	 * @param tendered
	 * @param change
	 */
	public Transaction(Date stamp, User user, Double total, Double tendered, Double change) {
		this.stamp = stamp;
		this.user = user;
		this.total = total;
		this.tendered = tendered;
		this.change = change;
	}

	/**
	 * @return the stamp
	 */
	public Date getStamp() {
		return stamp;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @return the tendered
	 */
	public Double getTendered() {
		return tendered;
	}

	/**
	 * @return the change
	 */
	public Double getChange() {
		return change;
	}

	/**
	 * @param stamp the stamp to set
	 */
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * @param tendered the tendered to set
	 */
	public void setTendered(Double tendered) {
		this.tendered = tendered;
	}

	/**
	 * @param change the change to set
	 */
	public void setChange(Double change) {
		this.change = change;
	}
}
