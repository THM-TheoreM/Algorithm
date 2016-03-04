import edu.princeton.cs.algs4.*;

//quick 3-way sort 快排
public class Quick3way
{
	//double & visulize
	private static int N=100;
	double[] a;
	
	Quick3way()
	{
		a=new double[N];
		StdDraw.setYscale(0,3);
 		
		for(int i=0;i<N;i++)
			a[i]=StdRandom.random();
		
		for(int i=0;i<N;i++)
			StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,a[i]/2.0);
		
		sort(0,N-1);
	}
	
	private void sort(int lo,int hi)
	{
		if(hi<=lo) return;
		int[] index=partition(lo,hi);
		sort(lo,index[0]-1);
		sort(index[1]+1,hi);
	}

	private int[] partition(int lo,int hi)
	{
		int[] index=new int[2];
		int lt=lo;
		int gt=hi;
		int i=lo+1;
		double v=a[lo];
		double t;
		
		
		while(i<=gt)
		{
			if(a[i]<v)
			{
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledRectangle(1.0*lt/N,a[lt]/2.0,0.5/N,1);
				StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,1);
		
				t=a[lt];
				a[lt]=a[i];
				a[i]=t;
					
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledRectangle(1.0*lt/N,a[lt]/2.0,0.5/N,a[lt]/2.0);
				StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,a[i]/2.0);
				
				lt++;
				i++;
			}
			else if(a[i]>v) 
			{
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledRectangle(1.0*gt/N,a[gt]/2.0,0.5/N,1);
				StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,1);
		
				t=a[gt];
				a[gt]=a[i];
				a[i]=t;
					
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledRectangle(1.0*gt/N,a[gt]/2.0,0.5/N,a[gt]/2.0);
				StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,a[i]/2.0);
				
				gt--;
			}
			else i++;
		}
		
		index[0]=lt;
		index[1]=gt;
		return index;
		
	}
	
	//comparable 
	public static void sort(Comparable[] a)
	{sort(a,0,a.length-1);}
	
	private static void sort(Comparable[] a,int lo,int hi)
	{
		if(hi<=lo) return;
		int[] index=partition(a,lo,hi);
		sort(a,lo,index[0]-1);
		sort(a,index[1]+1,hi);
	}
	
	private static int[] partition(Comparable[] a,int lo,int hi)
	{
		int[] index=new int[2];
		int lt=lo;
		int gt=hi;
		int i=lo+1;
		Comparable v=a[lo];
		
		while(i<=gt)
		{
			int cmp=a[i].compareTo(v);
			if(cmp<0)      exch(a,lt++,i++);
			else if(cmp>0) exch(a,i,gt--);
			else           i++;
		}
		index[0]=lt;
		index[1]=gt;
		return index;
	}
	
	private static boolean less(Comparable v,Comparable w)
	{return v.compareTo(w)<0;}
	
	private static void exch(Comparable[] a,int i,int j)
	{Comparable t=a[i];a[i]=a[j];a[j]=t;}
	
	private static boolean isSorted(Comparable[] a)
	{
		for(int i=1;i<a.length;i++)
			if(less(a[i],a[i-1])) return false;
		return true;
	}
	
	private static void show(Comparable[] a)
	{
		for(Comparable x: a)
			StdOut.print(x+" ");
		StdOut.println();
	}
	
	public static void main(String[] args)
	{
		String[] array={"S","O","R","T","E","X","A","M","P","L","E"};
		sort(array);
		StdOut.println(isSorted(array));
		show(array);
		
		new Quick3way();
	}
}
