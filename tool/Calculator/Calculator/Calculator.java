import edu.princeton.cs.algs4.*;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Calculator extends JFrame
{
	private final int ROW=10;
	private final int COL=4;
	private final int WINDOW_WIDTH=300;
	private final int WINDOW_HEIGHT=400;
	private final String[] keyName={"Del" , "<"   , ">"    ,  "CE"   ,
									"asin",	"acos", "atan" ,  "neg"  ,
									"sin" ,	"cos" ,	"tan"  ,  "round",
									"sinh", "cosh", "tanh" ,  "ceil" ,
						            "pow" , "log" ,	"mod"  ,  ","    ,
									"P"   , "C"   , "("    ,  ")"    ,	
									"7"   ,	"8"   ,	"9"    ,  "+"    ,
									"4"   ,	"5"   ,	"6"    ,  "-"    ,
									"1"   ,	"2"   ,	"3"    ,  "*"    ,
									"0"   ,	"."   ,	"="    ,  "/"   };
	
	private JTextField textfield=new JTextField("_");
	private JTextField emptyfield=new JTextField();
	private JTextField ansfield=new JTextField("0");
	private JButton[] buttons=new JButton[keyName.length];
	private String expression="_";
	private int currentPosition=0;//index of "_"
	
	public Calculator()
	{
		super("Calculator");
		
		//define textfield panel
		textfield.setEditable(false);
		textfield.setBackground(Color.WHITE);
		textfield.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.isShiftDown())
				{
					switch(e.getKeyCode())
					{
						case KeyEvent.VK_EQUALS:		handleChar("+");  	break;
						case KeyEvent.VK_8: 			handleChar("*");  	break;
						case KeyEvent.VK_9: 			handleChar("(");  	break;
						case KeyEvent.VK_0: 			handleChar(")");  	break;
					}
				}
				else
				{
					switch(e.getKeyCode())
					{
						case KeyEvent.VK_LEFT:  		handleLeft();		break;
						case KeyEvent.VK_RIGHT: 		handleRight();  	break;
						case KeyEvent.VK_BACK_SPACE:	handleDel();  		break;
						case KeyEvent.VK_ENTER:			handleEquals();  	break;
						case KeyEvent.VK_EQUALS:		handleEquals();  	break;
						case KeyEvent.VK_COMMA: 		handleChar(",");	break;
						case KeyEvent.VK_MINUS:			handleChar("-");	break;
						case KeyEvent.VK_SLASH:			handleChar("/");	break;
						case KeyEvent.VK_0: 			handleChar("0");  	break;
						case KeyEvent.VK_1: 			handleChar("1");  	break;
						case KeyEvent.VK_2: 			handleChar("2");  	break;
						case KeyEvent.VK_3: 			handleChar("3");  	break;
						case KeyEvent.VK_4: 			handleChar("4");  	break;
						case KeyEvent.VK_5: 			handleChar("5");  	break;
						case KeyEvent.VK_6: 			handleChar("6");  	break;
						case KeyEvent.VK_7: 			handleChar("7");  	break;
						case KeyEvent.VK_8: 			handleChar("8");  	break;
						case KeyEvent.VK_9: 			handleChar("9");  	break;
						case KeyEvent.VK_PERIOD:		handleChar(".");  	break;
					}
				}
			}
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
		});
		emptyfield.setEditable(false);
		ansfield.setHorizontalAlignment(JTextField.RIGHT);
		ansfield.setEditable(false);
		ansfield.setBackground(Color.WHITE);
		JPanel panelTextField=new JPanel(new GridLayout(3,1));
		panelTextField.add("Center",textfield);
		panelTextField.add("Center",emptyfield);
		panelTextField.add("Center",ansfield);
		
		//define buttons panel
		JPanel panelButton=new JPanel(new GridLayout(ROW,COL));
		for(int i=0;i<keyName.length;i++)
		{
			buttons[i]=new JButton(keyName[i]);
			buttons[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String label=e.getActionCommand();
					if(label.equals("CE"))//clear all
						handleCE();
					else if(label.equals("Del"))//Backspace
						handleDel();
					else if(label.equals("="))//Evaluate
						handleEquals();
					else if(label.equals("<"))//left move
						handleLeft();
					else if(label.equals(">"))//right move
						handleRight();
					else//numbers and operators
						handleChar(label);
					textfield.requestFocusInWindow();//!!!IMPORTANT: Set focus back to textfield, or keyListener will be disabled
				}
			});
			panelButton.add(buttons[i]);
		}
		
		//add two panels to the frame
		setLayout(new BorderLayout(0,WINDOW_HEIGHT/15));
		add("North",panelTextField);
		add("Center",panelButton);
		
		//set the parameters of the frame
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
    }
	
	private void handleCE()
	{
		expression="_";
		textfield.setText("_");
		ansfield.setText("0");
		currentPosition=0;
	}
	
	private void handleDel()
	{
		int tmp=currentPosition;
		handleLeft();
		expression=expression.substring(0,currentPosition+1)+expression.substring(tmp+1,expression.length());
		textfield.setText(expression);
	}
	
	private void handleEquals()
	{
		if(LegalExpression())
			ansfield.setText(Evaluate()+"");
		else
			ansfield.setText("Illegal Expression");
	}
	
	private void handleLeft()
	{
		if(currentPosition==0){}
		else if(expression.charAt(currentPosition-1)=='(' && currentPosition!=1 && expression.charAt(currentPosition-2)!='(')
		{
			int tmp=currentPosition;
			currentPosition-=2;
			while(currentPosition>-1 && "0123456789.()+-*/,".indexOf(expression.charAt(currentPosition))<0)
				currentPosition--;
			if(currentPosition==-1)
			expression="_"+expression.substring(0,tmp)+expression.substring(tmp+1,expression.length());
			else
				expression=expression.substring(0,currentPosition+1)+"_"+expression.substring(currentPosition+1,tmp)+expression.substring(tmp+1,expression.length());
			currentPosition++;
			textfield.setText(expression);
		}
		else
		{
			expression=expression.substring(0,currentPosition-1)+"_"+expression.charAt(currentPosition-1)+expression.substring(currentPosition--+1,expression.length());
			textfield.setText(expression);
		}
	}
	
	private void handleRight()
	{
		if(currentPosition==expression.length()-1){}
		else if("0123456789.()+-*/,".indexOf(expression.charAt(currentPosition+1))<0)
		{
			int tmp=currentPosition;
			while(expression.charAt(++currentPosition)!='('){}
			expression=expression.substring(0,tmp)+expression.substring(tmp+1,currentPosition+1)+"_"+expression.substring(currentPosition+1,expression.length());
			textfield.setText(expression);
		}
		else
		{
			expression=expression.substring(0,currentPosition)+expression.charAt(currentPosition+1)+"_"+expression.substring(currentPosition+++2,expression.length());
			textfield.setText(expression);
		}
	}
	
	private void handleChar(String label)
	{
		if("0123456789.".indexOf(label)<0 && ",()+-*/".indexOf(label)<0)//functions which need a "("
		{
			expression=expression.substring(0,currentPosition)+label+"("+expression.substring(currentPosition,expression.length());
			currentPosition+=label.length()+1;
		}
		else
			expression=expression.substring(0,currentPosition)+label+expression.substring(currentPosition++,expression.length());
		textfield.setText(expression);
	}
	
	//convert the expression into one with whitespaces in order to calculate easily
	private String convert()
	{
		String str="";
		int cnt=-1,tmp;
		do
		{
			cnt++;
			tmp=currentPosition;
			handleLeft();
		}while(tmp!=currentPosition);
		handleRight();
		while(tmp!=currentPosition)
		{
			if("0123456789.".indexOf(expression.substring(tmp,currentPosition))>=0)
				while(currentPosition!=expression.length()-1 && "0123456789.".indexOf(expression.charAt(currentPosition+1))>=0)
					handleRight();
			str=str+expression.substring(tmp,currentPosition)+" ";
			tmp=currentPosition;
			handleRight();
		}
		return str;
	}
	
	public double Evaluate()//evaluate expression
	{ return ExpCal.EvaluatePostfix(ExpCal.InfixToPostfix(convert())); }
	
	public boolean LegalExpression()//judge whether expression can be evaluated
	{ return ExpDis.expDis(convert()); }
	
	public static void main(String[] args)
	{
		new Calculator();
	}
}

