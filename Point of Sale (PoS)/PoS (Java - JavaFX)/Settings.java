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
	private Properties appProps;
	
	Settings() {
		loadProperties();
	}
	
	Settings(int n, boolean p) {
		this.userDisplay = n;
		this.popUp = p;
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
		saveSettings();
	}

	private void saveSettings() {
		try {
			PrintWriter printProps = new PrintWriter(new File("Compuflex_PoS_JavaFX_1.3.properties"));
			printProps.println("# Compuflex - Point of Sale (PoS) - Swing Version 2.0\r\n" + 
					"# Main Properties File\r\n" + 
					"\r\n" + 
					"! Pay Button Pop Up (pay in current window = false; pay in pop up window = true) [default: false].\r\n" + 
					"popUp = " + this.popUp + "\r\n" + 
					"\r\n" + 
					"! User Display Options (button = 0; label = 1; dropdown = 2) [default: 0].\r\n" + 
					"userDisplay = " + this.userDisplay);
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
		saveSettings();
	}
	
	private void loadProperties() {
		File f = new File("Compuflex_PoS_JavaFX_1.3.properties");
		
		if(!f.isFile()) {
			File d = new File("default.properties");
			
			if(d.isFile()) {
				try {
					FileFunctions.copyFile(d, f);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					PrintWriter printDef = new PrintWriter(new File("default.properties"));
					printDef.println("# Compuflex - Point of Sale (PoS) - JavaFX Version 1.3\r\n" + 
							"# Default Properties File\r\n" + 
							"\r\n" + 
							"! Pay Button Pop Up (pay in current window = false; pay in pop up window = true) [default: false].\r\n" + 
							"popUp = false\r\n" + 
							"\r\n" + 
							"! User Display Options (button = 0; label = 1; dropdown = 2) [default: 0].\r\n" + 
							"userDisplay = 0");
					printDef.close();
					saveSettings();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
		// now load properties 
		// from last invocation
		try {
			this.appProps = new Properties();
			FileInputStream in = new FileInputStream("Compuflex_PoS_JavaFX_1.3.properties");
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
	}
}
