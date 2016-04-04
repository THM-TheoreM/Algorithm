import java.util.Arrays;

public class KendallTau
{	
	//Test the correctness of Index.sort and Index.inversion by index instead of items using the idea of mergesort
	public static void test(Comparable[] a)
	{
		System.out.print("Initial array: ");
		for(int i=0;i<a.length;i++)
			System.out.print(a[i].toString()+" ");
		System.out.println();
		System.out.println("After merge sort:");
		int[] index=Index.sort(a);
		System.out.print("Index array: ");
		for(int i=0;i<a.length;i++)
			System.out.print(index[i]+" ");
		System.out.println();
		System.out.print("Value array: ");
		for(int i=0;i<a.length;i++)
			System.out.print(a[index[i]].toString()+" ");
		System.out.println();
		System.out.println("Number of inversions of the original array: "+Index.inversion(a));	
	}

	//Test the correctness of Kendall Tau distance
	public static void distance(Comparable[] tau,Comparable[] sigma)
	{
		System.out.println("Kendall Tau distance of");
		for(Comparable x:tau)
			System.out.print(x.toString()+" ");
		System.out.println();
		for(Comparable x:sigma)
			System.out.print(x.toString()+" ");
		System.out.println();
		
		//generate int array tau^{-1}
		int[] invTau=Index.sort(tau);
		
		//make sigma to be an int array Sigma
		int[] invsigma=Index.sort(sigma);
		Integer[] invSigma=new Integer[invsigma.length];
		for(int i=0;i<invsigma.length;i++)
			invSigma[i]=invsigma[i];
		int[] Sigma=Index.sort(invSigma); 
		
		//ans=tau^{-1}*Sigma, let it be an Integer array instead of an int one so that Index.inversion can take it as an input parameter
		Integer[] ans=new Integer[sigma.length];
		for(int i=0;i<sigma.length;i++)
			ans[i]=invTau[Sigma[i]];
		/*//Explictly show invTau*Sigma not in the form of an int array but of a Comparable array
		System.out.print("invTau*Sigma=");
		for(int i=0;i<sigma.length;i++)
			System.out.print(tau[invTau[ans[i]]]+" ");
		System.out.println();
		*/
		System.out.println("equals "+Index.inversion(ans));
	}
	
	public static void main(String[] args)
	{
		test(new Integer[]{0,3,1,6,2,5,4});
		//test(new String[] {"A","C","B"});
		
		System.out.println("-------------------------------------------");
		
		distance(new Integer[]{0,3,1,6,2,5,4},new Integer[]{1,0,3,6,4,2,5});
		//distance(new String[]{"A","C","B"},new String[]{"B","A","C"});
	}
}

class Index
{
	private static int[] index;//record the index array
	private static int[] aux;//auxiliary array in merge sort
	private static int N;//record the number of inversions
	
	public static int inversion(Comparable[] a)
	{
		sort(a);
		return N;
	}
	
	public static int[] sort(Comparable[] a)
	{
		//initialize
		N=0;
		index=new int[a.length];
		for(int i=0;i<index.length;i++)
			index[i]=i;
		aux=new int[a.length];
		
		sort(a,0,a.length-1);
		return index;
	}
	
	private static void sort(Comparable[] a,int lo,int hi)
	{
		if(hi<=lo)	return;
		int mid=lo+(hi-lo)/2;
		sort(a,lo,mid);
		sort(a,mid+1,hi);
		merge(a,lo,mid,hi);
	}
	
	private static void merge(Comparable[] a,int lo,int mid,int hi)
	{
		//sort
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;k++)
			aux[k]=index[k];
		for(int k=lo;k<=hi;k++)
		{
			if(i>mid)									index[k]=aux[j++];
			else if(j>hi)								index[k]=aux[i++];
			else if(a[aux[j]].compareTo(a[aux[i]])<0)	index[k]=aux[j++];
			else										index[k]=aux[i++];
		}
		
		//calculate the number of inversions
		i=mid;j=hi;
		for(int k=lo;k<=hi;k++)
		{
			if(i<lo || j<=mid)							break;
			if(a[aux[j]].compareTo(a[aux[i]])<0)
			{
				N+=j-mid;
				i--;
			}
			else										j--;
		}
	}
}