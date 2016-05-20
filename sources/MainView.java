
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainView extends Application {

	public static Stage primaryStage;
	public static BorderPane rootPane;
	public static Scene primaryScene;
	public static AnchorPane tablePane;
	public static AnchorPane databaseSelectorPane;
	public static AnchorPane addTripPane;
	public static String tripTableId;

	public static void initRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainView.class.getResource("RootPane.fxml"));

		rootPane = (BorderPane) loader.load();
		primaryScene = new Scene(rootPane);

		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}

	public static void initTableLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainView.class.getResource("Table.fxml"));

		tablePane = (AnchorPane) loader.load();
	}

	public static void initDatabaseSelector() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainView.class.getResource("DatabaseSelector.fxml"));

		databaseSelectorPane = (AnchorPane) loader.load();
		rootPane.setCenter(databaseSelectorPane);
	}
	

	public static void showRenameDatabaseDialog(String id)
			throws IOException, TransformerConfigurationException, ParserConfigurationException, SAXException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainView.class.getResource("RenameDatabase.fxml"));
		Pane dialogPane = (Pane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(dialogPane);
		dialogStage.setScene(scene);

		ControllerRenameDatabase controller = loader.getController();
		controller.setId(id);
		controller.init();
		controller.setStage(dialogStage);

		dialogStage.setResizable(false);
		dialogStage.showAndWait();
	}

	public static void showNewTripDialog() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainView.class.getResource("NewTrip.fxml"));
		AnchorPane dialogPane = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(dialogPane);
		dialogStage.setScene(scene);
		
		ControllerNewTrip controller = loader.getController();
		controller.setStage(dialogStage);
		
		dialogStage.setResizable(false);
		dialogStage.showAndWait();
		
	}
	
	public static void showEditTripDialog(Trip trip) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainView.class.getResource("EditTrip.fxml"));
		AnchorPane dialogPane = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(dialogPane);
		dialogStage.setScene(scene);
		
		ControllerEditTrip controller = loader.getController();
		controller.setStage(dialogStage);
		controller.setTrip(trip);
		
		dialogStage.setResizable(false);
		dialogStage.showAndWait();
		
	}

	// Ez csúnya
	public static void setScene(AnchorPane pane) {
		rootPane.setCenter(pane);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		MainView.primaryStage = primaryStage;
		MainView.primaryStage.setTitle("Cím");

		initRootLayout();
		initDatabaseSelector();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
