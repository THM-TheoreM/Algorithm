//used to set the frame into the center of the screen instead of the upleft corner of the screen
//import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class Listener
{
	public static void main(String[] args) 
	{
		JFrame jf = new JFrame();

		
		//int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
		//int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
		//jf.setLocation((screen_width - 500) / 2, (screen_height - 300) / 2); //set location to be the center of the screen
		jf.setTitle("Mouse and Keyboard Listener");
		jf.setSize(500, 300);
		jf.setResizable(false);//judging if the frame can be resizable
		jf.setVisible(true);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close the frame(instead of hidding into the background) if clicking the close button on the upright corner of the frame
		
		
		jf.addMouseListener
		(
			new MouseListener() 
			{
				public void mouseClicked(MouseEvent e) 
				{
					switch(e.getModifiers())
					{
						case InputEvent.BUTTON3_MASK: JOptionPane.showMessageDialog(jf, "Right click： "+e.getX()+" "+e.getY()); break;
						case InputEvent.BUTTON2_MASK: JOptionPane.showMessageDialog(jf, "Mouse wheel press： "+e.getX()+" "+e.getY()); break;
						case InputEvent.BUTTON1_MASK: JOptionPane.showMessageDialog(jf, "Left click： "+e.getX()+" "+e.getY()); break;
						//the following code may seem to differ single click from double click, however, each double click consists of two single clicks,
						//thus if we both have single click event and double click event, the latter will be ignored
						/*{
							if(e.getClickCount()>=2)
								Timer end=new Timer();
								JOptionPane.showMessageDialog(jf, "double left-click： "+e.getX()+" "+e.getY());
							else
								JOptionPane.showMessageDialog(jf, "single left-click： "+e.getX()+" "+e.getY());
							break;
						}*/
						/*JOptionPane.showMessageDialog(jf, "click");*/
					}
				}
				public void mouseExited(MouseEvent e) 
				{ /*JOptionPane.showMessageDialog(jf, "mouse leaves away from the frame");*/ }
				public void mouseEntered(MouseEvent e) 
				{ /*JOptionPane.showMessageDialog(jf, "mouse enter the frame");*/ }
				public void mouseReleased(MouseEvent e) 
				{ /*JOptionPane.showMessageDialog(jf, "mouse released");*/ }
				public void mousePressed(MouseEvent e) 
				{ /*JOptionPane.showMessageDialog(jf, "mouse press");*/ }
			}
		);
		
		
		jf.addMouseMotionListener
		(
			new MouseMotionListener()
			{
				public void mouseMoved(MouseEvent e) 
				{ /*JOptionPane.showMessageDialog(jf, "mouse move");*/ }
				public void mouseDragged(MouseEvent e) 
				{ /*JOptionPane.showMessageDialog(jf, "mouse dragged");*/ }
			}
		);
		
		
		jf.addKeyListener
		(
		    new KeyListener()
			{
				public void keyReleased(KeyEvent e)
				{ /*JOptionPane.showMessageDialog(jf, "release a key");*/ }
				public void keyPressed(KeyEvent e)
				{
					switch(e.getKeyCode())
					{
						case KeyEvent.VK_UP: JOptionPane.showMessageDialog(jf, "press: up"); break;
						case KeyEvent.VK_DOWN: JOptionPane.showMessageDialog(jf, "press: down"); break;
						case KeyEvent.VK_LEFT: JOptionPane.showMessageDialog(jf, "press: left"); break;
						case KeyEvent.VK_RIGHT: JOptionPane.showMessageDialog(jf, "press: right"); break;
					}
					/*
					VK_HOME         Home                  VK_CONTROL		  Ctrl
					VK_END          End                   VK_SHIFT			  Shift
					VK_PGUP         page up               VK_BACK_SPACE       BackSpace
					VK_PGDN         page down             VK_CAPS_LOCK        CapsLock
					VK_UP           up arrow              VK_NUM_LOCK         NumLock
					VK_DOWN         down arrow            VK_ENTER            enter
					VK_LEFT         left arrow            VK_UNDEFINED        other unknown keys
					VK_RIGHT      	right arrow           VK_F1--VK_F12       F1 -- F12
					VK_ESCAPE      	Esc                   VK_0 --VK_9         0  -- 9
					VK_TAB			Tab                   VK_A --VK_Z         A  -- Z
					VK_ALT			Alt
					*/
				}
				public void keyTyped(KeyEvent e)//different from "keyPressed", think about the case when you type a capital letter using "shift"
				{ /*JOptionPane.showMessageDialog(jf, "used when releasing a pressed key");*/ }
			}
		);
	}
}