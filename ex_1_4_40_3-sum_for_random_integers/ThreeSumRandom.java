import edu.princeton.cs.algs4.*;

//From the test experiment, the hypothesis can be "the number of triples of N random int values that sum to 0 is the order of N^2/M"
//Is there any mathematical provement to support it? And how to test large N,M if possible. 
public class ThreeSumRandom
{
	public static int threesum(int[] a)
	{
		int cnt=0;
		for(int i=0;i<a.length;i++) 
			for(int j=i+1;j<a.length;j++)
				if(BinarySearch.rank(-a[i]-a[j],a) > j)
					cnt++;
		return cnt;
	}
  
	public static void main(String[] args)
	{
		for(int N=10;N<2000;N*= 2)
		{
			for(int M=100;M<2000;M*= 2)
			{
				int cnt=0;
				for (int i=0;i<1000;i++)
				{
					int[] a=new int[N];
					for(int j=0;j<N;j++) 
						a[j]=StdRandom.uniform(-M,M);
					cnt+=threesum(a);
				}
				StdOut.print(cnt/1000+"\t");
			}
			StdOut.println();
		}
    }
}
