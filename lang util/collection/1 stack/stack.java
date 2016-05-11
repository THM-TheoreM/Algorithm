import java.util.*;

/*
 * Vector extends AbstractList implements List
 * Stack extends Vector
 * http://www.cnblogs.com/strivers/archive/2010/12/28/1918877.html
 * http://blog.csdn.net/a19881029/article/details/9408649
 */
 
public class stack
{
	public static void main(String[] args)
	{
		Vector v=new Vector(4);
		v.add(1);
		v.add("a");
		v.add('c');
		v.add(1);
		
		System.out.println(v.size());
		
		for(int i=0;i<v.size();i++)
			System.out.print(v.get(i)+" ");
		
		System.out.println();
		
		Stack<String> s=new Stack<String>();
		s.push("a");
		s.push("b");
		s.push("c");
		s.push("d");
		System.out.println(s.pop());
		for(String x: s)
			System.out.print(x+" ");
	}
}