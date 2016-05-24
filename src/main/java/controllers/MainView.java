// CHECKSTYLE:OFF
package controllers;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.pmw.tinylog.Logger;
import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Trip;
import utils.DataUtils;

public class MainView extends Application {

	public static Stage primaryStage;
	public static AnchorPane tablePane;
	public static AnchorPane databaseSelectorPane;
	public static AnchorPane addTripPane;
	public static String tripTableId;

	public static void initTableLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainView.class.getResource("/fxml/Table.fxml"));
			tablePane = (AnchorPane) loader.load();
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	}

	public static void showDatabaseSelectorPane() {
		try {
			DataUtils.intit();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainView.class.getResource("/fxml/DatabaseSelector.fxml"));
			databaseSelectorPane = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setScene(new Scene(databaseSelectorPane));

			ControllerDatabase controller = loader.getController();
			controller.setStage(dialogStage);

			dialogStage.setResizable(false);
			dialogStage.showAndWait();

		} catch (IOException e) {
			Logger.error(e.toString());
		}

	}

	public static void showRenameDatabaseDialog(String id) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainView.class.getResource("/fxml/RenameDatabase.fxml"));
			Pane dialogPane;
			dialogPane = (Pane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setScene(new Scene(dialogPane));

			ControllerRenameDatabase controller = loader.getController();
			controller.setId(id);
			controller.init();
			controller.setStage(dialogStage);

			dialogStage.setResizable(false);
			dialogStage.showAndWait();
		} catch (IOException | TransformerConfigurationException | ParserConfigurationException | SAXException e) {
			Logger.error(e.toString());
		}
	}

	public static void showNewTripDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainView.class.getResource("/fxml/NewTrip.fxml"));
			AnchorPane dialogPane;
			dialogPane = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(dialogPane);
			dialogStage.setScene(scene);

			ControllerNewTrip controller = loader.getController();
			controller.setStage(dialogStage);

			dialogStage.setResizable(false);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	}

	public static void showEditTripDialog(Trip trip) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainView.class.getResource("/fxml/EditTrip.fxml"));
			AnchorPane dialogPane;
			dialogPane = (AnchorPane) loader.load();
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
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	}

	public static void showSummary() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainView.class.getResource("/fxml/Summary.fxml"));
			Pane dialogPane;
			dialogPane = (Pane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(dialogPane);
			dialogStage.setScene(scene);

			ControllerShowSummary controller = loader.getController();
			controller.setStage(dialogStage);

			dialogStage.setResizable(false);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	}

	@Override
	public void start(Stage primaryStage) {
		MainView.primaryStage = primaryStage;
		MainView.primaryStage.setTitle("Trip Manager");
		showDatabaseSelectorPane();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
