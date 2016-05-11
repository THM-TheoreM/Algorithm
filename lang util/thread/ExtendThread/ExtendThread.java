class NewThread extends Thread
{
	NewThread()
	{
		super("Demo Thread");
		System.out.println("child thread:"+this);
		start();
	}
	
	public void run()
	{
		try{
			for(int i=5;i>0;i--)
			{
				System.out.println("child thread:"+i);
				Thread.sleep(50);
			}
		} catch(InterruptedException e){
			System.out.println("child thread interrupted.");}
			
		System.out.println("existing child thread.");
	}
}

public class ExtendThread
{
	public static void main(String[] args)
	{
		new NewThread();
		
		try{
			for(int i=5;i>0;i--)
			{
				System.out.println("main thread:"+i);
				Thread.sleep(100);
			} 
		} catch(InterruptedException e){
			System.out.println("main thread interrupted.");}
		
		System.out.println("existing main thread.");
	}
}