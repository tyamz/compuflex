package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
	@FXML private ComboBox<String> productsSettings;
	
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
		this.s.setProductFile(new File("products\\" + this.productsSettings.getSelectionModel().getSelectedItem().toString() + ".json"));
		this.s.saveSettings();
		
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
		
		File prodFolder = new File("products/");
		
		File[] ls = this.s.createProdFolder(prodFolder);
		ObservableList<String> productOptions = FXCollections.observableArrayList();
		
		for(File f : ls) {
			if(f.isFile() && f.getName().contains(".json")) {
				productOptions.add(f.getName().substring(0, f.getName().indexOf(".json")));
			}
		}
		
		this.productsSettings.getItems().addAll(productOptions);
		boolean fileFound = false;
		for(int i = 0; i < productOptions.size(); i++) {
			String str = s.getProductFile().getName().substring(0, s.getProductFile().getName().indexOf(".json"));
			if(productOptions.get(i).equals(str)) {
				this.productsSettings.getSelectionModel().select(i);
				fileFound = true;
			}
		}
		
		if(!fileFound) {
			this.productsSettings.getSelectionModel().selectFirst();
		}
	}
}
