// CHECKSTYLE:OFF
package controllers;

import java.io.IOException;
import java.util.Optional;

import org.pmw.tinylog.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Trip;
import utils.ConverterUtils;
import utils.DataUtils;

public class ControllerTable {

	@FXML
	private TableView<Trip> tableView;

	@FXML
	private TableColumn<Trip, String> nameColumn, beginDateColumn, endDateColumn, durationColumn, lengthColumn;

	@FXML
	private TableColumn<Trip, String> startHeightColumn, endHeightColumn, heightDifferenceColumn, avgSpeedColumn,
			avgElevationColumn, commentColumn;

	@FXML
	private CheckBox chkName, chkBeginDate, chkEndDate, chkDuration, chkLength, chkStartHeight, chkEndHeight,
			chkHeightDifference, chkAvgSpeed, chkAvgElevation, chkComment;

	@FXML
	private Button buttonDelete, buttonAdd, buttonEdit, buttonLoad, buttonShowSummary, buttonInfo;

	@FXML
	private RadioButton meterRadio, kilometerRadio, mpersRadio, kmperhRadio;

	@FXML
	private TextField textField;

	private ObservableList<Trip> tripList;

	private String tripTableId;

	public ControllerTable() {
	}

	public void setTripTableId(String tripTableId) {
		this.tripTableId = tripTableId;
	}

