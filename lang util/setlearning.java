import java.util.*;

/*
 * Set extends Collection
 * AbstractSet extends AbstractCollection implements Set
 * SortedSet extends Set
 * NavigableSet extends SortedSet
 * HashSet extends AbstractSet implements Set
 * TreeSet extends AbstractSet implements NavigableSet
 * http://blog.163.com/asd_wll/blog/static/210310402010112833332260/
 */
 
public class setlearning
{
	public static void main(String[] args)
	{
		//HashSet
		HashSet h=new HashSet();
		h.add(1);
		h.add("b");
		h.add(3);
		h.add('t');
		h.add(1);
		
		System.out.println(h.size());
		
		Iterator it=h.iterator();
		while(it.hasNext())
		{
			Object o=it.next();
			System.out.println(o);
		}
		
		//TreeSet
		TreeSet t=new TreeSet();
		t.add("b");
		t.add("a");
		t.add("d");
		t.add("e");
		t.add("c");
		t.add("b");
		
		System.out.println(t.size());
		
		Iterator its=t.iterator();
		while(its.hasNext())
		{
			Object o=its.next();
			System.out.println(o);
		}	
	}
}