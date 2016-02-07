import edu.princeton.cs.algs4.*;

public class QuickUnion
{
	private int[] id;//represent its parent node number
	private int count;
		
	public QuickUnion(int N)
	{
		id=new int[N];
		for(int i=0;i<N;i++)
			id[i]=i;
		count=0;
	}
	
	public int count()
	{  return count;  }
	
	public boolean connected(int p,int q)
	{  return find(p)==find(q);  }
	
	public int find(int p)
	{
		count++;
		while(p!=id[p])//find root
		{
			p=id[p];
			count+=2;
		}
		return p;
	}
		
	public void union(int p,int q)
	{
		int pRoot=find(p);
		int qRoot=find(q);
		
		if(pRoot==qRoot)	return;
		
		id[pRoot]=qRoot;
		count++;
	}
	
	public static void main(String[] args)
	{
		StdDraw.setXscale(-50,650);
		StdDraw.setYscale(-100,1300);
		StdDraw.line(-50,0,650,0);
		StdDraw.line(0,-100,0,1300);
		StdDraw.setPenRadius(0.01);
		int N=StdIn.readInt();
		QuickUnion qu=new QuickUnion(N);
		int i=0,cnt=0;
		while(!StdIn.isEmpty())
		{
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(qu.connected(p,q))
				continue;
			qu.union(p,q);
			StdDraw.point(i++,qu.count()-cnt);
			StdDraw.show(10);
			cnt=qu.count();
		}
	}
}