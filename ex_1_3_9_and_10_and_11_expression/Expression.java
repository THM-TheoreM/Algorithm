import edu.princeton.cs.algs4.*;

/*
This class mainly works with prefix, infix , simple infix and postfix arithmetic expressions, including change between forms and evaluation.
The main idea is to change the infix expression into one that is easy to be evaluated by a computer,
generalizing the concept "infix expression" in the textbook (in this file we call it a !!!"SIMPLE INFIX EXPRESSION"!!!)
which asks the infix expression to be fully parenthesized.
Actually, this gives the algorithm generalizing Dijkstra's Two-Stack Algorithm on page 129 in the textbook, and can realize a calculator now!

API:
static String InfixToPostfix(String str)				line 23
static double EvaluatePostfix(String str)				line 63
static String PostfixToSimpleInfix(String str)			line 79
static String InfixToPrefix(String str)					line 108
static double EvaluatePrefix(String str)				line 155
static String PrefixToSimpleInfix(String str)			line 171
static String LeftParenthesesComplete(String str)		line 204
static double EvaluateSimpleInfix(String str)			line 286
static String SimpleInfixToInfix(String str)			line 315
*/
public class Expression
{
	//transfer an infix arithmetic expression to a postfix expression
	public static String InfixToPostfix(String str)
	{
		String str_new="";
		String[] s=str.split(" ");
		
		//transfer postfix expression into the string "str_new"
		Stack<String> operator=new Stack<String>();//stack "operator" memorize ( + - * / that has not yet been popped to "str_new"
		for(int i=0;i<s.length;i++)
		{
			if(s[i].equals("("))			
				operator.push("(");
			else if(s[i].equals(")"))//pop all elements in "operator" till meeting (	
			{
				while(!operator.peek().equals("("))
					str_new=str_new+operator.pop()+" ";
				operator.pop();
			}
			//for + - * /, always pop operators that has NO LESS PIORITY first and then push it in "operator"
			else if(s[i].equals("+") || s[i].equals("-"))
			{
				while(!operator.isEmpty() && !operator.peek().equals("("))
					str_new=str_new+operator.pop()+" ";
				operator.push(s[i]);
			}
			else if(s[i].equals("*") || s[i].equals("/"))
			{
				while(!operator.isEmpty() && (operator.peek().equals("*") || operator.peek().equals("/")))
					str_new=str_new+operator.pop()+" ";
				operator.push(s[i]);
			}
			else//numbers 						
				str_new=str_new+s[i]+" ";
		}
		while(!operator.isEmpty())//push all operators left in the stack "operator"
			str_new=str_new+operator.pop()+" ";
			
		return str_new.substring(0,str_new.length()-1);//delete the last redudent space in case of troubles in other functions
	}
	
	//Evaluate a postfix expression
	public static double EvaluatePostfix(String str)
	{
		String[] s=str.split(" ");
		Stack<Double> operand=new Stack<Double>();
		for(int i=0;i<s.length;i++)
		{
			if(s[i].equals("*"))		operand.push(operand.pop()*operand.pop());
			else if(s[i].equals("/"))	operand.push(1.0/(operand.pop()/operand.pop()));//"-", "/" is influenced by the order of function pop 
			else if(s[i].equals("+"))	operand.push(operand.pop()+operand.pop());
			else if(s[i].equals("-"))	operand.push(-operand.pop()+operand.pop());
			else 						operand.push(Double.parseDouble(s[i]));
		}
		return operand.pop();
	}
	
	//transfer a postfix arithmetic expression to an infix expression
	public static String PostfixToSimpleInfix(String str)
	{
		String[] s=str.split(" ");
		return PostfixToSimpleInfix(s)[0];
	}
	
	//auxiliary function to help with "static String PostfixToSimpleInfix(String str)"
	private static String[] PostfixToSimpleInfix(String[] str)
	{
		boolean flag=false;int i;
		for(i=0;i<str.length;i++)
			if(str[i].equals("+") || str[i].equals("-") || str[i].equals("*") || str[i].equals("/"))
			{
				flag=true;
				break;
			}
		if(flag==false)
			return str;
		String[] str_new=new String[str.length-2];
		int index=0;
		for(int j=0;j<str.length;j++)
			if(j!=(i-2))
				str_new[index++]=str[j];
			else
				str_new[index++]="( "+str[j++]+" "+str[i]+" "+str[j++]+" )";
		return PostfixToSimpleInfix(str_new);
	}
	
