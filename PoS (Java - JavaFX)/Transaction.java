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

	Transaction(Date s, String u, Double tot, Double tend, Double ch) {
		this.stamp = new SimpleStringProperty(this.dateFmt.format(s));
		this.user = new SimpleStringProperty(u);
		this.total = new SimpleStringProperty(fmt.format(tot));
		this.tendered = new SimpleStringProperty(fmt.format(tend));
		this.change = new SimpleStringProperty(fmt.format(ch));
	}

	public StringProperty stampProperty() {
		return this.stamp;
	}
	

	public String getStamp() {
		return this.stampProperty().get();
	}
	

	public void setStamp(String stamp) {
		this.stampProperty().set(stamp);
	}
	

	public StringProperty userProperty() {
		return this.user;
	}
	

	public String getUser() {
		return this.userProperty().get();
	}
	

	public void setUser(String user) {
		this.userProperty().set(user);
	}
	

	public StringProperty totalProperty() {
		return this.total;
	}
	

	public String getTotal() {
		return this.totalProperty().get();
	}
	

	public void setTotal(String total) {
		this.totalProperty().set(total);
	}
	

	public StringProperty tenderedProperty() {
		return this.tendered;
	}
	

	public String getTendered() {
		return this.tenderedProperty().get();
	}
	

	public void setTendered(String tendered) {
		this.tenderedProperty().set(tendered);
	}
	

	public StringProperty changeProperty() {
		return this.change;
	}
	

	public String getChange() {
		return this.changeProperty().get();
	}
	

	public void setChange(String change) {
		this.changeProperty().set(change);
	}
	

}
