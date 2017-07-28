package posSwing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JComponent;

public class Settings {
	private ArrayList<JComponent> components = new ArrayList<>();
	private int userDisplayOption;
	private Boolean popUp;
	private Properties applicationProps;

	Settings() {
		loadProperties();
	}
	
	Settings(JComponent[] j) {
		for(JComponent c : j) {
			this.addComponent(c);
		}
		loadProperties();
	}
	
	Settings(ArrayList<JComponent> aj) {
		this.components = aj;
		loadProperties();
	}

	public ArrayList<JComponent> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<JComponent> components) {
		this.components = components;
	}
	
	public int getUserDisplayOption() {
		return userDisplayOption;
	}

	public void setUserDisplayOption(int userDisplayOption) {
		try {
			this.userDisplayOption = userDisplayOption;
			saveProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isPopUp() {
		return popUp;
	}

	public void setPopUp(boolean popUp) {
		try {
			this.popUp = popUp;
			saveProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addComponent(JComponent j) {
		this.components.add(j);
	}
	
	public JComponent removeComponent(JComponent j) {
		for(JComponent c: this.components) {
			if(c.equals(j)) {
				return c;
			}
		}
		
		return j;
	}
	
	void loadProperties() {
		try {
			// create and load default properties
			Properties defaultProps = new Properties();
			FileInputStream in = new FileInputStream("default.properties");
			defaultProps.load(in);
			in.close();
			
			// create application properties with default
			this.applicationProps = new Properties(defaultProps);

			// now load properties 
			// from last invocation
			in = new FileInputStream("Compuflex_PoS_Swing_2.0.properties");
			this.applicationProps.load(in);
			in.close();
			
			if(this.applicationProps.getProperty("popUp").equals("true")) {
				this.popUp = true;
			} else {
				this.popUp = false;
			}
			
			this.userDisplayOption = Integer.parseInt(this.applicationProps.getProperty("userDisplay"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveProperties() throws IOException {
		// save properties
		FileOutputStream out = new FileOutputStream("Compuflex_PoS_Swing_2.0.properties");
		this.applicationProps.setProperty("popUp", popUp.toString());
		this.applicationProps.setProperty("userDisplay", userDisplayOption + "");
		this.applicationProps.store(out, "-- No Comment --");
		out.close();
	}
}