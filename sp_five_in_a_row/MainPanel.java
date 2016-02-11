import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainPanel extends Panel implements MouseListener
{
	private static final int COLUMN = 16;
	private static final int ROW = 16;
	private static final int GAP = 40;//the side length of a square
	private static boolean isBlack = true;
	private char[][] allChess= new char[ROW][COLUMN];//"*" for empty, "!" for black chess, "~" for white chess
	
	//constructor: using construtor in parent class MouseListener and set allChess to be empty for the additional character array
	public MainPanel()
	{
		super();
		for(int i=0;i<allChess.length;i++)
			for(int j=0;j<allChess[i].length;j++)
				allChess[i][j]='*';
	}
	
	//paint the chessboard as well as the chesses
    public void paint(Graphics g)
	{
		//draw chessboard first
    	for(int i=0;i<ROW;i++)
		{
    		g.setColor(Color.BLACK);
    		g.drawLine(20,20+i*GAP,640-20,20+i*GAP);
    	}
    	for(int i=0;i<COLUMN;i++)
		{
    		g.setColor(Color.BLACK);
    		g.drawLine(20+i*GAP,20,20+i*GAP,640-20);
    	}
		
		//draw chesses according to the array allChess
		//API: drawOval(int x,int y,int a,int b), draw an ellipse with center at (x,y) and major axis length=a while minor axis length=b
    	for(int i=0;i<allChess.length;i++)
    		for(int j=0;j<allChess[i].length;j++)
			{
    			if(allChess[i][j]=='~')
				{
    				g.setColor(Color.WHITE);
    				g.fillOval(5+i*40,5+j*40,30,30);
    				g.drawOval(5+i*40,5+j*40,30,30);
    			}
    			if(allChess[i][j]=='!'){
    				g.setColor(Color.BLACK);
    				g.fillOval(5+i*40,5+j*40,30,30);
    				g.drawOval(5+i*40,5+j*40,30,30);
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
	
	public void mousePressed(MouseEvent e)//add a chess and judge whether one side has won
	{
		//judge the array access in allChess according to the mouse position
		int click_x = e.getX();
		int click_y = e.getY();
		int chess_x = Math.round((float)(click_x-20)/GAP);
		int chess_y = Math.round((float)(click_y-20)/GAP);
		
        if(isBlack==true && allChess[chess_x][chess_y]=='*')
		{
		    allChess[chess_x][chess_y]='!';
		    isBlack = false;
        }
        if(isBlack==false && allChess[chess_x][chess_y]=='*')
		{
        	allChess[chess_x][chess_y] = '~';
        	isBlack = true;
        }
		
	    repaint();
	    
		if(isWin(chess_x,chess_y))
		{
	    	if(isBlack)//one side wins and now it should turn to the black side to drop a chess, thus the white side wins
	    	    JOptionPane.showMessageDialog(null,"white wins!");
	    	else
	    		JOptionPane.showMessageDialog(null,"black wins!");
	    	System.exit(0);
	    }
	}
	
	public void mouseClicked(MouseEvent e)  {}	
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e)  {}
	public void mouseExited(MouseEvent e)   {}
	
	public void setAllChess(char[][] allChess) 
	{ this.allChess = allChess; }
	
	public char[][] getAllChess() 
	{ return allChess; }
	
	public static void main(String[] args)  {}
}