import edu.princeton.cs.algs4.*;

public class ErdosRenyi 
{
    public static int count(int N) 
	{
        int edges=0;
        UF uf=new UF(N);
        while(uf.count()>1)//more than one component
		{
            int i=StdRandom.uniform(N);
            int j=StdRandom.uniform(N);
            uf.union(i,j);
            edges++;
        }
        return edges;
    }

    public static void main(String[] args) 
	{
        for(int N=100;N<10000;N+=100)
		{
			int T = 1000;// number of trials
			int[] edges = new int[T];
			// repeat the experiment T times
			for (int t = 0; t < T; t++) 
				edges[t] = count(N);
			StdDraw.setXscale(0,10000);
			StdDraw.setYscale(200,50000);
			StdDraw.setPenRadius(0.01);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.point(N,0.5 * N * Math.log(N));
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.point(N,StdStats.mean(edges));
		}
    }
}