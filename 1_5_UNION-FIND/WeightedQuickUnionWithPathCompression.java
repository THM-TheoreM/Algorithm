import edu.princeton.cs.algs4.*;

//Notice: the path compression is followed by what is described on page 231 in the textbook, not by exercise 1.5.12, there may be differences!!!
public class WeightedQuickUnionWithPathCompression
{
	private int[] id;//represent its parent node number
	private int[] sz;//indicate the size of root nodes
	private int count;
		
	public WeightedQuickUnionWithPathCompression(int N)
	{
		id=new int[N];
		sz=new int[N];
		for(int i=0;i<N;i++)
		{
			id[i]=i;
			sz[i]=1;
		}
		count=0;
	}
	
	public int count()
	{  return count;  }
	
	public boolean connected(int p,int q)
	{  return find(p)==find(q);  }
	
	public int find(int p)
	{
		int q=p;
		count++;
		while(q!=id[q])//find root
		{
			q=id[q];
			count+=2;
		}
		while(p!=q)//link the nodes encountered along the way directly to the root
		{
			id[p]=q;
			p=id[p];
			count+=2;
		}
		return q;
	}
		
	public void union(int p,int q)
	{
		int i=find(p);
		int j=find(q);
		
		if(i==j)	return;
		
		if(sz[i]<sz[j])
		{
			id[i]=j;
			sz[j]+=sz[i];
		}
		else
		{
			id[j]=i;
			sz[i]+=sz[j];
		}
		count+=5;
	}
	
	public static void main(String[] args)
	{
		StdDraw.setXscale(-50,650);
		StdDraw.setYscale(-100,1300);
		StdDraw.line(-50,0,650,0);
		StdDraw.line(0,-100,0,1300);
		StdDraw.setPenRadius(0.01);
		int N=StdIn.readInt();
		WeightedQuickUnionWithPathCompression wquwpc=new WeightedQuickUnionWithPathCompression(N);
		int i=0,cnt=0;
		while(!StdIn.isEmpty())
		{
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(wquwpc.connected(p,q))
				continue;
			wquwpc.union(p,q);
			StdDraw.point(i++,wquwpc.count()-cnt);
			StdDraw.show(10);
			cnt=wquwpc.count();
		}
	}
}