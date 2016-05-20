import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerRenameDatabase {
	
	@FXML
	Label label;
	
	@FXML
	TextField textField;
	
	@FXML
	Button buttonOk, buttonCancel;
	
	private String id;
	private String name;
	private Stage stage;
	
	public void init() throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException{
		name = DataHandler.databaseMap.get(id);
		textField.setText(name);
		label.setVisible(false);
	}
	
	@FXML
	public void handleButtonOk(ActionEvent e){
		if(e.getSource() == buttonOk){
			if(!(name.equals(textField.getText()))){
				String tmpName = textField.getText();
				System.out.println(DataHandler.validateName(tmpName));
				if (DataHandler.validateName(tmpName) == -1) {
					label.setText("The name must be at least 3 characters long!");
					label.setVisible(true);
				} else if (DataHandler.validateName(tmpName) == -2) {
					label.setText("Name already in use!");
					label.setVisible(true);
				} else if (DataHandler.validateName(tmpName) == -3) {
					label.setText("The name can only contain letters of the english ABC!");
					label.setVisible(true);
				} else if (DataHandler.validateName(tmpName) == 1) {
					String newName = tmpName;
					DataHandler.databaseMap.replace(id, newName);
					stage.close();
				}
			}
		}
	}
	
	@FXML
	public void handleCancelButton(ActionEvent e){
		if(e.getSource() == buttonCancel){
			stage.close();
		}
	}
	
	@FXML
	public void handleTexFieldKeyPress(KeyEvent keyEvent) {
		label.setText("");
		label.setVisible(false);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
