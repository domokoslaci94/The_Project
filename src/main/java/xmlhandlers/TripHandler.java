package xmlhandlers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import model.Trip;

/**
 * Handler class for accessing information stored in {@code XML} files.
 */
public class TripHandler extends DefaultHandler {

	private Trip trip = new Trip();
	private List<Trip> trips = new ArrayList<Trip>();

	private Boolean name = false;
	private Boolean start_time = false;
	private Boolean end_time = false;
	private Boolean start_height = false;
	private Boolean end_height = false;
	private Boolean length = false;
	private Boolean comment = false;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

	/**
	 * Handles the different tags and saves their value in a {@link model.Trip}s
	 * appropriate fields.
	 */
	@Override
	public void characters(char[] ch, int start, int length) {
		if (name) {
			trip.setName(new String(ch, start, length));
			name = false;
		} else if (start_time) {
			trip.setStartTime(LocalDateTime.parse(new String(ch, start, length), formatter));
			start_time = false;
		} else if (end_time) {
			trip.setEndTime(LocalDateTime.parse(new String(ch, start, length), formatter));
			end_time = false;
		} else if (start_height) {
			trip.setStartHeight(Integer.parseInt(new String(ch, start, length)));
			start_height = false;
		} else if (end_height) {
			trip.setEndHeight(Integer.parseInt(new String(ch, start, length)));
			end_height = false;
		} else if (this.length) {
			trip.setLength(Integer.parseInt(new String(ch, start, length)));
			this.length = false;
		} else if (comment) {
			if (new String(ch, start, length).trim().length() == 0) {
				trip.setComment("-");
			} else {
				trip.setComment(new String(ch, start, length));
			}
			comment = false;
		}
	}

	/**
	 * This method tells the {@link #characters(char[], int, int)} method which
	 * is the next field.
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		if (qName.equalsIgnoreCase("trip")) {
			trip = new Trip();
			trip.setId(attributes.getValue("id"));
		} else if (qName.equalsIgnoreCase("name")) {
			name = true;
		} else if (qName.equalsIgnoreCase("start_time")) {
			start_time = true;
		} else if (qName.equalsIgnoreCase("end_time")) {
			end_time = true;
		} else if (qName.equalsIgnoreCase("start_height")) {
			start_height = true;
		} else if (qName.equalsIgnoreCase("end_height")) {
			end_height = true;
		} else if (qName.equalsIgnoreCase("length")) {
			length = true;
		} else if (qName.equalsIgnoreCase("comment")) {
			comment = true;
		}
	}

	/**
	 * Adds the {@link model.Trip} to the list.
	 */
	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equalsIgnoreCase("trip")) {
			trip.refresh();
			trips.add(trip);
		}
	}

	/**
	 * This method returns the trips in a list. The list is sorted by name.
	 * 
	 * @return the list which stores the available lists.
	 */
	public List<Trip> getTripList() {
		trips.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
		return trips;
	}

}
