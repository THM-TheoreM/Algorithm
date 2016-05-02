import edu.princeton.cs.algs4.*;

//Restatement the problem to be clearer:
//Suppose that you have an N-story building and k identical eggs. Suppose also that an egg is broken if it is thrown off floor F or higher,
//and unhurt otherwise(thus can be reused in the next test). Devise a strategy to determine the value of F.
//exercise 1.4.24 means: when k=+infinity, firstly, there exists such a strategy whose worst case takes ~lgN throws and at most needs ~lgN eggs
//                                         secondly, the strategy can be improved such that the worst case takes ~2*lgF throws.
//exercise 1.4.25 means: when k=2, firstly, there exists such a strategy whose worst case takes at most ceil(2√N) throws
//                                 secondly, the strategy can be improved such that the worst case takes ~c√F throws, where c is a small constant compared to F.
//Futhermore, when k=1, there is the only strategy to determine F, that is starting from the first floor, throwing the egg, if not broken, go upstairs by one floor,
//                                                                         repeat until the egg breaks, that is the value of F, and it takes F throws.
//            when k=3, the worst case can be recursively implemented, tranverse 1~N and max_{i=1}^N{ max{Problem(floor=N-i+1,egg=3)[intact],Problem(floor=i-1,egg=2)[broken]} }, where i means the trial floor
//			  similar cases when k>3, all can be recursively implemented.
//p.s.: If you want to check whether your program for the worst case is correct, http://datagenetics.com/blog/july22012/index.html may be helpful.
public class ThrowingEggs
{
	//auxiliary function binarysearch
	private static int binarysearch(int F,int lo,int hi)
	{
		if(lo==hi)
			return 0;
		int cnt=0;	
		while(lo<hi)
		{
			cnt++;
			int mid=lo+(hi-lo)/2;//throw on floor mid
			StdOut.print(mid+" ");
			if(hi-lo==1)	return cnt;//F=lo: test mid=lo turns out to be broken, F=hi: test mid=lo turns out to be intact
			else if(mid>=F)	hi=mid;//egg broken;
			else			lo=mid+1;//egg intact;
			if(lo==hi)		return cnt;
		}
		throw new RuntimeException("Unexpected End");
	}
	
	
	//BinarySearch -> ~lgN
	public static int PlentyEggs(int F,int N)
	{
		return binarysearch(F,1,N);
	}
	
	//Finding the smallest 2^i but no less than F and binary search between (2^(i-1),2^i] -> ~2*lgF
	public static int PlentyEggsFast(int F,int N)
	{
		int i=1,cnt=1;
		StdOut.print(i+" ");
		while(i<F)//throw on floor i till egg broken
		{
			i*=2;
			cnt++;
			StdOut.print(i+" ");
		}
		return cnt+binarysearch(F,i/2+1,i);
	}
	
	//Actually, the upper bound ceil(2√N) can be improved to be a tighter one ceil(√(2*N))!!!
	//Algorithm: check floor x, then check floor x+(x-1) and so on
	//The worst case is the smallest positive integer x such that "x+(x-1)+...+1>=N" -> ceil((-1+√(1+8*N))/2) <= ceil(√(2*N)) (it is a tight upper bound because the equality holds even when N=97)
	public static int TwoEggs(int F,int N)
	{
		int cnt=1,x=(int)Math.ceil((-1+Math.sqrt(1+8*N))/2),sum=x;
		StdOut.print(sum+" ");
		while(sum<F)//the first egg is intact
		{
			sum+=--x;
			StdOut.print(Math.min(sum,N)+" ");//be careful with the case when sum reaches N
			cnt++;
		}
		for(int i=sum-x+1;i<=F;i++)
		{
			if(i!=sum && i!=N)//case when "i=sum" or "i=N" means test on the "i"th floor has been tested before; e.g.: N=F=8(i=N); N=8,F=7(i=sum)
			{
				StdOut.print(i+" ");
				cnt++;
			}
		}
		return cnt;
	}
	
	//first use lgF times to reduce the range 1~N, and then use methods in TwoEggs, for lgF<<√F, so total cost is ~c√F
	public static int TwoEggsFast(int F,int N)
	{
		//special cases, if reduction to a lower floor with corresponding F, it will lead to errors 
		if(F==1)//if reduction, F=1,N=0 is not well-defined
		{
			StdOut.print(1);
			return 1;
		}
		
		//throw on floor i till egg broken
		int i=1,cnt=1;
		StdOut.print(i+" ");
		while(i<F)
		{
			i*=2;
			cnt++;
			StdOut.print(i+" ");
		}
		
		F=F-i/2;N=i/2;//!!!reduction: treat as a ( ( i-(i/2+1) ) + 1 )-story building, floor i/2+1 in the original building as the first floor in the new building
		
		//Method same to TwoEggs
		int cnt2=0,x=(int)Math.ceil((-1+Math.sqrt(1+8*N))/2),sum=x;
		if(sum+i/2!=i)//in case of 1 2 4 -> find 4 in 3,4, but first test 4, repeated
		{
			StdOut.print(sum+i/2+" ");
			cnt2++;
		}
		while(sum<F)
		{
			sum+=--x;
			if(sum<N)//if sum>=N, we will test N+i/2=i which has already been tested: e.g.: F=16
			{
				StdOut.print(sum+i/2+" ");
				cnt2++;
			}
		}
		for(int j=sum-x+1;j<=F;j++)
		{
			if(j!=sum && j!=N)
			{
				StdOut.print(j+i/2+" ");
				cnt2++;
			}
		}
		return cnt+cnt2;
	}
	
