package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.pmw.tinylog.Logger;
import org.xml.sax.SAXException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Trip;
import utils.DataUtils;
import xmlhandlers.TripHandler;

/**
 * An implementation of the {@link dao.TripDao} interface. This implementation
 * uses {@code XML} files to handle data. The {@link xmlhandlers.TripHandler}
 * {@code SAX} parser parses the {@code XML} file.
 */
public class TripDaoImpl implements TripDao {

	private TripHandler handler;
	private SAXParserFactory saxParserFactory;
	private SAXParser parser;

	/**
	 * Empty constructor. Initializes the required objects to parse a
	 * {@code XML} file.
	 */
	public TripDaoImpl() {
		handler = new TripHandler();
		saxParserFactory = SAXParserFactory.newInstance();
		try {
			parser = saxParserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			Logger.warn(e.toString());
		}
	}

	/**
	 * This method returns the {@code List}, by the given id which stores the
	 * available trips.
	 * 
	 * @param id
	 *            {@code String} the id of the trip list.
	 * @return {@code List<Trip>} which stores the available trips.
	 */
	@Override
	public List<Trip> getTripsById(String id) {
		Path dirPath = Paths.get(System.getProperty("user.home"), ".trip-manager", ".databases");
		if (!dirPath.toFile().exists()) {
			dirPath.toFile().mkdir();
		}

		Path filePath = Paths.get(System.getProperty("user.home"), ".trip-manager", ".databases", id + ".xml");
		File xml = filePath.toFile();

		if (!xml.exists()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("File corrupted");
			alert.showAndWait();

			DataUtils.saveTripList(id);

		} else {
			try {
				parser.parse(xml, handler);
			} catch (SAXException | IOException e) {
				Logger.error(e.toString());
			}
		}

		return handler.getTripList();
	}

}
