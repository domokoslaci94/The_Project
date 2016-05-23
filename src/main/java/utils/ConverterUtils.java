package utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This calss contains methods which are used to convert between units.
 */
public class ConverterUtils {
	/**
	 * Date time format (<i> YYYY.MM.dd HH:mm </i>).
	 */
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY.MM.dd HH:mm");

	/**
	 * Duration format (<i>HH:mm:ss</i>).
	 */
	public static final String DURATIONFORMAT = "%02d:%02d:%02d";

	/**
	 * Converts m/s to km/h.
	 * 
	 * @param speed
	 *            {@code double} the speed in m/s.
	 * @return the given speed in km/h.
	 */
	public static Double convertMStoKMH(double speed) {
		return speed * 3.6;
	}

	/**
	 * Converts km/h to m/s.
	 * 
	 * @param speed
	 *            the speed in km/h.
	 * @return the given speed in m/s.
	 */
	public static Double convertKMHtoMS(double speed) {
		if (speed == 0) {
			return 0d;
		} else {
			return speed / 3.6;
		}
	}

	/**
	 * Converts km to m.
	 * 
	 * @param length
	 *            the length in km.
	 * @return the given length in m.
	 */
	public static Double convertKMtoM(double length) {
		return length * 1000;
	}

	/**
	 * Converts m to km.
	 * 
	 * @param length
	 *            the given length in m.
	 * @return the given length in km.
	 */
	public static Double convertMtoKM(double length) {
		return (double) length / 1000;
	}

	/**
	 * Formats the given date to <i>"YYYY.MM.dd HH:mm"</i> format.
	 * 
	 * @param time
	 *            {@code LocalDateTime} which is to be formatted.
	 * @return the given time in <i>"YYYY.MM.dd HH:mm"</i> format.
	 */
	public static String formatLocalDate(LocalDateTime time) {
		if (time == null) {
			return "";
		} else {
			return time.format(dateTimeFormatter);
		}
	}

	/**
	 * Formats the given date to <i>"YYYY.MM.dd HH:mm"</i> format.
	 * 
	 * @param time
	 *            {@code ObjectProperty<LocalDateTime>} which is to be
	 *            formatted.
	 * @return the given time in <i>"YYYY.MM.dd HH:mm"</i> format.
	 */
	public static StringProperty formatLocalDate(ObjectProperty<LocalDateTime> time) {
		if (time.get() == null || time.isNull().getValue()) {
			return new SimpleStringProperty("");
		} else {
			return new SimpleStringProperty(time.getValue().format(dateTimeFormatter));
		}
	}

	/**
	 * Formats the given duration to <i>HH:mm:ss</i> format.
	 * 
	 * @param duration {@code Duration} which is to be formatted.
	 * @return the given duration in <i>HH:mm:ss</i> format.
	 */
	public static String formatDuration(Duration duration) {
		if (duration == null) {
			return new String("");
		} else {
			long secs = duration.getSeconds();
			return String.format(DURATIONFORMAT, secs / 3600, (secs % 3600) / 60, (secs % 60));
		}

	}
	
	/**
	 * Formats the given duration to <i>HH:mm:ss</i> format.
	 * 
	 * @param duration {@code ObjectProperty<Duration>} which is to be formatted.
	 * @return the given duration in <i>HH:mm:ss</i> format.
	 */
	public static StringProperty formatDuration(ObjectProperty<Duration> duration) {
		if (duration == null || duration.isNull().getValue()) {
			return new SimpleStringProperty("");
		} else {
			long secs = duration.get().getSeconds();
			return new SimpleStringProperty(
					String.format(DURATIONFORMAT, secs / 3600, (secs % 3600) / 60, (secs % 60)));
		}
	}

}
