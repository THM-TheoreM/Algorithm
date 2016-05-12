import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class TestURL
{
	public static void test() throws IOException
	{
		URL url = new URL("http://www.baidu.com");
		
		InputStream in = url.openStream();
		int c;
		while((c = in.read()) != -1)
			System.out.print(c);
		in.close();
	}
	
	public static void main(String[] args) throws IOException
	{test();}
}