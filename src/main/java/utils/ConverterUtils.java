package utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConverterUtils {
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY.MM.dd HH:mm");
	public static final String DURATIONFORMAT = "%02d:%02d:%02d";

	public static Double convertMStoKMH(double speed) {
		return speed * 3.6;
	}

	public static Double convertKMHtoMS(double speed) {
		if (speed == 0) {
			return 0d;
		} else {
			return speed / 3.6;
		}
	}

	public static Double convertKMtoM(double length) {
		return length * 1000;
	}

	public static Double convertMtoKM(double length) {
		return (double) length / 1000;
	}

	public static String formatLocalDate(LocalDateTime time) {
		if (time == null) {
			return "";
		} else {
			return time.format(dateTimeFormatter);
		}
	}

	public static StringProperty formatLocalDate(ObjectProperty<LocalDateTime> time) {
		if (time.get() == null || time.isNull().getValue()) {
			return new SimpleStringProperty("");
		} else {
			return new SimpleStringProperty(time.getValue().format(dateTimeFormatter));
		}
	}

	public static String formatDuration(Duration duration) {
		if (duration == null) {
			return new String("");
		} else {
			long secs = duration.getSeconds();
			return String.format(DURATIONFORMAT, secs / 3600, (secs % 3600) / 60, (secs % 60));
		}

	}

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
