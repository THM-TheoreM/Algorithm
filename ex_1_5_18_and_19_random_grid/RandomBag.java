import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomBag<Item> implements Iterable<Item> 
{
    private Node first;    // beginning of bag
    private int N;         // number of elements in bag

    // helper linked list class
    private class Node
	{
        private Item item;
        private Node next;
    }

    public RandomBag() 
	{
        first=null;
        N=0;
    }

    public boolean isEmpty() 
	{
        return first==null;//"return N==0" also works
    }

    public int size() 
	{
        return N;
    }

    public void add(Item item)
	{
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        N++;
    }

    public Iterator iterator()  
	{
        return new ListIterator();  
    }
	
    private class ListIterator implements Iterator<Item> 
	{
		private Item[] items;
		private int current;
		
		public ListIterator() 
		{
			if(N==0)	
			{
				items=null;
				current=-1;
			}
            else
			{
				items=(Item[])new Object[N];
				current=0;
				Node tmp=first;
				for(int i=0;i<N;i++)
				{
					items[i]=tmp.item;
					tmp=tmp.next;
				}
				for(int i=0;i<N;i++)//shuffle algorithm on page 32
				{
					int r=i+StdRandom.uniform(N-i);
					Item temp=items[i];
					items[i]=items[r];
					items[r]=temp;
				}
			}
        }
		
        public boolean hasNext()  {return current!=N;}
        public void remove()      {}

        public Item next()
		{
            if (!hasNext()) 
				throw new NoSuchElementException();
            return items[current++];
        }
    }

    public static void main(String[] args) {
        RandomBag<String> bag = new RandomBag<String>();
        bag.add("a");
		bag.add("b");
		bag.add("c");
		bag.add("d");
		bag.add("e");

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}