import javax.swing.*;  
  
public class EmptyJFrame extends JFrame
{  
    public EmptyJFrame()
	{initGUI();}
	
    private void initGUI()
	{  
        setVisible(true);  
        setSize(300,400);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
    }
	
    public static void main(String[] args) 
	{  
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                EmptyJFrame f = new EmptyJFrame();  
            }                 
        });  
    }  
}  