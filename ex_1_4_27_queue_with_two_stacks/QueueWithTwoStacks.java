import edu.princeton.cs.algs4.*;

//Question: How to understand "each queue operation takes a constant amortized number of stack operations"? Or to be precise, the word "amortized"
//In this exercise, from my understanding:
//if you start from an empty stack, enqueue N times in a row, you will use N in.push, and thus N/N=1 operation per enqueue
//if you start from a stack with N Items, dequeue N times in a row, you will use N in.pop + N out.push + 1 out.pop, and thus (2N+1)/N~2 operations per dequeue
//is this kind of understanding correct? even if the understanding is correct, how to understand the Proof sketch of Proposition E on page 199 in the textbook?
//and also the "amortized" in exercise 1.4.32?
public class QueueWithTwoStacks<Item>
{
	Stack<Item> in=new Stack<Item>();
	Stack<Item> out=new Stack<Item>();
	int N=0;
	
	public boolean isEmpty()	{return N==0;}
	
	public void enqueue(Item item)
	{
		while(!out.isEmpty())
			in.push(out.pop());				
		in.push(item);
		N++;
	}
	
	public Item dequeue()
	{
		if(N==0)
			throw new RuntimeException("No element in the queue, fail in dequeue.");
		while(!in.isEmpty())
			out.push(in.pop());
		N--;
		return out.pop();
	}
	
	public static void main(String[] args)
	{
		QueueWithTwoStacks<Integer> q=new QueueWithTwoStacks<Integer>();
		for(int i=0;i<10;i++)
			q.enqueue(2*i+1);
		while(!q.isEmpty())
			StdOut.println(q.dequeue());
	}
}