	public void showNothingIsSelectedAllert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Warning");
		alert.setContentText("Nothig is selected");
		alert.showAndWait();
	}

	@FXML
	public void handleButtonInfo() {	
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainView.class.getResource("/fxml/Info.fxml"));
			Pane infoPane;
			infoPane = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(MainView.primaryStage);
			dialogStage.setScene(new Scene(infoPane));

			ControllerInfo controller = loader.getController();
			controller.setStage(dialogStage);

			dialogStage.setResizable(false);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@FXML
	public void handleButtonDelete() {
		Logger.info("Delete button pressed");
		if (tableView.getSelectionModel().getSelectedIndex() < 0) {
			Logger.error("Nothing is selected!");
			showNothingIsSelectedAllert();
		} else {
			long count = tableView.getSelectionModel().getSelectedItems().stream().count();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Remove");
			alert.setContentText(String.format("Are you sure you want to delte %d trip(s)", count));
			Optional<ButtonType> buttonClicked = alert.showAndWait();

			if (buttonClicked.get().equals(ButtonType.OK)) {
				Logger.info(count + " elements deleted");
				DataUtils.tripList.removeAll(tableView.getSelectionModel().getSelectedItems());
				tripList.setAll(DataUtils.tripList);
				DataUtils.saveTripList(MainView.tripTableId);
			}
		}

	}

	@FXML
	public void handleButtonAdd() {
		Logger.info("Button add pressed");
		MainView.showNewTripDialog();
		tripList.setAll(DataUtils.tripList);
		DataUtils.saveTripList(MainView.tripTableId);
	}

	@FXML
	public void handleButtonEdit() {
		Logger.info("Edit button pressed");
		if (tableView.getSelectionModel().getSelectedIndex() < 0) {
			Logger.error("Nothing is selected");
			showNothingIsSelectedAllert();
		} else {
			MainView.showEditTripDialog(tableView.getSelectionModel().getSelectedItem());
			tripList.setAll(DataUtils.tripList);
			DataUtils.saveTripList(MainView.tripTableId);
		}
	}

	@FXML
	public void handleButtonLoad() {
		MainView.showDatabaseSelectorPane();
	}

	@FXML
	public void handleButtonSummary() {
		MainView.showSummary();
	}

	@FXML
	public void handleChkName(ActionEvent e) {
		if (chkName.isSelected() == true) {
			Logger.info("Name checkbox checked");
			nameColumn.setVisible(true);
		} else {
			Logger.info("Name checkbox unchecked");
			nameColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkBeginDate(ActionEvent e) {
		if (chkBeginDate.isSelected() == true) {
			Logger.info("Begin date checkbox checked");
			beginDateColumn.setVisible(true);
		} else {
			Logger.info("Begin date checkbox unchecked");
			beginDateColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkEndDate(ActionEvent e) {
		if (chkEndDate.isSelected() == true) {
			Logger.info("End date checkbox checked");
			endDateColumn.setVisible(true);
		} else {
			Logger.info("End date checkbox unchecked");
			endDateColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkDuration(ActionEvent e) {
		if (chkDuration.isSelected() == true) {
			Logger.info("Duration checkbox checked");
			durationColumn.setVisible(true);
		} else {
			Logger.info("Duration checkbox unchecked");
			durationColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkLength(ActionEvent e) {
		if (chkLength.isSelected() == true) {
			Logger.info("Length checkbox checked");
			lengthColumn.setVisible(true);
		} else {
			Logger.info("Name checkbox unchecked");
			lengthColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkStartHeight(ActionEvent e) {
		if (chkStartHeight.isSelected() == true) {
			Logger.info("Start height checkbox checked");
			startHeightColumn.setVisible(true);
		} else {
			Logger.info("Start height checkbox unchecked");
			startHeightColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkEndHeight(ActionEvent e) {
		if (chkEndHeight.isSelected() == true) {
			Logger.info("End height checkbox checked");
			endHeightColumn.setVisible(true);
		} else {
			Logger.info("End height checkbox unchecked");
			endHeightColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkHeightDifference(ActionEvent e) {
		if (chkHeightDifference.isSelected() == true) {
			Logger.info("Height difference checkbox checked");
			heightDifferenceColumn.setVisible(true);
		} else {
			Logger.info("Height difference checkbox unchecked");
			heightDifferenceColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkAvgSpeed(ActionEvent e) {
		if (chkAvgSpeed.isSelected() == true) {
			Logger.info("Average speed checkbox checked");
			avgSpeedColumn.setVisible(true);
		} else {
			Logger.info("Average speed checkbox unchecked");
			avgSpeedColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkAvgElevation(ActionEvent e) {
		if (chkAvgElevation.isSelected() == true) {
			Logger.info("Average elevation checkbox checked");
			avgElevationColumn.setVisible(true);
		} else {
			Logger.info("Average elevation checkbox unchecked");
			avgElevationColumn.setVisible(false);
		}
	}

	@FXML
	public void handleChkComment(ActionEvent e) {
		if (chkComment.isSelected() == true) {
			Logger.info("Comment checkbox checked");
			commentColumn.setVisible(true);
		} else {
			Logger.info("Comment checkbox unchecked");
			commentColumn.setVisible(false);
		}
	}

	public void refresh(TableView<Trip> tableView, ObservableList<Trip> list) {
		tableView.setItems(null);
		tableView.layout();
		tableView.setItems(list);
	}

	@FXML
	private void initialize() {
		Logger.info("Table view initialized!");
		tripTableId = MainView.tripTableId;
		tripList = FXCollections.observableArrayList(DataUtils.getTripListById(tripTableId));
		FilteredList<Trip> filteredTripList = new FilteredList<>(tripList);

		ToggleGroup groupLength = new ToggleGroup();
		ToggleGroup groupSpeed = new ToggleGroup();

		meterRadio.setToggleGroup(groupLength);
		kilometerRadio.setToggleGroup(groupLength);

		mpersRadio.setToggleGroup(groupSpeed);
		kmperhRadio.setToggleGroup(groupSpeed);

		groupLength.selectedToggleProperty()
				.addListener((ObservableValue<? extends Toggle> value, Toggle oldToggle, Toggle newToggle) -> {
					if (groupLength.getSelectedToggle().equals(kilometerRadio)) {
						lengthColumn.setCellValueFactory(e -> new SimpleStringProperty(String.format("%.2f",
								ConverterUtils.convertMtoKM(e.getValue().getLength()).doubleValue())));

						startHeightColumn.setCellValueFactory(e -> new SimpleStringProperty(String.format("%.2f",
								ConverterUtils.convertMtoKM(e.getValue().getStartHeight()).doubleValue())));

						endHeightColumn.setCellValueFactory(e -> new SimpleStringProperty(String.format("%.2f",
								ConverterUtils.convertMtoKM(e.getValue().getEndHeight()).doubleValue())));

						heightDifferenceColumn.setCellValueFactory(e -> new SimpleStringProperty(String.format("%.2f",
								ConverterUtils.convertMtoKM(e.getValue().getHeightDifference()))));

						refresh(tableView, tripList);

					} else if (groupLength.getSelectedToggle().equals(meterRadio)) {
						lengthColumn.setCellValueFactory(
								e -> new SimpleStringProperty(e.getValue().getLengthProperty().getValue().toString()));
						startHeightColumn.setCellValueFactory(
								e -> new SimpleStringProperty(e.getValue().getStartHeight().toString()));
						endHeightColumn.setCellValueFactory(e -> new SimpleStringProperty(
								e.getValue().getEndHeightProperty().getValue().toString()));
						heightDifferenceColumn.setCellValueFactory(
								e -> new SimpleStringProperty(e.getValue().getHeightDifference().toString()));

						refresh(tableView, tripList);
					}
				});

		groupSpeed.selectedToggleProperty()
				.addListener((ObservableValue<? extends Toggle> value, Toggle oldToggle, Toggle newToggle) -> {
					if (groupSpeed.getSelectedToggle().equals(kmperhRadio)) {
						avgSpeedColumn.setCellValueFactory(e -> new SimpleStringProperty(String.format("%.2f",
								ConverterUtils.convertMStoKMH(e.getValue().getAvgSpeed().doubleValue()))));

						refresh(tableView, tripList);

					} else if (groupSpeed.getSelectedToggle().equals(mpersRadio)) {
						avgSpeedColumn.setCellValueFactory(e -> new SimpleStringProperty(
								String.format("%.2f", e.getValue().getAvgSpeed().doubleValue())));

						refresh(tableView, tripList);
					}

				});

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
		beginDateColumn.setCellValueFactory(e -> ConverterUtils.formatLocalDate(e.getValue().getStartTimeProperty()));
		endDateColumn.setCellValueFactory(e -> ConverterUtils.formatLocalDate(e.getValue().getEndTimeProperty()));
		durationColumn.setCellValueFactory(e -> ConverterUtils.formatDuration(e.getValue().getDurationProperty()));
		lengthColumn.setCellValueFactory(
				e -> new SimpleStringProperty(e.getValue().getLengthProperty().getValue().toString()));
		startHeightColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getStartHeight().toString()));
		endHeightColumn.setCellValueFactory(
				e -> new SimpleStringProperty(e.getValue().getEndHeightProperty().getValue().toString()));
		heightDifferenceColumn.setCellValueFactory(
				e -> new SimpleStringProperty(e.getValue().getheightDifferenceProperty().getValue().toString()));
		avgSpeedColumn.setCellValueFactory(e -> new SimpleStringProperty(
				String.format("%.2f", e.getValue().getAvgSpeedProperty().getValue().doubleValue())));
		avgElevationColumn.setCellValueFactory(e -> new SimpleStringProperty(
				String.format("%.2f", e.getValue().getAvgElevationProperty().getValue().doubleValue())));
		commentColumn.setCellValueFactory(e -> e.getValue().getCommentProperty());
	}
}
