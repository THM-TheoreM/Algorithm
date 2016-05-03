import edu.princeton.cs.algs4.*;
import java.util.regex.*;

public class Email
{
	public static void main(String[] args)
	{
		Pattern pattern=Pattern.compile("\\S+?@\\S+?.com");
		Matcher matcher=pattern.matcher("XXX@qq.com");
		StdOut.println(matcher.find());
	}
}