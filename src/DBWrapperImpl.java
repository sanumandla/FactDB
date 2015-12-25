import java.util.List;

public class DBWrapperImpl implements DBWrapperI, Runnable { 
	
	private long id = 0;
	private List<String> str = null;
	private final FactDbApi dbApi;
	private FactDbRecord dbRecord = null;
		
	public DBWrapperImpl() {
		dbApi = new FactDbApiImpl();		
	}
	
	public void connect() {
		dbApi.createConnection();
	}
	
	public void disconnect() {
		dbApi.closeConnection();
	}
	
	public void getLatestValues(List<String> str, long id) {
		this.id = id;
		this.str = str;	
		new Thread(this).start();
	}
	
	public void run() {		
		handleRequest();
	}
	
	private void handleRequest() {			
		String tmp = "";
		dbApi.executeQuery();
		for (String s: str) {
			dbRecord = dbApi.gotoBeforeEarliest();			
			while (dbRecord != null) {											
				if (System.currentTimeMillis() - dbRecord.getMillis() > 1000) {
					break;				
				}
				if (id == dbRecord.getEntityId() && s.equalsIgnoreCase(dbRecord.fieldName())) {						
					tmp = tmp + dbRecord.fieldValue() + " ";						
					break;
				}					
				dbRecord = dbApi.previous();						
			}
		}
		System.out.println(tmp); 
	}	
	
}



