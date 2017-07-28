package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransactionRecord {
	private ObservableList<Transaction> trans = FXCollections.observableArrayList();
	
	TransactionRecord() { }
	
	TransactionRecord(Transaction[] tr) {
		for(Transaction t : tr) {
			this.add(t);
		}
	}
	
	TransactionRecord(ArrayList<Transaction> tr) {
		for(Transaction t : tr) {
			this.add(t);
		}
	}
	
	public ObservableList<Transaction> getTrans() {
		return this.trans;
	}
	
	public void add(Transaction t) {
		this.trans.add(t);
	}
	
	public void add(Transaction[] tr) {
		for(Transaction t : tr) {
			this.add(t);
		}
	}
	
	public void add(ArrayList<Transaction> tr) {
		for(Transaction t : tr) {
			this.add(t);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String plusLine = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n";
		sb.append(plusLine + String.format("+ %151s +\r\n", "Transaction(s):" + trans.size()) + 
				plusLine + String.format("+ %21s + %35s + %20s + %20s + %20s + %20s +\r\n", "Transaction ID#", "Stamp", "User", "Total", "Tendered", "Change") + plusLine);
		
		for(int i = 0; i < trans.size(); i++) {
			sb.append(String.format("+ %20d. + %35s + %20s + %20s + %20s + %20s +\r\n",trans.get(i).getCartId(),trans.get(i).getStamp(),trans.get(i).getUser(),trans.get(i).getTotal(),trans.get(i).getTendered(),trans.get(i).getChange()));
		}
		
		sb.append(plusLine);
		return sb.toString();
	}
}