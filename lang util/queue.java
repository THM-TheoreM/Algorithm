import java.util.*;

/*
 * Queue (interface) extends Collection
 * AbstractQueue (abstract class) extends AbstractCollection implements Queue
 * Deque (interface) extends Queue
 * ArrayDeque extends AbstractCollection implements Deque
 * http://www.yiibai.com/java/util/java_util_arraydeque.html
 */
 
public class queue
{
	public static void main(String[] args)
	{
		ArrayDeque<Integer> a=new ArrayDeque<Integer>();
		
		for(int i=0;i<20;i++)
			a.offer(i);
		
		for(int i=0;i<20;i++)
			System.out.print(a.poll()+" ");
	}
}