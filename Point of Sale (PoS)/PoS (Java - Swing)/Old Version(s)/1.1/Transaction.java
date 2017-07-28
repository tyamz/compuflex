package compuflexPosSwing;

import java.util.Date;

/**
 * Transaction Class
 * This class stores transaction information such as the date/time of transaction, the user who processed it,
 * the total cost of the transaction, the total amount tendered for the transaction, and the amount of change
 * returned to the customer.
 * @author Thomas Yamakaitis
 */
class Transaction {
	Date stamp;
	User user;
	Double total;
	Double tendered;
	Double change;
	int transId;
	
	/**
	 * Default Constructor
	 */
	Transaction() { }
	
	/**
	 * Transaction Constructor
	 * @param s the timestamp of the transaction
	 * @param u the user who processed the transaction
	 * @param tot the total cost of the transaction
	 * @param tend the total amount tendered for the transaction
	 * @param ch the total amount of change returned for the transaction
	 */
	Transaction(Date s, User u, Double tot, Double tend, Double ch, int id) {
		this.stamp = s;
		this.user = u;
		this.total = tot;
		this.tendered = tend;
		this.change = ch;
		this.transId = id;
	}
	
	/**
	 * Get Time Stamp
	 * @return the timestamp of the transactio
	 */
	Date getStamp() {
		return this.stamp;
	}
	
	/**
	 * Get User
	 * @return the user who processed the transaction
	 */
	User getUser() {
		return this.user;
	}
	
	/**
	 * Get Total
	 * @return the total cost of the transaction
	 */
	Double getTotal() {
		return this.total;
	}
	
	/**
	 * Get Tendered
	 * @return the total amount tendered for the transaction
	 */
	Double getTendered() {
		return this.tendered;
	}
	
	/**
	 * Get Change
	 * @return the total amount of change returned for the transaction
	 */
	Double getChange() {
		return this.change;
	}
	
	/**
	 * Get ID
	 * @return ID of Cart/Transaction
	 */
	int getId() {
		return this.transId;
	}
}
