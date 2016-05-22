package dao;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Trip;
import utils.DataUtils;
import xmlhandlers.TripHandler;

public class TripDaoImpl implements TripDao {

	private TripHandler handler;
	private SAXParserFactory saxParserFactory;
	private SAXParser parser;

	public TripDaoImpl() throws ParserConfigurationException, SAXException, IOException {
		handler = new TripHandler();
		saxParserFactory = SAXParserFactory.newInstance();
		parser = saxParserFactory.newSAXParser();
	}

	@Override
	public List<Trip> getAllTripsById(String id){
		Path dirPath = Paths.get(System.getProperty("user.home"), ".trip-manager", ".databases");
		if (!dirPath.toFile().exists()) {
			dirPath.toFile().mkdir();
		}
		
		Path filePath = Paths.get(System.getProperty("user.home"), ".trip-manager", ".databases", id+".xml" );
		File xml = filePath.toFile();
		
		if(!xml.exists()){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("File corrupted");
			alert.showAndWait();
			
			try {
				DataUtils.saveTripList(id);
			} catch (SAXException | IOException | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			try {
				parser.parse(xml, handler);
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return handler.getTripList();
	}

}