	//transfer an infix arithmetic expression to a prefix expression
	public static String InfixToPrefix(String str)
	{
		Stack<String> prefix=new Stack<String>();//use the stack "prefix" to memorize the reverse of the result
		String[] s=str.split(" ");
		
		//transfer prefix expression into the stack "prefix"
		Stack<String> operator=new Stack<String>();//stack "operator" memorize ) + - * / that has not yet been popped to "prefix"
		for(int i=s.length-1;i>=0;i--)
		{
			if(s[i].equals(")"))			
				operator.push(")");
			else if(s[i].equals("("))//pop all elements in operator till meeting ")"	
			{
				while(!operator.peek().equals(")"))
					prefix.push(operator.pop());
				operator.pop();
			}
			else if(s[i].equals("+") || s[i].equals("-"))//pop all higher priority operator, that is * or / before being pushed in except for )
			{
				if(operator.isEmpty() || operator.peek().equals(")") || operator.peek().equals("+") || operator.peek().equals("-"))
					operator.push(s[i]);
				if(operator.peek().equals("*") || operator.peek().equals("/"))
				{
					do{
						prefix.push(operator.pop());
						if(operator.isEmpty())
							break;
					}while(operator.peek().equals("*") || operator.peek().equals("/"));
					operator.push(s[i]);
				}
			}
			else if(s[i].equals("*") || s[i].equals("/"))	
				operator.push(s[i]);
			else//numbers
				prefix.push(s[i]);
		}
		while(!operator.isEmpty())//push all operators left in the stack "operator"
				prefix.push(operator.pop());
			
		//make the stack "prefix" to be a string named after str_new
		String str_new=prefix.pop();
		while(!prefix.isEmpty())
			str_new=str_new+" "+prefix.pop();
		return str_new;
	}
	
	//Evaluate a prefix expression
	public static double EvaluatePrefix(String str)
	{
		String[] s=str.split(" ");
		Stack<Double> operand=new Stack<Double>();
		for(int i=s.length-1;i>=0;i--)
		{
			if(s[i].equals("*"))		operand.push(operand.pop()*operand.pop());
			else if(s[i].equals("/"))	operand.push(operand.pop()/operand.pop());
			else if(s[i].equals("+"))	operand.push(operand.pop()+operand.pop());
			else if(s[i].equals("-"))	operand.push(operand.pop()-operand.pop());
			else 						operand.push(Double.parseDouble(s[i]));
		}
		return operand.pop();
	}
	
	//transfer a prefix arithmetic expression to an infix expression
	public static String PrefixToSimpleInfix(String str)
	{
		String[] s=str.split(" ");
		return PrefixToSimpleInfix(s)[0];
	}
	
	//auxiliary function to help with "static String PrefixToSimpleInfix(String str)"
	private static String[] PrefixToSimpleInfix(String[] str)
	{
		boolean flag=false;int i;
		for(i=str.length-1;i>=0;i--)
			if(str[i].equals("+") || str[i].equals("-") || str[i].equals("*") || str[i].equals("/"))
			{
				flag=true;
				break;
			}
		if(flag==false)
			return str;
		String[] str_new=new String[str.length-2];
		int index=str_new.length-1;
		for(int j=str.length-1;j>=0;j--)
			if(j!=(i+2))
				str_new[index--]=str[j];
			else
			{
				str_new[index--]="( "+str[i+1]+" "+str[i]+" "+str[i+2]+" )";
				j-=2;
			}
			
		return PrefixToSimpleInfix(str_new);
	}
	
	//return the simple infix expression with one that only gives out the right parentheses
	//NOTICE: It is very useful if we have a binary tree represent an arithmetic expression, with leave nodes as numbers and other nodes as operators.
	//If we traverse from the root node, first output the leftmost leave node, and then from left to right, output the operator node the first time we encounter it,
	//output ")" the second time we encounter it, then we will have such a simple infix expression without left parentheses.
	public static String LeftParenthesesComplete(String str)
	{
		String[] s=str.split(" ");
		return LeftParenthesesComplete(s)[0];
	}
	
