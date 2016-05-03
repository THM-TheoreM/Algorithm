import edu.princeton.cs.algs4.*;
import java.util.regex.*;

public class RegExp
{
	public static void main(String[] args)
	{
		Pattern pattern=Pattern.compile("(?:class=\"repo\" title=\")(\\S*?)(?:\")");
		Matcher matcher=pattern.matcher("class=\"repo\" title=\"Github\" class=\"repo\" title=\"Java-regular-expression\" class=\"repo\" title=\"Java-swing-and-awt\"");
		
		while (matcher.find()) 
			StdOut.println(matcher.group(1));			
	}
}