package compuflex;

import java.awt.*;
import java.text.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.util.*;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PoS {

	private JFrame frame;
	private JTable prodTable;
	private JTable transTable;
	private User user;
	private Double tendered;
	private Double required;
	private Date date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PoS window = new PoS();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PoS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 793, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Cart cart = new Cart();
		Trans trans = new Trans();
		DateFormat dateFmt = trans.getDateFormat();
		ArrayList<User> users = new ArrayList<>();
		user = new User();
		users.add(user);
		Border border = BorderFactory.createLineBorder(Color.GREEN);
		DecimalFormat fmt = cart.getNumFormat();
		tendered = 0.00;
		required = cart.getTotal() - tendered;
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenuItem usrBtn = new JMenuItem(user.name);
		usrBtn.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(usrBtn);
		date = new Date();
		JMenuItem timeStamp = new JMenuItem(dateFmt.format(date));
		menuBar.add(timeStamp);
		frame.getContentPane().setLayout(new MigLayout("insets 4 4 4 4",
                "[fill,grow]", "[fill,90%]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		JPanel cartPanel = new JPanel();
		tabbedPane.addTab("Cart", null, cartPanel, null);
		cartPanel.setLayout(new MigLayout("", "[grow]", "[][grow][grow][]"));
		
		JPanel transPanel = new JPanel();
		tabbedPane.addTab("Trans", null, transPanel, null);
		transPanel.setLayout(new MigLayout("", "[grow]", "[][grow][grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		cartPanel.add(scrollPane, "cell 0 1,grow");
		
		JScrollPane transPane = new JScrollPane();
		transPanel.add(transPane, "cell 0 1,grow");
		
		transTable = trans.getTable();
		transPane.setViewportView(transTable);
		prodTable = cart.getTable();
		scrollPane.setViewportView(prodTable);
		
		JPanel toolbar = new JPanel();
		cartPanel.add(toolbar, "cell 0 2,grow");
		toolbar.setLayout(new MigLayout("", "[][][][][grow][][][][][][][][][][][][][][][][][][][][]", "[][]"));
		
		JLabel lblTendered = new JLabel("Tendered:");
		lblTendered.setVisible(false);
		toolbar.add(lblTendered, "cell 3 0,alignx trailing");
		
		JTextField tenderedField = new JTextField();
		tenderedField.setVisible(false);
		tenderedField.setHorizontalAlignment(SwingConstants.RIGHT);
		tenderedField.setText("0.00");
		toolbar.add(tenderedField, "cell 4 0,growx");
		tenderedField.setColumns(10);
		
		JLabel lblRequired = new JLabel("Required:  ");
		lblRequired.setVisible(false);
		toolbar.add(lblRequired, "cell 7 0");
		
		JLabel requiredLabel = new JLabel(fmt.format(cart.getTotal() - tendered));
		requiredLabel.setVisible(false);
		requiredLabel.setBorder(border);
		requiredLabel.setHorizontalAlignment(SwingConstants.CENTER);
		toolbar.add(requiredLabel, "cell 8 0");
		
		JLabel lblTotal = new JLabel("Total:  ");
		toolbar.add(lblTotal, "cell 0 0");
		
		JLabel total = new JLabel(fmt.format(cart.getTotal()));
		total.setBorder(border);
		toolbar.add(total, "cell 1 0");
		
		JButton btnProcess = new JButton("Process");
		btnProcess.setVisible(false);
		toolbar.add(btnProcess, "flowx,cell 24 0");
		
		JButton btnAdd = new JButton("Add");
		cartPanel.add(btnAdd, "flowx,cell 0 0");
		
		JButton btnRemove = new JButton("Remove");
		cartPanel.add(btnRemove, "cell 0 0");
		
		JButton btnClear = new JButton("Clear");
		cartPanel.add(btnClear, "cell 0 0");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setVisible(false);
		toolbar.add(btnCancel, "cell 24 0");
		
		JButton btnPay = new JButton("Pay");
		btnPay.setEnabled(false);
		toolbar.add(btnPay, "cell 24 0");
		
		/* ACTION LISTENERS */
		ActionListener clrCancel = (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cart.clearAll("CANCELLED");
				prodTable = cart.getTable();
				btnPay.setEnabled(false);
				if(!btnPay.isVisible()) { toggleVisibility(btnPay); }
				JComponent[] com = {btnCancel,btnProcess,lblTendered,tenderedField,lblRequired,requiredLabel};
				for(int i = 0; i < com.length; i++) {
					if(com[i].isVisible()) {
						toggleVisibility(com[i]);
					} else {
						break;
					}
				}
				
				tendered = 0.00;
				required = cart.getTotal() - tendered;
				total.setText(fmt.format(cart.getTotal()));
				tenderedField.setText("0.00");
				requiredLabel.setText(fmt.format(required));
			}
		});
		btnClear.addActionListener(clrCancel);
		btnCancel.addActionListener(clrCancel);
		
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent[] com = {btnPay,btnCancel,btnProcess,lblTendered,tenderedField,lblRequired,requiredLabel};
				for(int i = 0; i < com.length; i++) {
					toggleVisibility(com[i]);
				}
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(prodTable.getRowCount() == prodTable.getSelectedRows().length) {
					cart.clearAll("CANCELLED");
					prodTable = cart.getTable();
					tendered = 0.00;
					required = cart.getTotal() - tendered;
					total.setText(fmt.format(cart.getTotal()));
					tenderedField.setText("0.00");
					requiredLabel.setText(fmt.format(required));
					btnPay.setEnabled(false);
					if(!btnPay.isVisible()) { toggleVisibility(btnPay); }
					JComponent[] com = {btnCancel,btnProcess,lblTendered,tenderedField,lblRequired,requiredLabel};
					for(int i = 0; i < com.length; i++) {
						if(com[i].isVisible()) {
							toggleVisibility(com[i]);
						} else {
							break;
						}
					}
				} else {
					cart.removeProduct();
					prodTable = cart.getTable();
				}
				
				total.setText(fmt.format(cart.getTotal()));
				requiredLabel.setText(fmt.format(required));
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] item = {"Macadamia", "Hazelnut", "Almond", "Peanut", "Walnut", "Pistachio", "Pecan", "Brazil"};
				Double[] price = {2.00, 1.90, 1.31, 0.85, 1.12, 1.53, 1.25, 1.75};
				
				int choice = (int) (Math.random() * item.length);
				
				Product p = new Product(item[choice], price[choice]);
				
				cart.addProduct(p);
				prodTable = cart.getTable();
				btnPay.setEnabled(true);
				total.setText(fmt.format(cart.getTotal()));
				required = cart.getTotal() - tendered;
				requiredLabel.setText(fmt.format(required));
			}
		});
		
		tenderedField.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent documentEvent) {
            	changeRequired();
            }
			public void insertUpdate(DocumentEvent documentEvent) {
            	changeRequired();
            }
			public void removeUpdate(DocumentEvent documentEvent) {
            	changeRequired();
            }
			public void changeRequired() {
				if(tenderedField.getText().matches("^[0-9]\\d*(\\.\\d+)?$")) {
					tendered = Double.parseDouble(tenderedField.getText());
					required = cart.getTotal() - tendered;
					requiredLabel.setText(fmt.format(required));
				}
			}
		});
		tenderedField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date date = new Date();
				try {
					if(!tenderedField.getText().matches("^[0-9]\\d*(\\.\\d+)?$")) {
						if(tenderedField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(frame, "Error! Tendered field cannot be empty", "Error - Empty Field", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(frame, "Error! Tendered field can only contain numbers greater than or equal to 0.00", "Error - Tendered", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						if(Double.parseDouble(tenderedField.getText()) < cart.getTotal()) {
							JOptionPane.showMessageDialog(frame, "Error! Tendered amount must be greater than or equal to " + fmt.format(cart.getTotal()), "Error - Not Enough", JOptionPane.ERROR_MESSAGE);
						} else {
							Transaction t = new Transaction(date,user,cart.getTotal(),tendered,fmt.parse(requiredLabel.getText()).doubleValue());
							trans.addTrans(t);
							transTable = trans.getTable();
							cart.clearAll("PROCESSED");
							prodTable = cart.getTable();
							btnPay.setEnabled(false);
							if(!btnPay.isVisible()) { toggleVisibility(btnPay); }
							JComponent[] com = {btnCancel,btnProcess,lblTendered,tenderedField,lblRequired,requiredLabel};
							
							for(int i = 0; i < com.length; i++) {
								if(com[i].isVisible()) {
									toggleVisibility(com[i]);
								} else {
									break;
								}
							}
							
							tendered = 0.00;
							required = cart.getTotal() - tendered;
							total.setText(fmt.format(cart.getTotal()));
							tenderedField.setText("0.00");
							requiredLabel.setText(fmt.format(required));
						}
					}
				} catch (NumberFormatException | ParseException e) {
					e.printStackTrace();
				}
			}
		});
		
		usrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User oldUser = user;
				user = new User();
				while(oldUser.getName().equals(user.getName())) {
					user = new User();
				}
				Boolean userFound = false;
				for(int i = 0; i < users.size(); i++) {
					if(user.getName().equals(users.get(i).getName())) {
						user = users.get(i);
						userFound = true;
						break;
					}
				}
				
				if(!userFound) {
					users.add(user);
				}
				usrBtn.setText(user.getName());
			}
		});
		Timer clock = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				date = new Date();
				timeStamp.setText(dateFmt.format(date));
			}
		});
		clock.setRepeats(true);
        clock.start();
	}
	
	/**
	 * Toggle Visibility
	 * Toggles the visibility of a JComponent, if off, turn on, if on, turn off.
	 * @param j the JComponent to toggle
	 */
	void toggleVisibility(JComponent j) {
		if(!j.isVisible()) {
			j.setVisible(true);
		} else {
			j.setVisible(false);
		}
	}
}


