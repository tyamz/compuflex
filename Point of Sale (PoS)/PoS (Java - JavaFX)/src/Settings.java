package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import tyamz.FileFunctions;

public class Settings {
	private int userDisplay = 0;
	private boolean popUp = false;
	private File productFile;
	private Properties appProps;
	
	Settings() {
		loadProperties();
	}
	
	Settings(int n, boolean p, File f) {
		this.userDisplay = n;
		this.popUp = p;
		this.productFile = f;
	}
	
	Settings(int n) {
		this.userDisplay = n;
	}
	
	Settings(boolean p) {
		this.popUp = p;
	}

	/**
	 * @return the appProps
	 */
	public Properties getAppProps() {
		return appProps;
	}

	/**
	 * @param appProps the appProps to set
	 */
	public void setAppProps(Properties appProps) {
		this.appProps = appProps;
	}

	/**
	 * @return the userDisplay
	 */
	public int getUserDisplay() {
		return userDisplay;
	}

	/**
	 * @return the popUp
	 */
	public boolean isPopUp() {
		return popUp;
	}

	/**
	 * @param userDisplay the userDisplay to set
	 */
	public void setUserDisplay(int userDisplay) {
		this.userDisplay = userDisplay;
	}

	/**
	 * @return the productFile
	 */
	public File getProductFile() {
		return productFile;
	}

	/**
	 * @param productFile the productFile to set
	 */
	public void setProductFile(File f) {
		this.productFile = f;
	}

	protected void saveSettings() {
		try {
			PrintWriter printProps = new PrintWriter(new File("Compuflex_PoS_JavaFX_1.4.properties"));
			printProps.println("# Compuflex - Point of Sale (PoS) - Swing Version 2.0\r\n" + 
					"# Main Properties File\r\n" + 
					"\r\n" + 
					"! Pay Button Pop Up (pay in current window = false; pay in pop up window = true) [default: false].\r\n" + 
					"popUp = " + this.popUp + "\r\n" + 
					"\r\n" + 
					"! User Display Options (button = 0; label = 1; dropdown = 2) [default: 0].\r\n" + 
					"userDisplay = " + this.userDisplay + "\r\n" + 
					"! Product File Options (choose a file name from the /products folder, INCLUDE .json, DO NOT INCLUDE leading or trailing slashes '/ or \\'\r\n" +
					"productFile = " + this.productFile.getName());
			printProps.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param popUp the popUp to set
	 */
	public void setPopUp(boolean popUp) {
		this.popUp = popUp;
	}
	
	private void loadProperties() {
		File f = new File("Compuflex_PoS_JavaFX_1.4.properties");
		createProdFolder(new File("products/"));
		
		if(!f.isFile()) {
			File d = new File("default.properties");
			
			if(!d.isFile()) {
				try {
					PrintWriter printDef = new PrintWriter(new File("default.properties"));
					printDef.println("# Compuflex - Point of Sale (PoS) - JavaFX Version 1.4\r\n" + 
							"# Default Properties File\r\n" + 
							"\r\n" + 
							"! Pay Button Pop Up (pay in current window = false; pay in pop up window = true) [default: false].\r\n" + 
							"popUp = false\r\n" + 
							"\r\n" + 
							"! User Display Options (button = 0; label = 1; dropdown = 2) [default: 0].\r\n" + 
							"userDisplay = 0\r\n" +
							"! Product File Options (choose a file name from the /products folder, INCLUDE .json, DO NOT INCLUDE leading or trailing slashes '/ or \\'\r\n" +
							"productFile = default.json");
					printDef.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			try {
				FileFunctions.copyFile(d, f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// now load properties 
		// from last invocation
		try {
			this.appProps = new Properties();
			FileInputStream in = new FileInputStream("Compuflex_PoS_JavaFX_1.4.properties");
			this.appProps.load(in);
			in.close();
			
			if(this.appProps.getProperty("popUp").equals("false")) {
				this.popUp = false;
			} else {
				this.popUp = true;
			}
			
			this.userDisplay = Integer.parseInt(this.appProps.getProperty("userDisplay"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.productFile = new File("products\\" + this.appProps.getProperty("productFile"));
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
