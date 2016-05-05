import java.util.*;

//控制计秒次数4次。由于这时候timer.cancel()要放到ScheduleRunTask类的里面，所以ScheduleRunTask类不能孤立的写在MyTimerTask2之外。

public class MyTimerTask2
{
	private int num=0;
	Timer timer;
 
    public MyTimerTask2(int delaytime) 
	{
        timer = new Timer();
        timer.schedule(new ScheduleRunTask(), 0, delaytime * 1000);
    }
	
	class ScheduleRunTask extends TimerTask 
	{
		public void run() 
		{
			System.out.println("过了2秒");
			num++;
			if(num==4) timer.cancel();
		}
	}
    
	public static void main(String[] args) 
	{
        new MyTimerTask2(2);
    }
}
