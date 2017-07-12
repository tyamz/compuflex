package compuflexPosSwing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

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