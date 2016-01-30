import edu.princeton.cs.algs4.*;

public class ModifyRank
{
	//It's tricky to modify just a few lines of rank on page 9 to realize the function, not that easy to just change the inequality symbol from < to <=.
	//Returns the element with the smallest index that matches the search element
	public static int ranksmall(int key,int[] a)
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
	public static int ranklarge(int key,int[] a)
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
	
	public static int howMany(int key,int[] a)
	{
		int lo=ranksmall(key,a);
		if(lo==-1)
			return 0;
		int hi=ranklarge(key,a);
		return hi-lo+1;
	}
	
	public static void main(String[] args) 
	{
		int[] a=new int[5];
		a[0]=3;a[1]=3;a[2]=3;a[3]=3;a[4]=5;
		StdOut.println(ranksmall(3,a));
		StdOut.println(ranklarge(3,a));
		StdOut.println(howMany(3,a));
    }
}