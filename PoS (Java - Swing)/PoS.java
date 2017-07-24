package posSwing;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

public class PoS {

	private JFrame frame;
	private JTable itemTable;
	private JTable transTable;
	private JButton userBtn; 
	private JPanel buttonPanel;
	private JLabel userLbl;
	private JPanel labelPanel;
	private JMenu userDrop;
	private User u;
	private Cart cart;
	private TransactionRecord trans;
	private String[] names = {"Iron Man", "Captain America", "Deadpool", "Black Panther", "Scarlet Witch", "The Incredible Hulk", "Wolverine", "Starlord", "Rocket Raccoon"};
	private Settings appSettings;
	private JMenuItem settingsMenu;
	private JComponent userDisplay;
	private boolean functions = false;
	private JButton btnAdd;
	private JButton btnClear;
	private JLabel totalAmt;
	private JButton btnPay;
	private JButton btnProcess;
	private JButton btnCancel;
	private JLabel lblTendered;
	private JTextField tenderedAmt;
	private JLabel lblRequired;
	private JLabel requiredAmt;
	private JLabel timeStamp;
	private DecimalFormat fmt;
	private DateFormat dateFmt;
	private JPanel panel_1;
	private PrintWriter printer;

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
		u = new User("Default User");
		cart = new Cart();
		trans = new TransactionRecord();
		fmt = cart.getFmt();
		dateFmt = trans.getDateFmt();
		try {
			printer = new PrintWriter(new File("Compuflex_PoS_Swing_2.0.log"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 721, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		JPanel cartPanel = new JPanel();
		tabbedPane.addTab("Cart", null, cartPanel, null);
		cartPanel.setLayout(new MigLayout("", "[grow]", "[][grow][grow][grow]"));
		
		btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		cartPanel.add(btnAdd, "flowx,cell 0 0");
		
		btnClear = new JButton("Clear");
		btnClear.setEnabled(true);
		cartPanel.add(btnClear, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		cartPanel.add(scrollPane, "cell 0 1,growx,aligny top");
		
		itemTable = cart.getItems();
		scrollPane.setViewportView(itemTable);
		
		JPanel panel = new JPanel();
		cartPanel.add(panel, "cell 0 2,grow");
		panel.setLayout(new MigLayout("", "[][][][][grow][][][][][][][][][][][][][][][][][][]", "[][]"));
		
		JLabel lblTotal = new JLabel("Total:");
		panel.add(lblTotal, "cell 0 0");
		
		totalAmt = new JLabel("$0.00");
		panel.add(totalAmt, "cell 1 0");
		
		lblTendered = new JLabel("Tendered:");
		panel.add(lblTendered, "cell 3 0,alignx trailing");
		
		tenderedAmt = new JTextField();
		tenderedAmt.setHorizontalAlignment(SwingConstants.RIGHT);
		tenderedAmt.setText("0.00");
		panel.add(tenderedAmt, "cell 4 0,growx");
		tenderedAmt.setColumns(10);
		
		lblRequired = new JLabel("Required:");
		panel.add(lblRequired, "cell 6 0");
		
		requiredAmt = new JLabel("$0.00");
		panel.add(requiredAmt, "cell 7 0");
		
		btnProcess = new JButton("Process");
		btnProcess.setEnabled(false);
		panel.add(btnProcess, "cell 21 0");
		
		btnCancel = new JButton("Cancel");
		panel.add(btnCancel, "flowx,cell 22 0");
		
		btnPay = new JButton("Pay");
		btnPay.setVisible(false);
		btnPay.setEnabled(false);
		panel.add(btnPay, "cell 22 0");
		
		JPanel transPanel = new JPanel();
		tabbedPane.addTab("Trans", null, transPanel, null);
		transPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane transScrollPane = new JScrollPane();
		transPanel.add(transScrollPane, "cell 0 0,grow");
		
		transTable = trans.getTransactions();
		transScrollPane.setViewportView(transTable);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(frame, popupMenu);
		
		settingsMenu = new JMenuItem("Settings");
		popupMenu.add(settingsMenu);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		userDrop = new JMenu(u.getName());
		menuBar.add(userDrop);
		
		labelPanel = new JPanel();
		menuBar.add(labelPanel);
		labelPanel.setLayout(new MigLayout("", "[]", "[]"));
		
		userLbl = new JLabel(u.getName());
		labelPanel.add(userLbl, "cell 0 0");
		userLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		buttonPanel = new JPanel();
		menuBar.add(buttonPanel);
		buttonPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		userBtn = new JButton(u.getName());
		buttonPanel.add(userBtn, "flowx,cell 0 0,alignx left,aligny center");
		
		Date date = new Date();
		
		panel_1 = new JPanel();
		menuBar.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[]", "[]"));
		timeStamp = new JLabel(dateFmt.format(date));
		panel_1.add(timeStamp, "cell 0 0");
		
		/* CODE BEGINS HERE */
		togglePay();
		JComponent[] jcomps = {buttonPanel,labelPanel,userDrop};
		toggleVisibility(jcomps);
		
		setUser();
		appSettings = new Settings();
		setSettings();
		show();
	}

	private void setSettings() {
		appSettings.getComponents().clear();
		if(!functions) { assignFunctions(); } // Assigns functionality to buttons, drop-downs, labels, etc.
		setDisplayOptions(); // Sets display options
	}

	private void setDisplayOptions() {
		if(userDisplay != null) { userDisplay.setVisible(false); }
		switch(appSettings.getUserDisplayOption()) {
			case 0:
				userDisplay = buttonPanel;
				break;
			case 1:
				userDisplay = labelPanel;
				break;
			case 2:
				userDisplay = userDrop;
				break;
			default:
				userDisplay = buttonPanel;
				break;
		}
		userDisplay.setVisible(true);
	}

	private void assignFunctions() {		
		userBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser();
			}
		});
		
		for(String s: names) {
			JMenuItem jmi = new JMenuItem(s);
			jmi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setUser(jmi.getText());
				}
			});
			userDrop.add(jmi);
		}
		
		userLbl.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				setUser();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		userLbl.setBorder(new EmptyBorder(5,5,5,5));
		
		settingsMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SettingsWindow sw = new SettingsWindow(appSettings);
				sw.frame.setVisible(true);
				sw.frame.addWindowListener(new WindowListener() {

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosed(WindowEvent e) {
						setSettings();
					}

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
		});
		
		functions = true;
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				try {
					cart.addItem();
					itemTable = cart.getItems();
					totalAmt.setText(fmt.format(cart.getTotal()));
					Double tendered = Double.parseDouble(tenderedAmt.getText());
					requiredAmt.setText(fmt.format(cart.getTotal() - tendered));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(cart.getItems().getRowCount() > 0) {
					btnClear.setEnabled(true);
					btnPay.setEnabled(true);
				}
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					printer.println(cart.toString("CANCELLED"));
					System.out.println(cart.toString("CANCELLED"));
					cart.clear();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				itemTable = cart.getItems();
				totalAmt.setText(fmt.format(cart.getTotal()));
				btnClear.setEnabled(false);
				btnPay.setEnabled(false);
			}
		});
		
		btnPay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(appSettings.isPopUp()) {
					@SuppressWarnings("unused")
					PayWindow pw = new PayWindow(cart,trans,u);
					pw.frame.addWindowListener(new WindowListener() {

						@Override
						public void windowActivated(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowClosed(WindowEvent arg0) {
							try {
								printer.println(cart.toString("PROCESSED"));
								System.out.println(cart.toString("COMPLETE"));
								JButton[] buttons = {btnPay,btnClear};
								toggle(buttons);
								cart.clear();
								totalAmt.setText(fmt.format(cart.getTotal()));
								tenderedAmt.setText("0.00");
								requiredAmt.setText(fmt.format(cart.getTotal()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						

						@Override
						public void windowClosing(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeactivated(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeiconified(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowIconified(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowOpened(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}
						
					});
				} else {
					togglePay();
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				togglePay();
			}
		});
		
		btnProcess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Double total = cart.getTotal();
					Double tendered = Double.parseDouble(tenderedAmt.getText());
					Double required = fmt.parse(requiredAmt.getText()).doubleValue();
					Date current = new Date();
					Transaction t = new Transaction(current,u,total,tendered,required);
					trans.add(t);
					togglePay();
					JButton[] buttons = {btnPay,btnClear};
					toggle(buttons);
					printer.println(cart.toString("PROCESSED"));
					System.out.println(cart.toString("COMPLETE"));
					cart.clear();
					totalAmt.setText(fmt.format(cart.getTotal()));
					tenderedAmt.setText("0.00");
					requiredAmt.setText(fmt.format(cart.getTotal()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		tenderedAmt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updateFields();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				updateFields();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				updateFields();
			}

			private void updateFields() {
				if(!tenderedAmt.getText().isEmpty()) {
					try {
						Double total = fmt.parse(totalAmt.getText()).doubleValue();
						Double required = total - Double.parseDouble(tenderedAmt.getText());
						requiredAmt.setText(fmt.format(required));
						if(required > 0) {
							requiredAmt.setBorder(BorderFactory.createLineBorder(Color.red));
							btnProcess.setEnabled(false);
						} else {
							requiredAmt.setBorder(BorderFactory.createLineBorder(Color.green));
							btnProcess.setEnabled(true);
						}
					} catch (NumberFormatException | ParseException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		
		Timer clock = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date date = new Date();
				timeStamp.setText(dateFmt.format(date));
			}
		});
		clock.setRepeats(true);
        clock.start();
        
        frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				printer.println(trans.toString());
				System.out.println(trans.toString());
				printer.close();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
	}

	protected void togglePay() {
		JComponent[] jcomps = {btnPay,btnProcess,btnCancel,lblTendered,tenderedAmt,lblRequired,requiredAmt};
		toggleVisibility(jcomps);
		JButton[] buttons = {btnAdd,btnClear,btnProcess};
		toggle(buttons);
	}

	private void toggle(JButton[] buttons) {
		for(JButton b : buttons) {
			if(b.isEnabled()) {
				b.setEnabled(false);
			} else {
				b.setEnabled(true);
			}
		}
	}

	private void setUser(String name) {
		u = new User(name);
		
		userBtn.setText(name);
		userLbl.setText(name);
		userDrop.setText(name);
	}

	private void show() {
		JComponent[] jcomps = new JComponent[0];
		toggleVisibility(appSettings.getComponents().toArray(jcomps));
	}

	private void setUser() {
		u = new User(pickRandomName(u.getName()));
		
		userBtn.setText(u.getName());
		userLbl.setText(u.getName());
		userDrop.setText(u.getName());
	}

	private String pickRandomName(String old) {
		int r = random(names.length);
		
		while(names[r].equals(old)) {
			r = random(names.length);
		}
		
		return names[r];
	}

	private int random(int n) {
		return (int) (Math.random() * n);
	}
	
	private void toggleVisibility(JComponent[] jcomps) {
		for(JComponent j : jcomps) {
			if(j.isVisible()) {
				j.setVisible(false);
			} else {
				j.setVisible(true);
			}
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
