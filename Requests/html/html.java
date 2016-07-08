import Requests.Response;
import Requests.Requests;
import java.io.IOException;
import java.util.Hashtable;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class html
{
	public static void main(String[] args) throws IOException
	{
		Response response = Requests.get("http://www.imdb.com/movies-in-theaters/?ref_=nv_mv_inth_1", "GET", 0, 0, new Hashtable<String, String>(), new Hashtable<String, String>(), false);
		Pattern pattern = Pattern.compile("title=\"(.*?)\"\nsrc=");
		Matcher matcher = pattern.matcher(response.text);
		while(matcher.find()) System.out.println(matcher.group(1));
	}
}