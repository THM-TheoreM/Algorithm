import edu.princeton.cs.algs4.*;

public class calculate
{
	//We can see "()" as a function f(x)=x.
    //There are two priorities: function > '*' and '/' > '+' and '/'.
    //Maybe we can introduce other functions into CalZero,such as sqrt, sin, cos, exp. 	
	public static String CalZero(String s)
	{
		int a=s.indexOf("(");
		if(a==-1) return CalSecond(CalFirst(s));
		int index=0;
		for(int i=a+1;i<s.length();i++)
		{
			if(s.charAt(i)=='(') index++;
			if(s.charAt(i)==')') index--;
			if(index==-1) return CalZero(s.substring(0,a)+CalZero(s.substring(a+1,i))+s.substring(i+1,s.length()));
		}
		throw new RuntimeException("Leftparentheses are not matched with rightparentheses in the expression to be calculated");
	}
	
	public static String CalFirst(String s)
	{
	    int a=Math.min(s.indexOf("*")==-1?s.length():s.indexOf("*"),s.indexOf("/")==-1?s.length():s.indexOf("/"));
		if(a==s.length()) return s;
		int b=Math.max(s.lastIndexOf("+",a),s.lastIndexOf("-",a));
		int c=Math.min(Math.min(s.indexOf("+",a+1)==-1?s.length():s.indexOf("+",a+1),s.indexOf("-",a+1)==-1?s.length():s.indexOf("-",a+1)),Math.min(s.indexOf("*",a+1)==-1?s.length():s.indexOf("*",a+1),s.indexOf("/",a+1)==-1?s.length():s.indexOf("/",a+1)));
        if(s.charAt(a)=='*') return CalFirst(s.substring(0,b+1)+Double.parseDouble(s.substring(b+1,a))*Double.parseDouble(s.substring(a+1,c))+s.substring(c,s.length()));
		else                 return CalFirst(s.substring(0,b+1)+Double.parseDouble(s.substring(b+1,a))/Double.parseDouble(s.substring(a+1,c))+s.substring(c,s.length()));
	}
	
	public static String CalSecond(String s)
	{
		int a=Math.min(s.indexOf("+")==-1?s.length():s.indexOf("+"),s.indexOf("-",1)==-1?s.length():s.indexOf("-",1));
		if(a==s.length()) return s;
		int b=Math.min(s.indexOf("+",a+1)==-1?s.length():s.indexOf("+",a+1),s.indexOf("-",a+1)==-1?s.length():s.indexOf("-",a+1));		
		if(s.charAt(a)=='+') return CalSecond(Double.parseDouble(s.substring(0,a))+Double.parseDouble(s.substring(a+1,b))+s.substring(b,s.length()));
		else                 return CalSecond(Double.parseDouble(s.substring(0,a))-Double.parseDouble(s.substring(a+1,b))+s.substring(b,s.length()));
	}
	
	public static void main(String[] args)
	{ 
		StdOut.println(CalFirst("-11.1+1.2*2.3*3.4+4.5+5.6*6.7+7.8/8.9"));
		
		StdOut.println(CalSecond(CalFirst("-11.1+1.2*2.3*3.4+4.5+5.6*6.7+7.8/8.9")));
		
		StdOut.println(CalZero("-11+1*2*3+4+5*6+7*((8+9)*10)"));
	}
}
