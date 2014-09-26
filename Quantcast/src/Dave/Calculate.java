package Dave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Calculate {

	public int rows; 
	public int columns; 
	public String sheet[][]; 
	public double answers[][]; 
	public boolean isComputed[][];
	
	public ArrayList<String> letters = new ArrayList<String>();
	
	public Calculate()
	{
		// fill in letters 
		int start = 65; 
		for (int i=start; i<91; i++)
			{
				char c[] = Character.toChars(i);
				String s = "" + c[0];
				letters.add(s);
			}
	}
	
	public boolean checkCyclicDependency()
	{
		return CyclicDependency.check(this.sheet);
	}
	
	
	public double computeCell(int x, int y)
	{
	
		// NOTE: this step is to avoid stack overflow exception !!!!
		// if you still get this exception do increase stack size using -Xss
		if(this.isComputed[x][y])
		{
			return answers[x][y];
		}
		
		
		
		// first see if the cell contains letters
		boolean dependent = this.containsLetters(sheet[x][y]);
		
		double output = -1; 
		
		if(!dependent)
		{
			output = RPN.solve(sheet[x][y]);
		}
		
		
		if(dependent)
		{
			// replace all the letter_number by computeCell(L, N)
			String currentExp = sheet[x][y];
			String newExp = "";
			String tokens[] = currentExp.split(" ");
			
			// if the token contains letter then it is the corresponding row, and N is the column 
			for(String token : tokens)
			{
				if(this.containsLetters(token))
				{
					String temp[] = token.split(""); 
					int row = this.letters.indexOf(temp[0]);
					int col = Integer.parseInt(temp[1]);
					col = col - 1; 
					
					newExp = newExp + computeCell(row, col) + " ";
				}
				else
				{
					newExp = newExp + token + " ";
				}
				
				
			}
			
			newExp = newExp.trim();
			output = RPN.solve(newExp);
			
			
		}
		
		this.setIsComputed(x, y);
		this.setAnswer(x, y, output);
		
		return output;
	}
	
	
	
	
	
	
	
	
	public void setIsComputed(int x, int y)
	{
		this.isComputed[x][y] = true;
	}
	
	public void setAnswer(int x, int y, double answer)
	{
		this.answers[x][y] = answer;
	}
	
	
	public double solveExpression(String exp)
	{
		return -1; 
	}
	
	
	
	
	
	
	public boolean containsLetters(String x)
	{
		boolean output = false;
		for(String l: this.letters)
		{
			if(x.contains(l))
			{
				output = true;
				return output;
			}
		}
		return output;
	}
	
	
	
	public void readFirstLine(String fileName) throws Exception 
	{
		// read the first line and set the dimensions
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String line; 
	    
	    if((line=br.readLine()) != null)
	    {
	    	String dims[] = line.split(" ");
	    	this.columns = Integer.parseInt(dims[0]);
	    	this.rows = Integer.parseInt(dims[1]);
	    }
	    
	    this.answers = new double[rows][columns];
	    this.isComputed = new boolean[rows][columns];
	    
	    // System.out.println(this.rows + "-" + this.columns);
	}
	
	
	
	
	
	public String[] getValuesInOneD(String fileName) throws Exception
	{
		// read the text file and return a one dim array to represent all the cells
		
		int totalSize = this.rows*this.columns; 
		String output[] = new String[totalSize];
		int i = 0; 
		
		boolean firstLine = true; 
		
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String line; 
	    
	    while((line = br.readLine()) != null)
	    {
	    	if(firstLine)
	    	{
	    		// do nothing 
	    		firstLine = false;
	    	}    	
	    	else
	    	{
	    		output[i] = line;  
	    		i++;
	    	}
	    	
	    }
		
	    return output;
	
	}

	
	public void mapTo2D(String a[])
	{
		// populate sheet[][]
		
		this.sheet = new String[this.rows][this.columns];
		int count = 0; 

		
		for(int i=0; i<this.rows; i++)
		{
			for(int j=0; j<this.columns; j++)
			{
				if(count == a.length)
				{
					break;
				}
				
				this.sheet[i][j] = a[count];
				count++;

			}
		}
		
	}
	
	
	
	
}
