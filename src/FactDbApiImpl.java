import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class FactDbApiImpl implements FactDbApi {
	
	Object o = new Object();
	private Connection connection = null;
	private Statement statement = null;
	ResultSet resultSet = null;
	private String sqlStmt = "SELECT * FROM entity_table";
	
	public void executeQuery() {
		try {
			statement.executeQuery(sqlStmt);
			resultSet = statement.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	public void createConnection() {
		String username = "root";
        String password = "admin";
        String url = "jdbc:mysql://localhost:3306/testdb";
        
        if (connection != null) {
        	return;
        }
        
        try {                    	
        	Class.forName ("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);      
            statement = connection.createStatement();            
         } catch (Exception e) {        	 
             e.printStackTrace();
         }         
	}	
	
	public void closeConnection() {
		if (connection == null) {
			return;
		}
		
		try {
			resultSet.close();
			statement.close();
			connection.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public FactDbRecord next() {		
		boolean status = false; 
		synchronized (o) {
			try {							
				status = resultSet.next();						
			} catch (SQLException e) {
				e.printStackTrace();
			}        
			if (status) {
				return new FactDbRecordImpl(resultSet);
			} else {
				return null;
			}
		}
	}
	
	public FactDbRecord previous() {		
		boolean status = false;
		synchronized (o) {
			try {				
				status = resultSet.previous();
			} catch (SQLException e) {
				e.printStackTrace();
			}        
			if (status) {
				return new FactDbRecordImpl(resultSet);
			} else {
				return null;
			}
		}
	}
	
	public FactDbRecord gotoAfterLatest() {
		boolean status = false;
		synchronized (o) {
			try {						       
		        status = resultSet.first();	       
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (status) {
				return new FactDbRecordImpl(resultSet);
			} else {
				return null;
			}
		}
	}
	
	public FactDbRecord gotoBeforeEarliest() {
		boolean status = false;
		synchronized (o) {
			try {				
		        status = resultSet.last();	        
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			if (status) {
				return new FactDbRecordImpl(resultSet);
			} else {
				return null;
			}
		}
	}

}
