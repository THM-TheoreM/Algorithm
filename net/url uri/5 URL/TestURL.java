import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class TestURL
{
	public static void test() throws IOException
	{
		URL url = new URL("http://www.baidu.com");
		
		Object obj = url.getContent();
		System.out.println(obj.getClass());
		System.out.println(obj.getClass().getName());
	}
	
	public static void main(String[] args) throws IOException
	{test();}
}