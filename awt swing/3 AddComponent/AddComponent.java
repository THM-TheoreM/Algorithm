import javax.swing.*;

public class AddComponent extends JFrame
{  
    public AddComponent()
	{initGUI();}  
    
	private void initGUI()
	{  
        setVisible(true);  
        setSize(300,400);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  

        JButton jButton1 = new JButton("button"); //创建按钮   
        add(jButton1); //添加按钮	
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