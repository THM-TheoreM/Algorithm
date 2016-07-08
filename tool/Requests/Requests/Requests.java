import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;

public class Requests
{
	public static Response get(String url, String method, int connect_time, int read_time, Hashtable<String, String> query, Hashtable<String, String> header, Boolean set) throws IOException
	{
		String str = "";
		for(Iterator it = query.keySet().iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			String value = (String) query.get(key);
			str = str + key + "=" + URLEncoder.encode(value, "utf-8") + "&";
		}
		if(!str.equals(""))
		{
			if(url.charAt(url.length()-1) == '/') url = url + "?" + str.substring(0,str.length()-1);
			else                                  url = url + "/?" + str.substring(0,str.length()-1);
		}
		
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod(method);
		connection.setConnectTimeout(connect_time);
		connection.setReadTimeout(read_time);
		connection.setFollowRedirects(set);
		
		for(Iterator it = header.keySet().iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			String value = (String) header.get(key);
			connection.setRequestProperty(key, value);
		}
		
		Response response = new Response(connection);
		return response;
	}
	
	public static void main(String[] args) throws IOException
	{		
		Hashtable<String, String> query = new Hashtable<String, String>();
		query.put("from", "beijing");
		query.put("to", "shanghai");
		query.put("day", "1");
		Response response = Requests.get("http://trains.ctrip.com/TrainBooking/Search.aspx", "GET", 0, 0, query, new Hashtable<String, String>(), false);
		System.out.println(response.url);
		System.out.println(response.connect_time);
		System.out.println(response.read_time);
		System.out.println(response.set);
		System.out.print(response.response_header);
		System.out.println(response.method);
		System.out.println(response.status_code);
		System.out.println(response.message);
		System.out.println(response.encoding);
		System.out.println(response.text);
		System.out.print(response.get("Server"));
	}
}