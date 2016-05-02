public class IndexSort<Key extends Comparable<?super Key>>
{
	private int[] a;
	private Key[] b;
	
	public IndexSort(Key[] keys)
	{
		b=(Key[]) new Comparable[keys.length];
		a=new int[keys.length];
		
		
		for(int i=0;i<keys.length;i++)
		{
			a[i]=i;
			b[i]=keys[i];
		}
		
		for(int i=0;i<keys.length;i++)
			for(int j=i+1;j<keys.length;j++)
				if(less(j,i)) exch(i,j);
		
		for(int i=0;i<keys.length;i++)
		{
			int j=a[i];
			System.out.println(j+" "+keys[j]);
		}
	}

	private boolean less(int i, int j)
	{return b[a[i]].compareTo(b[a[j]])<0;}
	
	private void exch(int i, int j)
	{
		int swap=a[i];
		a[i]=a[j];
		a[j]=swap;
	}

	public static void main(String[] args)
	{
		Integer[] keys= {2,1,5,4,3};
		IndexSort<Integer> sort=new IndexSort<Integer>(keys);
	}
}
