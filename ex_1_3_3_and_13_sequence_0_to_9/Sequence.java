import edu.princeton.cs.algs4.*;

public class Sequence
{
	//judge whether a 0~9 sequence can be achieved through situable dequeue operations on an ordered 0~9 enqueued sequence
	public static boolean isLegalQueue(String str)
	{
		String[] s=str.split(" ");
		for(int i=0;i<10;i++)
			if(!s[i].equals(Integer.toString(i)))
				return false;
		return true;
	}
	
	
	//judge whether a 0~9 sequence can be achieved through situable pop operations on an ordered 0~9 pushed sequence
	public static boolean isLegalStack(String str)
	{
		String[] s=str.split(" ");
		int[] a=new int[10];
		for(int i=0;i<10;i++)
			a[i]=Integer.parseInt(s[i]);
		Stack<Integer> stack=new Stack<Integer>();
		int i=0;//index of the ordered sequence 0~9
		for(int j=0;j<10;j++)
		{
			if(i<=a[j])
			{
				while(i<=a[j])
					stack.push(i++);
				stack.pop();
			}
			else if(i>a[j])
			{
				if(stack.peek()!=a[j])
					return false;
				else
					stack.pop();
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		String str="0 1 2 3 4 5 6 7 8 9";
		StdOut.print("The sequence\t\""+str+"\"\tcan ");
		if(!isLegalQueue(str))
			StdOut.print("NOT ");
		StdOut.println("be achieved through a queue.");
		StdOut.println("----------------------");
		String[] s=new String[8];
		s[0]="4 3 2 1 0 9 8 7 6 5";
		s[1]="4 6 8 7 5 3 2 9 0 1";
		s[2]="2 5 6 7 4 8 9 3 1 0";
		s[3]="4 3 2 1 0 5 6 7 8 9";
		s[4]="1 2 3 4 5 6 9 8 7 0";
		s[5]="0 4 6 5 3 8 1 7 2 9";
		s[6]="1 4 7 9 8 6 5 3 0 2";
		s[7]="2 1 4 3 6 5 8 7 9 0";
		for(int i=0;i<8;i++)
		{
			StdOut.print("The sequence\t\""+s[i]+"\"\tcan ");
			if(!isLegalStack(s[i]))
				StdOut.print("NOT ");
			StdOut.println("be achieved through a stack.");
		}
	}
}