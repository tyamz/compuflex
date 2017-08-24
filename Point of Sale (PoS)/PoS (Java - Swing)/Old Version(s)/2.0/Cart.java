package posSwing;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Cart {
	private JTable items = new JTable();
	private DefaultTableModel itemTable;
	private final String[] itemList = {"Macadamia", "Hazelnut", "Almond", "Peanut", "Walnut", "Pistachio", "Pecan", "Brazil"};
	private final Double[] priceList = {2.00, 1.90, 1.31, 0.85, 1.12, 1.53, 1.25, 1.75};
	private final Object[] columns = {"Description", "Price", "Quantity", "Total"};
	private final DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	private Double total = 0.00;
	private int count = 1;

	Cart() {
		itemTable = new DefaultTableModel();
		itemTable.setColumnIdentifiers(columns);
	}
	
	Cart(Item[] items) {
		itemTable = new DefaultTableModel();
		itemTable.setColumnIdentifiers(columns);
		for(Item i : items) {
			Object[] row = new Object[4];
			row[0] = i.getName();
			row[1] = fmt.format(i.getPrice());
			row[2] = i.getQuantity();
			row[3] = fmt.format(i.getTotal());
			itemTable.addRow(row);
		}
		this.items.setModel(itemTable);
	}
	
	Cart(ArrayList<Item> items) {
		itemTable = new DefaultTableModel();
		itemTable.setColumnIdentifiers(columns);
		for(Item i : items) {
			Object[] row = new Object[4];
			row[0] = i.getName();
			row[1] = fmt.format(i.getPrice());
			row[2] = i.getQuantity();
			row[3] = fmt.format(i.getTotal());
			itemTable.addRow(row);
		}
		this.items.setModel(itemTable);
	}

	/**
	 * @return the items
	 */
	public JTable getItems() {
		return items;
	}

	/**
	 * @return the fmt
	 */
	public DecimalFormat getFmt() {
		return fmt;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(JTable items) {
		this.items = items;
	}

	public void add(Item i) {
		Object[] row = new Object[4];
		row[0] = i.getName();
		row[1] = fmt.format(i.getPrice());
		row[2] = i.getQuantity();
		row[3] = fmt.format(i.getTotal());
		total += i.getPrice();
		itemTable.addRow(row);
		items.setModel(itemTable);
	}
	
	public void addItem() throws ParseException {
		int choice = random(itemList.length);
		Item item = new Item(itemList[choice],priceList[choice]);
		Boolean itemExists = false;
		
		for(int i = 0; i < itemTable.getRowCount(); i++) {
			if(((String) itemTable.getValueAt(i, 0)).equals(item.getName())) {
				itemExists = true;
				int q = (int) itemTable.getValueAt(i, 2);
				itemTable.setValueAt(++q, i, 2);
				Double itemPrice = fmt.parse((String) itemTable.getValueAt(i, 1)).doubleValue();
				itemTable.setValueAt(fmt.format(itemPrice * q), i, 3);
				resetTotal();
			}
		}
		
		items.setModel(itemTable);
		
		if(!itemExists) {
			add(item);
		}
	}

	private int random(int n) {
		return (int) (Math.random() * n);
	}
	
	/**
	 * @return the total
	 */
	public Double getTotal() {
		return this.total;
	}

	/**
	 * @param total the total to set
	 * @throws ParseException 
	 */
	public Double resetTotal() throws ParseException {
		total = 0.00;
		for(int i = 0; i < itemTable.getRowCount(); i++) {
			total += fmt.parse((String) itemTable.getValueAt(i, 3)).doubleValue();
		}
		return total;
	}

	public void clear() throws ParseException {
		itemTable = new DefaultTableModel();
		itemTable.setColumnIdentifiers(columns);
		items.setModel(itemTable);
		resetTotal();
	}

	/**
	 * To String
	 */
	public String toString(String s) {
		StringBuilder sb = new StringBuilder();
		String plusLine = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n";
		sb.append(plusLine + String.format("+ %89s +\r\n", "(" + s + ") " + "Cart ID#: " + count) +
				plusLine + String.format("+ %20s + %20s + %20s + %20s +\r\n", "Description", "Price", "Quantity", "Total") + plusLine);
		
		for(int i = 0; i < items.getRowCount(); i++) {
			sb.append(String.format("+ %20s + %20s + %20d + %20s +\r\n", (String) items.getValueAt(i, 0), (String) items.getValueAt(i, 1), (int) items.getValueAt(i, 2), (String) items.getValueAt(i, 3)));
		}
		
		sb.append(plusLine + String.format("+ %89s +\r\n", "Total: " + fmt.format(this.getTotal())) + plusLine);
		if(s.equals("COMPLETE")) { count++; }
		return sb.toString();
	}
}