class Function
{
	//special constant
	public static final double E=2.7182818284590452354;
	public static final double P=3.14159265358979323846;
	
	//synthesize
	public static double f(Stack<Double> operand,String str)
	{	
		if(str.equals("sin"))        return Math.sin(operand.pop());
		if(str.equals("cos"))        return Math.cos(operand.pop());
		if(str.equals("tan"))        return Math.tan(operand.pop());
		if(str.equals("asin"))       return Math.asin(operand.pop());
		if(str.equals("acos"))       return Math.acos(operand.pop());
		if(str.equals("atan"))       return Math.atan(operand.pop());
		if(str.equals("sinh"))       return Math.sinh(operand.pop());
		if(str.equals("cosh"))       return Math.cosh(operand.pop());
		if(str.equals("tanh"))       return Math.tanh(operand.pop());
		if(str.equals("ceil"))       return Math.ceil(operand.pop());
		if(str.equals("floor"))      return Math.floor(operand.pop());
		if(str.equals("rint"))       return Math.rint(operand.pop());
		if(str.equals("round"))      return Math.round(operand.pop());
		if(str.equals("neg"))        return -operand.pop();

		
		double y=operand.pop();
		double x=operand.pop();
		if(str.equals("+"))          return x+y;
		if(str.equals("-"))          return x-y;
		if(str.equals("*"))          return x*y;
		if(str.equals("/"))          return x/y;
		if(str.equals("pow"))        return Math.pow(x,y);
		if(str.equals("log"))        return Math.log(y)/Math.log(x);
		if(str.equals("mod"))        return x%y;
		
		return 1;
	}
}

