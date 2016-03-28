import java.util.Arrays;
import java.util.Comparator;

public class StringSort
{	
	//there exists one-liner of changes between String and char[]
	//application: with the help of Arrays.sort, sorting a string will be quite easy
	//NOTE: Arrays.sort does NOT accept a String variable!
    public static String sort(String str)
	{
		if(str==null || str.isEmpty())
			return "Ïnvalid String";
		else
		{
			//Case Sensitive
			char[] c=str.toCharArray();
			Arrays.sort(c);
			return new String(c);
		}
	}
	
	public static String insort(String str)
	{
		if(str==null || str.isEmpty())
			return "Ïnvalid String";
		else
		{
			//Case Insensitive
			String[] s=str.split("");
			Arrays.sort(s,String.CASE_INSENSITIVE_ORDER);
			return String.join("",s);
		}
	}

	public static void main(String args[]) 
	{    
		String s="dacbAC";
		System.out.println(sort(s));
		System.out.println(insort(s));
	}			
}