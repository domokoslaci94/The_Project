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

/**
 * This class contains methods which are used to calculate the calculated values
 * of the trip (duration, height difference, average speed, average elevation).
 * 
 */
public class CalculatorUtils {

	/**
	 * Calculates the height difference by the given parameters.
	 * 
	 * @param startHeight
	 *            {@code Integer} the start height of the trip.
	 * @param endHeight
	 *            {@code Integer} the end height of the trip.
	 * @return the difference between the start height and the end height.
	 */
	public static Integer calculateHeightDifference(Integer startHeight, Integer endHeight) {
		return Math.abs(endHeight - startHeight);
	}

	/**
	 * Calculates the height difference by the given parameters.
	 * 
	 * @param startHeight
	 *            {@code IntegerProperty} the start height of the trip.
	 * @param endHeight
	 *            {@code IntegerProperty} the end height of the trip.
	 * @return the difference between the start height and the end height.
	 */
	public static IntegerProperty calculateHeightDifference(IntegerProperty startHeight, IntegerProperty endHeight) {
		return new SimpleIntegerProperty(Math.abs(endHeight.get() - startHeight.get()));
	}

	/**
	 * Calculates the average speed by the given parameters. If the difference
	 * between the start time and the end time is 0 or the length is 0 it
	 * returns 0. The return value is in m/s.
	 * 
	 * @param startTime
	 *            {@code LocalDateTime} start time of the trip.
	 * @param endTime
	 *            {@code LocalDateTime} end time of the trip.
	 * @param length
	 *            ({@code Integer} length of the trip.
	 * @return the length divided by the seconds between the start time and the
	 *         end time.
	 */
	public static Double calculateAvgSpeed(LocalDateTime startTime, LocalDateTime endTime, Integer length) {
		if (startTime == null || endTime == null) {
			return 0d;
		} else {
			if (startTime.until(endTime, ChronoUnit.SECONDS) == 0 || length == 0) {
				return 0d;
			} else {
				return (double) length / startTime.until(endTime, ChronoUnit.SECONDS);
			}
		}
	}

	/**
	 * Calculates the average speed by the given parameters. If the difference
	 * between the start time and the end time is 0 or the length is 0 it
	 * returns 0. The return value is in m/s.
	 * 
	 * @param startTime
	 *            {@code ObjectProperty<LocalDateTime>} start time of the trip.
	 * @param endTime
	 *            {@code ObjectProperty<LocalDateTime>} end time of the trip.
	 * @param length
	 *            ({@code IntegerProperty} length of the trip.
	 * @return the length divided by the seconds between the start time and the
	 *         end time.
	 */
	public static DoubleProperty calculateAvgSpeed(ObjectProperty<LocalDateTime> startTime,
			ObjectProperty<LocalDateTime> endTime, IntegerProperty length) {
		if (startTime.isNull().get() || endTime.isNull().get()) {
			return new SimpleDoubleProperty(0);
		}

		if (startTime.getValue().until(endTime.getValue(), ChronoUnit.SECONDS) == 0 || length.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty(
					(double) length.getValue() / startTime.getValue().until(endTime.getValue(), ChronoUnit.SECONDS));
		}

	}

	/**
	 * Calculates the average speed by the given parameters. If the difference
	 * between the start time and the end time is 0 or the length is 0 it
	 * returns 0. The return value is in m/s.
	 * 
	 * @param duration
	 *            {@code Duration} the duration of the trip.
	 * @param length
	 *            {@code Integer} the length of the trip.
	 * @return the length divided by the number of seconds in the given
	 *         duration.
	 */
	public static Double calculateAvgSpeed(Duration duration, Integer length) {
		if (duration == Duration.ZERO || length == 0) {
			return 0d;
		} else {
			return (double) length / duration.getSeconds();
		}
	}

	/**
	 * Calculates the average speed by the given parameters. If the difference
	 * between the start time and the end time is 0 or the length is 0 it
	 * returns 0. The return value is in m/s.
	 * 
	 * @param duration
	 *            {@code ObjectProperty<Duration>} the duration of the trip.
	 * @param length
	 *            {@code IntegerProperty} the length of the trip.
	 * @return the length divided by the number of seconds in the given
	 *         duration.
	 */
	public static DoubleProperty calculateAvgSpeed(ObjectProperty<Duration> duration, IntegerProperty length) {
		if (duration.getValue() == Duration.ZERO || length.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty((double) length.get() / duration.get().getSeconds());
		}
	}