/**
 * Product Class
 * This class contains the name, quantity and price of a product, and methods for accessing those values.
 * @author Thomas Yamakaitis
 */
class Product {
	String name;
	Double price;
	
	/**
	 * Product Constructor
	 * There is no default constructor because the product must have a name and a price.
	 * Products with no price should have a `p` value of 0.0
	 * @param n the product's name
	 * @param p the product's price
	 */
	Product(String n, Double p) {
		this.name = n;
		this.price = p;
	}
	
	/**
	 * Get Name Method
	 * @return the name of the product
	 */
	String getName() {
		return this.name;
	}
	
	/**
	 * Get Price Method
	 * @return the price of the product
	 */
	Double getPrice() {
		return this.price;
	}
	
	/**
	 * Set Name Method
	 * @param n the name that you would like to change the product's name to
	 */
	void setName(String n) {
		this.name = n;
	}
	
	/**
	 * Set Price Method
	 * @param p the price that you would like to change the product's price to
	 */
	void setPrice(Double p) {
		this.price = p;
	}
}

/**
 * Cart Class
 * This class contains the product list, quantity of those products in the cart, and a product table GUI to display the cart graphically.
 * @author Thomas Yamakaitis
 */
class Cart extends TableBuilder {
	ArrayList<Product> products = new ArrayList<>(); // Holds the products themselves
	ArrayList<Integer> quantities = new ArrayList<>(); // Holds the quantities themselves
	Object[] columns = {"Description","Price","Quantity","Total"}; // Column Identifiers
	Double total;
	