	//distance between element of array
	public static int[] distance(int N, int[] a)
	{
		int[] b=new int[a.length+1];
		
		b[0]=a[0];
		b[a.length]=N-1-a[a.length-1]+a.length;
		for(int i=1;i<a.length;i++)
			b[i]=a[i]-a[i-1]+i;
		
		return b;
	}
	
	//maximum of an array
 	public static int max(int[] a)
	{
		int index=a[0];
		
		for(int i=1;i<a.length;i++)
			if(index<a[i]) index=a[i];
			
		return index;
	}
	
	//smallest number fixed the first egg throwing number n
	public static int[] best(int N, int n)
	{
		int[] a=new int[n];
		
		if(n==1)
		{
			a[0]=1;
			
			for(int i=1;i<=N-1;i++)
			{
				int[] b={i};
				if(max(distance(N,a))>max(distance(N,b))) a=b;
			}
			
			return a;
		}
		
		for(int i=0;i<n;i++)
			a[i]=i+1;
		
		for(int i=1;i<=N-n;i++)
		{
			int[] t=best(N-i,n-1);
			int[] b=new int[n];
			
			b[0]=i;
			for(int j=1;j<n;j++)
				b[j]=t[j-1]+i;
			
			if(max(distance(N,a))>max(distance(N,b))) a=b;
		}
		
		return a;
	}
	
	//smallest number
	public static int[] best(int N)
	{
		int[] a=best(N,1);
		
		for(int n=2;n<=N-1;n++)
		{
			int[] b=best(N,n);
			
			if(max(distance(N,a))>max(distance(N,b))) a=b;
		}
		
		return a;
	}
	
	public static void main(String[] args)
	{	
		int cnt;
		
		StdOut.println("Plenty eggs ~lgN algorithm:");
		for(int N=100;N<200000;N*=2)
		{
			StdOut.print(N+"-story building, F="+1+": ");
			StdOut.println("\tusing "+PlentyEggs(1,N)+" times in total");
		}
		
		StdOut.println("-------------------------------");
		
		StdOut.println("Plenty eggs ~2*lgF algorithm:");
		for(int F=1;F<=102400;F*=2)
		{
			StdOut.print("102400-story building, F="+F+": ");
			StdOut.println("\tusing "+PlentyEggsFast(F,102400)+" times in total");
		}
		
		StdOut.println("-------------------------------");
		
		StdOut.println("Two eggs ~2*sqrt(N) algorithm:");
		for(int N=100;N<200000;N*=4)
		{
			StdOut.print(N+"-story building, F="+(N-1)+": ");
			StdOut.println("\tusing "+TwoEggs(N-1,N)+" times in total");
		}
		
		StdOut.println("-------------------------------");
		
		StdOut.println("Two eggs ~c*sqrt(F) algorithm:");
		for(int F=1;F<=102400;F*=4)
		{
			StdOut.print("102400-story building, F="+F+": ");
			StdOut.println("\tusing "+TwoEggsFast(F,102400)+" times in total");
		}
		
		StdOut.println("-------------------------------");
		
		int N=8;
		
		int[] a={1,2,3};
		for(int x: a)
			StdOut.print(x+" ");
		StdOut.println();
		for(int x: distance(N,a))
			StdOut.print(x+" "); 
		StdOut.println();
		StdOut.println(max(distance(N,a)));
		StdOut.println();
		
		a=best(N,3);
		for(int x: a)
			StdOut.print(x+" ");
		StdOut.println();
		for(int x: distance(N,a))
			StdOut.print(x+" "); 
		StdOut.println();
		StdOut.println(max(distance(N,a)));
		StdOut.println();
		
		a=best(N);
		for(int x: a)
			StdOut.print(x+" ");
		StdOut.println();
		for(int x: distance(N,a))
			StdOut.print(x+" "); 
		StdOut.println();
		StdOut.println(max(distance(N,a)));
		StdOut.println();
		
		int M=50;
				StdDraw.setXscale(0,M);
		StdDraw.setYscale(0,2*Math.sqrt(M));
		StdDraw.setPenRadius(0.01);
		
		for(int i=2;i<=M;i++)
			StdDraw.point(i,2*Math.sqrt(i));
		
		for(int i=2;i<=M;i++)
			StdDraw.point(i,max(distance(i,best(i))));
	}
}