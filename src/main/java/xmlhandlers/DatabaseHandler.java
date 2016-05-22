package xmlhandlers;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DatabaseHandler extends DefaultHandler {

	private Map<String, String> databases = new HashMap<String, String>();
	private String databaseid;
	private String name;

	Boolean isName = false;

	public DatabaseHandler() {
		databases = new HashMap<String, String>();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (isName) {
			name = new String(ch, start, length);
			isName = false;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("database")) {
			databases.put(databaseid, name);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("database")) {
			databaseid = attributes.getValue("id");
			isName = true;
		}
	}

	public Map<String, String> getDatabases() {

		return databases;

	}

}