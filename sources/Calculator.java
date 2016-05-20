import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Calculator {

	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY.MM.dd HH:mm");
	public static String hoursRegex = "([0-1]?[0-9])|([2][0-4])";
	public static String minutesRegex = "([0-5]?[0-9])|([0]?[0-9])";
	public static String durationFormat = "%02d:%02d:%02d";
	public static String lengthRegex = "[1-9][0-9]{0,6}";
	public static String heightRegex = "[+-]?[0-9][0-9]{0,6}";

	public static boolean isCorrectHour(String hour) {
		return hour.matches(hoursRegex);
	}

	public static boolean isCorrectHour(StringProperty hour) {
		return hour.get().matches(hoursRegex);
	}

	public static boolean isCorrectMinutes(String minutes) {
		return minutes.matches(minutesRegex);
	}

	public static boolean isCorrectMinutes(StringProperty minutes) {
		return minutes.get().matches(minutesRegex);
	}

	public static boolean isCorrectLength(String length) {
		return length.matches(lengthRegex);
	}

	public static boolean isCorrectLength(StringProperty length) {
		return length.get().matches(lengthRegex);
	}

	public static boolean isCorrectHeight(String height) {
		return height.matches(heightRegex);
	}

	public static boolean isCorrectHeight(StringProperty height) {
		return height.get().matches(heightRegex);
	}

	public static Integer calculateHeightDifference(Integer startHeight, Integer endHeight) {
		return Math.abs(endHeight - startHeight);
	}

	public static IntegerProperty calculateHeightDifference(IntegerProperty startHeight, IntegerProperty endHeight) {
		return new SimpleIntegerProperty(Math.abs(endHeight.get() - startHeight.get()));
	}

	public static Double calculateAvgSpeed(LocalDateTime startTime, LocalDateTime endTime, Integer length) {
		if (startTime.until(endTime, ChronoUnit.SECONDS) == 0 || length == 0) {
			return 0d;
		} else {
			return (double) length / startTime.until(endTime, ChronoUnit.SECONDS);
		}

	}

	public static DoubleProperty calculateAvgSpeed(ObjectProperty<LocalDateTime> startTime,
			ObjectProperty<LocalDateTime> endTime, IntegerProperty length) {
		if (startTime.getValue().until(endTime.getValue(), ChronoUnit.SECONDS) == 0 || length.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty(
					(double) length.getValue() / startTime.getValue().until(endTime.getValue(), ChronoUnit.SECONDS));
		}

	}

	public static Double calculateAvgSpeed(Duration duration, Integer length) {
		if (duration == Duration.ZERO || length == 0) {
			return 0d;
		} else {
			return (double) (duration.getSeconds() / length);
		}
	}

	public static DoubleProperty calculateAvgSpeed(ObjectProperty<Duration> duration, IntegerProperty length) {
		if (duration.getValue() == Duration.ZERO || length.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty((double) duration.get().getSeconds() / length.get());
		}
	}

	public static Double calculateAvgEvulation(Integer heightDifference, Integer length) {
		if (heightDifference == 0 || length == 0) {
			return 0d;
		} else {
			return (double) heightDifference / length;
		}
	}

	public static DoubleProperty calculateAvgEvulation(IntegerProperty heightDifference, IntegerProperty length) {
		if (heightDifference.getValue() == 0 || length.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty(heightDifference.getValue() / length.getValue());
		}
	}

	public static Double calculateAvgEvulation(Integer startHeight, Integer endHeight, Integer length) {
		if (endHeight - startHeight == 0 || length == 0) {
			return 0d;
		} else {
			return (double) Math.abs(endHeight - startHeight) / length;
		}
	}

	public static DoubleProperty calculateAvgEvulation(IntegerProperty startHeight, IntegerProperty endHeight,
			IntegerProperty length) {
		if (endHeight.getValue() - startHeight.getValue() == 0 || length.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty(
					(double) Math.abs(endHeight.getValue() - startHeight.getValue()) / length.getValue());
		}
	}

	public static Duration calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
		if (startTime == null || endTime == null) {
			return Duration.ZERO;
		} else {
			return Duration.between(startTime, endTime);
		}

	}

	public static ObjectProperty<Duration> calculateDuration(ObjectProperty<LocalDateTime> startTime,
			ObjectProperty<LocalDateTime> endTime) {
		if (startTime.get() == null || endTime.get() == null) {
			return new SimpleObjectProperty<Duration>(Duration.ZERO) {
			};
		} else {
			return new SimpleObjectProperty<Duration>(Duration.between(startTime.getValue(), endTime.getValue()));
		}

	}

	public static Double convertMStoKMH(double speed) {
		return speed * 3.6;
	}

	public static DoubleProperty convertMStoKMH(DoubleProperty speed) {
		return new SimpleDoubleProperty(speed.getValue() * 3.6);
	}

	public static Double covertKMHtoMS(double speed) {
		if (speed == 0) {
			return 0d;
		} else {
			return speed / 3.6;
		}
	}

	public static DoubleProperty convertKMHtoMS(DoubleProperty speed) {
		if (speed.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty(speed.getValue() / 3.6);
		}
	}

	public static Double convertKMtoM(double length) {
		return length * 1000;
	}

	public static DoubleProperty convertKMtoM(DoubleProperty length) {
		return new SimpleDoubleProperty(length.getValue() * 1000);
	}

	public static String formatLocalDate(LocalDateTime time) {
		if (time == null) {
			return "";
		} else {
			return time.format(dateTimeFormatter);
		}
	}

	public static StringProperty formatLocalDate(ObjectProperty<LocalDateTime> time) {
		if (time.get() == null) {
			return new SimpleStringProperty("");
		} else {
			return new SimpleStringProperty(time.getValue().format(dateTimeFormatter));
		}
	}

	public static String formatDuration(Duration duration) {
		long secs = duration.getSeconds();
		return String.format(durationFormat, secs / 3600, (secs % 3600) / 60, (secs % 60));
	}

	public static StringProperty formatDuration(ObjectProperty<Duration> duration) {
		long secs = duration.get().getSeconds();
		return new SimpleStringProperty(String.format(durationFormat, secs / 3600, (secs % 3600) / 60, (secs % 60)));
	}

	// Calculated Values of Trip

	// TODO: Finish calculators

	public static Double calculateAvgOfStartHeights(List<Trip> list) {
		return list.stream().collect(Collectors.averagingDouble(e -> e.getStartHeight()));
	}

	public static DoubleProperty calculateAvgOfStartHeights(ObservableList<Trip> list) {
		return new SimpleDoubleProperty(list.stream().collect(Collectors.averagingDouble(e -> e.getStartHeight())));
	}

	public static Double calculateSumOfStartHeights(List<Trip> list) {
		return list.stream().collect(Collectors.averagingDouble(e -> e.getStartHeight()));
	}

	public static DoubleProperty calculateSumOfStartHeights(ObservableList<Trip> list) {
		return new SimpleDoubleProperty(list.stream().collect(Collectors.averagingDouble(e -> e.getStartHeight())));
	}

	public static Double calculateAvgOfEndHeights(List<Trip> list) {
		return list.stream().collect(Collectors.averagingDouble(e -> e.getEndHeight()));
	}

	public static DoubleProperty calculateAvgOfEndHeights(ObservableList<Trip> list) {
		return new SimpleDoubleProperty(list.stream().collect(Collectors.averagingDouble(e -> e.getStartHeight())));
	}

	public static Double calculateAvgOfLengths(List<Trip> list) {
		return list.stream().collect(Collectors.averagingDouble(e -> e.getLength()));
	}

	public static DoubleProperty calculateAvgOfLengths(ObservableList<Trip> list) {
		return new SimpleDoubleProperty(list.stream().collect(Collectors.averagingDouble(e -> e.getLength())));
	}

	public static Double calculateSumOfLengths(List<Trip> list) {
		return list.stream().collect(Collectors.summingDouble(e -> e.getLength()));
	}

	public static DoubleProperty calculateSumOfLengths(ObservableList<Trip> list) {
		return new SimpleDoubleProperty(list.stream().collect(Collectors.summingDouble(e -> e.getLength())));
	}

}
