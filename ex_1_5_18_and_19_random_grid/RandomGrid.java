import edu.princeton.cs.algs4.*;

//Quesiton 1. The reason why we use Connection is that the generic type does not allow int[] as an argument, 
//            i.e.: " RandomBag<int[]> bag=new RandomBag<int[]>(); " is not allowed, or is there any method to realize it?
//Question 2. How can we use a private nested class Connection instead of a public class(a new data type) to stand for the return variable Connection[]?
//Question 3. The auxiliary class Connection and RandomBag should be copied in the class path, e.g.: put in cd /e/java/jdk/lib,
//            but theoretically, we have set classpath with ".;" at the beginning, and we just only need to put RandomGrid in the same folder, 
//            e.g.: /e/java/algorithm/ex_1_5_18_random_grid, what's wrong?
//Question 4. Am I wrong in the understanding of the question?
public class RandomGrid
{	
	public static Connection[] generate(int N)
	{
		//It is easy to prove that a N*N grid (x-label: 0~N, y-label: 0~N) has 2*N*(N+1) edges, and (N+1)^2 vertices, labelling from left to right, from bottom to top
		Connection[] connect=new Connection[2*N*(N+1)];int cnt=(N+1)*(N+1);
		RandomBag<Connection> bag=new RandomBag<Connection>();
		for(int i=1;i<cnt;i++)//for every vertice, adding its right and up edge, notice that the last vertice has no right nor up edge
		{
			if(i>=N*N+N+1)//the uppermost vertices
				bag.add(new Connection(i,i+1));
			else if(i%(N+1)==0)//the rightmost vertices
				bag.add(new Connection(i,i+N+1));
			else
			{
				bag.add(new Connection(i,i+1));
				bag.add(new Connection(i,i+N+1));
			}
		}
		int index=0;
		for(Connection tmp:bag)
			connect[index++]=tmp;
		return connect;
	}
	
	public static void main(String[] args)
	{
		int N=5;
		StdDraw.setXscale(-1,N+1);
		StdDraw.setYscale(-1,N+1);
		
		StdDraw.setPenRadius(0.01);
		for(int i=0;i<=N;i++)
			for(int j=0;j<=N;j++)
				StdDraw.point(i,j);
		
		Connection[] connect=generate(N);
		
		StdDraw.setPenRadius(0.001);
		for(int i=0;i<2*N*(N+1);i++)
		{
			int p=connect[i].getp(),q=connect[i].getq();
			StdDraw.line(p%(N+1),(p-1)/(N+1),q%(N+1),(q-1)/(N+1));
			StdDraw.show(200);
		}
	}
}