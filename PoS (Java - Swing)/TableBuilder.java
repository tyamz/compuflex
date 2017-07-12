package compuflexPosSwing;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

abstract class TableBuilder {
	JTable table = new JTable();
	DefaultTableModel tableModel = new DefaultTableModel();
	DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	DateFormat dateFmt = new SimpleDateFormat("EEEE, MMM d, yyyy h:mm:ss a");
	
	TableBuilder() { }
	
	/**
	 * Set Table Style
	 * Sets (or resets) default styles for Table Model
	 * Sets Table Model to the (new) Model
	 */
	void setTableStyle(Object[] columns) {
		this.tableModel.setColumnIdentifiers(columns);
		this.table.setModel(this.tableModel);
		this.table.setBackground(Color.white);
        this.table.setForeground(Color.black);
        Font font = new Font("Tahoma",1,22);
        this.table.setFont(font);
        this.table.setRowHeight(30);
	}
	
	/**
	 * Render the Table
	 * Creates rows from the products and quantities lists.
	 */
	abstract void renderTable();
	
	/**
	 * Get Table
	 * @return the rendered table
	 */
	JTable getTable() {
		renderTable(); // Render Table
		return this.table;
	}
	
	/**
	 * Get Date Format
	 * @return the date format
	 */
	public DateFormat getDateFormat() {
		return this.dateFmt;
	}
	
	/** Get Number Format
	 * @return the number format
	 */
	public DecimalFormat getNumFormat() {
		return this.fmt;
	}
	
	public abstract String toString();
}
