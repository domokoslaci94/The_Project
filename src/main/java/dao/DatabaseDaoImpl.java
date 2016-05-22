package dao;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import utils.DataUtils;
import xmlhandlers.DatabaseHandler;

public class DatabaseDaoImpl implements DatabaseDao {

	private DatabaseHandler handler;

	public DatabaseDaoImpl() throws ParserConfigurationException, SAXException, IOException, TransformerException {

		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		handler = new DatabaseHandler();
		
		File filePath = DataUtils.getDatabaseLocation();
		
		parser.parse(filePath, handler);
	}

	@Override
	public Map<String, String> getDatabases() {
		return handler.getDatabases();
	}

}
