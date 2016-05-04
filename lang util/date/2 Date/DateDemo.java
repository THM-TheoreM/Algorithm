import java.util.Date;
import java.text.SimpleDateFormat;

public class DateDemo
{
	public static void main(String[] args)
	{
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("E yyy.MM.dd 'at' hh:mm:ss a zzz");
		System.out.print(ft.format(dNow));
	}
}