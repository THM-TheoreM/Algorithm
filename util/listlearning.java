import java.util.*;

/*
 * List is an interface
 * class LinkedList, ArrayList, AbstractList implements List
 * Arrays is a self-contained class
 * http://www.cnblogs.com/liuling/archive/2013/04/15/list.html
 */

public class listlearning
{
	public static void main(String[] args)
	{
		LinkedList link=new LinkedList();
		
		//stack
		link.add(1);
		link.add("a");
		link.add(2);
		link.add('b');
		System.out.println(link);
		System.out.print(link.getFirst()+" ");
		link.removeFirst();
		System.out.print(link.getFirst()+" ");
		link.removeFirst();
		System.out.print(link.getFirst()+" ");
		link.removeFirst();
		System.out.println(link.getFirst());
		link.removeFirst();
		
		//queue
		link.add(1);
		link.add("a");
		link.add(2);
		link.add('b');
		System.out.println(link);
		System.out.print(link.getLast()+" ");
		link.removeLast();
		System.out.print(link.getLast()+" ");
		link.removeLast();
		System.out.print(link.getLast()+" ");
		link.removeLast();
		System.out.println(link.getLast());
		link.removeLast();
		
		ArrayList<Integer> array=new ArrayList<Integer>();
		for(int i=0;i<100;i++)
			array.add(i);
		for(int i: array)
			System.out.print(i+" ");
	}
}