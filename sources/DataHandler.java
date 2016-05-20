import java.io.File;
import java.io.IOException;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DataHandler {

	// TODO: Refractor to static

	private DataHandler() {
	}

	public static List<Trip> tripList = new ArrayList<Trip>();
	public static Map<String, String> databaseMap = new HashMap<String, String>();

	public static DatabaseDaoImpl databaseDaoImpl;
	public static TripDaoImpl tripDao;

	public static DocumentBuilderFactory documentBuilderFactory;
	public static DocumentBuilder documentBuilder;
	public static TransformerFactory transformerFactory;
	public static Transformer transformer;

	public static String regex = "[a-zA-z]*";

	static {
		try {
			databaseDaoImpl = new DatabaseDaoImpl();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tripDao = new TripDaoImpl();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transformerFactory = TransformerFactory.newInstance();
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		databaseMap = databaseDaoImpl.getDatabases();

	}

	public static Map<String, String> getDatabaseMap() {
		return databaseMap;
	}

	public static List<Trip> getTripListById(String id) {
		tripList = tripDao.getAllTripsById(id);

		return tripList;
	}

	public static void saveDataBaseMap() throws SAXException, IOException, TransformerException {

		Document document = documentBuilder.parse(new File("Database.xml"));

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

		StreamResult streamResult = new StreamResult(new File("Database.xml"));
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

		StreamResult streamResult = new StreamResult(new File(id + ".xml"));
		transformer.transform(source, streamResult);
	}

	public static void saveTripList(String id) throws SAXException, IOException, TransformerException {

		Document document = documentBuilder.parse(new File(id+".xml"));
		
		System.out.println(id);

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

		for (Trip trip : DataHandler.tripList) {
			Element newTrip = document.createElement("trip");

			Element name = document.createElement("name");
			name.setTextContent(trip.getName());
			newTrip.appendChild(name);

			Element startTime = document.createElement("start_time");
			startTime.setTextContent(Calculator.formatLocalDate(trip.getStartTime()));
			newTrip.appendChild(startTime);

			Element endTime = document.createElement("end_time");
			endTime.setTextContent(Calculator.formatLocalDate(trip.getEndTime()));
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

		StreamResult streamResult = new StreamResult(new File(id+".xml"));
		transformer.transform(source, streamResult);

	}

	public static void deleteDatabase(String filename) {
		File file = new File(filename);
		file.delete();

	}

	public static int validateName(String name) {
		if (databaseMap == null) {
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
