package compuflexPosSwing;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

/**
 * Trans (Transactions) Class
 * This class stores a list of previously processed transaction(s).
 * @author Thomas Yamakaitis
 */
class Trans extends TableBuilder {
	ArrayList<Transaction> trans = new ArrayList<>();
	Object[] columns = {"Stamp","User","Total","Tendered","Change"};
	
	Trans() { }

	/**
	 * Add Transaction
	 * @param t adds transaction to the list
	 */
	void addTrans(Transaction t) {
		this.trans.add(t);
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
		for(int i = 0; i < trans.size(); i++) {
			Object[] row = new Object[5];
			
			row[0] = dateFmt.format(trans.get(i).stamp);
			row[1] = trans.get(i).getUser().getName();
			row[2] = fmt.format(trans.get(i).getTotal());
			row[3] = fmt.format(trans.get(i).getTendered());
			row[4] = fmt.format(trans.get(i).getChange());
			
			this.tableModel.addRow(row);
		}
		
		if(!trans.isEmpty()) {
			System.out.println(this.toString());
		}
		
		this.table.setModel(this.tableModel);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String plusLine = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
		sb.append(plusLine + String.format("+ %127s +\n", "Transaction ID#:" + trans.size()) + 
				plusLine + String.format("+ %35s + %20s + %20s + %20s + %20s +\n", "Stamp", "User", "Total", "Tendered", "Change") + plusLine);
		
		for(int i = 0; i < trans.size(); i++) {
			sb.append(String.format("+ %35s + %20s + %20s + %20s + %20s +\n",dateFmt.format(trans.get(i).stamp),trans.get(i).getUser().getName(),fmt.format(trans.get(i).getTotal()),fmt.format(trans.get(i).getTendered()),fmt.format(trans.get(i).getChange())));
		}
		
		sb.append(plusLine);
		return sb.toString();
	}
}
