package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.DataUtils;
import utils.ValidatorUtils;

public class ValidatorUtilsTest {

	String string;
	StringProperty stringProperty = new SimpleStringProperty();
	LocalDateTime nullTime = null;
	Duration nullDuration = null;
	@Test
	public void testIsCorrectHour() {

		string = "";
		stringProperty.set(string);
		assertFalse(ValidatorUtils.isCorrectHour(string));
		assertFalse(ValidatorUtils.isCorrectHour(stringProperty));

		string = "30";
		stringProperty.set(string);
		assertFalse(ValidatorUtils.isCorrectHour(string));
		assertFalse(ValidatorUtils.isCorrectHour(stringProperty));

		string = "25";
		stringProperty.set(string);
		assertFalse(ValidatorUtils.isCorrectHour(string));
		assertFalse(ValidatorUtils.isCorrectHour(stringProperty));

		string = "3";
		stringProperty.set(string);
		assertTrue(ValidatorUtils.isCorrectHour(string));
		assertTrue(ValidatorUtils.isCorrectHour(stringProperty));

		string = "13";
		stringProperty.set(string);
		assertTrue(ValidatorUtils.isCorrectHour(string));
		assertTrue(ValidatorUtils.isCorrectHour(stringProperty));

		string = "23";
		stringProperty.set(string);
		assertTrue(ValidatorUtils.isCorrectHour(string));
		assertTrue(ValidatorUtils.isCorrectHour(stringProperty));
	}

	@Test
	public void testIsCorrectMinutes() {
		string = "";
		stringProperty.set(string);
		assertFalse(ValidatorUtils.isCorrectMinutes(string));
		assertFalse(ValidatorUtils.isCorrectMinutes(stringProperty));

		string = "3";
		stringProperty.set(string);
		assertTrue(ValidatorUtils.isCorrectMinutes(string));
		assertTrue(ValidatorUtils.isCorrectMinutes(stringProperty));

		string = "00";
		stringProperty.set(string);
		assertTrue(ValidatorUtils.isCorrectMinutes(string));
		assertTrue(ValidatorUtils.isCorrectMinutes(stringProperty));
	}

	@Test
	public void isCorrectLength() {
		string = "";
		stringProperty.set(string);
		assertFalse(ValidatorUtils.isCorrectLength(string));
		assertFalse(ValidatorUtils.isCorrectLength(stringProperty));

		string = "-10";
		stringProperty.set(string);
		assertFalse(ValidatorUtils.isCorrectLength(string));
		assertFalse(ValidatorUtils.isCorrectLength(stringProperty));

		string = "999999999";
		stringProperty.set(string);
		assertFalse(ValidatorUtils.isCorrectLength(string));
		assertFalse(ValidatorUtils.isCorrectLength(stringProperty));

		string = "100";
		stringProperty.set(string);
		assertTrue(ValidatorUtils.isCorrectLength(string));
		assertTrue(ValidatorUtils.isCorrectLength(stringProperty));
	}

	@Test
	public void testIsCorrectHeight() {
		string = "999999999";
		stringProperty.set(string);
		assertFalse(ValidatorUtils.isCorrectHeight(string));
		assertFalse(ValidatorUtils.isCorrectHeight(stringProperty));

		string = "100";
		stringProperty.set(string);
		assertTrue(ValidatorUtils.isCorrectHeight(string));
		assertTrue(ValidatorUtils.isCorrectHeight(stringProperty));

		string = "-100";
		stringProperty.set(string);
		assertTrue(ValidatorUtils.isCorrectHeight(string));
		assertTrue(ValidatorUtils.isCorrectHeight(stringProperty));
	}

	@Test
	public void testIsValidName() {
		string = "";
		assertEquals(new Integer(-1).intValue(), ValidatorUtils.validateName(string));

		string = "ab";
		assertEquals(new Integer(-1).intValue(), ValidatorUtils.validateName(string));

		string = "132";
		assertEquals(new Integer(-3).intValue(), ValidatorUtils.validateName(string));
	}

}
