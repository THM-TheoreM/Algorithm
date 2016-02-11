import java.awt.event.*;  
import java.util.*;
import javax.swing.JFrame;

public class DoubleClick implements MouseListener
{  
    private boolean flag=false;//show whether single or double click has been executed
    private int clickNum=0;//show whether double click should be executed
    private MouseEvent me=null;//in order to use "MouseEvent" in class "TimerTask"
    Timer timer = new Timer();
   
    public void mouseClicked(MouseEvent e) 
	{
        me=e;
        this.flag=false; 
 
        if(this.clickNum==1)
        {  
            this.mouseDoubleClicked(me);  
            this.clickNum=0; 
            this.flag = true;
            return;  
        }
		
        timer.schedule(new MyTimerTask(),0,300);//0 ms delayed for the first run() and 300 ms interval between each run()
    }
	
	//non-important but class MouseListener required functions, leave it empty
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	
   
    class MyTimerTask extends TimerTask
    {  
        public void run()
        {  
            if(flag)//single or double click has been executed, thus stop the timer 
            {
				this.cancel();
				return;
            }  
            if(clickNum==1)//after 300 ms, double click still not available, thus executing single click and stop the timer
            {
				mouseSingleClicked(me);  
				flag=true;  
				this.cancel();
				clickNum=0;
				return;
            }  
            clickNum++;
        }  
    }
   
     
    public void mouseSingleClicked(MouseEvent e) 
	{ System.out.println("Single Clicked!"); }  
  
     
    public void mouseDoubleClicked(MouseEvent e)
	{ System.out.println("Double Clicked!"); }  
   
    public static void main(String[] args)
    {
		JFrame jf = new JFrame();
		jf.setSize(300,300);
		jf.setVisible(true);
		jf.addMouseListener(new DoubleClick());
    }
} 