import edu.princeton.cs.algs4.*;

public class SnakeEatApple
{
	public static void main(String[] args)
	{
		//initialize window
		StdDraw.setXscale(0,16);
		StdDraw.setYscale(0,11);
		StdDraw.rectangle(7.5,5,7.5,5);
		
		//initialize apple
		double X=4.5;
		double Y=7.5;
		
		//initialize snake
		double[] x={2.5,1.5,0.5,0,0,0,0,0,0};
		double[] y={0.5,0.5,0.5,0,0,0,0,0,0};
        
		//initialize input
		char a='1';
		
		int index1=0; //control input index
		int index2=0; //control length of snake and win index
		int index3=0; //control lose index
		
		while(true)
		{
			//control time
            for(int i=0;i<100000000;i++)	
	
            //draw snake
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledRectangle(7.5,5,7.5,4.99);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledRectangle(X,Y,0.5,0.5);
			for(int i=0;i<3+index2;i++)
				StdDraw.filledRectangle(x[i],y[i],0.5,0.5);
			
			//move snake
            a='1';
			index1=0;
			if(StdDraw.hasNextKeyTyped()==true) a=StdDraw.nextKeyTyped();
			
			double x1=x[1];
			double y1=y[1];
			double x2=x[2+index2];
			double y2=y[2+index2];
			
			for(int i=2+index2;i>0;i--)
			{x[i]=x[i-1];y[i]=y[i-1];}
			
			if(a=='w' && y1!=y[0]+1)
			{y[0]++;index1=1;}
			
			if(a=='a' && x1!=x[0]-1)
			{x[0]--;index1=1;}
			
			if(a=='s' && y1!=y[0]-1)
			{y[0]--;index1=1;}
			
			if(a=='d' && x1!=x[0]+1)
			{x[0]++;index1=1;}
				
			if(a=='1' || (a!='w' && a!='a' && a!='s' && a!='d') || index1==0)
			{x[0]=2*x[0]-x1;y[0]=2*y[0]-y1;}
		
		    if(x[0]==X && y[0]==Y)
			{x[2+index2]=x2;y[2+index2]=y2;X=StdRandom.uniform(14)+0.5;Y=StdRandom.uniform(9)+0.5;index2++;}
			
			//judge whether win
			if(index2==7)
			{StdOut.println("win");break;}
			
			//judge whether lose
			for(int i=1;i<3;i++)
				if(x[i]==x[0] && y[i]==y[0]) index3=1;
			
			if(x[0]<0 || x[0]>15 || y[0]<0 || y[0]>10 || index3==1)
		    {StdOut.println("lose");break;}
		}
	}
}