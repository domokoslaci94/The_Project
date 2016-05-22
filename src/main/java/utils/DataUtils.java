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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import controllers.MainView;
import dao.DatabaseDaoImpl;
import dao.TripDaoImpl;
import model.Trip;

public class DataUtils {
	
	private DataUtils() {
	}

	public static List<Trip> tripList = new ArrayList<Trip>();
	public static Map<String, String> databaseMap = new HashMap<String, String>();

	public static DatabaseDaoImpl databaseDaoImpl;
	public static TripDaoImpl tripDaoImpl;

	public static DocumentBuilderFactory documentBuilderFactory;
	public static DocumentBuilder documentBuilder;
	public static TransformerFactory transformerFactory;
	public static Transformer transformer;

	public static Path dirPathDatabase;
	public static Path dirPathTrips;

	public static String regex = "[a-zA-z]*";

	public static void intit() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		transformerFactory = TransformerFactory.newInstance();
		transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		databaseDaoImpl = new DatabaseDaoImpl();
		tripDaoImpl = new TripDaoImpl();

		databaseMap = databaseDaoImpl.getDatabases();
	}

	public static Map<String, String> getDatabaseMap() {
		return databaseMap;
	}

	public static List<Trip> getTripListById(String id) {
		tripList = tripDaoImpl.getAllTripsById(id);

		return tripList;
	}

	public static void saveDataBaseMap()
			throws SAXException, IOException, TransformerException, ParserConfigurationException {

		File filePath = DataUtils.getDatabaseLocation();
		Document document = documentBuilder.parse(filePath);

		if (document.hasChildNodes()) {
			Node databases = document.getFirstChild();
			document.removeChild(databases);
		} else {
			// TODO: Ezt át kell írni
			System.out.println("Hiba!");

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

	}

	public static void createDatabase(String id, String name)
			throws ParserConfigurationException, TransformerException {
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
		transformer.transform(source, streamResult);
	}

	public static File getDatabaseLocation()
			throws IOException, TransformerException, SAXException, ParserConfigurationException {
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
			file.createNewFile();

			Document document = documentBuilder.newDocument();

			Element rootElement = document.createElement("databases");
			document.appendChild(rootElement);

			DOMSource source = new DOMSource(document);
			StreamResult streamResult = new StreamResult(file);
			transformer.transform(source, streamResult);
		}

		return file;

	}

	public static void saveTripList(String id) throws SAXException, IOException, TransformerException {

		Path filePath = Paths.get(System.getProperty("user.home"), ".trip-manager", ".databases", id + ".xml");
		File xml = filePath.toFile();
		Document document;
		if (!xml.exists()) {
			xml.createNewFile();

			document = documentBuilder.newDocument();

			Element rootElement = document.createElement("databases");
			document.appendChild(rootElement);

			DOMSource source = new DOMSource(document);

			transformerFactory = TransformerFactory.newInstance();
			StreamResult streamResult = new StreamResult(xml);
			transformer.transform(source, streamResult);
		}
		 
		document = documentBuilder.parse(xml);
		if (document.hasChildNodes()) {
			Node trips = document.getFirstChild();
			document.removeChild(trips);
		} else {
			// TODO: Ezt át kell írni
			System.out.println("Hiba!");
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

	}

	public static void deleteDatabase(String filename) {
		File file = new File(filename);
		file.delete();

	}

	public static int validateName(String name) {
		if (databaseMap == null || databaseMap.isEmpty()) {
			return 1;
		} else if (name.length() < 3) {
			return -1;
		} else if (databaseMap.containsValue(name)) {
			return -2;
		} else if (!(name.matches(regex))) {
			return -3;
		} else {
			return 1;
		}
	}
}
