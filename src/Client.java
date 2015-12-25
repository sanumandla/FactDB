import java.util.ArrayList;
import java.util.List;

public class Client {

	private static DBWrapperI wrapper = null;
	
	public void sendRequest(int id) {
		List<String> str = new ArrayList<String>();
		str.add("name");
		str.add("age");
		wrapper.getLatestValues(str, id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client c = new Client();
		wrapper = new DBWrapperImpl();
		wrapper.connect();
		c.sendRequest(1);
		c.sendRequest(2);
		wrapper.disconnect();
	}	
}
