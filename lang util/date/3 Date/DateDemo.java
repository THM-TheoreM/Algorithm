import java.util.Date;

public class DateDemo
{
	public static void main(String[] args)
	{
		Date date = new Date();
		
		String str = String.format("%1$s %2$tB %2$td, %2$tY", "Due date:", date);
		System.out.println(str);
		
		str = String.format("%s %tB %<td, %<tY", "Due date:", date);
		System.out.println(str);
		
		System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", date);
		System.out.println();
		
		System.out.printf("%s %tB %<td, %<tY", "Due date:", date);
		System.out.println();
	}
}