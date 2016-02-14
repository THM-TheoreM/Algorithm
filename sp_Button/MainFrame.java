import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainFrame 
{
	private JFrame frame;
	private JPanel panel;
	private JButton button;
	
	MainFrame()
	{
		frame = new JFrame("gobang");

		panel = new JPanel(new BorderLayout());
		frame.add(panel,BorderLayout.SOUTH);
		panel.setBackground(Color.GRAY);
		panel.setLayout(new FlowLayout());
		
		button = new JButton("Press me");		
		panel.add(button);
        	
		frame.setSize(680,680);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	
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
