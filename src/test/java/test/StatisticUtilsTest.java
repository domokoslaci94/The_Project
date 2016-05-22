package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Trip;
import utils.StatisticUtils;

public class StatisticUtilsTest {
	
	List<Trip> tripList = new ArrayList<Trip>();

	@Before
	public void setUp() {
		Trip trip1 = new Trip();
		Trip trip2 = new Trip();
		Trip trip3 = new Trip();

		trip1.setStartHeight(1);
		trip1.setEndHeight(1);
		trip1.setHeightDifference(1);
		trip1.setAvgElevation(1d);
		trip1.setLength(1);
		trip1.setAvgSpeed(1d);

		trip2.setStartHeight(2);
		trip2.setEndHeight(2);
		trip2.setHeightDifference(2);
		trip2.setAvgElevation(2d);
		trip2.setLength(2);
		trip2.setAvgSpeed(2d);

		trip3.setStartHeight(3);
		trip3.setEndHeight(3);
		trip3.setHeightDifference(3);
		trip3.setAvgElevation(3d);
		trip3.setLength(3);
		trip3.setAvgSpeed(3d);

		tripList.add(trip1);
		tripList.add(trip2);
		tripList.add(trip3);
	}
	
	@Test
	public void testCalculateAvgOfStartHeights() {
		assertEquals(new Double(0), StatisticUtils.calculateAvgOfStartHeights(null));
		assertEquals(new Double(2), StatisticUtils.calculateAvgOfStartHeights(tripList));
	}

	@Test
	public void testCalculateSumOfStartHeights() {
		assertEquals(new Double(0), StatisticUtils.calculateSumOfStartHeights(null));
		assertEquals(new Double(6), StatisticUtils.calculateSumOfStartHeights(tripList));
	}

	@Test
	public void testCalculateAvgOfEndHeights() {
		assertEquals(new Double(0), StatisticUtils.calculateAvgOfEndHeights(null));
		assertEquals(new Double(2), StatisticUtils.calculateAvgOfEndHeights(tripList));
	}

	@Test
	public void testCalculateSumOfEndHeights() {
		assertEquals(new Double(0), StatisticUtils.calculateSumOfEndHeights(null));
		assertEquals(new Double(6), StatisticUtils.calculateSumOfEndHeights(tripList));
	}

	@Test
	public void testCalculateAvgOfLengths() {
		assertEquals(new Double(0), StatisticUtils.calculateAvgOfLengths(null));
		assertEquals(new Double(2), StatisticUtils.calculateAvgOfLengths(tripList));
	}

	@Test
	public void testCalculateSumOfLengths() {
		assertEquals(new Double(0), StatisticUtils.calculateSumOfLengths(null));
		assertEquals(new Double(6), StatisticUtils.calculateSumOfLengths(tripList));
	}

	@Test
	public void testCalculateAvgOfAvgSpeeds() {
		assertEquals(new Double(0), StatisticUtils.calculateAvgOfAvgSpeeds(null));
		assertEquals(new Double(2), StatisticUtils.calculateAvgOfAvgSpeeds(tripList));
	}

	@Test
	public void testCalculateSumOfAvgSpeeds() {
		assertEquals(new Double(0), StatisticUtils.calculateSumOfAvgSpeeds(null));
		assertEquals(new Double(6), StatisticUtils.calculateSumOfAvgSpeeds(tripList));
	}

	@Test
	public void testCalculateAvgOfAvgElevations() {
		assertEquals(new Double(0), StatisticUtils.calculateAvgOfAvgElevations(null));
		assertEquals(new Double(2), StatisticUtils.calculateAvgOfAvgElevations(tripList));
	}

	@Test
	public void testCalculateSumOfAvgElevations() {
		assertEquals(new Double(0), StatisticUtils.calculateSumOfAvgElevations(null));
		assertEquals(new Double(6), StatisticUtils.calculateSumOfAvgElevations(tripList));
	}
	
	@Test
	public void testCalculateAvgOfHeightDifference() {
		assertEquals(new Double(0), StatisticUtils.calculateAvgOfHeightDifference(null));
		assertEquals(new Double(2), StatisticUtils.calculateAvgOfHeightDifference(tripList));
	}

	@Test
	public void testCalculateSumOfHeightDifference() {
		assertEquals(new Double(0), StatisticUtils.calculateSumOfHeightDifference(null));
		assertEquals(new Double(6), StatisticUtils.calculateSumOfHeightDifference(tripList));
	}

	@Test
	public void testGetTripsCount() {
		assertEquals(new Long(0), StatisticUtils.getTripsCount(null));
		assertEquals(new Long(3), StatisticUtils.getTripsCount(tripList));
	}
	
	@Test
	public void testGetShortTripsCount() {
		assertEquals(new Long(0), StatisticUtils.getShortTripsCount(null));
		assertEquals(new Long(3), StatisticUtils.getShortTripsCount(tripList));
	}
	
	@Test
	public void testGetMediumTripsCount() {
		assertEquals(new Long(0), StatisticUtils.getMediumTripsCount(null));
		assertEquals(new Long(0), StatisticUtils.getMediumTripsCount(tripList));
	}
	
	@Test
	public void testGetLongTripsCount() {
		assertEquals(new Long(0), StatisticUtils.getLongTripsCount(null));
		assertEquals(new Long(0), StatisticUtils.getLongTripsCount(tripList));
	}

}
