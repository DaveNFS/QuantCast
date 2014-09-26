package Dave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Calculate {

	public int rows; 
	public int columns; 
	public String sheet[][]; 
	public int answers[][]; 
	
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
