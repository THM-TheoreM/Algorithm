import javax.swing.*;  

public class EmptyJFrame extends JFrame
{  
    public static void main(String[] args) 
	{  
        EmptyJFrame f = new EmptyJFrame(); //创建一个对象  
        f.setVisible(true); //显示一个有关闭，最小化，最大化按钮的Frame 
        f.setSize(300,400); //指定Frame的大小 
        f.setLocationRelativeTo(null); //指定Frame的位置（中间）  
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //如果没有这一句，关闭Frame之后程序其实还在执行状态，加上这一句才算是真正的把资源释放掉了 
    }  
}  
