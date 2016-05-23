package dao;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.pmw.tinylog.Logger;
import org.xml.sax.SAXException;

import utils.DataUtils;
import xmlhandlers.DatabaseHandler;

/**
 * An implementation of the {@link dao.DatabaseDao} interface. This
 * implementation uses {@code XML} files to handle data. The
 * {@link xmlhandlers.DatabaseHandler} {@code SAX} parser parses the {@code XML}
 * file.
 */
public class DatabaseDaoImpl implements DatabaseDao {

	private DatabaseHandler handler;
	
	/**
	 * Empty constructor. Initializes the required objects to parse a {@code XML} file.
	 */
	public DatabaseDaoImpl() {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		File filePath = DataUtils.getDatabaseLocation();
		try {
			handler = new DatabaseHandler();
			parser = factory.newSAXParser();
			parser.parse(filePath, handler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			Logger.warn(e.toString());
		}

	}
	
	/**
	 * This method returns the {@code Map} which stores available databases.
	 * 
	 * @return {@code Map<String, String>} which stores the available databases.
	 */
	@Override
	public Map<String, String> getDatabases() {
		return handler.getDatabases();
	}

}
