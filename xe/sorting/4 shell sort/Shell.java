import edu.princeton.cs.algs4.*;

//shell sort 希尔排序
public class Shell
{
	public static void sort(Comparable[] a)
	{
		int h=1;
		
		while(h<a.length/3)
			h=3*h+1;
		
		while(h>0)
		{
			for(int i=h;i<a.length;i++)
				for(int j=i;j>h-1 && (less(a[j],a[j-h]));j-=h)
					exch(a,j-h,j);
			
			h=h/3;
		}	
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
		
		int N=100;
		double t;
		int h=1;
		double[] a=new double[N];
		StdDraw.setYscale(0,3);
 		
		//visualizing sorting algorithm
		for(int i=0;i<N;i++)
			a[i]=StdRandom.random();
		
		for(int i=0;i<N;i++)
			StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,a[i]/2.0);
		
		while(h<N/3)
			h=3*h+1;
		
		while(h>0)
		{
			for(int i=h;i<N;i++)
			{
				for(int j=i;j>h-1 && (a[j]<a[j-h]);j-=h)
				{
				
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledRectangle(1.0*(j-h)/N,a[j-h]/2.0,0.5/N,1);
					StdDraw.filledRectangle(1.0*j/N,a[j]/2.0,0.5/N,1);
		
					t=a[j-h];
					a[j-h]=a[j];
					a[j]=t;
					
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledRectangle(1.0*(j-h)/N,a[j-h]/2.0,0.5/N,a[j-h]/2.0);
					StdDraw.filledRectangle(1.0*j/N,a[j]/2.0,0.5/N,a[j]/2.0);
				}
				
			}
			
			h=h/3;
		}
	}
}