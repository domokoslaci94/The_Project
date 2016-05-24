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
	public void handleButtonOk(ActionEvent e){
		stage.close();
	}
	
	@FXML
	private void initialize() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(MainView.class.getResourceAsStream("/info.txt")));
		StringBuilder builder =  new StringBuilder();
		String info;
		String tmp;
		
		try {
			while((tmp = reader.readLine())!= null ){
				builder.append(tmp);
				if(tmp.equals("")){
					builder.append("\n\n");
				}
			}
		} catch (IOException e) {
			Logger.error(e.toString());
		}
		info = builder.toString();
		textArea.setText(info);
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
