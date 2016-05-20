import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class DatabaseDaoImpl implements DatabaseDao {

	private DatabaseHandler handler;

	public DatabaseDaoImpl() throws ParserConfigurationException, SAXException, IOException {

		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		handler = new DatabaseHandler();

		parser.parse(new File("Database.xml"), handler);
	}

	@Override
	public Map<String, String> getDatabases() {
		return handler.getDatabases();
	}

}
