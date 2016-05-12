import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class TestURL
{
	public static void test() throws IOException
	{
		URL url = new URL("http://www.baidu.com");
		
		Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
		int c;
		while((c = reader.read()) != -1)
			System.out.print((char) c);
		reader.close();
	}
	
	public static void main(String[] args) throws IOException
	{test();}
}