	//auxiliary function to realize function "static String LeftParenthesesComplete(String str)"
	private static String[] LeftParenthesesComplete(String[] str)
	{
		if(str.length==1)//base case
			return str;
		
		//first scan to judge how many "("s to add
		int RightParentheses=0,LeftParentheses=0;
		for(int i=0;i<str.length;i++)
		{
			if(str[i].equals(")"))
				RightParentheses++;
			else if(str[i].equals("+") || str[i].equals("-") || str[i].equals("*") || str[i].equals("/")){}
			else//numbers or expressions
			{
				if(!str[i+1].equals(")"))
					LeftParentheses++;
			}
		}
		if(RightParentheses==0)//base case
			return str;
		String[] str_new=new String[str.length+LeftParentheses];
		
		//second scan to add "("s into the String array "str"
		int index=0;//show the index of str_new
		for(int i=0;i<str.length;i++)
		{
			if(str[i].equals(")") || str[i].equals("+") || str[i].equals("-") || str[i].equals("*") || str[i].equals("/")){}
			else//numbers or expressions
				if(!str[i+1].equals(")"))
					str_new[index++]="(";
			str_new[index++]=str[i];
		}
		
		str_new=merge(str_new);//use auxiliary function merge to get rid of the well-matched parentheses
		
		return LeftParenthesesComplete(str_new);//recursive implementation
	}
	
	//auxiliary function to realize function "static String[] LeftParenthesesComplete(String[] str)"
	//we say expression like "left parenthesis + expression + operator + expression + right parenthesis" can be merged
	private static String[] merge(String[] str)
	{
		//first scan to judge whether there is an expression that can be merged
		boolean flag=false;int i;
		for(i=0;i<str.length;i++)
			if(str[i].equals("("))
				if(!(str[i+1].equals("+") || str[i+1].equals("-") || str[i+1].equals("*") || str[i+1].equals("/") || str[i+1].equals("(") || str[i+1].equals(")")))
					if(str[i+2].equals("+") || str[i+2].equals("-") || str[i+2].equals("*") || str[i+2].equals("/"))
						if(!(str[i+1].equals("+") || str[i+1].equals("-") || str[i+1].equals("*") || str[i+1].equals("/") || str[i+1].equals("(") || str[i+1].equals(")")))
							if(str[i+4].equals(")"))
							{
								flag=true;
								break;
							}
		if(flag==false)
			return str;
		
		//seconde scan to construct the return String array "str_new"
		String[] str_new=new String[str.length-4];
		int index=0;//show the index of str_new
		for(int j=0;j<str.length;j++)
		{
			if(j!=i)
				str_new[index++]=str[j];
			else
				str_new[index++]=str[j]+" "+str[++j]+" "+str[++j]+" "+str[++j]+" "+str[++j];
		}
		return merge(str_new);//recursive implementation
	}
	
	//Evaluate a simple infix expression with algorithm on page 129 in the textbook
	//NOTICE: Actually, "str" CAN BE AN EXPRESSION WITHOUT LEFT PARENTHESES according to the algorithm
	public static double EvaluateSimpleInfix(String str)
	{
		String[] s=str.split(" ");
		Stack<String> ops=new Stack<String>();
		Stack<Double> vals=new Stack<Double>();
		for(int i=0;i<s.length;i++)
		{
			if(s[i].equals("(")){}
			else if(s[i].equals("+")) ops.push(s[i]);
			else if(s[i].equals("-")) ops.push(s[i]);
			else if(s[i].equals("*")) ops.push(s[i]);
			else if(s[i].equals("/")) ops.push(s[i]);
			else if(s[i].equals(")"))
			{
				String op=ops.pop();
				double v=vals.pop();
				if(op.equals("+"))		v=vals.pop()+v;
				else if(op.equals("-"))	v=vals.pop()-v;
				else if(op.equals("*"))	v=vals.pop()*v;
				else if(op.equals("/"))	v=vals.pop()/v;
				vals.push(v);
			}
			else vals.push(Double.parseDouble(s[i]));
		}
		return vals.pop();
	}
	
