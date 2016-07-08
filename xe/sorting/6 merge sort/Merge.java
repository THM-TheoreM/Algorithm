import edu.princeton.cs.algs4.*;

//merge sort 归并排序
public class Merge
{
	//double & visulize
	private static int N=100;
	double[] a;
	double[] AUX;
	
	Merge()
	{
		a=new double[N];
		AUX=new double[N];
		StdDraw.setYscale(0,3);
 		
		for(int i=0;i<N;i++)
			a[i]=StdRandom.random();
		
		for(int i=0;i<N;i++)
			StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,a[i]/2.0);
		
		for(int sz=1;sz<a.length;sz=sz+sz)
			for(int lo=0;lo<a.length-sz;lo+=sz+sz)
				merge(lo,lo+sz-1,Math.min(lo+sz+sz-1,a.length-1));
	}

	private void merge(int lo,int mid,int hi)
	{
		int i=lo;
		int j=mid+1;
		
		for(int k=lo;k<hi+1;k++)
			AUX[k]=a[k];
		
		for(int k=lo;k<hi+1;k++)
			if(i>mid)                    
			{
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledRectangle(1.0*k/N,a[k]/2.0,0.5/N,1);
				
				a[k]=AUX[j++];
				
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledRectangle(1.0*k/N,a[k]/2.0,0.5/N,a[k]/2.0);
			}
			else if(j>hi) 
			{
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledRectangle(1.0*k/N,a[k]/2.0,0.5/N,1);
				
				a[k]=AUX[i++];
				
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledRectangle(1.0*k/N,a[k]/2.0,0.5/N,a[k]/2.0);
			}
			else if(AUX[j]<AUX[i])       
			{	
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledRectangle(1.0*k/N,a[k]/2.0,0.5/N,1);
				
				a[k]=AUX[j++];
				
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledRectangle(1.0*k/N,a[k]/2.0,0.5/N,a[k]/2.0);
			}
			else                     
			{				
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledRectangle(1.0*k/N,a[k]/2.0,0.5/N,1);
				
				a[k]=AUX[i++];
				
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledRectangle(1.0*k/N,a[k]/2.0,0.5/N,a[k]/2.0);
			}
	}
	
	//comparable 
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a)
	{
		aux=new Comparable[a.length];
		for(int sz=1;sz<a.length;sz=sz+sz)
			for(int lo=0;lo<a.length-sz;lo+=sz+sz)
				merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,a.length-1));
	}
		
	private static void merge(Comparable[] a,int lo,int mid,int hi)
	{
		int i=lo;
		int j=mid+1;
		
		for(int k=lo;k<hi+1;k++)
			aux[k]=a[k];
		
		for(int k=lo;k<hi+1;k++)
			if(i>mid)                    a[k]=aux[j++];
			else if(j>hi)                a[k]=aux[i++];
			else if(less(aux[j],aux[i])) a[k]=aux[j++];
			else                         a[k]=aux[i++];
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
		
		new Merge();
	}
}