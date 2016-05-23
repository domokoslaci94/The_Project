package dao;
import java.util.Map;

/**
 * This interface stores the methods which can be used to handle the databases.
 */
public interface DatabaseDao {
	
	/**
	 * This method returns the {@code Map} which stores available databases.
	 * 
	 * @return {@code Map<String, String>} which stores the available databases.
	 */
	public Map<String, String> getDatabases();
}
