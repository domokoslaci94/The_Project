import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

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
		File xml = new File(id + ".xml");
		try {
			parser.parse(xml, handler);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return handler.getTripList();
	}

}
