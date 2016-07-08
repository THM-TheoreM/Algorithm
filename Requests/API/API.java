import Requests.Response;
import Requests.Requests;
import Requests.Json;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;

public class API
{
	public static void main(String[] args) throws IOException
	{
		Response response = Requests.get("https://api.github.com", "GET", 0, 0, new Hashtable<String, String>(), new Hashtable<String, String>(), false);
		Hashtable<String, Object> json = Json.Big2(response.text);
		for(Iterator it = json.keySet().iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			Object value = json.get(key);
			System.out.println(key + ": " + value);
		}
	}
}