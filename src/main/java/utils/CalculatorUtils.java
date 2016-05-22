package utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CalculatorUtils {
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
			return (double) length / duration.getSeconds();
		}
	}

	public static DoubleProperty calculateAvgSpeed(ObjectProperty<Duration> duration, IntegerProperty length) {
		if (duration.getValue() == Duration.ZERO || length.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty((double) length.get() / duration.get().getSeconds());
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
			return new SimpleDoubleProperty((double) heightDifference.getValue() / length.getValue());
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
		if (startTime == null || endTime == null) {
			return new SimpleObjectProperty<Duration>(Duration.ZERO) {
			};
		} else {
			return new SimpleObjectProperty<Duration>(Duration.between(startTime.getValue(), endTime.getValue()));
		}

	}
}