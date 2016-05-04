import java.util.Calendar;

public class CalendarDemo
{
	public static void main(String[] args)
	{
		Calendar c = Calendar.getInstance();
		System.out.println(c);
		
		c.set(2009,6,12);
		System.out.println(c);
				
		c.set(Calendar.YEAR,2008);
		System.out.println(c);
		
		c.set(Calendar.DATE,10);
		System.out.println(c);
		
		c.add(Calendar.DATE,-10);
		System.out.println(c);
		
		int year = c.get(Calendar.YEAR);

		int month = c.get(Calendar.MONTH);
		
		int day = c.get(Calendar.DAY_OF_WEEK);

		int date = c.get(Calendar.DATE);

		int hour = c.get(Calendar.HOUR_OF_DAY);

		int minute = c.get(Calendar.MINUTE);

		int second = c.get(Calendar.SECOND);
	}
}