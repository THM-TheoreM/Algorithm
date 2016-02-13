import java.util.*;

//计秒每次2秒。一个简单的Timer和TimerTask的应用。

//继承抽象类TimerTask
class ScheduleRunTask extends TimerTask 
{
	//@override
	public void run() 
	{
		System.out.println("过了2秒");
	}
}

public class MyTimerTask
{
	Timer timer;
 
    public MyTimerTask(int delaytime) 
	{
        timer = new Timer();
        timer.schedule(new ScheduleRunTask(), 0, delaytime * 1000);
    }
	
	public static void main(String[] args) 
	{
        new MyTimerTask(2);
    }
}
