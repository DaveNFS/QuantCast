package Dave;

public class Spreadsheet {

	public static final String FILE_NAME = "spreadsheet.txt"; 
	
	public static void main(String args[]) throws Exception
	{
		
	
		
		// create a Calculate object 
		Calculate item = new Calculate();
		item.readFirstLine(FILE_NAME);
		String cells[] = item.getValuesInOneD(FILE_NAME);
//		for(int i=0; i<cells.length; i++)
//		{
//			System.out.println(cells[i]);
//		}
		
		item.mapTo2D(cells);
		

		
//		for(int i=0; i<item.rows; i++)
//		{
//			for(int j=0; j<item.columns; j++)
//			{
//				System.out.print(item.sheet[i][j] + "==");
//			}
//			System.out.println();
//		}
		
		
		if(item.checkCyclicDependency())
		{
			//print the first line as is
			System.out.println(item.columns + " " + item.rows);
			//print answers 
			for(int i=0; i<item.rows; i++)
			{
				for(int j=0; j<item.columns; j++)
				{
					double val = Double.valueOf(item.computeCell(i, j));
					String str = String.format("%.5f", val);
					System.out.println (str);
				}
			
			}
		
		}
		else
		{
			System.out.println("Cyclic Dependency found !!!");
			System.exit(-1);
		}
		
		

		
		
		//System.out.println(item.computeCell(1,0));
		
		
		
		
		
	}
	
	
	

	
}
