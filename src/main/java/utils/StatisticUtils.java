package utils;

import java.util.List;
import java.util.stream.Collectors;

import model.Trip;

public class StatisticUtils {
	public static final Long SHORTTRIPLENGTH = 1000l; // short trips are shorter
														// than 1000
	public static final Long MEDIUMTRIPLENGTH = 10000l; // medium trips are
														// between 1000 and10000

	public static Double calculateAvgOfStartHeights(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getStartHeight()));
		}
	}

	public static Double calculateSumOfStartHeights(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getStartHeight()));
		}
	}

	public static Double calculateAvgOfEndHeights(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getEndHeight()));
		}
	}

	public static Double calculateSumOfEndHeights(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getEndHeight()));
		}
	}

	public static Double calculateAvgOfLengths(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getLength()));
		}
	}

	public static Double calculateSumOfLengths(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getLength()));
		}
	}

	public static Double calculateAvgOfAvgSpeeds(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getAvgSpeed()));
		}
	}

	public static Double calculateSumOfAvgSpeeds(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getAvgSpeed()));
		}
	}

	public static Double calculateAvgOfHeightDifference(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getHeightDifference()));
		}
	}

	public static Double calculateSumOfHeightDifference(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getHeightDifference()));
		}
	}

	public static Double calculateAvgOfAvgElevations(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.averagingDouble(e -> e.getAvgElevation()));
		}
	}

	public static Double calculateSumOfAvgElevations(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0d;
		} else {
			return list.stream().collect(Collectors.summingDouble(e -> e.getAvgElevation()));
		}
	}

	public static Long getTripsCount(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0l;
		} else {
			return list.stream().count();
		}
	}

	public static Long getShortTripsCount(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0l;
		} else {
			return list.stream().filter(e -> e.getLength() < SHORTTRIPLENGTH).count();
		}
	}

	public static Long getMediumTripsCount(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0l;
		} else {
			return list.stream().filter(e -> e.getLength() >= SHORTTRIPLENGTH && e.getLength() < MEDIUMTRIPLENGTH)
					.count();
		}
	}

	public static Long getLongTripsCount(List<Trip> list) {
		if (list == null || list.isEmpty()) {
			return 0l;
		} else {
			return list.stream().filter(e -> e.getLength() >= MEDIUMTRIPLENGTH).count();
		}
	}
}
