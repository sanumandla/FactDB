import java.util.List;

public interface DBWrapperI {
	/**
	 * Wrapper around FactDbApi. Used by client to get the latest value from the database
	 * based on passed parameters.
	 */
	 
	/**
	 * Establish a connection to the database by calling FactDbApi.createConnection.
	 */	
	public void connect();
	
	/**
	 * Close the connection to the database by calling FactDbApi.closeConnection. 
	 */
	public void disconnect();
	
	/**
	 * Get latest values from the database.
	 * @param str
	 * @param id
	 */
	public void getLatestValues(List<String> str, long id);
}
