import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.IOException;

public class Response
{
	private HttpURLConnection connection;
	public String url;
	public int connect_time;
	public int read_time;
	public Boolean set;
	public String response_header;
	public String method;
	public int status_code;
	public String message;
	public String encoding;
	public String text = "";
	
	Response(HttpURLConnection connection) throws IOException
	{
		this.connection = connection;
		
		url = connection.getURL().toString();
		connect_time = connection.getConnectTimeout();
		read_time = connection.getReadTimeout();
		set = connection.getFollowRedirects();
		
		int N = 1;
		String str;
		
		response_header = connection.getHeaderField(0) + "\n";
		while((str = connection.getHeaderFieldKey(N)) != null)
		{
			response_header = response_header + str + ": " + connection.getHeaderField(N) + "\n";
			N++;
		}
		
		method = connection.getRequestMethod();
		status_code = connection.getResponseCode();
		message = connection.getResponseMessage();
		String[] content_type = connection.getContentType().split("=");
		if(content_type.length == 1) encoding = "utf-8";
		else                         encoding = content_type[1];
		
		InputStream TEXT = connection.getInputStream();
		while((N = TEXT.read()) != -1)
			text = text + (char)N;
		TEXT.close(); 
	}
	
	public String get(String name)
	{return connection.getHeaderField(name);}
	
	public static void main(String[] args) throws IOException
	{
		URL url = new URL("https://api.github.com");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		Response response = new Response(connection);
		
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