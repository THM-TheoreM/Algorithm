import edu.princeton.cs.algs4.*;
 
public class Certification
{
	public static boolean check(Comparable[] a)
	{
		//index each Comparable element with 0~a.length-1
		ComparableWithIndex[] b=new ComparableWithIndex[a.length];
		for(int i=0;i<a.length;i++)
			b[i]=new ComparableWithIndex(a[i],i);
		
		//sort
		Shell.sort(b);
			
		//check whether sorted correctly and stably
		for(int i=1;i<b.length;i++)
			if( ((b[i].getValue()).compareTo(b[i-1].getValue())<0) || ((b[i].getValue()).compareTo(b[i-1].getValue())==0 && b[i].getIndex()<b[i-1].getIndex()) )
				return false;
		return true;
	}
	
	public static void main(String[] args)
	{
		//Integer[] a={2,2,3,4,1};//for selection sort
		Integer[] a={5,1,2,4,4,6};//for shell sort
		StdOut.println(check(a));
	}
} 