	/**
	 * Calculates the average elevation by the given parameters. If either
	 * parameter equals 0 it returns 0. The return value is the average
	 * evaluation on 1 meter.
	 * 
	 * @param heightDifference
	 *            {@code Integer} the height difference of the trip.
	 * @param length
	 *            {@code Integer} the length of the trip.
	 * @return the height difference divided by the length.
	 */
	public static Double calculateAvgElevation(Integer heightDifference, Integer length) {
		if (heightDifference == 0 || length == 0) {
			return 0d;
		} else {
			return (double) heightDifference / length;
		}
	}

	/**
	 * Calculates the average elevation by the given parameters. If either
	 * parameter equals 0 it returns 0. The return value is the average
	 * elevation on 1 meter.
	 * 
	 * @param heightDifference
	 *            {@code Integer} the height difference of the trip.
	 * @param length
	 *            {@code Integer} the length of the trip.
	 * @return the height difference divided by the length.
	 */
	public static DoubleProperty calculateAvgElevation(IntegerProperty heightDifference, IntegerProperty length) {
		if (heightDifference.getValue() == 0 || length.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty((double) heightDifference.getValue() / length.getValue());
		}
	}

	/**
	 * Calculates the average elevation by the given parameters. If the
	 * difference between the end height and the start height equals 0 or the
	 * length parameter is 0 it returns 0. The return value is the average
	 * elevation on 1 meter.
	 * 
	 * @param startHeight
	 *            {@code Integer} the start height of the trip.
	 * @param endHeight
	 *            {@code Integer} the end height of the trip.
	 * @param length
	 *            {@code Integer} the length of the trip.
	 * @return the difference between the start height and the end height
	 *         divided by the length.
	 */
	public static Double calculateAvgElevation(Integer startHeight, Integer endHeight, Integer length) {
		if (endHeight - startHeight == 0 || length == 0) {
			return 0d;
		} else {
			return (double) Math.abs(endHeight - startHeight) / length;
		}
	}

	/**
	 * Calculates the average elevation by the given parameters. If the
	 * difference between the end height and the start height equals 0 or the
	 * length parameter is 0 it returns 0. The return value is the average
	 * elevation on 1 meter.
	 * 
	 * @param startHeight
	 *            {@code IntegerProperty} the start height of the trip.
	 * @param endHeight
	 *            {@code IntegerProperty} the end height of the trip.
	 * @param length
	 *            {@code IntegerProperty} the length of the trip.
	 * @return the difference between the start height and the end height
	 *         divided by the length.
	 */
	public static DoubleProperty calculateAvgElevation(IntegerProperty startHeight, IntegerProperty endHeight,
			IntegerProperty length) {
		if (endHeight.getValue() - startHeight.getValue() == 0 || length.getValue() == 0) {
			return new SimpleDoubleProperty(0);
		} else {
			return new SimpleDoubleProperty(
					(double) Math.abs(endHeight.getValue() - startHeight.getValue()) / length.getValue());
		}
	}

	/**
	 * Calculates the duration by the given parameters. If either parameter is
	 * null it returns 0.
	 * 
	 * @param startTime
	 *            {@code LocalDateTime} the start time of the trip.
	 * @param endTime
	 *            {@code LocalDateTime} the end time of the trip.
	 * @return the difference between the start time and the end time.
	 */
	public static Duration calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
		if (startTime == null || endTime == null) {
			return Duration.ZERO;
		} else {
			return Duration.between(startTime, endTime);
		}

	}

	/**
	 * Calculates the duration by the given parameters. If either parameter is
	 * null it returns 0.
	 * 
	 * @param startTime
	 *            {@code ObjectProperty<LocalDateTime>} the start time of the
	 *            trip.
	 * @param endTime
	 *            {@code ObjectProperty<LocalDateTime>} the end time of the
	 *            trip.
	 * @return the difference between the start time and the end time.
	 */
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