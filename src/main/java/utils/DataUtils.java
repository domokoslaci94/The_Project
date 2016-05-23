package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.pmw.tinylog.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import controllers.MainView;
import dao.DatabaseDaoImpl;
import dao.TripDaoImpl;
import model.Trip;

/**
 * This class is used to store and retrieve data. The available databases are
 * stored in a {@code Map} the {@link #databaseMap}. The available trips are
 * stored in a {@code List} the {@link #tripList}. With this class you can save
 * these data in {@code XML} files.
 */
public class DataUtils {
	/**
	 * {@code List<Trip>} used to store the available trips.
	 */
	public static List<Trip> tripList = new ArrayList<Trip>();

	/**
	 * {@code Map<String, String>} used to store the available databases.
	 */
	public static Map<String, String> databaseMap = new HashMap<String, String>();

	/**
	 * An instance of the {@link dao.DatabaseDaoImpl} class. It is used to parse
	 * the {@code XML} file which stores the available databases.
	 */
	public static DatabaseDaoImpl databaseDaoImpl;

	/**
	 * An instance of the {@link dao.TripDaoImpl} class. It is used to parse the
	 * {@code XML} files which store the available trips.
	 */
	public static TripDaoImpl tripDaoImpl;

	/**
	 * Required to save the data to {@code XML} files, see
	 * {@link DocumentBuilderFactory}.
	 */
	public static DocumentBuilderFactory documentBuilderFactory;

	/**
	 * Required to save the data to {@code XML} files, see
	 * {@link DocumentBuilder}.
	 */
	public static DocumentBuilder documentBuilder;

	/**
	 * Required to save the data to {@code XML} files, see
	 * {@link TransformerFactory}.
	 */
	public static TransformerFactory transformerFactory;

	/**
	 * Required to save the data to {@code XML} files, see {@link Transformer}.
	 */
	public static Transformer transformer;

	/**
	 * This method initializes the required objects for handling {@code XML}
	 * files.
	 */
	public static void intit() {
		try {
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			databaseDaoImpl = new DatabaseDaoImpl();
			tripDaoImpl = new TripDaoImpl();
			databaseMap = databaseDaoImpl.getDatabases();
		} catch (ParserConfigurationException | TransformerConfigurationException e) {
			Logger.warn(e.toString());
		}
	}

	/**
	 * This method sets the {@link #tripList} variable.
	 * 
	 * @param id
	 *            {@code String} the id of the trip list which is to be loaded.
	 * @return the loaded trip list.
	 */
	public static List<Trip> getTripListById(String id) {
		tripList = tripDaoImpl.getTripsById(id);

		return tripList;
	}

	/**
	 * This method saves the {@link #databaseMap} {@code Map} to an {@code XML}
	 * file.
	 */
	public static void saveDataBaseMap() {

		File filePath = DataUtils.getDatabaseLocation();
		Document document;
		try {
			document = documentBuilder.parse(filePath);

			if (document.hasChildNodes()) {
				Node databases = document.getFirstChild();
				document.removeChild(databases);
			} else {
				Logger.warn("Corrupted file!");
			}

			Element rootElement = document.createElement("databases");
			document.appendChild(rootElement);

			for (Map.Entry<String, String> entry : databaseMap.entrySet()) {
				Element newDatabase = document.createElement("database");
				newDatabase.setAttribute("id", entry.getKey());
				newDatabase.setTextContent(entry.getValue());
				rootElement.appendChild(newDatabase);
			}

			DOMSource source = new DOMSource(document);

			StreamResult streamResult = new StreamResult(filePath);
			transformer.transform(source, streamResult);
		} catch (SAXException | IOException | TransformerException e) {
			Logger.error(e.toString());
		}
	}

	/**
	 * This method creates a new database. The new entry is written in a
	 * {@code XML} file.
	 * 
	 * @param id
	 *            {@code String} the id of the new entry.
	 * @param name
	 *            {@code String} the name of the new entry.
	 */
	public static void createDatabase(String id, String name) {
		System.out.println("create");
		Document document = documentBuilder.newDocument();

		Element rootElement = document.createElement("database");
		document.appendChild(rootElement);

		Element databaseName = document.createElement("name");
		databaseName.setTextContent(name);
		rootElement.appendChild(databaseName);

		Element tripsDatabase = document.createElement("trips");
		tripsDatabase.setAttribute("databaseid", id);
		rootElement.appendChild(tripsDatabase);

		DOMSource source = new DOMSource(document);

		Path filePath = Paths.get(System.getProperty("user.home"), ".trip-manager", ".databases", id + ".xml");
		File file = filePath.toFile();

		StreamResult streamResult = new StreamResult(file);
		try {
			transformer.transform(source, streamResult);
		} catch (TransformerException e) {
			Logger.error(e.toString());
		}
	}

