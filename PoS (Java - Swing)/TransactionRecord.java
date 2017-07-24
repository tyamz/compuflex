package posSwing;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TransactionRecord {
	private JTable transactions = new JTable();
	private DefaultTableModel transTable = new DefaultTableModel();
	private final Object[] columns = {"Stamp","User","Total","Tendered","Change"};
	private final DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	private final DateFormat dateFmt = new SimpleDateFormat("EEEE, MMM d, yyyy h:mm:ss a");
	
	/**
	 * Default Constructor
	 */
	TransactionRecord() {
		transTable.setColumnIdentifiers(columns);
		transactions.setModel(transTable);
	}
	
	/**
	 * TransactionRecord Constructor
	 * @param trans
	 */
	TransactionRecord(Transaction[] trans) {
		for(Transaction t : trans) {
			Object[] row = new Object[5];
			row[0] = dateFmt.format(t.getStamp());
			row[1] = t.getUser().getName();
			row[2] = fmt.format(t.getTotal());
			row[3] = fmt.format(t.getTendered());
			row[4] = fmt.format(t.getChange());
			transTable.addRow(row);
		}
		transactions.setModel(transTable);
	}
	
	/**
	 * TransactionRecord Constructor
	 * @param trans
	 */
	TransactionRecord(ArrayList<Transaction> trans) {
		for(Transaction t : trans) {
			Object[] row = new Object[5];
			row[0] = dateFmt.format(t.getStamp());
			row[1] = t.getUser().getName();
			row[2] = fmt.format(t.getTotal());
			row[3] = fmt.format(t.getTendered());
			row[4] = fmt.format(t.getChange());
			transTable.addRow(row);
		}
		transactions.setModel(transTable);
	}
	
	/**
	 * Add Transaction to Record
	 * @param t the transaction
	 */
	void add(Transaction t) {
		Object[] row = new Object[5];
		row[0] = dateFmt.format(t.getStamp());
		row[1] = t.getUser().getName();
		row[2] = fmt.format(t.getTotal());
		row[3] = fmt.format(t.getTendered());
		row[4] = fmt.format(t.getChange());
		transTable.addRow(row);
		transactions.setModel(transTable);
	}

	/**
	 * @return the dateFmt
	 */
	public DateFormat getDateFmt() {
		return dateFmt;
	}

	/**
	 * @return the transactions
	 */
	public JTable getTransactions() {
		return transactions;
	}

	/**
	 * @return the transTable
	 */
	public DefaultTableModel getTransTable() {
		return transTable;
	}

	/**
	 * @return the columns
	 */
	public Object[] getColumns() {
		return columns;
	}

	/**
	 * @return the fmt
	 */
	public DecimalFormat getFmt() {
		return fmt;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(JTable transactions) {
		this.transactions = transactions;
	}

	/**
	 * @param transTable the transTable to set
	 */
	public void setTransTable(DefaultTableModel transTable) {
		this.transTable = transTable;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String plusLine = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n";
		sb.append(plusLine + String.format("+ %151s +\r\n", "Transaction(s): " + transactions.getRowCount()) + 
				plusLine + String.format("+ %21s + %35s + %20s + %20s + %20s + %20s +\r\n", "Transaction ID#", "Stamp", "User", "Total", "Tendered", "Change") + plusLine);
		
		for(int i = 0; i < transactions.getRowCount(); i++) {
			sb.append(String.format("+ %20d. + %35s + %20s + %20s + %20s + %20s +\r\n",i + 1,(String) transactions.getValueAt(i, 0),(String) transactions.getValueAt(i, 1),(String) transactions.getValueAt(i, 2),(String) transactions.getValueAt(i, 3),(String) transactions.getValueAt(i, 4)));
		}
		
		sb.append(plusLine);
		return sb.toString();
	}
}
