import edu.princeton.cs.algs4.*;

public class ThrowEggsOneToTen
{
	private static int[][] cnt=new int[101][11];
	private static boolean flag=false;
	
	public static int ThrowEggs(int height,int number)
	{
		if(flag==false)
		{
			ThrowEggs();
			flag=true;
		}
		return cnt[height][number];
	}
	
	private static void ThrowEggs()
	{
		for(int i=1;i<=10;i++)
			cnt[1][i]=1;
		for(int i=1;i<=100;i++)
			cnt[i][1]=i;
		for(int j=2;j<=10;j++)
		{
			for(int i=2;i<=100;i++)
			{
				int min=cnt[i-1][j]+1;
				for(int k=2;k<=i;k++)
				{
					int tmp=Math.max(cnt[k-1][j-1],cnt[i-k][j])+1;
					min=tmp<min?tmp:min;
				}
				cnt[i][j]=min;
			}
		}
	}
	
	public static void main(String[] args)
	{
		//for(int i=1;i<=25;i++)
		int i=100;
		{
			StdOut.print(i+"floors\t");
			for(int j=1;j<=10;j++)
			{
				StdOut.print(ThrowEggs(i,j)+"\t");
			}
			StdOut.println();
		}
	}
}