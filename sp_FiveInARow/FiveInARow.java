import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FiveInARow extends JPanel
{
	private JButton button1;
	private JButton button2;
	
	private static final int COLUMN=16;
	private static final int ROW=16;
	private static boolean isBlack=true;
	private char[][] allChess=new char[ROW][COLUMN]; //"*" for empty, "!" for black chess, "~" for white chess
	
	private int suit_x;
	private int suit_y;

	//constructor
	public FiveInARow()
	{
		setBackground(Color.GRAY);
		setLayout(null);
		
		JButton button1=new JButton("restart");
		button1.setBounds(20,700,100,50);
		add(button1);
		
		JButton button2=new JButton("restep");
		button2.setBounds(160,700,100,50);
		add(button2);
		
		
		for(int i=0;i<allChess.length;i++)
			for(int j=0;j<allChess[i].length;j++)
				allChess[i][j]='*';
	
		addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e){}	
			public void mouseReleased(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mousePressed(MouseEvent e)
			{
				int click_x = e.getX();
				int click_y = e.getY();
				int chess_x = Math.round((float)(click_x-20)/40);
				int chess_y = Math.round((float)(click_y-20)/40);
				
				if(chess_x<ROW && chess_y<COLUMN && allChess[chess_x][chess_y]=='*')
				{
					suit_x=chess_x;
					suit_y=chess_y;
					
					if(isBlack) 
					{
						allChess[chess_x][chess_y]='!';
						isBlack=false;
					}
					else
					{
						allChess[chess_x][chess_y]='~';
						isBlack=true;
					}
					
					repaint();

					if(isWin(chess_x,chess_y))
					{
						if(isBlack) JOptionPane.showMessageDialog(null,"white wins!");
						else        JOptionPane.showMessageDialog(null,"black wins!");
						System.exit(0);
					}
				}
			}
		});
		
		button1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				for(int i=0;i<allChess.length;i++)
					for(int j=0;j<allChess[i].length;j++)
						allChess[i][j]='*';
					
				repaint();
			}
		});
		
		button2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(allChess[suit_x][suit_y]!='*')
				{
					allChess[suit_x][suit_y]='*';
					if(isBlack) isBlack=false;
					else        isBlack=true;
				}
				
				repaint();
			}
		});
	}

	//draw chessboard
    public void paint(Graphics g)
	{
		super.paint(g);
		
		//draw horizontal line
		for(int i=0;i<16;i++)
			g.drawLine(20,20+i*40,620,20+i*40);
		
		//draw vertical line
    	for(int i=0;i<COLUMN;i++)
    		g.drawLine(20+i*40,20,20+i*40,620);
		
		//draw chess
    	for(int i=0;i<allChess.length;i++)
    		for(int j=0;j<allChess[i].length;j++)
			{
    			if(allChess[i][j]=='~')
				{
					g.setColor(Color.WHITE);
					g.fillOval(5+i*40,5+j*40,30,30);
				}
    			if(allChess[i][j]=='!')
				{
    				g.setColor(Color.BLACK);
    				g.fillOval(5+i*40,5+j*40,30,30);
    			}
    		}
    }
	
	//whether win
    private boolean isWin(int x,int y)
	{
    	char ch=allChess[x][y];
		int tmp_x,tmp_y;
    
		//horizontal max
		int LRNum=1;
    	tmp_x=x-1;   	
		while(tmp_x>=0 && allChess[tmp_x--][y]==ch)
		{LRNum++;}
		tmp_x=x+1; 
		while(tmp_x<ROW && allChess[tmp_x++][y]==ch)
		{LRNum++;}
		
		//vertical max
    	int UDNum=1;
    	tmp_y=y-1;  	
		while(tmp_y>=0 && allChess[x][tmp_y--]==ch)
		{UDNum++;}
		tmp_y=y+1;  
		while(tmp_y<COLUMN && allChess[x][tmp_y++]==ch)
		{UDNum++;}
		
		//maxium from southwest to northeast
    	int SWNENum=1;
    	tmp_x=x-1;tmp_y=y-1;    	
		while(tmp_x>=0 && tmp_y>=0 && allChess[tmp_x--][tmp_y--]==ch)
		{SWNENum++;}
		tmp_x=x+1;tmp_y=y+1;
		while(tmp_x<ROW && tmp_y<COLUMN && allChess[tmp_x++][tmp_y++]==ch)
		{SWNENum++;}
		
		//maxium from southeast to northwest
    	int SENWNum=1;
    	tmp_x=x+1;tmp_y=y-1;    	
		while(tmp_x<ROW && tmp_y>=0 && allChess[tmp_x++][tmp_y--]==ch)
		{SENWNum++;}
		tmp_x=x-1;tmp_y=y+1;
		while(tmp_x>=0 && tmp_y<COLUMN && allChess[tmp_x--][tmp_y++]==ch)
		{SENWNum++;}
		
		//win condition: one of the 4 directions above reaches 5 chesses in a row
    	if(LRNum>=5||UDNum>=5||SWNENum>=5||SENWNum>=5) return true;
    	return false;
    } 
	
	public static void main(String[] args)
	{
		JFrame jf=new JFrame("gobang");
		FiveInARow panel=new FiveInARow();
		
		jf.add(panel);
		jf.setSize(800,800);
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}