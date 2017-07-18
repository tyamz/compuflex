package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PayWindowLayoutController implements Initializable {
	@FXML Label totalAmt = new Label();
	@FXML Label reqAmt = new Label();
	@FXML TextField tendAmt = new TextField();
	@FXML Button processBtn;
	@FXML Button cancelBtn;
	DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tendAmt.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				Double tendered = Double.parseDouble(tendAmt.getText());
				Double total = 0.00;
				try {
					total = fmt.parse(totalAmt.getText()).doubleValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				reqAmt.setText(fmt.format(total - tendered));
				
				if(tendered < total) {
					processBtn.setDisable(true);
				} else {
					processBtn.setDisable(false);
				}
			}
		});
	}
	
}
