package utils;

import javafx.beans.property.StringProperty;

public class ValidatorUtils {
	public static final String HOURSREGEX = "([0-1]?[0-9])|([2][0-4])";
	public static final String MINUTESREGEX = "([0-5]?[0-9])|([0]?[0-9])";	
	public static final String LENGTHREGEX = "[1-9][0-9]{0,6}";
	public static final String HEIGHTREGEX = "[+-]?[0-9][0-9]{0,6}";
	
	public static boolean isCorrectHour(String hour) {
		return hour.matches(HOURSREGEX);
	}

	public static boolean isCorrectHour(StringProperty hour) {
		return hour.get().matches(HOURSREGEX);
	}

	public static boolean isCorrectMinutes(String minutes) {
		return minutes.matches(MINUTESREGEX);
	}

	public static boolean isCorrectMinutes(StringProperty minutes) {
		return minutes.get().matches(MINUTESREGEX);
	}

	public static boolean isCorrectLength(String length) {
		return length.matches(LENGTHREGEX);
	}

	public static boolean isCorrectLength(StringProperty length) {
		return length.get().matches(LENGTHREGEX);
	}

	public static boolean isCorrectHeight(String height) {
		return height.matches(HEIGHTREGEX);
	}

	public static boolean isCorrectHeight(StringProperty height) {
		return height.get().matches(HEIGHTREGEX);
	}
}