	/**
	 * Returns the location of the {@code XML} file which represents the
	 * database. If the file does not exist this method creates it.
	 * 
	 * @return the path of the database file.
	 */
	public static File getDatabaseLocation() {
		Path dirPathTrips;
		Path dirPathDatabase;

		dirPathDatabase = Paths.get(System.getProperty("user.home"), ".trip-manager");

		if (!dirPathDatabase.toFile().exists()) {
			dirPathDatabase.toFile().mkdir();
		}

		dirPathTrips = Paths.get(System.getProperty("user.home"), ".trip-manager", ".databases");

		if (!dirPathTrips.toFile().exists()) {
			dirPathTrips.toFile().mkdir();
		}

		Path filePath = Paths.get(System.getProperty("user.home"), ".trip-manager", "database.xml");

		File file = filePath.toFile();

		if (!file.exists()) {
			try {
				file.createNewFile();
				Document document = documentBuilder.newDocument();

				Element rootElement = document.createElement("databases");
				document.appendChild(rootElement);

				DOMSource source = new DOMSource(document);
				StreamResult streamResult = new StreamResult(file);
				transformer.transform(source, streamResult);
			} catch (IOException | TransformerException e) {
				Logger.error(e.toString());
			}
		}

		return file;

	}

	/**
	 * Saves the given trip list to the appropriate {@code XML} file.
	 * 
	 * @param id
	 *            the id of the trip list which is to be saved.
	 */
	public static void saveTripList(String id) {

		Path filePath = Paths.get(System.getProperty("user.home"), ".trip-manager", ".databases", id + ".xml");
		File xml = filePath.toFile();
		Document document;
		if (!xml.exists()) {
			try {
				xml.createNewFile();
				document = documentBuilder.newDocument();

				Element rootElement = document.createElement("databases");
				document.appendChild(rootElement);

				DOMSource source = new DOMSource(document);

				transformerFactory = TransformerFactory.newInstance();
				StreamResult streamResult = new StreamResult(xml);
				transformer.transform(source, streamResult);
			} catch (IOException | TransformerException e) {
				Logger.error(e.toString());
			}
		}

		try {
			document = documentBuilder.parse(xml);

			if (document.hasChildNodes()) {
				Node trips = document.getFirstChild();
				document.removeChild(trips);
			} else {
				Logger.error("Corrupted file!");
			}

			Element rootElement = document.createElement("trips");
			rootElement.setAttribute("databaseid", MainView.tripTableId);
			document.appendChild(rootElement);

			for (Trip trip : DataUtils.tripList) {
				Element newTrip = document.createElement("trip");

				Element name = document.createElement("name");
				name.setTextContent(trip.getName());
				newTrip.appendChild(name);

				Element startTime = document.createElement("start_time");
				startTime.setTextContent(ConverterUtils.formatLocalDate(trip.getStartTime()));
				newTrip.appendChild(startTime);

				Element endTime = document.createElement("end_time");
				endTime.setTextContent(ConverterUtils.formatLocalDate(trip.getEndTime()));
				newTrip.appendChild(endTime);

				Element startHeight = document.createElement("start_height");
				startHeight.setTextContent(trip.getStartHeight().toString());
				newTrip.appendChild(startHeight);

				Element endHeight = document.createElement("end_height");
				endHeight.setTextContent(trip.getEndHeight().toString());
				newTrip.appendChild(endHeight);

				Element length = document.createElement("length");
				length.setTextContent(trip.getLength().toString());
				newTrip.appendChild(length);

				Element comment = document.createElement("comment");
				comment.setTextContent(trip.getComment());
				newTrip.appendChild(comment);

				rootElement.appendChild(newTrip);
			}

			DOMSource source = new DOMSource(document);

			StreamResult streamResult = new StreamResult(xml);
			transformer.transform(source, streamResult);
		} catch (SAXException | IOException | TransformerException e) {
			Logger.error(e.toString());
		}

	}

	/**
	 * Deletes the given database entry.
	 * 
	 * @param filename
	 *            the name of the file which is to be deleted.
	 */
	public static void deleteDatabase(String filename) {
		File file = new File(filename);
		file.delete();

	}
}