	//transfer a simple infix expression to the infix one to cater to people's ordinary use of an arithmetic expression
	public static String SimpleInfixToInfix(String str)
	{
		String[] s=str.split(" ");
		return SimpleInfixToInfix(s)[0];
	}
	
	//auxiliary function to realize function "static String SimpleInfixToInfix(String str)"
	private static String[] SimpleInfixToInfix(String[] str)
	{
		//the same idea as function "merge"
		boolean flag=false;int i;
		for(i=0;i<str.length;i++)
			if(str[i].equals("("))
				if(!(str[i+1].equals("+") || str[i+1].equals("-") || str[i+1].equals("*") || str[i+1].equals("/") || str[i+1].equals("(") || str[i+1].equals(")")))
					if(str[i+2].equals("+") || str[i+2].equals("-") || str[i+2].equals("*") || str[i+2].equals("/"))
						if(!(str[i+1].equals("+") || str[i+1].equals("-") || str[i+1].equals("*") || str[i+1].equals("/") || str[i+1].equals("(") || str[i+1].equals(")")))
							if(str[i+4].equals(")"))
							{
								flag=true;
								break;
							}
		if(flag==false)
			return str;
		
		String[] str_new=new String[str.length-4];
		int index=0;//show the index of str_new
		for(int j=0;j<str.length;j++)
		{
			if(j!=i)
				str_new[index++]=str[j];
			else
			{
				flag=true;//now indicate whether the ( ) is redudent
				if(i!=0)
					if((str[i+2].equals("+")||str[i+2].equals("-")) && (str[i+5].equals("*")||str[i+5].equals("/")))//format (a+b)*c
						flag=false;
				if(i!=str.length-5)
					if((str[i+2].equals("+")||str[i+2].equals("-")) && (str[i-1].equals("*")||str[i-1].equals("/")))//format a*(b+c)
						flag=false;
				if(flag==true)
					str_new[index++]=str[j+1]+" "+str[j+2]+" "+str[j+3];
				else
					str_new[index++]=str[j]+" "+str[j+1]+" "+str[j+2]+" "+str[j+3]+" "+str[j+4];
				j+=4;
			}
		}
		return SimpleInfixToInfix(str_new);//recursive implementation
	}
	
	public static void main(String[] args)
	{
		String infix="3 + ( 2 - 5 ) * 6 / 3";
		String postfix=Expression.InfixToPostfix(infix);
		StdOut.println("Infix expression\t\""+infix+"\"\tis equivalent to postfix expression\t\""+postfix+"\"");
		String simpleinfix=Expression.PostfixToSimpleInfix(postfix);
		StdOut.println("Convering back to simple infix expression\t\""+simpleinfix+"\"");
		StdOut.println("Omitting the redudent parentheses, we have infix expression\t\""+Expression.SimpleInfixToInfix(simpleinfix)+"\"");
		StdOut.println("Postfix expression\t\""+postfix+"\"\tequals:\t"+EvaluatePostfix(postfix));
		StdOut.println("-----------------------------------");
		infix="1 + ( 2 + 3 ) * 4 - 5";
		String prefix=Expression.InfixToPrefix(infix);
		StdOut.println("Infix expression\t\""+infix+"\"\tis equivalent to prefix expression\t\""+prefix+"\"");
		simpleinfix=Expression.PrefixToSimpleInfix(prefix);
		StdOut.println("Convering back to simple infix expression\t\""+simpleinfix+"\"");
		StdOut.println("Omitting the redudent parentheses, we have infix expression\t\""+Expression.SimpleInfixToInfix(simpleinfix)+"\"");
		StdOut.println("Prefix expression\t\""+prefix+"\"\tequals:\t"+EvaluatePrefix(prefix));
		StdOut.println("-----------------------------------");
		infix="1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";//textbook example
		//infix="2 * 3 + 5 ) ) - 4 ) + 7 / 1 ) )";//another example. If you have other ways of implementation, try to prove both of them are correct first.
		simpleinfix=LeftParenthesesComplete(infix);
		StdOut.println("The complete simple infix expression given by expression without left parentheses\t\""+infix+"\"\tis\t\""+simpleinfix+"\"");
		StdOut.println("Simple infix expression\t\""+simpleinfix+"\"\tequals:\t"+EvaluateSimpleInfix(simpleinfix));
	}
}