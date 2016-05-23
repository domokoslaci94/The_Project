package test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.CalculatorUtils;

public class CalculatorUtilsTest {

	String string;
	StringProperty stringProperty = new SimpleStringProperty();
	LocalDateTime nullTime = null;
	Duration nullDuration = null;

	@Test
	public void testCalculateHeightDifference() {
		assertEquals(new Integer(100), CalculatorUtils.calculateHeightDifference(0, 100));
		assertEquals(new Integer(100), CalculatorUtils.calculateHeightDifference(-100, 0));
		assertEquals(new Integer(200), CalculatorUtils.calculateHeightDifference(-100, 100));
		assertEquals(new Integer(0), CalculatorUtils.calculateHeightDifference(100, 100));

		assertEquals(new SimpleIntegerProperty(100).getValue(), CalculatorUtils
				.calculateHeightDifference(new SimpleIntegerProperty(0), new SimpleIntegerProperty(100)).getValue());
		assertEquals(new SimpleIntegerProperty(100).getValue(), CalculatorUtils
				.calculateHeightDifference(new SimpleIntegerProperty(-100), new SimpleIntegerProperty(0)).getValue());
		assertEquals(new SimpleIntegerProperty(200).getValue(), CalculatorUtils
				.calculateHeightDifference(new SimpleIntegerProperty(-100), new SimpleIntegerProperty(100)).getValue());
		assertEquals(new SimpleIntegerProperty(0).getValue(), CalculatorUtils
				.calculateHeightDifference(new SimpleIntegerProperty(100), new SimpleIntegerProperty(100)).getValue());
	}

