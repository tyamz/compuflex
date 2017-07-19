package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class LayoutController implements Initializable {
	Cart cart = new Cart();
	DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	DateFormat dateFmt = new SimpleDateFormat("EEEE, MMM d, yyyy h:mm:ss a");
	private Double tendered;
	private Double required;
	private String usr = "Tommy";
	private PrintWriter pw;
	private Boolean processed = false;
	
	/* TOP BAR */
	@FXML private Button usrBtn;
	@FXML private Label date;
	
	/* CART */
	@FXML private TableView<CartItem> table;
	@FXML private TableColumn<CartItem, String> desc;
	@FXML private TableColumn<CartItem, String> price;
	@FXML private TableColumn<CartItem, Number> quantity;
	@FXML private TableColumn<CartItem, String> total;
	@FXML private Label totalAmt;
	@FXML private Label reqAmt;
	@FXML private TextField tenderedAmt;
	@FXML private Button payBtn;
	@FXML private Button prcBtn;
	@FXML private Button addBtn;
	@FXML private Button clearBtn;
	@FXML private AnchorPane payBar;
	
	/* TRANSACTIONS */
	@FXML private TableView<Transaction> transTable;
	@FXML private TableColumn<Transaction, String> stamp;
	@FXML private TableColumn<Transaction, String> user;
	@FXML private TableColumn<Transaction, String> tot;
	@FXML private TableColumn<Transaction, String> tend;
	@FXML private TableColumn<Transaction, String> change;
	
	private final ObservableList<Transaction> trans = FXCollections.observableArrayList();

	// Event Listener on Menu.onAction
	@FXML
	public void addItem(ActionEvent event) {
		if(this.payBtn.isDisabled()) {
			this.payBtn.setDisable(false);
		}
		CartItem c = new CartItem();
		this.cart.addItem(c);
		
		totalAmt.setText(cart.getStringTotal());
		tendered = Double.parseDouble(tenderedAmt.getText());
		reqAmt.setText(this.fmt.format(cart.getTotal() - tendered));
	}

	@FXML
	public void clearAll(ActionEvent event) {
		String status = (this.processed) ? "COMPLETE" : "CANCELLED";
		this.pw.println(this.cart.toString(status));
		System.out.println(this.cart.toString(status));
		this.cart.clearAll();
		this.totalAmt.setText(cart.getStringTotal());
		this.tenderedAmt.setText("0.00");
		this.reqAmt.setText(fmt.format(0.00));
		
		this.payBar.setVisible(false);
		this.prcBtn.setDisable(true);
		this.payBtn.setVisible(true);
		this.payBtn.setDisable(true);
		this.addBtn.setDisable(false);
		this.processed = false;
	}

	@FXML
	public void cancel(ActionEvent event) {
		this.payBar.setVisible(false);
		this.payBtn.setVisible(true);
		this.addBtn.setDisable(false);
	}

	@FXML
	public void process(ActionEvent event) {
		try {
			Date stamp = new Date();
			Transaction t = new Transaction(stamp,this.usr,cart.getTotal(),tendered,this.fmt.parse(reqAmt.getText()).doubleValue());
			this.trans.add(t);
			this.processed = true;
			this.clearAll(event);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void pay(ActionEvent event) {
		this.payBar.setVisible(true);
		this.payBtn.setVisible(false);
		this.addBtn.setDisable(true);
	}

	public void changeUser(ActionEvent event) {
		String[] names = {"Tommy","Spider-Man","Captain America","Thor","The Incredible Hulk","Black Widow","Iron Man","Deadpool"};
		int choice = (int) (Math.random() * names.length);
		
		while(this.usr.equals(names[choice])) {
			choice = (int) (Math.random() * names.length);
		}
		
		this.usr = names[choice];
		this.usrBtn.setText(this.usr);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.pw = new PrintWriter("Compuflex_PoS_JavaFX_1.0.log", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent actionEvent) {
						Date current = new Date();
						date.setText(dateFmt.format(current));
			        }
				}),
			    new KeyFrame(Duration.seconds(1))
			  );
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		/* CART CELL VALUES */
		this.desc.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
		this.price.setCellValueFactory(cellData -> cellData.getValue().itemPriceProperty());
		this.quantity.setCellValueFactory(cellData -> cellData.getValue().itemQuantityProperty());
		this.total.setCellValueFactory(cellData -> cellData.getValue().itemTotalProperty());
		this.table.setItems(cart.products);
		
		/* TRANSACTION CELL VALUES */
		this.stamp.setCellValueFactory(cellData -> cellData.getValue().stampProperty());
		this.user.setCellValueFactory(cellData -> cellData.getValue().userProperty());
		this.tot.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
		this.tend.setCellValueFactory(cellData -> cellData.getValue().tenderedProperty());
		this.change.setCellValueFactory(cellData -> cellData.getValue().changeProperty());
		this.transTable.setItems(this.trans);
		
		/* CHANGE LISTENER FOR TENDERED FIELD */
		tenderedAmt.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				totalAmt.setText(cart.getStringTotal());
				tendered = Double.parseDouble(tenderedAmt.getText());
				reqAmt.setText(fmt.format(cart.getTotal() - tendered));
				
				if(tendered >= cart.getTotal()) {
					prcBtn.setDisable(false);
				} else {
					prcBtn.setDisable(true);
				}
			}
		});
		
		
		/* CTRL + CLICK PAY BUTTON */
		this.payBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY) && event.isControlDown()) {
					try {
						addBtn.setDisable(true);
						clearBtn.setDisable(true);
						payBtn.setDisable(true);
						Stage stage = new Stage();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("PayWindowLayout.fxml"));
						Parent root = loader.load();
						PayWindowLayoutController pwlc = loader.getController();
						pwlc.totalAmt.setText(cart.getStringTotal());
						pwlc.reqAmt.setText(reqAmt.getText());
						
						stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

							@Override
							public void handle(WindowEvent event) {
								addBtn.setDisable(false);
								clearBtn.setDisable(false);
								payBtn.setDisable(false);
							}
							
						});
						
						pwlc.processBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

							@Override
							public void handle(MouseEvent event) {
								tendered = 0.00;
								required = 0.00;
								
								tendered = Double.parseDouble(pwlc.tendAmt.getText());
									try {
										required = fmt.parse(pwlc.reqAmt.getText()).doubleValue();
									} catch (ParseException e) {
										e.printStackTrace();
									}
									Date date = new Date();
									Transaction t = new Transaction(date,usr,cart.getTotal(),tendered,required);
									trans.add(t);
									
									
									pw.println(cart.toString("COMPLETE"));
									System.out.println(cart.toString("COMPLETE"));
									cart.clearAll();
									totalAmt.setText(cart.getStringTotal());
									tenderedAmt.setText("0.00");
									reqAmt.setText(fmt.format(0.00));
									
									payBar.setVisible(false);
									prcBtn.setDisable(true);
									payBtn.setVisible(true);
									payBtn.setDisable(true);
									addBtn.setDisable(false);
									clearBtn.setDisable(false);
									stage.close();
							}
							
						});
						
						pwlc.cancelBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

							@Override
							public void handle(MouseEvent event) {
								addBtn.setDisable(false);
								clearBtn.setDisable(false);
								payBtn.setDisable(false);
								stage.close();
							}
							
						});
						
						stage.setScene(new Scene(root));
						stage.showAndWait();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Main.mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				pw.close();
			}
			
		});
	}
}
