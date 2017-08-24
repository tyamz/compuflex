package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class PayWindowLayoutController implements Initializable {
	LayoutController main;
	Cart cart;
	
	@FXML Label totalAmt = new Label();
	@FXML Label reqAmt = new Label();
	@FXML TextField tendAmt = new TextField();
	@FXML Button processBtn;
	
	Double totalVal;
	Double reqVal;
	Double tendVal = 0.00;
	DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	
	@FXML
	public void process(ActionEvent e) {
		this.tendVal = Double.parseDouble(this.tendAmt.getText());
		this.reqVal = this.totalVal - this.tendVal;
		
		Date date = new Date();
		Transaction t = new Transaction(this.cart.getCount(),date,this.main.getUsr(),this.cart.getTotal(),tendVal,reqVal);
		this.main.getTrans().add(t);
		
		
		this.main.getPw().println(this.cart.toString("COMPLETE"));
		System.out.println(this.cart.toString("COMPLETE"));
		this.cart.clearAll();
		this.main.getTotalAmt().setText(cart.getStringTotal());
		this.main.getTenderedAmt().setText("0.00");
		this.main.getReqAmt().setText(fmt.format(0.00));
		
		this.main.getPayBar().setVisible(false);
		this.main.getPrcBtn().setDisable(true);
		this.main.getPayBtn().setVisible(true);
		this.main.getPayBtn().setDisable(true);
		this.main.getAddBtn().setDisable(false);
		this.main.getClearBtn().setDisable(false);
		this.main.getPayStage().close();
	}
	
	@FXML
	public void cancel(ActionEvent e) {
		this.main.getAddBtn().setDisable(false);
		this.main.getClearBtn().setDisable(false);
		this.main.getPayBtn().setDisable(false);
		this.main.getPayStage().close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.main = Main.getMainLayout();
		this.cart = this.main.getCart();
		this.totalVal = this.cart.getTotal();
		this.totalAmt.setText(this.fmt.format(this.totalVal));
		this.reqVal = this.cart.getTotal() - this.tendVal;
		this.reqAmt.setText(this.fmt.format(this.reqVal));
		this.tendAmt.setText("0.00");
		
		this.main.getPayStage().setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				main.getAddBtn().setDisable(false);
				main.getClearBtn().setDisable(false);
				main.getPayBtn().setDisable(false);
			}
			
		});
		
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
					reqAmt.setStyle("-fx-border-color: red; -fx-background-color: #fff");
					totalAmt.setStyle("-fx-border-color: red; -fx-background-color: #fff");
				} else {
					processBtn.setDisable(false);
					reqAmt.setStyle("-fx-border-color: green");
					totalAmt.setStyle("-fx-border-color: green");
				}
			}
		});
	}
	
}
