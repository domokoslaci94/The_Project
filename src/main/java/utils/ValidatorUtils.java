package utils;

import javafx.beans.property.StringProperty;

/**
 * This class contains methods for validating user inputs.
 */
public class ValidatorUtils {

	/**
	 * Regular expression which validates hours input.
	 */
	public static final String HOURSREGEX = "([0-1]?[0-9])|([2][0-4])";

	/**
	 * Regular expression which validates minutes input.
	 */
	public static final String MINUTESREGEX = "([0-5]?[0-9])|([0]?[0-9])";

	/**
	 * Regular expression which validates length input.
	 */
	public static final String LENGTHREGEX = "[1-9][0-9]{0,6}";

	/**
	 * Regular expression which validates height input.
	 */
	public static final String HEIGHTREGEX = "[+-]?[0-9][0-9]{0,6}";

	/**
	 * Regular expression which validate name input.
	 */
	public static final String NAMEREGEX = "[a-zA-z]*";

	/**
	 * Checks if the given string is a valid hours input.
	 * 
	 * @param hour
	 *            {@code String} which is to be validated.
	 * @return if the given string matches the {@link #HOURSREGEX}} regular
	 *         expression returns {@code true} otherwise it returns
	 *         {@code false}.
	 */
	public static boolean isCorrectHour(String hour) {
		return hour.matches(HOURSREGEX);
	}

	/**
	 * Checks if the given string is a valid hours input.
	 * 
	 * @param hour
	 *            {@code StringProperty} which is to be validated.
	 * @return if the given string matches the {@link #HOURSREGEX}} regular
	 *         expression returns {@code true} otherwise it returns
	 *         {@code false}.
	 */
	public static boolean isCorrectHour(StringProperty hour) {
		return hour.get().matches(HOURSREGEX);
	}

	/**
	 * Checks if the given string is a valid minutes input.
	 * 
	 * @param minutes
	 *            {@code String} which is to be validated.
	 * @return if the given string matches the {@link #MINUTESREGEX}} regular
	 *         expression returns {@code true} otherwise it returns
	 *         {@code false}.
	 */
	public static boolean isCorrectMinutes(String minutes) {
		return minutes.matches(MINUTESREGEX);
	}

	/**
	 * Checks if the given string is a valid minutes input.
	 * 
	 * @param minutes
	 *            {@code StringProperty} which is to be validated.
	 * @return if the given string matches the {@link #MINUTESREGEX}} regular
	 *         expression returns {@code true} otherwise it returns
	 *         {@code false}.
	 */
	public static boolean isCorrectMinutes(StringProperty minutes) {
		return minutes.get().matches(MINUTESREGEX);
	}

	/**
	 * Checks if the given string is a valid length input.
	 * 
	 * @param length
	 *            {@code String} which is to be validated.
	 * @return if the given string matches the {@link #LENGTHREGEX}} regular
	 *         expression returns {@code true} otherwise it returns
	 *         {@code false}.
	 */
	public static boolean isCorrectLength(String length) {
		return length.matches(LENGTHREGEX);
	}

	/**
	 * Checks if the given string is a valid length input.
	 * 
	 * @param length
	 *            {@code StringProperty} which is to be validated.
	 * @return if the given string matches the {@link #LENGTHREGEX}} regular
	 *         expression returns {@code true} otherwise it returns
	 *         {@code false}.
	 */
	public static boolean isCorrectLength(StringProperty length) {
		return length.get().matches(LENGTHREGEX);
	}

	/**
	 * Checks if the given string is a valid height input.
	 * 
	 * @param height
	 *            {@code String} which is to be validated.
	 * @return if the given string matches the {@link #HEIGHTREGEX}} regular
	 *         expression returns {@code true} otherwise it returns
	 *         {@code false}.
	 */
	public static boolean isCorrectHeight(String height) {
		return height.matches(HEIGHTREGEX);
	}

	/**
	 * Checks if the given string is a valid height input.
	 * 
	 * @param height
	 *            {@code StringProperty} which is to be validated.
	 * @return if the given string matches the {@link #HEIGHTREGEX}} regular
	 *         expression returns {@code true} otherwise it returns
	 *         {@code false}.
	 */
	public static boolean isCorrectHeight(StringProperty height) {
		return height.get().matches(HEIGHTREGEX);
	}

	/**
	 * Checks if the given name is a valid input. It checks if the name matches
	 * the {@link #NAMEREGEX} regular expression and checks if it is already in
	 * use.
	 * 
	 * @param name
	 *            {@code String} which is to be validated.
	 * @return If the name is shorter than 3 characters, it returns <b>-1</b>
	 *         which represents that the given name is too short. If the name is
	 *         already in use, it returns <b>-2</b>. If the name does not match
	 *         the {@link #NAMEREGEX} it returns <b>-3</b> which represents that
	 *         the given name is not a valid input.
	 */
	public static int validateName(String name) {
		if (name.length() < 3) {
			return -1;
		} else if (DataUtils.databaseMap.containsValue(name)) {
			return -2;
		} else if (!(name.matches(NAMEREGEX))) {
			return -3;
		} else {
			return 1;
		}
	}
}
