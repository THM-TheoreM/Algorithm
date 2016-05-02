import edu.princeton.cs.algs4.*;

//Here is a ~2*lgN algorithm, what is the ~lgN algorithm?
public class HotOrCold
{
	public static int guesswithoutput(int N,int ans)
	{
		if(ans==1)	return 1;//first time guess 1
		int cnt=1,preguess=1,guess=1,lo=2,hi=N;//[lo,hi] shows the reasonable interval now
		StdOut.println("lo \t hi \t preguess \t guess \t ans \t cold/hot \t lo \t high");
		while(guess!=ans)
		{
			StdOut.print(lo+"\t"+hi+"\t");
			guess=Math.abs(preguess-lo)>Math.abs(preguess-hi)?lo++:hi--;//guess rule: the remote endpoint at the distance of preguess
			StdOut.print(preguess+"\t\t"+guess+"\t"+ans+"\t");
			cnt++;
			if(guess==ans)
			{
				StdOut.println("correct!");
				return cnt;
			}
			if(Math.abs(preguess-ans)<=Math.abs(guess-ans))//cold(p.s.: when we counter "Math.abs(preguss-ans)==Math.abs(guess-ans)", we assume it tells cold)
			{
				StdOut.print("cold!\t");
				if(preguess<guess)
					hi=Math.min((preguess+guess)/2,hi);
				else if(preguess==guess)
					throw new RuntimeException("Unexpected preguess=guess");
				else
					lo=Math.max((preguess+guess)/2,lo);
			}
			else//hot
			{
				StdOut.print("hot!\t");
				if(preguess>guess)
					hi=Math.min((preguess+guess)/2+1,hi);
				else if(preguess==guess)
					throw new RuntimeException("Unexpected preguess=guess");
				else
					lo=Math.max((preguess+guess)/2+1,lo);
			}
			preguess=guess;
			StdOut.print(lo+"\t"+hi+"\n");
		}
		return cnt;
	}
	
	//just a version of no output
	public static int guess(int N,int ans)
	{
		if(ans==1)	
			return 1;
		int cnt=1,preguess=1,guess=1,lo=2,hi=N;
		while(guess!=ans)
		{
			guess=Math.abs(preguess-lo)>Math.abs(preguess-hi)?lo++:hi--;
			cnt++;
			if(guess==ans)
				return cnt;
			if(Math.abs(preguess-ans)<=Math.abs(guess-ans))
			{
				if(preguess<guess)
					hi=Math.min((preguess+guess)/2,hi);
				else
					lo=Math.max((preguess+guess)/2,lo);
			}
			else
			{
				if(preguess>guess)
					hi=Math.min((preguess+guess)/2+1,hi);
				else
					lo=Math.max((preguess+guess)/2+1,lo);
			}
			preguess=guess;
		}
		return cnt;
	}
	
	
	public static int maxguess(int N)
	{
		int maxcnt=0;
		for(int i=1;i<=N;i++)
		{
			int cnt=guess(N,i);
			if(cnt>maxcnt)
				maxcnt=cnt;
		}
		return maxcnt;
	}
	
	public static void main(String[] args)
	{
		int cnt=guesswithoutput(100,30);
		StdOut.println("Times used: "+cnt);
		StdOut.println("--------------------------------------------------------");
		for(int N=100;N<20000000;N+=N)
			StdOut.println("N="+N+",guess at most "+maxguess(N)+" times");
	}
}