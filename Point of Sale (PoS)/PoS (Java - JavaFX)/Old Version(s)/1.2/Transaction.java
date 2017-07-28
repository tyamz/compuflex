package application;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {
	StringProperty stamp;
	StringProperty user;
	StringProperty total;
	StringProperty tendered;
	StringProperty change;
	DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	DateFormat dateFmt = new SimpleDateFormat("EEEE, MMM d, yyyy h:mm:ss a");

	/**
	 * Transaction Constructor
	 * @param s the date of the transaction
	 * @param u the user who processed the transaction
	 * @param tot the total cost of the transaction
	 * @param tend the total tendered for the transaction
	 * @param ch the amount of change returned for the transaction
	 */
	Transaction(Date s, String u, Double tot, Double tend, Double ch) {
		this.stamp = new SimpleStringProperty(this.dateFmt.format(s));
		this.user = new SimpleStringProperty(u);
		this.total = new SimpleStringProperty(fmt.format(tot));
		this.tendered = new SimpleStringProperty(fmt.format(tend));
		this.change = new SimpleStringProperty(fmt.format(ch));
	}

	/**
	 * Stamp Property
	 * @return the stamp string property
	 */
	public StringProperty stampProperty() {
		return this.stamp;
	}
	/**
	 * Get Stamp
	 * @return the actual stamp string
	 */
	public String getStamp() {
		return this.stampProperty().get();
	}
	/**
	 * Set Stamp
	 * @param stamp the string value to set stamp to
	 */
	public void setStamp(String stamp) {
		this.stampProperty().set(stamp);
	}
	
	/**
	 * User Property
	 * @return the user string property
	 */
	public StringProperty userProperty() {
		return this.user;
	}
	/**
	 * Get User
	 * @return the name of the user who processed the transaction
	 */
	public String getUser() {
		return this.userProperty().get();
	}
	/**
	 * Set User
	 * @param user the string value to set user to
	 */
	public void setUser(String user) {
		this.userProperty().set(user);
	}
	
	/**
	 * Total Property
	 * @return the total string property
	 */
	public StringProperty totalProperty() {
		return this.total;
	}
	/**
	 * Get Total
	 * @return the total string
	 */
	public String getTotal() {
		return this.totalProperty().get();
	}
	/**
	 * Set Total
	 * @param total the string value to set total to
	 */
	public void setTotal(String total) {
		this.totalProperty().set(total);
	}
	
	/**
	 * Tendered Property
	 * @return the tendered string property
	 */
	public StringProperty tenderedProperty() {
		return this.tendered;
	}
	/**
	 * Get Tendered
	 * @return the tendered string
	 */
	public String getTendered() {
		return this.tenderedProperty().get();
	}
	/**
	 * Set Tendered
	 * @param tendered the string value to set tendered to
	 */
	public void setTendered(String tendered) {
		this.tenderedProperty().set(tendered);
	}
	
	/**
	 * Change Property
	 * @return the change string property
	 */
	public StringProperty changeProperty() {
		return this.change;
	}
	/**
	 * Get Change
	 * @return the change string
	 */
	public String getChange() {
		return this.changeProperty().get();
	}
	/**
	 * Set Change
	 * @param change the string value to set change to
	 */
	public void setChange(String change) {
		this.changeProperty().set(change);
	}
}
