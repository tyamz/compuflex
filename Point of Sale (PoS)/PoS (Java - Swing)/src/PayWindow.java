package posSwing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

public class PayWindow {

	JFrame frame;
	private JTextField tenderedAmt;
	private Cart cart;
	private TransactionRecord trans;
	private DecimalFormat fmt;
	private User user;
	
	/**
	 * Create the application.
	 */
	public PayWindow(Cart c, TransactionRecord t, User u) {
		this.cart = c;
		this.trans = t;
		this.user = u;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fmt = cart.getFmt();
		frame = new JFrame();
		frame.setBounds(100, 100, 247, 170);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[grow][grow][grow][]"));
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblTotal, "cell 0 0,grow");
		
		JLabel totalAmt = new JLabel(fmt.format(cart.getTotal()));
		totalAmt.setHorizontalAlignment(SwingConstants.RIGHT);
		frame.getContentPane().add(totalAmt, "cell 1 0,grow");
		
		JLabel lblTendered = new JLabel("Tendered:");
		lblTendered.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblTendered, "cell 0 1,grow");
		
		tenderedAmt = new JTextField();
		tenderedAmt.setHorizontalAlignment(SwingConstants.RIGHT);
		tenderedAmt.setText("0.00");
		frame.getContentPane().add(tenderedAmt, "cell 1 1,growx");
		tenderedAmt.setColumns(10);
		
		JLabel lblRequired = new JLabel("Required:");
		frame.getContentPane().add(lblRequired, "cell 0 2");
		
		JLabel requiredAmt = new JLabel(fmt.format(cart.getTotal()));
		requiredAmt.setHorizontalAlignment(SwingConstants.RIGHT);
		frame.getContentPane().add(requiredAmt, "cell 1 2,grow");
		
		totalAmt.setBorder(BorderFactory.createLineBorder(Color.green));
		requiredAmt.setBorder(BorderFactory.createLineBorder(Color.red));
		
		JButton btnProcess = new JButton("Process");
		btnProcess.setEnabled(false);
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double total = cart.getTotal();
					Double tendered = Double.parseDouble(tenderedAmt.getText());
					Double required = fmt.parse(requiredAmt.getText()).doubleValue();
					Date current = new Date();
					Transaction t = new Transaction(current,user,total,tendered,required);
					trans.add(t);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnProcess, "flowx,cell 0 3");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnCancel, "cell 0 3");
		
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
			
		});
		
		frame.setVisible(true);
	}

}
