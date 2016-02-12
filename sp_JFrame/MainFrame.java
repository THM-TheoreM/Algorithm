import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainFrame
{
	JFrame frame;
	Container content;
	JPanel panel;
	JButton button;
	
	MainFrame()
	{initGUI();}
	
	private void initGUI()
	{
		frame = new JFrame("gobang");
		content = frame.getContentPane();
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		content.add(panel,BorderLayout.SOUTH);
		
		button = new JButton("Press me");  
		panel.add(button); 
		
		frame.setSize(680,680);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		myEvent();
    }
	
	private void myEvent()
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Click!");
			}
		});
    }
	
	public static void main(String[] args) 
	{new MainFrame();}
}
