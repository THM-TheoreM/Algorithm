import edu.princeton.cs.algs4.*;

/*
 * First version:
 * This is a simplification of expression evaluation.
 * CalExpNoPareSecond(CalExpNoPareFirst()) calculates an expression without parethesis
 * CalExp() can calculate an expression with parethesis
 * However the first one has a heavy read-in task because it uses the string iteration method instead of LZZ's method.
 * Also there is no stack since I haven't read the corresponding section.
 *
 * public class calculate
 * ------------------------------------------
 * static String CalExpNoPareFirst(String s)
 * static String CalExpNoPareSecond(String s)
 * static String CalExp(String s)
 */
 
 /*
  * Second version:
  * Follow your revise. Have fewer line codes and be more simple. 
  */
  
 /*
  * Third version:
  * Simplest version.
  */

public class Calculate
{
	public static String CalExpNoPareFirst(String s)
	{
	    int a=s.indexOf("*");
		if(a==-1) return s;
		int b=Math.max(s.lastIndexOf("+",a),s.lastIndexOf("-",a));
		int c=Math.min(Math.min(s.indexOf("+",a+1)==-1?s.length():s.indexOf("+",a+1),s.indexOf("-",a+1)==-1?s.length():s.indexOf("-",a+1)),s.indexOf("*",a+1)==-1?s.length():s.indexOf("*",a+1));
        return CalExpNoPareFirst(s.substring(0,b+1)+Integer.parseInt(s.substring(b+1,a))*Integer.parseInt(s.substring(a+1,c))+s.substring(c,s.length()));
	}
	
	public static String CalExpNoPareSecond(String s)
	{
		int a=Math.min(s.indexOf("+")==-1?s.length():s.indexOf("+"),s.indexOf("-",1)==-1?s.length():s.indexOf("-",1));
		if(a==s.length())	 return s;
		int b=Math.min(s.indexOf("+",a+1)==-1?s.length():s.indexOf("+",a+1),s.indexOf("-",a+1)==-1?s.length():s.indexOf("-",a+1));		
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
			if(index==-1)        return CalExp(s.substring(0,a)+CalExp(s.substring(a+1,i))+s.substring(i+1,s.length()));
		}
		throw new RuntimeException("Leftparentheses are not matched with rightparentheses in the expression to be calculated");
	}
	
	public static void main(String[] args)
	{ 
		StdOut.println(CalExpNoPareFirst("-11+1*2*3+4+5*6"));
		
		StdOut.println(CalExpNoPareSecond(CalExpNoPareFirst("-11+1*2*3+4+5*6")));
		
		StdOut.println(CalExp("-11+1*2*3+4+5*6+7*((8+9)*10)"));
	}
}