// CHECKSTYLE:OFF
package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pmw.tinylog.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ControllerInfo {

	private Stage stage;

	@FXML
	private Button buttonOk;

	@FXML
	private TextArea textArea;

	@FXML
	public void handleButtonOk(ActionEvent e) {
		stage.close();
	}

	@FXML
	private void initialize() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(MainView.class.getResourceAsStream("/info.txt"), "UTF8"));
			StringBuilder builder = new StringBuilder();
			String info;
			String tmp;

			while ((tmp = reader.readLine()) != null) {
				builder.append(tmp);
				if (tmp.equals("")) {
					builder.append("\n\n");
				}
			}
			info = builder.toString();
			textArea.setText(info);
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
