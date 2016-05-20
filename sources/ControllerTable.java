import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerTable {

	@FXML
	private TableView<Trip> tableView;

	@FXML
	private TableColumn<Trip, String> nameColumn, beginDateColumn, endDateColumn, durationColumn, lengthColumn;

	@FXML
	private TableColumn<Trip, String> startHeightColumn, endHeightColumn, heightDifferenceColumn, avgSpeedColumn,
			avgElevationColumn, commentColumn;

	@FXML
	private CheckBox chkName, chkDate;

	@FXML
	private Button buttonDelete, buttonAdd, buttonEdit;

	@FXML
	private TextField textField;

	private ObservableList<Trip> tripList;

	private String tripTableId;

	public ControllerTable() {
	}

	public void setTripTableId(String tripTableId) {
		this.tripTableId = tripTableId;
	}

	@FXML
	public void handleButtonDelete() {
		long count = tableView.getSelectionModel().getSelectedItems().stream().count();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remove");
		alert.setContentText(String.format("Are you sure you want to delte %d trip(s)", count));
		Optional<ButtonType> buttonClicked = alert.showAndWait();

		if (buttonClicked.get().equals(ButtonType.OK)) {
			tripList.removeAll(tableView.getSelectionModel().getSelectedItems());
		}

		
	}

	@FXML
	public void handleButtonAdd() throws IOException, SAXException, TransformerException {
		MainView.showNewTripDialog();
		tripList.setAll(DataHandler.tripList);
		DataHandler.saveTripList(MainView.tripTableId);
	}

	@FXML
	public void handleButtonEdit() throws IOException, SAXException, TransformerException {
		MainView.showEditTripDialog(tableView.getSelectionModel().getSelectedItem());
		tripList.setAll(DataHandler.tripList);
		DataHandler.saveTripList(MainView.tripTableId);
	}

	@FXML
	public void handleChkBeginDate(ActionEvent e) {
		if (chkDate.isSelected() == true) {
			beginDateColumn.setVisible(true);
		} else {
			beginDateColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkName(ActionEvent e) {
		if (chkName.isSelected() == true) {
			nameColumn.setVisible(true);
		} else {
			nameColumn.setVisible(false);
		}
	}

	@FXML
	private void initialize() {
		tripTableId = MainView.tripTableId;
		tripList = FXCollections.observableArrayList(DataHandler.getTripListById(tripTableId));

		FilteredList<Trip> filteredTripList = new FilteredList<>(tripList);

		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredTripList.setPredicate(e -> {
				if (newValue == "" || newValue.isEmpty()) {
					return true;
				} else if (e.getName().toLowerCase().contains(newValue.toLowerCase())) {
					return true;
				}
				return false;
			});
		});

		SortedList<Trip> sortedAndFilteredTripList = new SortedList<>(filteredTripList);

		sortedAndFilteredTripList.comparatorProperty().bind(tableView.comparatorProperty());

		tableView.setItems(sortedAndFilteredTripList);

		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		nameColumn.setCellValueFactory(e -> e.getValue().getNameProperty());
		beginDateColumn.setCellValueFactory(e -> Calculator.formatLocalDate(e.getValue().getStartTimeProperty()));
		endDateColumn.setCellValueFactory(e -> Calculator.formatLocalDate(e.getValue().getEndTimeProperty()));
		durationColumn.setCellValueFactory(e -> Calculator.formatDuration(e.getValue().getDurationProperty()));
		lengthColumn.setCellValueFactory(
				e -> new SimpleStringProperty(e.getValue().getLengthProperty().getValue().toString()));
		startHeightColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getStartHeight().toString()));
		endHeightColumn.setCellValueFactory(
				e -> new SimpleStringProperty(e.getValue().getEndHeightProperty().getValue().toString()));
		heightDifferenceColumn.setCellValueFactory(
				e -> new SimpleStringProperty(e.getValue().getheightDifferenceProperty().getValue().toString()));
		avgSpeedColumn.setCellValueFactory(
				e -> new SimpleStringProperty(String.format("%.2f", e.getValue().getAvgSpeedProperty().getValue())));
		avgElevationColumn.setCellValueFactory(
				e -> new SimpleStringProperty(e.getValue().getAvgElevationProperty().getValue().toString()));
		commentColumn.setCellValueFactory(e -> e.getValue().getCommentProperty());
	}
}
