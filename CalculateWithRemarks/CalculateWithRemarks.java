import edu.princeton.cs.algs4.*;

//It is impressive to deal with the outermost parentheses one at a time of recursion, saving a lot of coding lines!!!
public class CalculateWithRemarks
{
	public static String CalExpNoPareFirst(String s)
	{
	    int a=s.indexOf("*");
		if(a==-1) return s;
		
		//1.Is the code segment below equivalent to "int b=Math.max(s.lastIndexOf("+",a),s.lastIndexOf("-",a));"???
		/*int[] ba={s.lastIndexOf("+",a),s.lastIndexOf("-",a)};
		int b=-1;
		for(int i=0;i<2;i++)
			if(ba[i]!=-1 && ba[i]>b) b=ba[i];*/
		int b=Math.max(s.lastIndexOf("+",a),s.lastIndexOf("-",a));
		    
		int[] ca={s.indexOf("+",a+1),s.indexOf("-",a+1),s.indexOf("*",a+1)};
	    int c=s.length();
		for(int i=0;i<3;i++)
			if(ca[i]!=-1 && ca[i]<c) c=ca[i]; 

        //2.s.substring(s.length(),s.length())="", so there is no need to discuss???
		/*if(c==s.length()) return CalExpNoPareFirst(s.substring(0,b+1)+Integer.parseInt(s.substring(b+1,a))*Integer.parseInt(s.substring(a+1,c)));
        else              return CalExpNoPareFirst(s.substring(0,b+1)+Integer.parseInt(s.substring(b+1,a))*Integer.parseInt(s.substring(a+1,c))+s.substring(c,s.length()));*/
		return CalExpNoPareFirst(s.substring(0,b+1)+Integer.parseInt(s.substring(b+1,a))*Integer.parseInt(s.substring(a+1,c))+s.substring(c,s.length()));
	}
	
	public static String CalExpNoPareSecond(String s)
	{
		int[] aa={s.indexOf("+"),s.indexOf("-")};
		int a=-1;
		if(aa[0]==-1 && aa[1]==-1)      return s;
		else if(aa[0]==-1 || aa[1]==-1) a=Math.max(aa[0],aa[1]);
		else                            a=Math.min(aa[0],aa[1]);
		 
		int[] ba={s.indexOf("+",a+1),s.indexOf("-",a+1)};
		int b=s.length();
		for(int i=0;i<2;i++)
			if(ba[i]!=-1 && ba[i]<b) b=ba[i];
		
		/*if(b==s.length())
		{
			if(s.charAt(a)=='+') return CalExpNoPareSecond(Integer.parseInt(s.substring(0,a))+Integer.parseInt(s.substring(a+1,b))+"");
			else                 return CalExpNoPareSecond(Integer.parseInt(s.substring(0,a))-Integer.parseInt(s.substring(a+1,b))+"");
		}
		else
		{
			if(s.charAt(a)=='+') return CalExpNoPareSecond(Integer.parseInt(s.substring(0,a))+Integer.parseInt(s.substring(a+1,b))+s.substring(b,s.length()));
			else                 return CalExpNoPareSecond(Integer.parseInt(s.substring(0,a))-Integer.parseInt(s.substring(a+1,b))+s.substring(b,s.length()));
		}*/
		if(s.charAt(a)=='+') return CalExpNoPareSecond(Integer.parseInt(s.substring(0,a))+Integer.parseInt(s.substring(a+1,b))+s.substring(b,s.length()));
		else                 return CalExpNoPareSecond(Integer.parseInt(s.substring(0,a))-Integer.parseInt(s.substring(a+1,b))+s.substring(b,s.length()));
	}
	
	public static String CalExp(String s)
	{
		int a=s.indexOf("(");
		if(a==-1) return CalExpNoPareSecond(CalExpNoPareFirst(s));
		
		int index=0;
		for(int i=a+1;i<s.length();i++)
		{
			if(s.charAt(i)=='(') index++;
			if(s.charAt(i)==')') index--;
			if(index==-1)
			/*{
				if(i==s.length()-1) return CalExp(s.substring(0,a)+CalExp(s.substring(a+1,i)));
				else                return CalExp(s.substring(0,a)+CalExp(s.substring(a+1,i))+s.substring(i+1,s.length()));
			}*/			
				return CalExp(s.substring(0,a)+CalExp(s.substring(a+1,i))+s.substring(i+1,s.length()));
		}
		//3.In this case () is not well matched, you can throw out an error message instead of return null
		/*return null;*/
		throw new RuntimeException("Leftparentheses are not matched with rightparentheses in the expression to be calculated");
	}
	
	public static void main(String[] args)
	{ 
		StdOut.println(CalExpNoPareFirst("1*2*3+4+5*6"));
		
		StdOut.println(CalExpNoPareSecond(CalExpNoPareFirst("1*2*3+4+5*6")));
		
		StdOut.println(CalExp("1*2*3+4+5*6+7*((8+9)*10)"));
	}
}