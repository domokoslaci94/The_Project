package utils;

import java.util.List;
import java.util.stream.Collectors;

import model.Trip;

/**
 * This class contains methods which helps to create statistics (e.g.:Calculates
 * the average of the height differences).
 */
public class StatisticUtils {
	/**
	 * If a trips length is shorter than this value (1000m) it is considered
	 * short.
	 */
	public static final Long SHORTTRIPLENGTH = 1000l;

	/**
	 * If a trips length is shorter than this (10 000m) value but longer than
	 * the {@link #SHORTTRIPLENGTH} (1000m) value it is considered medium. If a
	 * trips length is longer than this value it is considered long.
	 */
	public static final Long MEDIUMTRIPLENGTH = 10000l;

	/**
	 * Calculates the average of start heights of the given trips. If the list
	 * is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The
	 *            start height of these trips will be used for the calculation.
	 * @return the average of the given values.
	 */
	public static Double calculateAvgOfStartHeights(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getStartHeight()));
		}
	}

	/**
	 * Calculates the sum of start heights of the given trips. If the list is
	 * empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The end
	 *            height of these trips will be used for the calculation.
	 * @return the sum of the given values.
	 */
	public static Double calculateSumOfStartHeights(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getStartHeight()));
		}
	}

	/**
	 * Calculates the average of end heights of the given trips. If the list is
	 * empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The end
	 *            height of these trips will be used for the calculation.
	 * @return the average of the given values.
	 */
	public static Double calculateAvgOfEndHeights(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getEndHeight()));
		}
	}

	/**
	 * Calculates the sum of end heights of the given trips. If the list is
	 * empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The end
	 *            height of these trips will be used for the calculation.
	 * @return the sum of the given values.
	 */
	public static Double calculateSumOfEndHeights(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getEndHeight()));
		}
	}

	/**
	 * Calculates the average of lengths of the given trips. If the list is
	 * empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The
	 *            length of these trips will be used for the calculation.
	 * @return the average of the given values.
	 */
	public static Double calculateAvgOfLengths(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getLength()));
		}
	}

	/**
	 * Calculates the sum of lengths of the given trips. If the list is empty it
	 * returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The
	 *            length of these trips will be used for the calculation.
	 * @return the sum of the given values.
	 */
	public static Double calculateSumOfLengths(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getLength()));
		}
	}

	/**
	 * Calculates the average of average speeds of the given trips. If the list
	 * is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The
	 *            average speeds of these trips will be used for the
	 *            calculation.
	 * @return the average of the given values.
	 */
	public static Double calculateAvgOfAvgSpeeds(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getAvgSpeed()));
		}
	}

	/**
	 * Calculates the sum of average speeds of the given trips. If the list is
	 * empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The
	 *            average speed of these trips will be used for the calculation.
	 * @return the sum of the given values.
	 */
	public static Double calculateSumOfAvgSpeeds(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getAvgSpeed()));
		}
	}

	/**
	 * Calculates the average of height difference of the given trips. If the
	 * list is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The
	 *            height difference of these trips will be used for the
	 *            calculation.
	 * @return the average of the given values.
	 */
	public static Double calculateAvgOfHeightDifference(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getHeightDifference()));
		}
	}

	/**
	 * Calculates the sum of height differences of the given trips. If the list
	 * is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The
	 *            height difference of these trips will be used for the
	 *            calculation.
	 * @return the sum of the given values.
	 */
	public static Double calculateSumOfHeightDifference(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getHeightDifference()));
		}
	}

	/**
	 * Calculates the average of average elevations of the given trips. If the
	 * list is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The
	 *            average elevations of these trips will be used for the
	 *            calculation.
	 * @return the average of the given values.
	 */
	public static Double calculateAvgOfAvgElevations(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getAvgElevation()));
		}
	}

	/**
	 * Calculates the sum of average elevations of the given trips. If the list
	 * is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips. The
	 *            average elevations of these trips will be used for the
	 *            calculation.
	 * @return the sum of the given values.
	 */
	public static Double calculateSumOfAvgElevations(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getAvgElevation()));
		}
	}

	/**
	 * Counts the trips in the given list. If the list is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips.
	 * @return the count of the trips.
	 */
	public static Long getTripsCount(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0l;
		} else {
			return list.stream().count();
		}
	}

	/**
	 * Counts the short trips. If the list is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips.
	 * @return the count of short trips.
	 */
	public static Long getShortTripsCount(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0l;
		} else {
			return list.stream().filter(e -> e.getLength() < SHORTTRIPLENGTH).count();
		}
	}

	/**
	 * Counts the medium trips. If the list is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips.
	 * @return the count of medium trips.
	 */
	public static Long getMediumTripsCount(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0l;
		} else {
			return list.stream().filter(e -> e.getLength() >= SHORTTRIPLENGTH && e.getLength() < MEDIUMTRIPLENGTH)
					.count();
		}
	}

	/**
	 * Counts the long trips. If the list is empty it returns 0.
	 * 
	 * @param list
	 *            {@code List<Trip>} the list which contains the trips.
	 * @return the count of long trips.
	 */
	public static Long getLongTripsCount(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0l;
		} else {
			return list.stream().filter(e -> e.getLength() >= MEDIUMTRIPLENGTH).count();
		}
	}
}
