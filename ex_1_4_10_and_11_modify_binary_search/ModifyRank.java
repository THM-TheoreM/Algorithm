import edu.princeton.cs.algs4.*;
import java.util.Arrays;

/*
 * 1.4.6
 * a. O(N)
 * b. O(N)
 * c. O(NlogN)
 */
 
public class ModifyRank
{
	private int[] a;

	ModifyRank(int[] keys)
	{
		a=new int[keys.length];
		for(int i=0;i<keys.length;i++)
			a[i]=keys[i];
		Arrays.sort(a);
	}
	
	private int rank(int key)
	{
		int lo=0,hi=a.length-1,mid=lo+(hi-lo)/2;
		while(lo<=hi)
		{
			mid=lo+(hi-lo)/2;
			if(key<a[mid])      hi=mid-1;
			else if(key>a[mid]) lo=mid+1;
			else                return mid;
		}
		return -1;
	}
	
	public boolean contains(int key)
	{return rank(key)!=-1;}
 	
	//It's tricky to modify just a few lines of rank on page 9 to realize the function, not that easy to just change the inequality symbol from < to <=.
	//Returns the element with the smallest index that matches the search element
	private int ranksmall(int key)
	{
		int lo=0,hi=a.length-1,mid=lo+(hi-lo)/2;//mid=floor((lo+hi)/2)
		while(lo<=hi)
		{
			mid=lo+(hi-lo)/2;
			if(key<a[mid])			hi=mid-1;
			else if(key>a[mid])		lo=mid+1;
			else					
			{
				if(lo==hi)			return mid;
				else				hi=mid;
			}
		}
		return -1;
	}
	
	//There is also a tricky point at the assignment of value "mid", keeping "mid=lo+(hi-lo)/2" will lead to bugs.
	//Returns the element with the largest index that matches the search element
	private int ranklarge(int key)
	{
		int lo=0,hi=a.length-1,mid=lo+(hi-lo+1)/2;//mid=ceil((lo+hi)/2)
		while(lo<=hi)
		{
			mid=lo+(hi-lo+1)/2;
			if(key<a[mid])			hi=mid-1;
			else if(key>a[mid])		lo=mid+1;
			else					
			{
				if(lo==hi)			return mid;
				else				lo=mid;
			}
		}
		return -1;
	}
	
	public int howMany(int key)
	{
		int lo=ranksmall(key);
		if(lo==-1) return 0;
		int hi=ranklarge(key);
		return hi-lo+1;
	}
	
	public static void main(String[] args) 
	{
		int[] a={3,3,3,3,5};
		ModifyRank b=new ModifyRank(a);
		
		StdOut.println(b.rank(3));
		StdOut.println(b.contains(3));
		StdOut.println(b.ranksmall(3));
		StdOut.println(b.ranklarge(3));
		StdOut.println(b.howMany(3));
    }
}