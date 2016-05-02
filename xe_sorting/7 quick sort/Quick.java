import edu.princeton.cs.algs4.*;

//quick sort 快排
public class Quick
{
	//double & visulize
	private static int N=100;
	double[] a;
	
	Quick()
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
		int j=partition(lo,hi);
		sort(lo,j-1);
		sort(j+1,hi);
	}

	private int partition(int lo,int hi)
	{
		int i=lo;
		int j=hi+1;
		double v=a[lo];
		double t;
		
		while(true)
		{
			while(a[++i]<v)  if(i==hi) break;
			while(v<a[--j]) if(j==lo) break;
			if(i>=j) break;
					
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,1);
			StdDraw.filledRectangle(1.0*j/N,a[j]/2.0,0.5/N,1);
		
		    t=a[i];
			a[i]=a[j];
			a[j]=t;
					
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,a[i]/2.0);
			StdDraw.filledRectangle(1.0*j/N,a[j]/2.0,0.5/N,a[j]/2.0);
		}
							
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(1.0*lo/N,a[lo]/2.0,0.5/N,1);
		StdDraw.filledRectangle(1.0*j/N,a[j]/2.0,0.5/N,1);
		
		t=a[lo];
		a[lo]=a[j];
		a[j]=t;
					
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(1.0*lo/N,a[lo]/2.0,0.5/N,a[lo]/2.0);
		StdDraw.filledRectangle(1.0*j/N,a[j]/2.0,0.5/N,a[j]/2.0);
		
		return j;
	}
	
	//comparable 
	public static void sort(Comparable[] a)
	{sort(a,0,a.length-1);}
	
	private static void sort(Comparable[] a,int lo,int hi)
	{
		if(hi<=lo) return;
		int j=partition(a,lo,hi);
		sort(a,lo,j-1);
		sort(a,j+1,hi);
	}
	
	private static int partition(Comparable[] a,int lo,int hi)
	{
		int i=lo;
		int j=hi+1;
		Comparable v=a[lo];
		
		while(true)
		{
			while(less(a[++i],v)) if(i==hi) break;
			while(less(v,a[--j])) if(j==lo) break;
			if(i>=j) break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
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
		
		new Quick();
	}
}
