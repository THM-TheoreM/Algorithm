import edu.princeton.cs.algs4.*;

public class QuickFind
{
	private int[] id;//show which component the node belongs to
	private int count;//represent the number of array accesses used so far
	
	public QuickFind(int N)
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
		return id[p];
	}
	
	public void union(int p,int q)
	{
		int pID=find(p);
		int qID=find(q);
		
		if(pID==qID)	return;
		
		for(int i=0;i<id.length;i++)
		{
			count++;
			if(id[i]==pID)
			{
				id[i]=qID;
				count++;
			}
		}
	}
	
	public static void main(String[] args)
	{
		StdDraw.setXscale(-50,650);
		StdDraw.setYscale(-100,1300);
		StdDraw.line(-50,0,650,0);
		StdDraw.line(0,-100,0,1300);
		StdDraw.setPenRadius(0.01);
		int N=StdIn.readInt();
		QuickFind qf=new QuickFind(N);
		int i=0,cnt=0;
		while(!StdIn.isEmpty())
		{
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(qf.connected(p,q))
				continue;
			qf.union(p,q);
			StdDraw.point(i++,qf.count()-cnt);
			StdDraw.show(10);
			cnt=qf.count();
		}
	}
}