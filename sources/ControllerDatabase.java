import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

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
			if (tableView.getSelectionModel().getSelectedIndex() < 0) {
				showNothingIsSelectedAllert();
			} else {
				int index = tableView.getSelectionModel().getSelectedIndex();
				if (index >= 0) {
					MainView.tripTableId = items.get(index).getKey();
					MainView.initTableLayout();
					MainView.setScene(MainView.tablePane);
				} else {
					// TODO: át kell írni!
					System.out.println("Hiba");
				}
			}

		}

	}

	@FXML
	public void handleButtonAdd(ActionEvent e)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		if (e.getSource() == buttonAdd) {
			String name = textField.getText();
			if (DataHandler.validateName(name) == -1) {
				label.setText("The name must be at least 3 characters long!");
				label.setVisible(true);
			} else if (DataHandler.validateName(name) == -2) {
				label.setText("Name already in use!");
				label.setVisible(true);
			} else if (DataHandler.validateName(name) == -3) {
				label.setText("The name can only contain letters of the english ABC!");
				label.setVisible(true);
			} else if (DataHandler.validateName(name) == 1) {
				String id = UUID.randomUUID().toString();
				DataHandler.databaseMap.put(id, name);
				items.add(new AbstractMap.SimpleEntry<String, String>(id, name));
				DataHandler.saveDataBaseMap();
				DataHandler.createDatabase(id, name);
			}
		}
	}

	public void handleButtonRename(ActionEvent e)
			throws TransformerConfigurationException, IOException, ParserConfigurationException, SAXException {
		if (e.getSource() == buttonRename) {
			if (tableView.getSelectionModel().getSelectedIndex() < 0) {
				showNothingIsSelectedAllert();
			} else {
				String id = tableView.getSelectionModel().getSelectedItem().getKey();
				MainView.showRenameDatabaseDialog(id);
				// TODO: Refractor? (csak az adott elem módosítása)
				items.setAll(DataHandler.databaseMap.entrySet());
			}
		}

	}

	@FXML
	public void handleButtonRemove(ActionEvent e) throws SAXException, IOException, TransformerException {
		if (e.getSource() == buttonRemove) {

			if (tableView.getSelectionModel().getSelectedIndex() < 0) {
				showNothingIsSelectedAllert();
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Remove");
				alert.setContentText("Are you sure you want to delete the selected item?");
				Optional<ButtonType> buttonClicked = alert.showAndWait();

				if (buttonClicked.get().equals(ButtonType.OK)) {
					Map.Entry<String, String> selectedItem = tableView.getSelectionModel().getSelectedItem();
					DataHandler.databaseMap.remove(selectedItem.getKey());
					tableView.getItems().remove(selectedItem);
					DataHandler.saveDataBaseMap();
					DataHandler.deleteDatabase(selectedItem.getKey() + ".xml");
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

	// @SuppressWarnings("unchecked")
	@FXML
	private void initialize()
			throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException {

		if (DataHandler.databaseMap == null) {
			items = FXCollections.observableArrayList();
		} else {
			items = FXCollections.observableArrayList(DataHandler.databaseMap.entrySet());
		}
		tableView.setItems(items);

		Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>> cellValueFactory = new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Entry<String, String>, String> p) {
				return new SimpleStringProperty(p.getValue().getValue());
			}
		};

		// TODO: unchecked
		tableView.getColumns().setAll(nameColumn);
		nameColumn.setCellValueFactory(cellValueFactory);

		label.setVisible(false);

	}
}