class ExpDis
{
	public static boolean expDis(String str)
	{
		String[] s=str.split(" ");
		
		//有四种符号要判断：数字，运算，左括号，右括号
		String s1="-*\\d+\\.{0,1}\\d*";
		String s2="[-[+*/,]]";
		
		//括号匹配指标
		int index=0;
		
		//开头判断
		if(!Pattern.matches(s1,s[0]) && s[0].indexOf("(")==-1) return false;			

		//中间判断
		for(int i=0;i<s.length-1;i++)
		{
			if(Pattern.matches(s1,s[i]))
			{
				if(Pattern.matches(s1,s[i+1]))  	   return false;
				else if(s[i+1].indexOf("(")!=-1)       return false;
			}
			else if(Pattern.matches(s2,s[i]))
			{
				if(Pattern.matches(s2,s[i+1])) 		   return false;
				else if(s[i+1].indexOf(")")!=-1)       return false;
			}
			else if(s[i].indexOf("(")!=-1)
			{
				if(Pattern.matches(s2,s[i+1])) 		   return false;
				else if(s[i+1].indexOf(")")!=-1)       return false;
				index++;
			}
			else
			{
				if(Pattern.matches(s1,s[i+1]))  	   return false;
				else if(s[i+1].indexOf("(")!=-1)       return false;
				index--;
			}
			
			if(index<0) return false;
		}
		
		//结尾判断
		if(!Pattern.matches(s1,s[s.length-1]))
			if(s[s.length-1].indexOf(")")==-1) return false;
			else index--;
		
		if(index!=0) return false;
			
		return true;
	}
}

class ExpCal
{
	public static String InfixToPostfix(String str)
	{
		String[] s=str.split(" ");
		Stack<String> operator=new Stack<String>();
		String str_new="";
		String t;
		
		for(int i=0;i<s.length;i++)
		{
			if(s[i].indexOf("(")!=-1) operator.push(s[i]);
			else if(s[i].indexOf(")")!=-1)	
			{
				while(operator.peek().indexOf("(")==-1)
					str_new=str_new+operator.pop()+" ";
				
				if(operator.peek().equals("(")) operator.pop();
				else
				{
					t=operator.pop();
					str_new=str_new+t.substring(0,t.length()-1)+" ";
				}					
			}
			else if(s[i].equals("+") || s[i].equals("-"))
			{
				while(!operator.isEmpty() && operator.peek().indexOf("(")==-1)
					str_new=str_new+operator.pop()+" ";
				operator.push(s[i]);
			}
			else if(s[i].equals("*") || s[i].equals("/"))
			{
				while(!operator.isEmpty() && (operator.peek().equals("*") || operator.peek().equals("/")))
					str_new=str_new+operator.pop()+" ";
				operator.push(s[i]);
			}
			else if(!s[i].equals(",")) str_new=str_new+s[i]+" ";
		}
		
		while(!operator.isEmpty())
			str_new=str_new+operator.pop()+" ";
			
		return str_new.substring(0,str_new.length()-1);
	}
	
	public static double EvaluatePostfix(String str)
	{
		String[] s=str.split(" ");
		Stack<Double> operand=new Stack<Double>();
		String t="-*\\d+\\.{0,1}\\d*";
		
		for(int i=0;i<s.length;i++)
		{
			if(Pattern.matches(t,s[i])) operand.push(Double.parseDouble(s[i]));
			else                        operand.push(Function.f(operand,s[i]));
		}
		
		return operand.pop();
	}
}