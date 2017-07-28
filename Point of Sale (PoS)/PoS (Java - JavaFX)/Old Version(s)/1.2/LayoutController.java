package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class LayoutController implements Initializable {
	Cart cart = new Cart();
	DecimalFormat fmt = new DecimalFormat("$#,##0.00;$-#,##0.00");
	DateFormat dateFmt = new SimpleDateFormat("EEEE, MMM d, yyyy h:mm:ss a");
	String[] names = {"Tommy","Spider-Man","Captain America","Thor","The Incredible Hulk","Black Widow","Iron Man","Deadpool"};
	private Double tendered;
	private Double required;
	private String usr = "Tommy";
	private PrintWriter pw;
	private Boolean processed = false;
	private ArrayList<MenuItem> namesMenu;
	
	/* GENERAL */
	@FXML private AnchorPane mainAnchorPane;
	private Properties appProps;
	
	/* WINDOW STAGES */
	private Stage settingsStage;
	private Stage payStage;
	
	/* TOP BAR */
	@FXML private Button usrBtn;
	@FXML private Label usrLbl;
	@FXML private MenuButton usrDrop;
	@FXML private ImageView nameImage;
	@FXML private ContextMenu contextMenu;
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
	private Button payBtn;
	@FXML private Button payBtnInside;
	@FXML private Button payBtnPop;
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
	
	private ObservableList<Transaction> trans = FXCollections.observableArrayList();

	public Button getUsrBtn() {
		return usrBtn;
	}

	public Label getUsrLbl() {
		return usrLbl;
	}

	public MenuButton getUsrDrop() {
		return usrDrop;
	}

	public Stage getSettingsStage() {
		return settingsStage;
	}

	public Cart getCart() {
		return cart;
	}

	public Double getTendered() {
		return tendered;
	}

	public String getUsr() {
		return usr;
	}

	public Stage getPayStage() {
		return payStage;
	}

	public TableColumn<CartItem, String> getTotal() {
		return total;
	}

	public Label getTotalAmt() {
		return totalAmt;
	}

	public TextField getTenderedAmt() {
		return tenderedAmt;
	}

	public Button getPayBtn() {
		return payBtn;
	}
	
	public void setPayBtn(Button btn) {
		this.payBtn = btn;
	}
	
	public Button getPayBtnInside() {
		return payBtnInside;
	}
	
	public Button getPayBtnPop() {
		return payBtnPop;
	}

	public Button getAddBtn() {
		return addBtn;
	}

	public Button getClearBtn() {
		return clearBtn;
	}

	public ObservableList<Transaction> getTrans() {
		return trans;
	}
	
	public PrintWriter getPw() {
		return this.pw;
	}

	public Double getRequired() {
		return required;
	}

	public Label getReqAmt() {
		return reqAmt;
	}

	public Button getPrcBtn() {
		return prcBtn;
	}

	public AnchorPane getPayBar() {
		return payBar;
	}
	
	public ImageView getNameImage() {
		return nameImage;
	}

	/* TABLE METHODS */
	// These methods access, render, and modify the `table` TableView
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
	/* END OF TABLE METHODS */
	
	/* VIEW METHODS */
	// These methods modify the view of the window and specific variables show on the window.
	@FXML
	public void pay(ActionEvent event) {
		this.payBar.setVisible(true);
		this.payBtn.setVisible(false);
		this.addBtn.setDisable(true);
	}
	
	@FXML
	public void payPop(ActionEvent event) {
		try {
			this.addBtn.setDisable(true);
			this.clearBtn.setDisable(true);
			this.payBtn.setDisable(true);
			this.payStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PayWindowLayout.fxml"));
			Parent root = loader.load();
			this.payStage.setTitle("Pay - Compuflex - Point of Sale (PoS) System");
			this.payStage.setScene(new Scene(root));
			this.payStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void changeUser(Event event) {
		int choice = (int) (Math.random() * this.names.length);
		
		while(this.usr.equals(this.names[choice])) {
			choice = (int) (Math.random() * this.names.length);
		}
		
		this.usr = this.names[choice];
		this.usrBtn.setText(this.usr);
		this.usrLbl.setText(this.usr);
		this.usrDrop.setText(this.usr);
		this.nameImage.setImage(this.textToImage(this.usr));
	}
	
	public void changeUser(String user) {
		this.usr = user;
		this.usrDrop.setText(this.usr);
		this.usrBtn.setText(this.usr);
		this.usrLbl.setText(this.usr);
		this.nameImage.setImage(this.textToImage(this.usr));
	}
	
	@FXML
	public void openSettings(ActionEvent event) {
		try {
			this.settingsStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsLayout.fxml"));
			Parent root = loader.load();
			this.settingsStage.setTitle("Settings - Compuflex - Point of Sale (PoS) System");
			this.settingsStage.setScene(new Scene(root));
			this.settingsStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* END OF VIEW METHODS */
	
	/**
	 * Initialize the window
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.usr = this.names[(int) (Math.random() * this.names.length)];
		this.usrBtn.setText(this.usr);
		this.usrLbl.setText(this.usr);
		this.usrDrop.setText(this.usr);
		this.nameImage.setImage(textToImage(this.usr));
		this.namesMenu = new ArrayList<>();
		
		initializeProperties();
		
		setUpMenus();
		
		initializePrinter();
		
		initializeClock();
		
		setupCellValues();
		
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
	}
	
	private void initializePrinter() {
		try {
			this.pw = new PrintWriter("Compuflex_PoS_JavaFX_1.2.log", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		Main.getMainStage().setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				pw.close();
			}
			
		});
	}

	private void initializeClock() {
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
	}

	private void setupCellValues() {
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
	}

	private void setUpMenus() {
		MenuItem settings = new MenuItem("Settings");
		settings.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				openSettings(event);
			}
		});
		
		this.contextMenu = new ContextMenu();
		this.contextMenu.getItems().add(settings);
		this.mainAnchorPane.setOnContextMenuRequested(e -> 
		this.contextMenu.show(this.mainAnchorPane, e.getScreenX(), e.getScreenY()));
		
		for(String s : this.names) {
			MenuItem setup = new MenuItem();
			setup.setText(s);
			setup.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					changeUser(setup.getText());
				}
			});
			this.usrDrop.getItems().add(setup);
			this.namesMenu.add(setup);
		}
	}

	private void initializeProperties() {
		try {
			// create and load default properties
			Properties defaultProps = new Properties();
			FileInputStream in;
			in = new FileInputStream("default.properties");
			defaultProps.load(in);
			in.close();
	
			// create application properties with default
			this.appProps = new Properties(defaultProps);
	
			// now load properties 
			// from last invocation
			in = new FileInputStream("Compuflex_PoS_JavaFX_1.2.properties");
			this.appProps.load(in);
			in.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		if(this.appProps.getProperty("payPop").equals("false")) {
			this.setPayBtn(this.payBtnInside);
			this.payBtnInside.setVisible(true);
		} else {
			this.setPayBtn(this.payBtnPop);
			this.payBtnPop.setVisible(true);
		}
		
		int nameSetting = Integer.parseInt(this.appProps.getProperty("nameSetting"));
		
		switch(nameSetting) {
			case 0:
				this.usrBtn.setVisible(true);
				break;
			case 1:
				this.usrLbl.setVisible(true);
				break;
			case 2:
				this.usrDrop.setVisible(true);
				break;
			case 3:
				this.nameImage.setVisible(true);
				break;
			default:
				this.usrBtn.setVisible(true);
		}
	}

	private Image textToImage(String text) {
	    Label label = new Label(text);
	    label.setMinSize(126, 24);
	    label.setMaxSize(126, 24);
	    label.setPrefSize(126, 24);
	    label.setStyle("-fx-background-color: #f4f4f4; -fx-text-fill:black;");
	    label.setWrapText(true);
	    Scene scene = new Scene(new Group(label));
	    WritableImage img = new WritableImage(126,24);
	    scene.snapshot(img);
	    return img;
	}
}


