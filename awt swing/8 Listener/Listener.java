import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;

public class Listener
{
	public static void main(String[] args) 
	{
		int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		JFrame jf = new JFrame();
		jf.setTitle("Listener");
		jf.setSize(500,300);
		jf.setVisible(true);
		jf.setLocation((screen_width - 500) / 2, (screen_height - 300) / 2);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf.addMouseMotionListener(new MouseMotionListener()
		{
			public void mouseMoved(MouseEvent e)
			{
				int x=0;
				int y=0;

				x=e.getX();
				y=e.getY();
				System.out.println(x+" "+y);
			}
			
			public void mouseDragged(MouseEvent e){}
		});
		
		jf.addMouseListener(new MouseListener() //按鼠标
		{
			public void mouseClicked(MouseEvent e) 
			{
				switch(e.getModifiers())
				{
					case InputEvent.BUTTON3_MASK: JOptionPane.showMessageDialog(jf, "Right click： "+e.getX()+" "+e.getY()); break;
					case InputEvent.BUTTON2_MASK: JOptionPane.showMessageDialog(jf, "Mouse wheel press： "+e.getX()+" "+e.getY()); break;
					case InputEvent.BUTTON1_MASK: JOptionPane.showMessageDialog(jf, "Left click： "+e.getX()+" "+e.getY()); break;
				}
			}
			
			public void mouseExited(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
		});
		
		jf.addKeyListener(new KeyListener() //按键盘
		{
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_UP: JOptionPane.showMessageDialog(jf, "press: up"); break;
					case KeyEvent.VK_DOWN: JOptionPane.showMessageDialog(jf, "press: down"); break;
					case KeyEvent.VK_LEFT: JOptionPane.showMessageDialog(jf, "press: left"); break;
					case KeyEvent.VK_RIGHT: JOptionPane.showMessageDialog(jf, "press: right"); break;
				}
			}
			
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
		});
	}
}