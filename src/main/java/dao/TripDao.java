package dao;

import java.util.List;

import model.Trip;

/**
 * This interface stores the methods which can be used to handle the trips.
 */
public interface TripDao {

	/**
	 * This method returns the {@code List}, by the given id which stores the
	 * available trips.
	 * 
	 * @param id
	 *            {@code String} the id of the trip list.
	 * @return {@code List<Trip>} which stores the available trips.
	 */
	public List<Trip> getTripsById(String id);
}
