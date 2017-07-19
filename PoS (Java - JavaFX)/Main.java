package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Point of Sale (PoS) - JavaFX
 * This is a sample Point of Sale (PoS) System made with JavaFX for the Compuflex Corporation to help test various screen mapping and management software(s).
 * @author Thomas Yamakaitis
 * @version 1.0
 */
public class Main extends Application {
	public static Stage mainStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.mainStage = primaryStage;
			Pane root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
			Scene scene = new Scene(root,700,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.mainStage.setScene(scene);
			Main.mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
