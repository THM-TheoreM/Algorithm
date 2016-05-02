import edu.princeton.cs.algs4.*;

public class UnionFind
{
	public static void main(String[] args)
	{
		StdDraw.setXscale(-50,650);
		StdDraw.setYscale(-100,1300);
		StdDraw.line(-50,0,650,0);
		StdDraw.line(0,-100,0,1300);
		StdDraw.setPenRadius(0.005);
		int N=StdIn.readInt();
		
		QuickFind qf=new QuickFind(N);
		QuickUnion qu=new QuickUnion(N);
		WeightedQuickUnion wqu=new WeightedQuickUnion(N);
		WeightedQuickUnionWithPathCompression wquwpc=new WeightedQuickUnionWithPathCompression(N);
		
		int i=0;int[] cnt=new int[4];
		while(!StdIn.isEmpty())
		{
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(qf.connected(p,q))
				continue;
			
			qf.union(p,q);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.point(i,qf.count()-cnt[0]);
			cnt[0]=qf.count();
			
			qu.union(p,q);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.point(i,qu.count()-cnt[1]);
			cnt[1]=qu.count();
			
			wqu.union(p,q);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.point(i,wqu.count()-cnt[2]);
			cnt[2]=wqu.count();
			
			wquwpc.union(p,q);
			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.point(i,wquwpc.count()-cnt[3]);
			cnt[3]=wquwpc.count();
			
			StdDraw.show(10);
			i++;
		}
	}
}