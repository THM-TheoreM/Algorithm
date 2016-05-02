import edu.princeton.cs.algs4.*;

//Auto-unboxing: "int ii=i;" is allowed, which is equivalent to int ii=i.intValue();
//Autoboxing: "Integer i=5;" is allowed, but have different meaning according to the assignment value of i
//            when the value of i is between -128 and 127, it is almost equivalent to int i=5, thus "Integer i=5,j=5; StdOut.println(i==j)" will output "true";
//            when the value of i is outside the range above, it is equivalent to "Integer i=new Integer(1000); Integer j=new Integer(1000)" thus i!=j (for they have different references)
//Although autoboxing and auto-unboxing is convenient, it is worthwhile to see the penalty if we use it when we can have other ways to avoid it.
//The following code can not be compiled but can run if revised with just a few lines, think of it or just develop a new method to see the penalty described above.
public class AutoboxingPerformance
{
	private class FixedCapacityStackOfInts
	{
		private int[] a;
		private int N;
		
		FixedCapacityStackOfInts(int cap)	{a=new int[cap];}
		void push(int item)					{a[N++]=item;}
		int pop()							{return a[--N];}
	}
	
	private class FixedCapacityStack<Item>
	{
		private Item[] a;
		private int N;
		
		FixedCapacityStack(int cap) 		{a=(Item[])new Object[cap];}
		void push(Item item)				{a[N++]=item;}
		Item pop()							{return a[--N];}
	}
	
	public static double[] timeTrial(int N)
	{
		double[] a=new double[2];
		FixedCapacityStackOfInts s1=new FixedCapacityStackOfInts(N);
		long start=System.currentTimeMillis();
		for(int i=0;i<N;i++)
			s1.push(i);
		long end=System.currentTimeMillis();
		a[0]=end-start;
		FixedCapacityStack<Integer> s2=new FixedCapacityStack<Integer>(N);
		start=System.currentTimeMillis();
		for(int i=0;i<N;i++)
			s2.push(i);
		end=System.currentTimeMillis();
		a[1]=end-start;
		return a;
	}
	
	public static void main(String[] args)
	{
		for(int N=250;N<10000000;N+=N)
		{
			double[] a=timeTrial(N);
			StdOut.printf("%5.1f\t%5.1f\n",a[0],a[1]);
		}
	}
}