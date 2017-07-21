package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SettingsLayoutController implements Initializable {
	@FXML
	private ToggleGroup nameSettings;
	private int nameSetting;
	@FXML
	private ToggleGroup payBtnSettings;
	@FXML
	private RadioButton buttons;
	@FXML
	private RadioButton labels;
	@FXML
	private RadioButton dropdown;
	@FXML
	private RadioButton images;
	@FXML
	private RadioButton inside;
	@FXML
	private RadioButton popup;
	@FXML
	private Stage stageSettings;
	
	private Properties applicationProps;
	private String pop;

	// Event Listener on Button.onAction
	@FXML
	public void save(ActionEvent event) throws IOException {
		Toggle t = this.nameSettings.getSelectedToggle();
		Toggle p = this.payBtnSettings.getSelectedToggle();
		
		if(t.equals(this.buttons)) {
			Main.getMainLayout().getUsrBtn().setVisible(true);
			Main.getMainLayout().getUsrLbl().setVisible(false);
			Main.getMainLayout().getUsrDrop().setVisible(false);
			Main.getMainLayout().getNameImage().setVisible(false);
			this.nameSetting = 0;
		} else if(t.equals(this.labels)) {
			Main.getMainLayout().getUsrLbl().setVisible(true);
			Main.getMainLayout().getUsrBtn().setVisible(false);
			Main.getMainLayout().getUsrDrop().setVisible(false);
			Main.getMainLayout().getNameImage().setVisible(false);
			this.nameSetting = 1;
		} else if(t.equals(this.dropdown)) {
			Main.getMainLayout().getUsrDrop().setVisible(true);
			Main.getMainLayout().getUsrBtn().setVisible(false);
			Main.getMainLayout().getUsrLbl().setVisible(false);
			Main.getMainLayout().getNameImage().setVisible(false);
			this.nameSetting = 2;
		} else if(t.equals(this.images)) {
			Main.getMainLayout().getNameImage().setVisible(true);
			Main.getMainLayout().getUsrBtn().setVisible(false);
			Main.getMainLayout().getUsrLbl().setVisible(false);
			Main.getMainLayout().getUsrDrop().setVisible(false);
			this.nameSetting = 3;
		}
		
		if(p.equals(this.inside)) {
			Main.getMainLayout().getPayBtnInside().setVisible(true);
			Main.getMainLayout().getPayBtnPop().setVisible(false);
			Main.getMainLayout().setPayBtn(Main.getMainLayout().getPayBtnInside());
			this.pop = "false";
		} else if(p.equals(this.popup)) {
			Main.getMainLayout().getPayBtnPop().setVisible(true);
			Main.getMainLayout().getPayBtnInside().setVisible(false);
			Main.getMainLayout().setPayBtn(Main.getMainLayout().getPayBtnPop());
			this.pop = "true";
		}
		
		Main.getMainLayout().getPayBtn().setDisable(false);
		
		saveProperties();
		Main.getMainLayout().getSettingsStage().close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void cancel(ActionEvent event) {
		Main.getMainLayout().getSettingsStage().close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			createProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(this.applicationProps.getProperty("payPop").equals("false")) {
			this.payBtnSettings.selectToggle(this.inside);
		} else {
			this.payBtnSettings.selectToggle(this.popup);
		}
		
		this.nameSetting = Integer.parseInt(applicationProps.getProperty("nameSetting"));
		
		switch(this.nameSetting) {
			case 0:
				this.nameSettings.selectToggle(this.buttons);
				break;
			case 1:
				this.nameSettings.selectToggle(this.labels);
				break;
			case 2:
				this.nameSettings.selectToggle(this.dropdown);
				break;
			case 3:
				this.nameSettings.selectToggle(this.images);
				break;
			default:
				this.nameSetting = 0;
				this.nameSettings.selectToggle(this.buttons);
		}
	}
	
	private void createProperties() throws IOException {
		// create and load default properties
		Properties defaultProps = new Properties();
		FileInputStream in = new FileInputStream("default.properties");
		defaultProps.load(in);
		in.close();

		// create application properties with default
		this.applicationProps = new Properties(defaultProps);

		// now load properties 
		// from last invocation
		in = new FileInputStream("Compuflex_PoS_JavaFX_1.2.properties");
		this.applicationProps.load(in);
		in.close();
	}
	
	private void saveProperties() throws IOException {
		// save properties
		FileOutputStream out = new FileOutputStream("Compuflex_PoS_JavaFX_1.2.properties");
		this.applicationProps.setProperty("payPop", this.pop);
		this.applicationProps.setProperty("nameSetting", this.nameSetting + "");
		this.applicationProps.store(out, "-- No Comment --");
		out.close();
	}
}
