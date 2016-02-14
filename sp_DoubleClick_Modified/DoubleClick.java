import java.awt.event.*;  
import java.util.*;
import javax.swing.JFrame;

public class DoubleClick implements MouseListener
{  
    private boolean flag=true;//show whether to start the next click judgment
    private int clickNum=0;//show the times of clicks
    Timer timer = new Timer();
   
    public void mouseClicked(MouseEvent e) 
	{
        clickNum++;
        timer.schedule(new MyTimerTask(),0,300);//0 ms delayed for the first run() and 300 ms interval between each run()
    }
	
	//non-important but class MouseListener required functions, leave it empty
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	
   
    class MyTimerTask extends TimerTask
    {  
		private final int clickInitial=clickNum;//mark when the timer starts
		private int clickPrevious=0;//initialize as an impossible value for clickNum       
		
		public void run()
        {
            if(clickPrevious!=0)
            {
				if(flag && clickInitial==clickNum)//after 300 ms, double click still not available, thus executing single click and stop the timer
				{
					mouseSingleClicked();    
					this.cancel();
					return;
				}
				else if(flag && clickPrevious<clickNum)//the first judgment of double click
				{
					mouseDoubleClicked();
					flag=false;
				}
				else if(clickPrevious==clickNum)//no clicks in the recent 300ms, so we can start the next click judgment
				{
					flag=true;
					this.cancel();
					return;
				}
				else//several clicks after a double click within 300ms
				{
					assert (flag==false && clickPrevious<clickNum);
				}
				
			}
			clickPrevious=clickNum;
        }  
    }
   
     
    public void mouseSingleClicked() 
	{ System.out.println("Single Clicked!"); }  
  
     
    public void mouseDoubleClicked()
	{ System.out.println("Double Clicked!"); }  
   
    public static void main(String[] args)
    {
		JFrame jf = new JFrame();
		jf.setSize(300,300);
		jf.setVisible(true);
		jf.addMouseListener(new DoubleClick());
    }
} 