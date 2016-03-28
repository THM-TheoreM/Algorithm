import java.util.Arrays;

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
			
			//Case Insensitive
			/*char[] c=str.toCharArray();
			String[] s=new String[c.length];
			for(int i=0;i<c.length;i++)
				s[i]=""+c[i];
			Arrays.sort(s,String.CASE_INSENSITIVE_ORDER);
			String ans="";
			for(int i=0;i<c.length;i++)
				ans+=s[i];
			return ans;*/
		}
	}
	
	public static void main(String args[]) 
	{    
		String s="dacbAC";
		System.out.print(sort(s));
	}			
}    