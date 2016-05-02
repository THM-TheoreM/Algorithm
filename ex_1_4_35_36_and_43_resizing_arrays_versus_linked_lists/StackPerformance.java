import edu.princeton.cs.algs4.*;

//Question 1: This program can show that a stack implemented by resizing arrays is faster than by linked lists, but how could it be proved by exercise 1.4.35 and 1.4.36?
//Question 2: What does the exercise 1.4.35 means?
//Question 3: All space usage is correct expect linked list with item type int, which I think should be 48N?
//Question 4: Is the formula at the first line on page 199 correct? It is different from the one in the lecture video. 
//            From my point of view, N should be discussed, and it is upper bounded when N=2^i+1 while lower bounded when N=2^i
public class StackPerformance
{
	public static double[] timeTrial(int N)
	{
		double[] a=new double[2];
		Stack<Integer> s1=new Stack<Integer>();
		long start=System.currentTimeMillis();
		for(int i=0;i<N;i++)
			s1.push(i);
		long end=System.currentTimeMillis();
		a[0]=end-start;
		ResizingArrayStack<Integer> s2=new ResizingArrayStack<Integer>();
		start=System.currentTimeMillis();
		for(int i=0;i<N;i++)
			s2.push(i);
		end=System.currentTimeMillis();
		a[1]=end-start;
		return a;
	}
	
	public static void main(String[] args)
	{
		for(int N=250;N<5000000;N+=N)
		{
			double[] a=timeTrial(N);
			StdOut.printf("%5.1f\t%5.1f\n",a[0],a[1]);
		}
	}
}