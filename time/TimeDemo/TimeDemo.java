import java.time.*;

public class TimeDemo
{
	public static void main(String[] args)
	{
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);
		
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);
		
		localDateTime = localTime.atDate(localDate);
		System.out.println(localDateTime);
		
		localDateTime = localDate.atTime(localTime);
		System.out.println(localDateTime);
		System.out.println();
		
		Year year = Year.now();
		System.out.println(year);
		
		year = Year.of(2012);
		System.out.println(year);
		
		localDate = year.atDay(59);
		System.out.println(localDate);
		
		localDateTime = localDate.atTime(localTime);
		System.out.println(localDateTime);
		
		YearMonth yearMonth = year.atMonth(2);
		System.out.println(yearMonth);
		
		localDate = yearMonth.atDay(29);
		System.out.println(localDate);
		
		localDateTime = localDate.atTime(localTime);
		System.out.println(localDateTime);
		
		System.out.println(localDate.isLeapYear());
	}
}