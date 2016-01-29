import edu.princeton.cs.algs4.*;
import java.util.Arrays;
import java.awt.Color;

public class sorting
{
	public static void main(String[] args)
	{
		int N=100;
		double t1,t2;
		double[] a=new double[N];
		double[] b=new double[N];
		StdDraw.setYscale(0,3);
 		
		for(int i=0;i<N;i++)
		{
			a[i]=StdRandom.random();
			b[i]=a[i];
		}
		
		for(int i=0;i<N;i++)
		{
			StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,a[i]/2.0);
			StdDraw.filledRectangle(1.0*i/N,b[i]/2.0+2,0.5/N,b[i]/2.0);
		}
		
		for(int i=0;i<N;i++)
		{
			for(int j=i+1;j<N;j++)
			{
				if(a[j]<a[i])
				{
					StdDraw.setPenColor(Color.WHITE);
					StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,1);
					StdDraw.filledRectangle(1.0*j/N,a[j]/2.0,0.5/N,1);
		
		            t1=a[i];
					a[i]=a[j];
					a[j]=t1;
					
					StdDraw.setPenColor(Color.BLACK);
					StdDraw.filledRectangle(1.0*i/N,a[i]/2.0,0.5/N,a[i]/2.0);
					StdDraw.filledRectangle(1.0*j/N,a[j]/2.0,0.5/N,a[j]/2.0);
				}
				
				if(b[j-i-1]>b[j-i])
				{
					StdDraw.setPenColor(Color.WHITE);
					StdDraw.filledRectangle(1.0*(j-i-1)/N,b[j-i-1]/2.0+2,0.5/N,1);
					StdDraw.filledRectangle(1.0*(j-i)/N,b[j-i]/2.0+2,0.5/N,1);					
					
					t2=b[j-i-1];
					b[j-i-1]=b[j-i];
					b[j-i]=t2;
					
					StdDraw.setPenColor(Color.BLACK);
					StdDraw.filledRectangle(1.0*(j-i-1)/N,b[j-i-1]/2.0+2,0.5/N,b[j-i-1]/2.0);
					StdDraw.filledRectangle(1.0*(j-i)/N,b[j-i]/2.0+2,0.5/N,b[j-i]/2.0);
				}
			}
		}
	}
}