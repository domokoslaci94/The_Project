package xmlhandlers;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler class for accessing information stored in {@code XML} files.
 */
public class DatabaseHandler extends DefaultHandler {

	private Map<String, String> databases = new HashMap<String, String>();
	private String databaseid;
	private String name;
	private Boolean isDatabase = false;

	/**
	 * This method sets the name of the new map entry.
	 */
	@Override
	public void characters(char[] ch, int start, int length) {
		if (isDatabase) {
			name = new String(ch, start, length);
			isDatabase = false;
		}
	}
	
	/**
	 * This method sets the id of the new map entry.
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		if (qName.equalsIgnoreCase("database")) {
			databaseid = attributes.getValue("id");
			isDatabase = true;
		}
	}
	
	/**
	 * This method adds the new element to the map.
	 */
	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equalsIgnoreCase("database")) {
			databases.put(databaseid, name);
		}
	}


	/**
	 * This method is used to get the map which stores the available databases.
	 * 
	 * @return the map which stores the available databases.
	 */
	public Map<String, String> getDatabases() {
		return databases;
	}

}