	/**
	 * Default Cart Constructor
	 * Runs the setTableStyle method to initialize Table Styles and Columns
	 */
	Cart() {
		setTableStyle(this.columns);
	}

	/**
	 * Add Product
	 * Searches for the product in the product list, if found, it will increase the quantity, otherwise it will add the product to the products list.
	 * No duplicate items in the cart, one item with a variable quantity. Afterwards, it re-renders the table.
	 * @param p the product to be added
	 */
	void addProduct(Product p) {
		Boolean prodFound = false; // Flag (default: false)
		
		// Runs through the product list to search for the product by product name, if it's found, flag will be set to true.
		for(int i = 0; i < products.size(); i++) {
			if(this.products.get(i).getName().equals(p.getName())) {
				this.quantities.set(i, this.quantities.get(i) + 1);
				this.tableModel.setValueAt(this.quantities.get(i), i, 2);
				prodFound = true;
				break;
			}
		}
		
		// Runs only if the specified product in the argument is not found in the products list
		if(!prodFound) {
			this.products.add(p);
			this.quantities.add(1);
		}
	}
	
	/**
	 * Remove Product
	 * 
	 * Stores the selected (highlighted) rows' indices from the GUI Product Table in rows array
	 * Removes all entries at those indices in the products and quantities which are the same across the board
	 * products[i] <=> quantities[i] <=> rows[i] (the values are not the same, but they're mapped to eachother indirectly by index number)
	 * Re-renders the table, afterwards.
	 */
	void removeProduct() {
		int[] rows = this.table.getSelectedRows();
		
		// Remove all rows and entries that were selected (highlighted) in the GUI Product Table
		for(int i = rows.length - 1; i >= 0; i--) {
			products.remove(rows[i]);
			quantities.remove(rows[i]);
		}
	}
	
