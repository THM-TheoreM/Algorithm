import edu.princeton.cs.algs4.*;

/* 
 * reference of local minimum of an array
 * [1]http://stackoverflow.com/questions/12238241/find-local-minima-in-an-array
 * 
 * reference of local minimum of a matrix 
 * [2]http://stackoverflow.com/questions/18525179/find-local-minimum-in-n-x-n-matrix-in-on-time
 * [3]http://stackoverflow.com/questions/10063289/find-a-local-minimum-in-a-2-d-array
 *
 * attention
 * (1)use distinct integers because of [1]
 * (2)consider the boundary because of [3]
 */

public class LocalMin
{
	//O(N)
	public static int LocalMinumum(int[] a)
	{  
		if(a.length==1 || a[0]<a[1])    return 0;
		if(a[a.length-2]>a[a.length-1]) return a.length-1;
		
		for(int i=1;i<a.length-1;i++)
			if(a[i]<a[i-1] && a[i]<a[i+1]) 
				return i;
		
		return -1;
	}
	
	//O(logN)
	public static int LocalMinumum2(int[] a)
	{
		int lo=0,hi=a.length-1;
		if(hi==0 || a[0]<a[1]) return 0;
		if(a[hi-1]>a[hi])      return hi;
		
		while(true)
		{
			int i=lo+(hi-lo)/2;
			if(a[i]>a[i-1])     hi=i-1; 
			else if(a[i]>a[i+1])lo=i+1;
			else return i;
		}
	}
	
	//O(N^2)
	public static int[] LocalMinumum(int[][] a)
	{
		int N=a.length;
		int[] index=new int[2];
		
		if(N==1 || (a[0][0]<a[0][1] && a[0][0]<a[1][0]))
		{index[0]=0;index[1]=0;return index;}
	
		if(a[0][N-1]<a[0][N-2] && a[0][N-1]<a[1][N-1])
		{index[0]=0;index[1]=N-1;return index;}
	
		if(a[N-1][0]<a[N-1][1] && a[N-1][0]<a[N-2][0])
		{index[0]=N-1;index[1]=0;return index;}
	
		if(a[N-1][N-1]<a[N-1][N-2] && a[N-1][N-1]<a[N-2][N-1])
		{index[0]=N-1;index[1]=N-1;return index;}
	    
		for(int i=1;i<N-1;i++)
			if(a[0][i]<a[1][i] && a[0][i]<a[0][i+1] && a[0][i]<a[0][i-1])
			{index[0]=0;index[1]=i;return index;}	
		
		for(int i=1;i<N-1;i++)
			if(a[N-1][i]<a[N-1][i+1] && a[N-1][i]<a[N-2][i] && a[N-1][i]<a[N-1][i-1])
			{index[0]=N-1;index[1]=i;return index;}	
				
		for(int i=1;i<N-1;i++)
			if(a[i][0]<a[i+1][0] && a[i][0]<a[i][1] && a[i][0]<a[i-1][0])
			{index[0]=i;index[1]=0;return index;}	
				
		for(int i=1;i<N-1;i++)
			if(a[i][N-1]<a[i+1][N-1] && a[i][N-1]<a[i-1][N-1] && a[i][N-1]<a[i][N-2])
			{index[0]=i;index[1]=N-1;return index;}	
		
		for(int i=1;i<N-1;i++)
			for(int j=1;j<N-1;j++)
				if(a[i][j]<a[i+1][j] && a[i][j]<a[i][j+1] && a[i][j]<a[i-1][j] && a[i][j]<a[i][j-1])
				{index[0]=i;index[1]=j;return index;}
		
		return index;
	}	
	
	//O(N)
	public static int[] LocalMinumum2(int[][] a)
	{
		int N=a.length;
		int[] index=new int[2];
		int i0=(N-1)/2,j0=0;
		
		if(N==1)
		{index[0]=0;index[1]=0;return index;}
		
		while(true)
		{
			for(int j=0;j<N;j++)
				if(a[i0][j]<a[i0][j0])
					j0=j;
			
			if(i0==0 || i0==N-1)
			{index[0]=i0;index[1]=j0;return index;}
			
			if(a[i0][j0]>a[i0-1][j0])      i0--;
			else if(a[i0][j0]>a[i0+1][j0]) i0++;
			else
			{index[0]=i0;index[1]=j0;return index;}				
		}		
		
	}
	
	public static void main(String[] args)
	{
		int[] a={7,6,3,2,4,5};
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
		StdOut.println();
		StdOut.println(LocalMinumum(a));
		StdOut.println(LocalMinumum2(a)+"\n");
		
		int[][] b={{1,12,3,1,-23},{7,9,8,5,6},{4,5,6,-1,77},{7,0,35,-2,-4},{6,83,1,7,-6}};
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b.length;j++)
				StdOut.print(b[i][j]+" ");
			StdOut.println();
		}
		StdOut.println(LocalMinumum(b)[0]+" "+LocalMinumum(b)[1]);
		StdOut.println(LocalMinumum2(b)[0]+" "+LocalMinumum2(b)[1]);
	}
}