import edu.princeton.cs.algs4.*;
import java.awt.Color;

public class shuffle
{ 
	public static void main(String[] args)
	{
		int M=Integer.parseInt(args[0]);
		int N=Integer.parseInt(args[1]);
		int[] a=new int[M];
		int[] b=new int[M];
		int[][] c=new int[M][M];
		int[][] d=new int[M][M];
		
		StdDraw.setYscale(0,3*M);
		for(int i=0;i<M;i++)
		{
			a[i]=i;
			b[i]=a[i];
		}
		for(int i=0;i<M;i++)
		{
			StdDraw.filledRectangle(1.0*i/M,a[i]/2.0,0.5/M,a[i]/2.0);
		    StdDraw.filledRectangle(1.0*i/M,b[i]/2.0+2*M,0.5/M,b[i]/2.0);
		}
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				//Empirical shuffle check
				int r1=j+StdRandom.uniform(M-j);
				StdDraw.setPenColor(Color.WHITE);
				StdDraw.filledRectangle(1.0*j/M,a[j]/2.0,0.5/M,M);
				StdDraw.filledRectangle(1.0*r1/M,a[r1]/2.0,0.5/M,M);
				
				int temp1=a[j];
				a[j]=a[r1];
				a[r1]=temp1;
				
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.filledRectangle(1.0*j/M,a[j]/2.0,0.5/M,a[j]/2.0);
				StdDraw.filledRectangle(1.0*r1/M,a[r1]/2.0,0.5/M,a[r1]/2.0);
				
				//Bad Shuffling
				int r2=StdRandom.uniform(M);
				StdDraw.setPenColor(Color.WHITE);
				StdDraw.filledRectangle(1.0*j/M,b[j]/2.0+2*M,0.5/M,M);
				StdDraw.filledRectangle(1.0*r2/M,b[r2]/2.0+2*M,0.5/M,M);
				
				int temp2=b[j];
				b[j]=b[r2];
				b[r2]=temp2;
				
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.filledRectangle(1.0*j/M,b[j]/2.0+2*M,0.5/M,b[j]/2.0);
				StdDraw.filledRectangle(1.0*r2/M,b[r2]/2.0+2*M,0.5/M,b[r2]/2.0);
			}
			
			for(int k=0;k<M;k++)
			{
				for(int l=0;l<M;l++)
				{
					if(a[l]==k) c[k][l]++;
					if(b[l]==k) d[k][l]++;
				}
			}
		}
		
		for(int k=0;k<M;k++)
		{
			for(int l=0;l<M;l++)
			{
				StdOut.print(c[k][l]+" ");
			}
			StdOut.print("\n");
		}
		
		StdOut.print("\n");
		
	    for(int k=0;k<M;k++)
		{
			for(int l=0;l<M;l++)
			{
				StdOut.print(d[k][l]+" ");
			}
			StdOut.print("\n");
		}
    }
}