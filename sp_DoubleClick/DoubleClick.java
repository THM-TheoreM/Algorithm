import java.util.*;
import java.awt.*;
import java.awt.event.*;  
import javax.swing.JFrame;

public class DoubleClick implements MouseListener
{
	private int clickNum=0;
    private boolean flag=false; 
	private int N=0;
    Timer timer=new Timer();

    class MyTimerTask extends TimerTask
    {
		private int n;
		private int index=0;
		
		MyTimerTask(int N)
		{n=N-1;}
		
        public void run()
        {  
            if(flag)
            {
				this.cancel();
				return;
			}
			
			if(n==N)
            {
				mouseSingleClicked();
				clickNum=0;
				this.cancel();
				return;
            } 
			
			if(index==0)
			{
				n++;
				clickNum++;
				index++;
			}
			else
			{
				this.cancel();
				return;
			}
		}  
    }
	
    public void mouseClicked(MouseEvent e) 
	{
		flag=false;
		
        if(clickNum==1)
        {  
            mouseDoubleClicked();
			clickNum=0;
            flag=true;
        }
		else 
		{
			N++;
			timer.schedule(new MyTimerTask(N),0,300);
		}
    }
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	
	public void mouseSingleClicked() 
	{System.out.println("Single Clicked!");}  
    public void mouseDoubleClicked()
	{System.out.println("Double Clicked!");} 
	
    public static void main(String[] args)
    {
		JFrame jf = new JFrame();
		jf.setSize(300,300);
		jf.setVisible(true);
		jf.addMouseListener(new DoubleClick());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
} 