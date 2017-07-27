package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SettingsLayoutController implements Initializable {
	@FXML private ToggleGroup userDisplay;
	@FXML private ToggleGroup popUp;
	@FXML private RadioButton buttons;
	@FXML private RadioButton labels;
	@FXML private RadioButton dropdown;
	@FXML private RadioButton images;
	@FXML private RadioButton inside;
	@FXML private RadioButton payPop;
	@FXML private Stage stageSettings;
	
	private Settings s;

	// Event Listener on Button.onAction
	@FXML
	public void save(ActionEvent event) throws IOException {
		Toggle t = this.userDisplay.getSelectedToggle();
		Toggle p = this.popUp.getSelectedToggle();
		
		Main.getMainLayout().getUsrDisplay().setVisible(false);
		if(t.equals(this.buttons)) {
			Main.getMainLayout().setUsrDisplay(Main.getMainLayout().getUsrBtn());
			s.setUserDisplay(0);
		} else if(t.equals(this.labels)) {
			Main.getMainLayout().setUsrDisplay(Main.getMainLayout().getUsrLbl());
			s.setUserDisplay(1);
		} else if(t.equals(this.dropdown)) {
			Main.getMainLayout().setUsrDisplay(Main.getMainLayout().getUsrDrop());
			s.setUserDisplay(2);
		} else if(t.equals(this.images)) {
			Main.getMainLayout().setUsrDisplay(Main.getMainLayout().getNameImage());
			s.setUserDisplay(3);
		}
		Main.getMainLayout().getUsrDisplay().setVisible(true);
		
		Main.getMainLayout().getPayBtn().setVisible(false);
		if(p.equals(this.inside)) {
			Main.getMainLayout().setPayBtn(Main.getMainLayout().getPayBtnInside());
			s.setPopUp(false);
		} else if(p.equals(this.payPop)) {
			Main.getMainLayout().setPayBtn(Main.getMainLayout().getPayBtnPop());
			s.setPopUp(true);
		}
		Main.getMainLayout().getPayBtn().setVisible(true);
		
		Main.getMainLayout().getSettingsStage().close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void cancel(ActionEvent event) {
		Main.getMainLayout().getSettingsStage().close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		s = Main.getMainLayout().getSettings();
		
		if(s.isPopUp()) {
			this.popUp.selectToggle(this.payPop);
		} else {
			this.popUp.selectToggle(this.inside);
		}
		
		switch(s.getUserDisplay()) {
			case 0:
				this.userDisplay.selectToggle(this.buttons);
				break;
			case 1:
				this.userDisplay.selectToggle(this.labels);
				break;
			case 2:
				this.userDisplay.selectToggle(this.dropdown);
				break;
			case 3:
				this.userDisplay.selectToggle(this.images);
				break;
			default:
				this.userDisplay.selectToggle(this.buttons);
				break;
		}
	}
}
