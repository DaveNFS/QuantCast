package Dave;

import java.util.Stack;

public class RPN {

	
	// simple stack implementation of reverse polish notation
	// Note: won't work for -ve numbers or ++ and -- operators 
	
	
	public static double solve(String exp)
	{
		String tokens[] = exp.split(" "); 
		double output = 0; 
		
		String operators = "+-*/";
		 
		Stack<String> stack = new Stack<String>();
 
		for (String t : tokens)
		{
			if (!operators.contains(t)) 
			{
				stack.push(t);
			} 
			else 
			{
				double a = Double.valueOf(stack.pop());
				double b = Double.valueOf(stack.pop());
				
				switch (t) 
				{
				case "+":
					stack.push(String.valueOf(a + b));
					break;
				case "-":
					stack.push(String.valueOf(b - a));
					break;
				case "*":
					stack.push(String.valueOf(a * b));
					break;
				case "/":
					stack.push(String.valueOf(b / a));
					break;
				}
			}
		}
 
		output = Double.valueOf(stack.pop());
		
		return output; 
 
	}
	
	
}
