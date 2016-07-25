import java.util.Arrays;
import java.util.Random;

public class CmpSort 
{
	public static void main(String[] args) 
	{
		System.out.println("Quick\tHeap\tMerge");
		
		for(int N=(int)1e4;N<(int)1e7;N*=2)
		{
			//copy 3 random arrays
			int[] aryQuick=genRandAry(N); 
			int[] aryHeap=Arrays.copyOf(aryQuick,aryQuick.length);
			int[] aryMerge=Arrays.copyOf(aryQuick,aryQuick.length); 
			
			//quicksort
			long time=0;
			long begin=System.currentTimeMillis();   
			Qs(aryQuick,0,aryQuick.length-1);
			long end=System.currentTimeMillis();
			time=(end - begin); 
			System.out.print(time+""+isSorted(aryQuick));
			
			//heapsort
			time = 0;      
			begin=System.currentTimeMillis();  
			Heap.sort(aryHeap);
			end=System.currentTimeMillis();
			time=(end - begin); 
			System.out.print("\t"+time+""+isSorted(aryHeap));
			
			//Mergesort
			time=0;
			begin=System.currentTimeMillis();  
			Merge.sort(aryMerge);
			end=System.currentTimeMillis();
			time=(end - begin); 
			System.out.print("\t"+time+""+isSorted(aryMerge));
			
			System.out.println();
		}
	}
	
	//generate a random array with length n
	public static int[] genRandAry(int n)
	{
		int[] ary=new int[n];   
		Random rand=new Random();   
		for(int i=0;i<ary.length;i++)  
			ary[i] = rand.nextInt();   
		return ary;
	} 

	//judge whether an array is sorted
	public static boolean isSorted(int[] a)
	{
		for(int i=0;i<a.length-1;i++)
			if(a[i]>a[i+1])
				return false;
		return true;
	}
	
	//simplest quicksort p289 && p291
	public static void Qs(int[] a,int lo, int hi)
	{
		if(lo>=hi)	return;
		int j=partition(a,lo,hi);
		Qs(a,lo,j-1);
		Qs(a,j+1,hi);
	}

	private static int partition(int[] a, int lo, int hi)
	{
		int i=lo,j=hi+1;
		int v=a[lo];
		while(true)
		{
			while(a[++i]<v)	if(i==hi)	break;
			while(v<a[--j])	if(j==lo)	break;
			if(i>=j)	break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}
	
	private static void exch(int[] a,int i,int j)
	{
		int tmp=a[i];
		a[i]=a[j];
		a[j]=tmp;
	}
}

//heapsort p324 && http://algs4.cs.princeton.edu/24pq/Heap.java.html
class Heap
{
	public static void sort(int[] pq) {
        int N = pq.length;
        for (int k = N/2; k >= 1; k--)
            sink(pq, k, N);
        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }
	
	private static void sink(int[] pq, int k, int N) 
	{
        while (2*k <= N) 
		{
            int j = 2*k;
            if (j < N && pq[j-1]<pq[j]) j++;
            if (pq[k-1]>=pq[j-1]) 		break;
            exch(pq, k, j);
            k = j;
        }
    }
	
	

    private static void exch(int[] pq, int i, int j)
	{
        int swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }
}

//mergesort p271 && p273
class Merge
{
	private static int[] aux;

	public static void sort(int[] a)
	{
		aux=new int[a.length];
		sort(a,0,a.length-1);
	}
		
	private static void sort(int[] a,int lo,int hi)
	{
		if(hi<=lo)	return;
		int mid=lo+(hi-lo)/2;
		sort(a,lo,mid);
		sort(a,mid+1,hi);
		merge(a,lo,mid,hi);
	}
	
	private static void merge(int[] a,int lo,int mid,int hi)
	{
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;k++)
			aux[k]=a[k];
		for(int k=lo;k<=hi;k++)
		{
			if(i>mid)				a[k]=aux[j++];
			else if(j>hi)			a[k]=aux[i++];
			else if(aux[j]<aux[i])	a[k]=aux[j++];
			else					a[k]=aux[i++];
		}
	}
}