import java.util.Stack;

public class MathEvaluator
{
	private static void eval(Stack<Double> operand,Stack<String> operator)
	{
		if(operator.peek().equals("neg"))
		{
			operator.pop();
			operand.push(-operand.pop());
		}
		else if(operator.peek().equals("+"))
		{
			operator.pop();
			operand.push(operand.pop()+operand.pop());
		}
		else if(operator.peek().equals("-"))
		{
			operator.pop();
			operand.push(-operand.pop()+operand.pop());
		}
		else if(operator.peek().equals("*"))
		{
			operator.pop();
			operand.push(operand.pop()*operand.pop());
		}
		else if(operator.peek().equals("/"))
		{
			operator.pop();
			operand.push(1/operand.pop()*operand.pop());
		}
	}
	
	public static double calculate(String str)
	{
		//str=str.replaceAll("[^0-9-\\.\\+\\*\\/\\(\\)]","");
		str=str.replace(" ","");
		Stack<Double> operand=new Stack<Double>();
		Stack<String> operator=new Stack<String>();
		int i_pre=0;
		for(int i=0;i<str.length();i++)
		{
			if(!String.valueOf(str.charAt(i)).matches("[.0-9]"))
			{
				//deal with numbers
				if(!(i==i_pre))
					operand.push(Double.parseDouble(str.substring(i_pre,i)));
				i_pre=i+1;
				
				//deal with operators
				char tmp=str.charAt(i);
				if(tmp=='-' && (i==0 || !String.valueOf(str.charAt(i-1)).matches("[.0-9]")))
					operator.push("neg");
				else if(tmp=='(')
					operator.push("(");
				else if(tmp==')')
				{
					while(!operator.peek().equals("("))
						eval(operand,operator);
					operator.pop();
				}
				else if(tmp=='*' || tmp=='/')
				{
					while(!operator.isEmpty() && !operator.peek().matches("[\\(+-]"))
						eval(operand,operator);
					operator.push(String.valueOf(tmp));
				}
				else if(tmp=='+' || tmp=='-')
				{
					while(!operator.isEmpty() && !operator.peek().matches("[\\(]"))
						eval(operand,operator);
					operator.push(String.valueOf(tmp));
				}
			}
		}
		if(i_pre!=str.length())
			operand.push(Double.parseDouble(str.substring(i_pre,str.length())));
		while(!operator.isEmpty())
			eval(operand,operator);
		return operand.peek();
	}
	
	public static void main(String[] args)
	{
		String str="2/(2+3)*4.33--6";
		System.out.println(str+"="+calculate(str));
	}
}