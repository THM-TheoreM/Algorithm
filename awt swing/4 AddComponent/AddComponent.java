import javax.swing.*;

public class AddComponent extends JFrame
{
	private JButton jButton;  
    
	public AddComponent()
	{initGUI();addComp();}  

    private void initGUI()
	{  
        setVisible(true);  
        setSize(300,400);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
    }  
	
	private void addComp()
	{  
        jButton = new JButton("button");  
        add(jButton);  
    }
	
    public static void main(String[] args) 
	{  
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                AddComponent f = new AddComponent();  
            }                 
        });  
    }  
}  