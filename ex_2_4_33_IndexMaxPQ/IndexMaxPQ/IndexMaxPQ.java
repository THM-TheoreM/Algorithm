import edu.princeton.cs.algs4.*;

public class IndexMaxPQ<Key extends Comparable<Key>>
{
	private int[] pq;
	private Key[] keys;
	private int N=0;
	
	public IndexMaxPQ(int maxN)
	{
		keys=(Key[]) new Comparable[maxN+1];
		pq=new int[maxN+1];
	}
	
	public boolean isEmpty()
	{return N==0;}
	
	public int size()
	{return N;}
	
	public void insert(Key v)
	{
		N++;
		pq[N]=N-1;
		keys[N-1]=v;
		swim(N);
	}
	
	public Key max()
	{return keys[pq[1]];}
	
	public int delMax()
	{
		int max=pq[1];
		exch(1,N--);
		sink(1);
		return max;
	}
	
	private void swim(int k)
	{
		while(k>1 && less(k/2, k))
		{
			exch(k/2, k);
			k=k/2;
		}
	}
	
	private void sink(int k)
	{
		while(2*k<=N)
		{
			int j=2*k;
			if(j<N && less(j,j+1)) j++;
			if(!less(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	
	private boolean less(int i, int j)
	{return keys[pq[i]].compareTo(keys[pq[j]])<0;}
	
	private void exch(int i, int j)
	{
		int swap=pq[i];
		pq[i]=pq[j];
		pq[j]=swap;
	}
	
	public static void merge(In[] streams)
	{
		int M=streams.length;
		IndexMaxPQ<String> a=new IndexMaxPQ<String>(M);
		
		for(int i=0;i<M;i++)
			if(!streams[i].isEmpty())
				a.insert(streams[i].readString());
			
		while(!a.isEmpty())
		{
			StdOut.print(a.max()+" ");
			int i=a.delMax();
			if(!streams[i].isEmpty())
				a.insert(streams[i].readString());
		}	
	}
	
	public static void main(String[] args)
	{
		int[] keys= {2,1,5,4,3};
		IndexMaxPQ<Integer> pq=new IndexMaxPQ<Integer>(5);
		
		for(int i=0;i<keys.length;i++)
			pq.insert(keys[i]);
		
		while(!pq.isEmpty())
		{
			int i=pq.delMax();
			System.out.println(i+" "+keys[i]);
		}
	}
}