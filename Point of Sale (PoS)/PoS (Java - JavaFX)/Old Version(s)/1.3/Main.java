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
 * @version 1.3
 */
public class Main extends Application {
	public static Stage mainStage;
	public static LayoutController mainLayout;
	
	public static Stage getMainStage() {
		return mainStage;
	}

	public static void setMainStage(Stage mainStage) {
		Main.mainStage = mainStage;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Main.mainStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout.fxml"));
			Pane root = loader.load();
			Scene scene = new Scene(root,700,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			mainLayout = loader.getController();
			Main.mainStage.setScene(scene);
			Main.mainStage.show();
			Main.mainStage.setTitle("Compuflex - Point of Sale (PoS) - Test System");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static LayoutController getMainLayout() {
		return mainLayout;
	}

	public static void setMainLayout(LayoutController mainLayout) {
		Main.mainLayout = mainLayout;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
