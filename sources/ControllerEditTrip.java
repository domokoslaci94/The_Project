import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerEditTrip {

	private Stage stage;
	private Trip trip;

	@FXML
	private TextField nameField, startHeightField, endHeightField, lengthField, commentField, startTimeHoursField,
			startTimeMinutesField, endTimeHoursField, endTimeMinutesField;

	@FXML
	private Button buttonAdd, buttonCancel;

	@FXML
	private DatePicker startTimeDatePicker, endTimeDatePicker;

	@FXML
	private Label nameErrorLabel, startTimeErrorLabel, endTimeErrorLabel, startHeightErrorLabel, endHeightErrorLabel,
			lengthErrorLabel, commentErrorLabel;

	public void nullLabels() {
		nameErrorLabel.setText("");
		nameErrorLabel.setVisible(false);

		startTimeErrorLabel.setText("");
		startTimeErrorLabel.setVisible(false);

		endTimeErrorLabel.setText("");
		endTimeErrorLabel.setVisible(false);

		startHeightErrorLabel.setText("");
		startHeightErrorLabel.setVisible(false);

		endHeightErrorLabel.setText("");
		endHeightErrorLabel.setVisible(false);

		endHeightErrorLabel.setText("");
		endHeightErrorLabel.setVisible(false);

		lengthErrorLabel.setText("");
		lengthErrorLabel.setVisible(false);

		commentErrorLabel.setText("");
		commentErrorLabel.setVisible(false);
	}

	@FXML
	private void initialize() {
		nullLabels();
		commentField.setAlignment(Pos.TOP_LEFT);
	}

	@FXML
	private void handleAddButton(ActionEvent e) {
		if (e.getSource() == buttonAdd) {
			boolean correct = true;
			LocalDateTime startTime = null;
			LocalDateTime endTime = null;
			nullLabels();

			if (!Calculator.isCorrectHour(startTimeHoursField.getText())
					|| !Calculator.isCorrectMinutes(startTimeMinutesField.getText())) {
				startTimeErrorLabel.setText("The specified date or time is invalid!");
				startTimeErrorLabel.setVisible(true);
				correct = false;
			}

			if (startTimeDatePicker.getValue() == null || startTimeHoursField.getText().isEmpty()
					|| startTimeMinutesField.getText().isEmpty()) {
				startTimeErrorLabel.setText("There can't be empty fields!");
				startTimeErrorLabel.setVisible(true);
				correct = false;
			}

			if (!(startTimeDatePicker.getValue() == null)) {
				if (startTimeDatePicker.getValue().isAfter(LocalDate.now())) {
					startTimeErrorLabel.setText("The specified date is in the future");
					startTimeErrorLabel.setVisible(true);
					correct = false;
				}
			}

			if (!Calculator.isCorrectHour(endTimeHoursField.getText())
					|| !Calculator.isCorrectMinutes(endTimeMinutesField.getText())) {
				endTimeErrorLabel.setText("The specified date or time is invalid!");
				endTimeErrorLabel.setVisible(true);
				correct = false;
			}

			if (!(endTimeDatePicker.getValue() == null)) {
				if (endTimeDatePicker.getValue().isAfter(LocalDate.now())) {
					endTimeErrorLabel.setText("The specified date is in the future");
					endTimeErrorLabel.setVisible(true);
					correct = false;
				}
			}

			if (endTimeDatePicker.getValue() == null || endTimeHoursField.getText().isEmpty()
					|| endTimeMinutesField.getText().isEmpty()) {
				endTimeErrorLabel.setText("There can't be empty fields!");
				endTimeErrorLabel.setVisible(true);
				correct = false;
			}

			if (correct) {
				startTime = LocalDateTime.of(startTimeDatePicker.getValue().getYear(),
						startTimeDatePicker.getValue().getMonthValue(), startTimeDatePicker.getValue().getDayOfMonth(),
						Integer.parseInt(startTimeHoursField.getText()),
						Integer.parseInt(startTimeMinutesField.getText()));

				endTime = LocalDateTime.of(endTimeDatePicker.getValue().getYear(),
						endTimeDatePicker.getValue().getMonthValue(), endTimeDatePicker.getValue().getDayOfMonth(),
						Integer.parseInt(endTimeHoursField.getText()), Integer.parseInt(endTimeMinutesField.getText()));

				if (!endTime.isAfter(startTime)) {
					endTimeErrorLabel
							.setText("The value of this field must be greater than the value of start time field");
					endTimeErrorLabel.setVisible(true);
					correct = false;
				}

			}

			if (nameField.getText().length() > 50) {
				nameErrorLabel.setText("The max length of the name is 50 characters");
				correct = false;
			}

			if (nameField.getText().trim().length() < 1) {
				nameErrorLabel.setText("This field can't be empty!");
				nameErrorLabel.setVisible(true);
				correct = false;
			}

			if (!Calculator.isCorrectHeight(startHeightField.getText())) {
				startHeightErrorLabel
						.setText("The specified height is invalid the value must be between 0 and 10 000 000");
				startHeightErrorLabel.setVisible(true);
				correct = false;
			}

			if (startHeightField.getText().isEmpty()) {
				startHeightErrorLabel.setText("The field can't be empty!");
				startHeightErrorLabel.setVisible(true);
				correct = false;
			}

			if (!Calculator.isCorrectHeight(endHeightField.getText())) {
				endHeightErrorLabel
						.setText("The specified height is invalid the value must be between 0 and 10 000 000");
				endHeightErrorLabel.setVisible(true);
				correct = false;
			}

			if (endHeightField.getText().isEmpty()) {
				endHeightErrorLabel.setText("The field can't be empty!");
				endHeightErrorLabel.setVisible(true);
				correct = false;
			}

			if (!Calculator.isCorrectLength(lengthField.getText())) {
				lengthErrorLabel.setText(
						"The specified length is invalid the value must be between -10 000 000 and 10 000 000");
				lengthErrorLabel.setVisible(true);
				correct = false;
			}

			if (lengthField.getText().isEmpty()) {
				lengthErrorLabel.setText("The field can't be empty!");
				lengthErrorLabel.setVisible(true);
				correct = false;
			}

			if (commentField.getText().length() > 100) {
				commentErrorLabel.setText("The max length of the comment is 100");
				commentErrorLabel.setVisible(true);
				correct = false;
			}

			if (correct) {
				trip.setName(nameField.getText());
				trip.setStartTime(startTime);
				trip.setEndTime(endTime);
				trip.setStartHeight(Integer.parseInt(startHeightField.getText()));
				trip.setEndHeight(Integer.parseInt(endHeightField.getText()));
				trip.setLength(Integer.parseInt(lengthField.getText()));

				if (commentField.getText().trim().length() < 1) {
					trip.setComment("-");
				} else {
					trip.setComment(commentField.getText());
				}

				trip.refresh();
				System.out.println(trip.getAvgSpeed());
				stage.close();
			}

		}
	}

	@FXML
	private void handleCancelButton(ActionEvent e) {
		if (e.getSource() == buttonCancel) {
			stage.close();
		}
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
		trip.refresh();
		nameField.setText(trip.getName());
		LocalDate startTime = LocalDate.of(trip.getStartTime().getYear(), trip.getStartTime().getMonth(),
				trip.getStartTime().getDayOfMonth());
		LocalDate endTime = LocalDate.of(trip.getEndTime().getYear(), trip.getEndTime().getMonth(),
				trip.getEndTime().getDayOfMonth());
		startTimeDatePicker.setValue(startTime);
		endTimeDatePicker.setValue(endTime);

		startTimeHoursField.setText(String.valueOf(trip.getStartTime().getHour()));
		startTimeMinutesField.setText(String.valueOf(trip.getStartTime().getMinute()));
		endTimeHoursField.setText(String.valueOf(trip.getEndTime().getHour()));
		endTimeMinutesField.setText(String.valueOf(trip.getEndTime().getMinute()));

		startHeightField.setText(String.valueOf(trip.getStartHeight()));
		endHeightField.setText(String.valueOf(trip.getEndHeight()));
		lengthField.setText(String.valueOf(trip.getLength()));
		commentField.setText(String.valueOf(trip.getComment()));

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
