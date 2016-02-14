import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FiveInARow extends JPanel
{
	private JButton button1;//restart button
	private JButton button2;//restep button
	
	private int x_previous;//x-axis of the previous step
	private int y_previous;//y-axis of the previous step
	
	private static final int COLUMN = 16;
	private static final int ROW = 16;
	private static final int GAP = 40;//the side length of a square
	private static boolean isBlack = true;
	private char[][] allChess= new char[ROW][COLUMN];//"*" for empty, "!" for black chess, "~" for white chess
	
	//constructor: using construtor in parent class MouseListener and set allChess to be empty for the additional character array
	public FiveInARow()
	{
		super();
		
		setBackground(Color.GRAY);
		setLayout(null);
		
		JButton button1=new JButton("restart");
		button1.setBounds(GAP*(ROW-1)/4,GAP/4,GAP*3,GAP/2);//API: setBounds(int x,int y,int width,int height) with (x,y) being the coordinate of the up left corner of the panel 
		add(button1);
		
		JButton button2=new JButton("restep");
		button2.setBounds(GAP*(ROW-1)/4*3,GAP/4,GAP*3,GAP/2);
		add(button2);
		
		for(int i=0;i<allChess.length;i++)
			for(int j=0;j<allChess[i].length;j++)
				allChess[i][j]='*';
			
		addMouseListener(new MouseListener()
		{
			public void mousePressed(MouseEvent e)//add a chess and judge whether one side has won
			{
				//judge the array access in allChess according to the mouse position
				int x = Math.round((float)e.getX()/GAP-1);
				int y = Math.round((float)e.getY()/GAP-1);
		
				if(x>=0 && x<ROW && y>=0 && y<COLUMN)//avoid array index out of bound
				{
					x_previous=x;
					y_previous=y;
					
					if(isBlack==true && allChess[x][y]=='*')
					{
						allChess[x][y]='!';
						isBlack = false;
					}
					if(isBlack==false && allChess[x][y]=='*')
					{
						allChess[x][y] = '~';
						isBlack = true;
					}
		
					repaint();
			
					if(isWin(x,y))
					{
						if(isBlack)//one side wins and now it should turn to the black side to drop a chess, thus the white side wins
							JOptionPane.showMessageDialog(null,"white wins!");
						else
							JOptionPane.showMessageDialog(null,"black wins!");
						System.exit(0);
					}
				}
			}
		
			public void mouseClicked(MouseEvent e)  {}	
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e)  {}
			public void mouseExited(MouseEvent e)   {}
		});
		
		button1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				for(int i=0;i<allChess.length;i++)
					for(int j=0;j<allChess[i].length;j++)
						allChess[i][j]='*';
				
				isBlack=true;
					
				repaint();
			}
		});
		
		button2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(allChess[x_previous][y_previous]!='*')
				{
					allChess[x_previous][y_previous]='*';
					if(isBlack) isBlack=false;
					else        isBlack=true;
				}
				
				repaint();
			}
		});
	}
	
	public static int getCOLUMN()
	{ return COLUMN;}
	
	public static int getROW()
	{ return ROW;}
	
	public static int getGAP()
	{ return GAP;}
	
	//paint the chessboard as well as the chesses
	//NOTICE: the positive direction of x-axis is right while the positive direction of y-axis is down, and the origin point (0,0) is at the top left corner
    public void paint(Graphics g)
	{
		super.paint(g);
		
		//draw chessboard first
    	for(int i=0;i<ROW;i++)
		{
    		g.setColor(Color.BLACK);
    		g.drawLine(GAP,(i+1)*GAP,COLUMN*GAP,(i+1)*GAP);
    	}
    	for(int i=0;i<COLUMN;i++)
		{
    		g.setColor(Color.BLACK);
    		g.drawLine((i+1)*GAP,GAP,(i+1)*GAP,ROW*GAP);
    	}
		
		//draw chesses according to the array allChess
		//API: drawOval(int x,int y,int a,int b), draw an ellipse with center at (x+a/2,y+b/2) and major axis length=a while minor axis length=b
    	for(int i=0;i<allChess.length;i++)
    		for(int j=0;j<allChess[i].length;j++)
			{
    			if(allChess[i][j]=='~')
				{
    				g.setColor(Color.WHITE);
    				g.fillOval((i+1)*GAP-GAP/4*3/2,(j+1)*GAP-GAP/4*3/2,GAP/4*3,GAP/4*3);
    			}
    			if(allChess[i][j]=='!')
				{
    				g.setColor(Color.BLACK);
    				g.fillOval((i+1)*GAP-GAP/4*3/2,(j+1)*GAP-GAP/4*3/2,GAP/4*3,GAP/4*3);
    			}
    		}
    }
	
	//auxiliary function, judging whether one side wins after allChess[x][y] is put
    private boolean isWin(int x,int y)
	{
    	char ch=allChess[x][y];
		int tmp_x,tmp_y;
    
		//horizon max
		int LRNum=1;
    	tmp_x=x-1;   	
		while(tmp_x>=0 && allChess[tmp_x--][y]==ch)
		{ LRNum++; }
		tmp_x=x+1; 
		while(tmp_x<ROW && allChess[tmp_x++][y]==ch)
		{ LRNum++; }
		
		//vertical max
    	int UDNum=1;
    	tmp_y=y-1;  	
		while(tmp_y>=0 && allChess[x][tmp_y--]==ch)
		{ UDNum++; }
		tmp_y=y+1;  
		while(tmp_y<COLUMN && allChess[x][tmp_y++]==ch)
		{ UDNum++; }
		
		//maxium from southwest to northeast
    	int SWNENum=1;
    	tmp_x=x-1;tmp_y=y-1;    	
		while(tmp_x>=0 && tmp_y>=0 && allChess[tmp_x--][tmp_y--]==ch)
		{ SWNENum++; }
		tmp_x=x+1;tmp_y=y+1;
		while(tmp_x<ROW && tmp_y<COLUMN && allChess[tmp_x++][tmp_y++]==ch)
		{ SWNENum++; }
		
		//maxium from southeast to northwest
    	int SENWNum=1;
    	tmp_x=x+1;tmp_y=y-1;    	
		while(tmp_x<ROW && tmp_y>=0 && allChess[tmp_x++][tmp_y--]==ch)
		{ SENWNum++; }
		tmp_x=x-1;tmp_y=y+1;
		while(tmp_x>=0 && tmp_y<COLUMN && allChess[tmp_x--][tmp_y++]==ch)
		{ SENWNum++; }
		
		//win condition: one of the 4 directions above reaches 5 chesses in a row
    	if(LRNum>=5||UDNum>=5||SWNENum>=5||SENWNum>=5)                                            
    		return true;
    	return false;
    } 
	
	public static void main(String[] args)
	{
		JFrame jf=new JFrame("Five-in-a-row");
		FiveInARow panel=new FiveInARow();
		
		jf.add(panel);
		jf.setSize(FiveInARow.getROW()*FiveInARow.getGAP()+50,FiveInARow.getCOLUMN()*FiveInARow.getGAP()+50);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
	}
}