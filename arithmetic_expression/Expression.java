import edu.princeton.cs.algs4.*;

public class Expression
{
	//transfer an infix arithmetic expression to a postfix expression
	public static String InfixToPostfix(String str)
	{
		String str_new="";
		
		//enqueue the string with spaces as delimiters into "queue"
		String[] s=str.split(" ");
		Queue<String> queue=new Queue<String>();
		for(int i=0;i<s.length;i++)
			queue.enqueue(s[i]);
		
		//transfer postfix expression in "queue" into the string "str_new"
		Stack<String> operator=new Stack<String>();//stack "operator" memorize ( + - * / that has not yet been popped to "str_new"
		String tmp;
		for(int i=0;i<s.length;i++)
		{
			tmp=queue.dequeue();
			if(tmp.equals("("))			
				operator.push("(");
			else if(tmp.equals(")"))//dequeue all elements in operator till meeting "("	
			{
				while(!operator.peek().equals("("))
					str_new=str_new+operator.pop()+" ";
				operator.pop();
			}
			else if(tmp.equals("+"))//pop all operators left in "operator" till meeting (
			{
				while(!operator.isEmpty() && !operator.peek().equals("("))
					str_new=str_new+operator.pop()+" ";
				operator.push("+");
			}
			else if(tmp.equals("-"))
			{
				while(!operator.isEmpty() && !operator.peek().equals("("))
					str_new=str_new+operator.pop()+" ";
				operator.push("-");
			}
			else if(tmp.equals("*"))//push * in except for meeting another * or /
			{
				while(!operator.isEmpty() && (operator.peek().equals("*") || operator.peek().equals("/")))
					str_new=str_new+operator.pop()+" ";
				operator.push("*");
			}
			else if(tmp.equals("/"))
			{
				while(!operator.isEmpty() && (operator.peek().equals("*") || operator.peek().equals("/")))
					str_new=str_new+operator.pop()+" ";
				operator.push("/");
			}
			else 						
				str_new=str_new+tmp+" ";
			//the following codes are just for debugging
			/*StdOut.println(str_new);
			for(String b:operator)
				StdOut.print(b+" ");
			StdOut.println();*/
		}
		while(!operator.isEmpty())//push all operators left in the stack "operator"
			str_new=str_new+operator.pop()+" ";
			
		return str_new;
	}
	
	//Evaluate a postfix expression
	public static double EvaluatePostfix(String str)
	{
		String[] s=str.split(" ");
		Queue<String> queue=new Queue<String>();
		for(int i=0;i<s.length;i++)
			queue.enqueue(s[i]);
		Stack<Double> operand=new Stack<Double>();
		String tmp;double top;
		for(int i=0;i<s.length;i++)
		{
			tmp=queue.dequeue();
			if(tmp.equals("*"))			operand.push(operand.pop()*operand.pop());
			else if(tmp.equals("/"))	
			{
				top=operand.pop();
				operand.push(operand.pop()/top);
			}
			else if(tmp.equals("+"))	operand.push(operand.pop()+operand.pop());
			else if(tmp.equals("-"))	
			{
				top=operand.pop();
				operand.push(operand.pop()-top);
			}
			else 						operand.push(Double.parseDouble(tmp));
		}
		return operand.pop();
	}
	
	//transfer an infix arithmetic expression to a prefix expression
	public static String InfixToPrefix(String str)
	{
		Stack<String> prefix=new Stack<String>();//use the stack "prefix" to memorize the reverse of the result
		
		//push the string with spaces as delimiters into "stack" to get a reverse order
		String[] s=str.split(" ");
		Stack<String> stack=new Stack<String>();
		for(int i=0;i<s.length;i++)
			stack.push(s[i]);
		
		//transfer prefix expression in "stack" into the stack "prefix"
		Stack<String> operator=new Stack<String>();//stack "operator" memorize ) + - * / that has not yet been popped to "prefix"
		String tmp;
		for(int i=0;i<s.length;i++)
		{
			tmp=stack.pop();
			if(tmp.equals(")"))			operator.push(")");
			else if(tmp.equals("("))//pop all elements in operator till meeting ")"	
			{
				while(!operator.peek().equals(")"))
					prefix.push(operator.pop());
				operator.pop();
			}
			else if(tmp.equals("+"))//pop all higher priority operator, that is * or / before being pushed in except for )
			{
				if(operator.isEmpty() || operator.peek().equals(")") || operator.peek().equals("-"))
					operator.push("+");
				if(operator.peek().equals("*") || operator.peek().equals("/"))
				{
					do{
						prefix.push(operator.pop());
						if(operator.isEmpty())
							break;
					}while(operator.peek().equals("*") || operator.peek().equals("/"));
					operator.push("+");
				}
			}
			else if(tmp.equals("-"))
			{
				if(operator.isEmpty() || operator.peek().equals(")") || operator.peek().equals("+"))
					operator.push("-");
				if(operator.peek().equals("*") || operator.peek().equals("/"))
				{
					do{
						prefix.push(operator.pop());
						if(operator.isEmpty())
							break;
					}while(operator.peek().equals("*") || operator.peek().equals("/"));
					operator.push("-");
				}
			}
			else if(tmp.equals("*"))	operator.push("*");
			else if(tmp.equals("/"))	operator.push("/");
			else 						prefix.push(tmp);
			//the following codes are just for debugging
			/*for(String a:prefix)
				StdOut.print(a+" ");
			StdOut.println();
			for(String b:operator)
				StdOut.print(b+" ");
			StdOut.println();*/
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
		Stack<String> stack=new Stack<String>();
		for(int i=0;i<s.length;i++)
			stack.push(s[i]);
		Stack<Double> operand=new Stack<Double>();
		String tmp;
		for(int i=0;i<s.length;i++)
		{
			tmp=stack.pop();
			if(tmp.equals("*"))			operand.push(operand.pop()*operand.pop());
			else if(tmp.equals("/"))	operand.push(operand.pop()/operand.pop());
			else if(tmp.equals("+"))	operand.push(operand.pop()+operand.pop());
			else if(tmp.equals("-"))	operand.push(operand.pop()-operand.pop());
			else 						operand.push(Double.parseDouble(tmp));
		}
		return operand.pop();
	}	
	
	public static void main(String[] args)
	{
		StdOut.println("-------------");
		String infix="3 + ( 2 - 5 ) * 6 / 3";
		String postfix=Expression.InfixToPostfix(infix);
		StdOut.println("Infix expression\t\""+infix+"\"\tis equivalent to postfix expression\t\""+postfix+"\"");
		StdOut.println("Postfix expression\t\""+postfix+"\"\tequals:\t"+EvaluatePostfix(postfix));
		StdOut.println("-------------");
		infix="1 + ( ( 2 + 3 ) * 4 ) - 5";
		String prefix=Expression.InfixToPrefix(infix);
		StdOut.println("Infix expression\t\""+infix+"\"\tis equivalent to prefix expression\t\""+prefix+"\"");
		StdOut.println("Prefix expression\t\""+prefix+"\"\tequals:\t"+EvaluatePrefix(prefix));
	}
}