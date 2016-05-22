package dao;
import java.util.List;

import model.Trip;

public interface TripDao {
	public List<Trip> getAllTripsById(String id);
}
