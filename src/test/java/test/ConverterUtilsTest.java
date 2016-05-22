package test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import utils.ConverterUtils;

public class ConverterUtilsTest {
	LocalDateTime nullTime = null;
	Duration nullDuration = null;

	@Test
	public void testConvertMStoKMH() {
		assertEquals(new Double(0), ConverterUtils.convertMStoKMH(0));
		assertEquals(new Double(3.6), ConverterUtils.convertMStoKMH(1));
	}

	@Test
	public void testConvertKMHtoMs() {
		assertEquals(new Double(0), ConverterUtils.convertKMHtoMS(0));
		assertEquals(new Double(1), ConverterUtils.convertKMHtoMS(3.6));
	}

	@Test
	public void testConvertKMtoM() {
		assertEquals(new Double(0), ConverterUtils.convertKMtoM(0));
		assertEquals(new Double(1000), ConverterUtils.convertKMtoM(1));
	}

	@Test
	public void testConvertMtoKM() {
		assertEquals(new Double(0), ConverterUtils.convertMtoKM(0));
		assertEquals(new Double(1), ConverterUtils.convertMtoKM(1000));
	}

	@Test
	public void testFormatLocalDate() {
		assertEquals(new String(""), ConverterUtils.formatLocalDate(nullTime));
		assertEquals(new String("1994.08.15 05:00"),
				ConverterUtils.formatLocalDate(LocalDateTime.of(1994, 8, 15, 5, 0)));

		assertEquals(new SimpleStringProperty("").getValue(),
				ConverterUtils.formatLocalDate(new SimpleObjectProperty<LocalDateTime>(null)).getValue());
		assertEquals(new SimpleStringProperty("1994.08.15 05:00").getValue(),
				ConverterUtils
						.formatLocalDate(new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(1994, 8, 15, 5, 0)))
						.getValue());
	}

	@Test
	public void testFormatDuration() {
		assertEquals(new String(""), ConverterUtils.formatDuration(nullDuration));
		assertEquals(new String("00:10:00"), ConverterUtils.formatDuration(Duration.of(10, ChronoUnit.MINUTES)));

		assertEquals(new SimpleStringProperty("").getValue(),
				ConverterUtils.formatDuration(new SimpleObjectProperty<Duration>(nullDuration)).getValue());
		assertEquals(new SimpleStringProperty("00:10:00").getValue(), ConverterUtils
				.formatDuration(new SimpleObjectProperty<Duration>(Duration.of(10, ChronoUnit.MINUTES))).getValue());
	}
}
