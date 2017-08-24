package posSwing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

public class SettingsWindow {

	JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<String> productList;
	private Settings appSettings;
	
	/**
	 * Create the application.
	 */
	public SettingsWindow(Settings s) {
		appSettings = s;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 316, 233);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "cell 0 0,grow");
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[][][grow]", "[][][][][][]"));
		
		JLabel lblUserNameOptions = new JLabel("User Name Options");
		panel.add(lblUserNameOptions, "cell 0 0");
		
		JLabel lblPayButtonOptions = new JLabel("Pay Button Options");
		panel.add(lblPayButtonOptions, "cell 2 0");
		
		JRadioButton rdbtnButtons = new JRadioButton("Buttons");
		rdbtnButtons.setSelected(true);
		buttonGroup.add(rdbtnButtons);
		panel.add(rdbtnButtons, "cell 0 1");
		
		JLabel lblProductListOptions = new JLabel("Product List Options");
		lblProductListOptions.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(lblProductListOptions, "cell 2 2");
		
		productList = new JComboBox<>();
		panel.add(productList, "cell 2 3,growx");
		
		JRadioButton rdbtnImages = new JRadioButton("Images");
		buttonGroup.add(rdbtnImages);
		panel.add(rdbtnImages, "cell 0 4");
		
		JToggleButton tglbtnPopUp = new JToggleButton("Pop Up");
		panel.add(tglbtnPopUp, "cell 2 1");
		
		JRadioButton rdbtnLabels = new JRadioButton("Labels");
		buttonGroup.add(rdbtnLabels);
		panel.add(rdbtnLabels, "cell 0 2");
		
		JRadioButton rdbtnDropdown = new JRadioButton("Dropdown");
		buttonGroup.add(rdbtnDropdown);
		panel.add(rdbtnDropdown, "cell 0 3");
		
		lblUserNameOptions.setBorder(BorderFactory.createLineBorder(Color.black));
		lblPayButtonOptions.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = 0;
				if(rdbtnButtons.isSelected()) {
					choice = 0;
				} else if(rdbtnLabels.isSelected()) {
					choice = 1;
				} else if(rdbtnDropdown.isSelected()) {
					choice = 2;
				} else if(rdbtnImages.isSelected()) {
					choice = 3;
				}
				
				if(tglbtnPopUp.isSelected()) {
					appSettings.setPopUp(true);
				} else {
					appSettings.setPopUp(false);
				}
				
				appSettings.setUserDisplayOption(choice);
				
				appSettings.setProductFile(new File("products/" + productList.getSelectedItem().toString() + ".json"));
				
				frame.dispose();
			}
		});
		panel.add(btnOkay, "flowx,cell 0 5");
		
		JButton btnCancel = new JButton("Cancel");
		panel.add(btnCancel, "cell 0 5");
		
		switch(appSettings.getUserDisplayOption()) {
			case 0:
				rdbtnButtons.setSelected(true);
				break;
			case 1:
				rdbtnLabels.setSelected(true);
				break;
			case 2:
				rdbtnDropdown.setSelected(true);
				break;
			case 3:
				rdbtnImages.setSelected(true);
				break;
			default:
				rdbtnButtons.setSelected(true);
				break;
		}
		
		if(appSettings.isPopUp()) {
			tglbtnPopUp.setSelected(true);
		} else {
			tglbtnPopUp.setSelected(false);
		}
		
		File[] ls = createProdFolder(new File("products/"));
		Boolean fileFound = false;
		for(File f : ls) {
			productList.addItem(f.getName().substring(0, f.getName().indexOf(".json")));
			String newFile = f.getName().substring(0, f.getName().indexOf(".json"));
			String file = appSettings.getProductFile().getName().substring(0, appSettings.getProductFile().getName().indexOf(".json"));
			if(file.equals(newFile)) {
				productList.setSelectedItem(newFile);
				fileFound = true;
			}
		}
		
		if(!fileFound) {
			productList.setSelectedIndex(0);
		}
	}
	
	protected File[] createProdFolder(File prodFolder) {
		if(!prodFolder.exists()) {
			prodFolder.mkdir();
		}
		File[] ls = prodFolder.listFiles();
		if(ls.length == 0) {
			try {
				PrintWriter def = new PrintWriter("products/default.json");
				def.println("{\r\n" + 
						"	\"items\": [\r\n" + 
						"		{\"name\": \"Product #1\", \"price\": 1.00},\r\n" + 
						"		{\"name\": \"Product #2\", \"price\": 2.00}\r\n" + 
						"	]\r\n" + 
						"}");
				def.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return ls;
	}

}
