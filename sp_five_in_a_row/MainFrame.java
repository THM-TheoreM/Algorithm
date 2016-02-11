import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame
{
	//constructor, using the constructor in parent class "JFrame"
	public MainFrame()
	{ super(); }
    public MainFrame(String str)
	{ super(str); }
	
    public static void main(String[] args) 
	{
		MainFrame frame = new MainFrame("五子棋");
		frame.setSize(680,680);
		frame.setVisible(true);
	    
		MainPanel panel = new MainPanel();
		panel.setBackground(Color.GRAY);
		frame.add(panel,BorderLayout.CENTER);//set panel in the center of the frame 
		panel.addMouseListener(panel);
	}
}
