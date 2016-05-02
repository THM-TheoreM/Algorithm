import edu.princeton.cs.algs4.*;

//Question: Is this understanding correct? If it it correct, what does the hint mean?
public class Fraction
{
	public static int[] fraction(double x,int N)
	{
		if(1.0/(N-1)>x)
			throw new RuntimeException("No such approximate fraction number exists.");
		int[] ans=new int[2];
		double sum=0;
		int i=2,i_pre=1;
		while(i<=N)
		{
			if(sum+1.0/i<=x)
			{
				sum+=1.0/i;
				ans[0]=ans[0]*i/i_pre+1;
				ans[1]=i;
				i_pre=i;
			}
			i*=2;
		}
		return ans;
	}
	
	public static void main(String[] args)
	{
		int[] ans=new int[2];
		ans=fraction(0.63,10);
		StdOut.println(ans[0]+"/"+ans[1]);
	}
}