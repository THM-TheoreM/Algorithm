import java.util.*;

/*
 * HashTable extends Dictionary (abstract class) implements Map (interface)
 * http://www.yiibai.com/java/util/java_util_dictionary.html
 */
 
public class diclearning
{
	public static void main(String[] args)
	{
		Hashtable d=new Hashtable();
		
		d.put("1", 1);
		d.put("2", "a");
		d.put("3", 2);
		d.put("4", 3);
		d.put("5", 'b');
		
		System.out.print(d.get("2")+" ");
		System.out.print(d.remove("2")+" ");
		System.out.println(d.get("2"));
		
		System.out.print(d.isEmpty()+" ");
		System.out.println(d.size());
		
		for(Enumeration e = d.elements(); e.hasMoreElements();)
			System.out.print(e.nextElement()+" ");
	}
}