import java.util.Arrays;
import java.util.Comparator;

class len implements Comparator<String>
{
	public int compare(String v, String w)
	{
		if(v.length()<w.length()) return -1;
		if(v.length()>v.length()) return 1;
		return 0;
	}
}

class tive implements Comparator<String>
{
	public int compare(String v,String w)
	{
		if(v.toUpperCase().compareTo(w.toUpperCase())!=0) return v.toUpperCase().compareTo(w.toUpperCase());
		return v.compareTo(w);
	}
}

public class StringSort
{
	
    public static String sensitive(String str)
	{
		if(str==null || str.isEmpty())
			return "Ïnvalid String";
		else
		{
			char[] s=str.toCharArray();
			Arrays.sort(s);
			return new String(s);
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
	
	public static String tive(String str)
	{
		if(str==null || str.isEmpty())
			return "Ïnvalid String";
		else
		{
			String[] s=str.split("");
			Arrays.sort(s,new tive());
			return String.join("",s);
		}
	}
	
	public static void len(String[] s)
	{Arrays.sort(s,new len());}

	public static void main(String args[]) 
	{    
		String str="dacbAC";
		System.out.println(sensitive(str));
		System.out.println(insensititive(str));
		System.out.println(tive(str));
		
		String[] s={"asd","asdfd","a","asdfsdfas","dasd"};
		len(s);
		for(String x:s)
			System.out.print(x+" ");
	}			
}