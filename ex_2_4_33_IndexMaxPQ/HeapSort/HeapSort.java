/*
 * 1. priority queue based on an unordered data type = selection sort 
 * 2. priority queue based on an ordered data type   = insertion sort
 * 3. priority queue based on a heap data type       = heap sort
 */

public class HeapSort
{
	public static void sort(Comparable[] a)
	{
		int N=a.length;
		
		//heap construction
		for(int k=N/2;k>=1;k--)
			sink(a,k,N);
		
		//sortdown
		while(N>1)
		{
			exch(a,1,N--);
			sink(a,1,N);
		}
	}
	
	private static void sink(Comparable[] a, int k, int N)
	{
		while(2*k<=N)
		{
			int j=2*k;
			if(j<N && less(a,j,j+1)) j++;
			if(!less(a,k,j)) break;
			exch(a,k,j);
			k=j;
		}
	}
	
	private static boolean less(Comparable[] a, int i, int j)
	{return a[i-1].compareTo(a[j-1])<0;}
	
	private static void exch(Comparable[] a, int i, int j)
	{
		Comparable swap=a[i-1];
		a[i-1]=a[j-1];
		a[j-1]=swap;
	}
	
	public static void main(String[] args)
	{
		Integer[] a= {2,1,5,4,3};
		sort(a);
		for(int x: a)
			System.out.print(x+" ");
	}
}