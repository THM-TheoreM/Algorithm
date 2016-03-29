import java.util.Arrays;
import java.util.Comparator;

public class StringSort
{
	String a;
	String[] b;
	
	
    public static String sensitive(String str)
	{
		if(str==null || str.isEmpty())
			return "Ïnvalid String";
		else
		{
			/*char[] c=str.toCharArray();
			Arrays.sort(c);
			return new String(c);*/
			String[] s=str.split("");
			Arrays.sort(s);
			return String.join("",s);
		}
	}
	
	public static String insensititive(String str)
	{
		if(str==null || str.isEmpty())
			return "Ïnvalid String";
		else
		{
			String[] s=str.split("");
			Arrays.sort(s,String.CASE_INSENSITIVE_ORDER);
			return String.join("",s);
		}
	}

	public static void main(String args[]) 
	{    
		String s="dacbAC";
		System.out.println(sensitive(s));
		System.out.println(insensititive(s));
	}			
}