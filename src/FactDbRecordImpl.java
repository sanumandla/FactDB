import java.sql.ResultSet;
import java.sql.SQLException;


public class FactDbRecordImpl implements FactDbRecord {

	private ResultSet resultSet = null;
	
	public FactDbRecordImpl(ResultSet resultSet) {
		this.resultSet = resultSet; 
	}
	
	public long getMillis() {
		long millis = 0;
		try {
			millis = resultSet.getLong(4);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return millis;
	}
	
	public long getEntityId() {
		long id = 0;
		try {
			id = resultSet.getLong(1);				
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return id;
	}
	
	public String fieldName() {
		String name = null;
		try {
			name = resultSet.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return name;
	}
	
	public String fieldValue() {
		String value = null;
		try {
			value = resultSet.getString(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return value;
	}
	
}
