import edu.princeton.cs.algs4.*;

public class RandomInteger
{
	public static void main(String[] args)
	{
		//The first test is to prove that the answer to the birthday problem(exercise 1.4.44) is √(pi*N/2)-1/3, but the question is how to prove it in mathematics???
		//In our program, the expectation=1*Pr(stop at the second number)+2*Pr(stop at the third number)+...+N*Pr(stop at the (N+1)th number),
		//                while Pr(stop at the ith number)=( A_N^(i-1)[permutation number] * (i-1) * N^(N-i+1) )/ N^(N+1),
		//                where the numerator means (choose i-1 different numbers from N numbers with permutation) * (the choice of the ith element) * (the freedom choice of the (i+1)~(N+1)th element)
		//In fact, I even can not prove that the sum of all probabilities equals one although this is also true with experiment, which is remarked in the following code.
		//On the other side, wiki https://en.wikipedia.org/wiki/Birthday_problem#cite_note-knuth73-18 gives another formula which can be tested also to be compatible with √(pi*N/2)-1/3,
		//                   how to prove the equivalence???
		for(int N=100;N<2000000;N*=2)
		{
			double sum=1.0/N,item=sum;
			for(int i=2;i<=N;i++)
			{
				item=item*Math.pow(i,2)*(N-i+1)/Math.pow(i-1,2)/N;
				//the following sentence can check whether the probabilities sum to 1
				//item=item*i*(N-i+1)/(i-1)/N;
				sum+=item;
			}
			StdOut.println(sum-Math.sqrt(Math.PI*N/2));
		}
		StdOut.println("----------------------------");
		
		//The second test is to prove the coupon collector problem(exercise 1.4.45), however it is too slow to wait for the answers with are still not accurate, 
		//																			 is improving the efficiency possible???
		//In mathematics, here is the prove: suppose T is a random variable that stands for the numbers we need to achieve N different numbers
		//									 t_i is a random variable that stands for the numbers we need to achieve the ith different numbers if we already have (i-1) different numbers
		//									 E(T)=E(t_1+t_2+t_3+...+t_N)=E(t_1)+E(t_2)+E(t_3)+...+E(t_N)
		//									 E(t_i)=1*Pr(using 1 number)+2*Pr(using 2 numbers)+3*Pr(using 2 numbers)...=1*(N-i)/N+2*(i/N)*(N-i)/N+3*(i/N)^2*(N-i)/N+...=N/(N-i)
		//									 E(T)=1+N/(N-1)+...+N/(N-(N-1))=N*H_N
		for(int N=100;N<2000;N*=2)
		{
			double sum=0;int cnt=0;
			boolean[] flag=new boolean[N];//flag[i]=false means i hasn't appeared, flag[i]=true means the opposite
			boolean index=false;//index=true means all 0~N-1 has appeared, index=false means the opposite
			for(int j=0;j<10000;j++)//repeated 10000 times to get average
			{
				//initialize
				for(int i=0;i<N;i++)
					flag[i]=false;
				index=false;
				
				while(!index)
				{
					flag[StdRandom.uniform(0,N)]=true;
					index=true;
					for(int i=0;i<N;i++)
					{
						index=index&flag[i];
						if(index==false)//just to save running time
							break;
					}
					cnt++;
				}
			}
			cnt=cnt/10000;
			
			double ref=0;//the reference answer N*H_N
			for(int i=1;i<=N;i++)
				ref+=1.0/i;
			ref*=N;
			StdOut.println(cnt-ref);
		}
	}
}