	/**
	 * Clear All
	 * Clears the products and quantities list and re-renders the table.
	 */
	void clearAll(String status) {
		System.out.println(this.toString(status));
		products.clear(); // Clear Products
		quantities.clear(); // Clear Quantities
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
		for(int i = 0; i < products.size(); i++) {
			Object[] row = new Object[4];
			
			row[0] = products.get(i).getName();
			row[1] = fmt.format(products.get(i).getPrice());
			row[2] = quantities.get(i);
			row[3] = fmt.format(products.get(i).getPrice() * quantities.get(i));
			
			this.tableModel.addRow(row);
		}
		
		this.table.setModel(this.tableModel);
	}
	
	/**
	 * Get Total
	 * @return total cost of all product(s) in cart
	 */
	Double getTotal() {
		this.total = 0.00;
		
		for(int i = 0; i < products.size(); i++) {
			total += (products.get(i).getPrice() * quantities.get(i));
		}
		
		return total;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String plusLine = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
		sb.append(plusLine + String.format("+ %89s +\n", "Cart") +
				plusLine + String.format("+ %20s + %20s + %20s + %20s +\n", "Description", "Price", "Quantity", "Total") + plusLine);
		
		for(int i = 0; i < products.size(); i++) {
			sb.append(String.format("+ %20s + %20s + %20s + %20s +\n", products.get(i).getName(), fmt.format(products.get(i).getPrice()), quantities.get(i), fmt.format(products.get(i).getPrice() * quantities.get(i))));
		}
		
		sb.append(plusLine);
		return sb.toString();
	}
	
	public String toString(String s) {
		StringBuilder sb = new StringBuilder();
		String plusLine = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
		sb.append(plusLine + String.format("+ %89s +\n", "(" + s + ") " + "Cart") +
				plusLine + String.format("+ %20s + %20s + %20s + %20s +\n", "Description", "Price", "Quantity", "Total") + plusLine);
		
		for(int i = 0; i < products.size(); i++) {
			sb.append(String.format("+ %20s + %20s + %20s + %20s +\n", products.get(i).getName(), fmt.format(products.get(i).getPrice()), quantities.get(i), fmt.format(products.get(i).getPrice() * quantities.get(i))));
		}
		
		sb.append(plusLine + String.format("+ %89s +\n", "Total: " + fmt.format(this.getTotal())) + plusLine);
		return sb.toString();
	}
}

/**
 * User Class
 * Defines the user(s) viewing and processing the transaction(s) in the cart.
 * @author Thomas Yamakaitis
 */
class User {
	String name = "Tommy";
	
	User() {
		pickName();
	}
	
	/**
	 * Get Name
	 * @return user's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Pick Name
	 * Picks a new random name for the user
	 */
	void pickName() {
		String[] names = {"Spider-Man","Captain America","Thor","The Incredible Hulk","Black Widow","Iron Man","Deadpool"};
		int choice = (int) (Math.random() * names.length);
		
		this.name = names[choice];
	}
}

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
	Transaction(Date s, User u, Double tot, Double tend, Double ch) {
		this.stamp = s;
		this.user = u;
		this.total = tot;
		this.tendered = tend;
		this.change = ch;
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
}

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