	@Test
	public void testCalculateAvgSpeed() {
		assertEquals(new Double(0), CalculatorUtils.calculateAvgSpeed(LocalDateTime.of(2016, 5, 22, 13, 00),
				LocalDateTime.of(2016, 5, 22, 13, 00), 1000));
		assertEquals(new Double(0), CalculatorUtils.calculateAvgSpeed(LocalDateTime.of(2016, 5, 22, 13, 00),
				LocalDateTime.of(2016, 5, 22, 14, 00), 0));
		assertEquals(new Double((double) 1000 / 3600), CalculatorUtils
				.calculateAvgSpeed(LocalDateTime.of(2016, 5, 22, 13, 00), LocalDateTime.of(2016, 5, 22, 14, 00), 1000));

		assertEquals(new SimpleDoubleProperty(0).getValue(),
				CalculatorUtils.calculateAvgSpeed(
						new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(2016, 5, 22, 13, 00)),
						new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(2016, 5, 22, 13, 00)),
						new SimpleIntegerProperty(1000)).getValue());
		assertEquals(new SimpleDoubleProperty(0).getValue(),
				CalculatorUtils.calculateAvgSpeed(
						new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(2016, 5, 22, 13, 00)),
						new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(2016, 5, 22, 14, 00)),
						new SimpleIntegerProperty(0)).getValue());
		assertEquals(new SimpleDoubleProperty((double) 1000 / 3600).getValue(),
				CalculatorUtils.calculateAvgSpeed(
						new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(2016, 5, 22, 13, 00)),
						new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(2016, 5, 22, 14, 00)),
						new SimpleIntegerProperty(1000)).getValue());

		assertEquals(new Double(0), CalculatorUtils.calculateAvgSpeed(Duration.of(0, ChronoUnit.SECONDS), 1000));
		assertEquals(new Double(0), CalculatorUtils.calculateAvgSpeed(Duration.of(1000, ChronoUnit.SECONDS), 0));
		assertEquals(new Double((double) 1000 / 3600),
				CalculatorUtils.calculateAvgSpeed(Duration.of(1, ChronoUnit.HOURS), 1000));

		assertEquals(new SimpleDoubleProperty(0).getValue(),
				CalculatorUtils.calculateAvgSpeed(new SimpleObjectProperty<Duration>(Duration.of(0, ChronoUnit.SECONDS)),
						new SimpleIntegerProperty(1000)).getValue());
		assertEquals(new SimpleDoubleProperty(0).getValue(),
				CalculatorUtils.calculateAvgSpeed(new SimpleObjectProperty<Duration>(Duration.of(1000, ChronoUnit.SECONDS)),
						new SimpleIntegerProperty(0)).getValue());
		assertEquals(new SimpleDoubleProperty((double) 1000 / 3600).getValue(),
				CalculatorUtils.calculateAvgSpeed(new SimpleObjectProperty<Duration>(Duration.of(1, ChronoUnit.HOURS)),
						new SimpleIntegerProperty(1000)).getValue());
	}

	@Test
	public void testCalculateAvgElevation() {
		assertEquals(new Double(0), CalculatorUtils.calculateAvgElevation(0, 1000));
		assertEquals(new Double(0), CalculatorUtils.calculateAvgElevation(1000, 0));
		assertEquals(new Double(0.5), CalculatorUtils.calculateAvgElevation(1000, 2000));

		assertEquals(new SimpleDoubleProperty(0).getValue(), CalculatorUtils
				.calculateAvgElevation(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1000)).getValue());
		assertEquals(new SimpleDoubleProperty(0).getValue(), CalculatorUtils
				.calculateAvgElevation(new SimpleIntegerProperty(1000), new SimpleIntegerProperty(0)).getValue());
		assertEquals(new SimpleDoubleProperty(0.5).getValue(), CalculatorUtils
				.calculateAvgElevation(new SimpleIntegerProperty(1000), new SimpleIntegerProperty(2000)).getValue());

		assertEquals(new Double(0), CalculatorUtils.calculateAvgElevation(100, 100, 100));
		assertEquals(new Double(0), CalculatorUtils.calculateAvgElevation(100, 200, 0));
		assertEquals(new Double(0.5), CalculatorUtils.calculateAvgElevation(1000, 2000, 2000));

		assertEquals(new SimpleDoubleProperty(0).getValue(),
				CalculatorUtils.calculateAvgElevation(new SimpleIntegerProperty(100), new SimpleIntegerProperty(100),
						new SimpleIntegerProperty(100)).getValue());
		assertEquals(new SimpleDoubleProperty(0).getValue(),
				CalculatorUtils.calculateAvgElevation(new SimpleIntegerProperty(100), new SimpleIntegerProperty(200),
						new SimpleIntegerProperty(0)).getValue());
		assertEquals(new SimpleDoubleProperty(0.5).getValue(),
				CalculatorUtils.calculateAvgElevation(new SimpleIntegerProperty(1000), new SimpleIntegerProperty(2000),
						new SimpleIntegerProperty(2000)).getValue());
	}

	@Test
	public void testCalculateDuration() {
		assertEquals(Duration.ZERO, CalculatorUtils.calculateDuration(null, LocalDateTime.now()));
		assertEquals(Duration.ZERO, CalculatorUtils.calculateDuration(LocalDateTime.now(), null));
		assertEquals(Duration.of(1, ChronoUnit.HOURS),
				CalculatorUtils.calculateDuration(LocalDateTime.of(1994, 8, 15, 5, 0), LocalDateTime.of(1994, 8, 15, 6, 0)));

		assertEquals(new SimpleObjectProperty<Duration>(Duration.ZERO).getValue(), CalculatorUtils.calculateDuration(null,
				new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now()).getValue()));
		assertEquals(new SimpleObjectProperty<Duration>(Duration.ZERO).getValue(), CalculatorUtils
				.calculateDuration(new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now()), null).getValue());
		assertEquals(new SimpleObjectProperty<Duration>(Duration.of(1, ChronoUnit.HOURS)).getValue(),
				CalculatorUtils
						.calculateDuration(new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(1994, 8, 15, 5, 0)),
								new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(1994, 8, 15, 6, 0)))
						.getValue());
	}
}
