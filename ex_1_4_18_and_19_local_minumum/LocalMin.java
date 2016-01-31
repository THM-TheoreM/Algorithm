import edu.princeton.cs.algs4.*;

public class LocalMin
{
	public static int LocalMinumum(int[] a)
	{
		for(int i=1;i<a.length-1;i++)
		{
			if(a[i]<a[i-1]&&a[i]<a[i+1])
				return i;
		}
		return -1;
	}
	
	public static int[] LocalMinumum(int[][] a)
	{
		int[] index=new int[2];
		for(int i=1;i<a.length-1;i++)
		{
			for(int j=1;j<a[0].length-1;j++)
			if(a[i][j]<a[i+1][j]&&a[i][j]<a[i][j+1]&&a[i][j]<a[i-1][j]&&a[i][j]<a[i][j-1])
			{
				index[0]=i;index[1]=j;
				return index;
			}
		}
		index[0]=-1;index[1]=-1;
		return index;
	}
	
	public static void main(String[] args)
	{
		int[] a=new int[6];
		a[0]=1;a[1]=2;a[2]=3;a[3]=2;a[4]=3;a[5]=5;
		StdOut.println(LocalMinumum(a));
		
		int[][] b=new int[3][4];
		b[0][0]=1;b[0][1]=2;b[0][2]=3;b[0][3]=2;
		b[1][0]=3;b[1][1]=3;b[1][2]=1;b[1][3]=4;
		b[2][0]=1;b[2][1]=2;b[2][2]=2;b[2][3]=3;
		StdOut.println(LocalMinumum(b)[0]+" "+LocalMinumum(b)[1]);
	}
}