 // CHECKSTYLE:OFF
package controllers;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.pmw.tinylog.Logger;
import org.xml.sax.SAXException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import utils.DataUtils;
import utils.ValidatorUtils;

public class ControllerDatabase {

	@FXML
	Button buttonLoad, buttonAdd, buttonRemove, buttonRename;

	@FXML
	TableView<Map.Entry<String, String>> tableView;

	@FXML
	TableColumn<Map.Entry<String, String>, String> nameColumn;

	@FXML
	Label label;

	@FXML
	TextField textField;

	private ObservableList<Map.Entry<String, String>> items;

	@FXML
	public void handleButtonLoad(ActionEvent e) throws IOException {
		if (e.getSource() == buttonLoad) {
			Logger.info("Load button pressed");
			if (tableView.getSelectionModel().getSelectedIndex() < 0) {
				showNothingIsSelectedAllert();
				Logger.error("Nothing is selected");
			} else {
				int index = tableView.getSelectionModel().getSelectedIndex();
				MainView.tripTableId = items.get(index).getKey();
				MainView.initTableLayout();
				MainView.setScene(MainView.tablePane);
			}

		}

	}

	@FXML
	public void handleButtonAdd(ActionEvent e)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		if (e.getSource() == buttonAdd) {
			Logger.info("Add button pressed");
			String name = textField.getText();
			if (ValidatorUtils.validateName(name) == -1) {
				Logger.error("The name must be at least 3 characters long!");
				label.setText("The name must be at least 3 characters long!");
				label.setVisible(true);
			} else if (ValidatorUtils.validateName(name) == -2) {
				Logger.error("Name already in use!");
				label.setText("Name already in use!");
				label.setVisible(true);
			} else if (ValidatorUtils.validateName(name) == -3) {
				Logger.error("The name can only contain letters of the english ABC!");
				label.setText("The name can only contain letters of the english ABC!");
				label.setVisible(true);
			} else if (ValidatorUtils.validateName(name) == 1) {
				Logger.info("Correct name!");
				String id = UUID.randomUUID().toString();
				DataUtils.databaseMap.put(id, name);
				items.add(new AbstractMap.SimpleEntry<String, String>(id, name));
				DataUtils.saveDataBaseMap();
				DataUtils.createDatabase(id, name);
			}
		}
	}

	public void handleButtonRename(ActionEvent e)
			throws TransformerConfigurationException, IOException, ParserConfigurationException, SAXException {
		if (e.getSource() == buttonRename) {
			Logger.info("Rename button pressed!");
			if (tableView.getSelectionModel().getSelectedIndex() < 0) {
				showNothingIsSelectedAllert();
				Logger.error("Nothing is selected!");
			} else {
				String id = tableView.getSelectionModel().getSelectedItem().getKey();
				MainView.showRenameDatabaseDialog(id);
				items.setAll(DataUtils.databaseMap.entrySet());
				Logger.info("Database renamed!");
			}
		}

	}

	@FXML
	public void handleButtonRemove(ActionEvent e)
			throws SAXException, IOException, TransformerException, ParserConfigurationException {
		if (e.getSource() == buttonRemove) {
			Logger.info("Remove button pressed");

			if (tableView.getSelectionModel().getSelectedIndex() < 0) {
				showNothingIsSelectedAllert();
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Remove");
				alert.setContentText("Are you sure you want to delete the selected item?");
				Optional<ButtonType> buttonClicked = alert.showAndWait();

				if (buttonClicked.get().equals(ButtonType.OK)) {
					Logger.info("Item removed");
					Map.Entry<String, String> selectedItem = tableView.getSelectionModel().getSelectedItem();
					DataUtils.databaseMap.remove(selectedItem.getKey());
					tableView.getItems().remove(selectedItem);
					DataUtils.saveDataBaseMap();
					DataUtils.deleteDatabase(selectedItem.getKey() + ".xml");
				}
			}
		}
	}

	@FXML
	public void handleTexFieldKeyPress(KeyEvent keyEvent) {
		label.setText("");
		label.setVisible(false);
	}

	public void showNothingIsSelectedAllert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Warning");
		alert.setContentText("Nothig is selected");
		alert.showAndWait();
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize()
			throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException {
		
		Logger.info("Database selector initialized");
		if (DataUtils.databaseMap == null) {
			items = FXCollections.observableArrayList();
		} else {
			items = FXCollections.observableArrayList(DataUtils.databaseMap.entrySet());
		}
		tableView.setItems(items);

		Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>> cellValueFactory = new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Entry<String, String>, String> p) {
				return new SimpleStringProperty(p.getValue().getValue());
			}
		};

		//Unchecked
		tableView.getColumns().setAll(nameColumn);
		nameColumn.setCellValueFactory(cellValueFactory);

		label.setVisible(false);

	}
}
