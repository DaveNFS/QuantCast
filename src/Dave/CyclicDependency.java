package Dave;

import java.util.ArrayList;

public class CyclicDependency {

	
	// Note the best way to implement this check (in my opinion) would be a topo sort
	// if the sort is successful then there are now dependencies 
	
	public static int idCount = 1;
	public static ArrayList<String> vList = new ArrayList<String>();
	
	// NOTE: please change this based on the input  
	private final int MAX_VERTS = 1000;
	
	public static boolean check(String a[][], int rows, int cols)
	{
		CyclicDependency CD = new CyclicDependency();
		boolean output = false;
		
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
			
				// create Vertex for every cell
				CD.addVertex("" + i + "" + j, idCount++);
				
				vList.add("" + i + "" + j);
						
				
				
			}
		}
		
		
		// now add edges
		
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{

				String currentValue = a[i][j];
				// now find the dependencies if any:
				
					String tokens[] = currentValue.split(" ");
					
					for(String token : tokens)
					{
						if(new Calculate().containsLetters(token))
						{
							String temp[] = token.split(""); 
							int row = new Calculate().letters.indexOf(temp[0]);
							int col = Integer.parseInt(temp[1]);
							col = col - 1; 
							
							int v1 = vList.indexOf("" + i + "" + j);
							int v2 = vList.indexOf("" + row + "" + col);
							
							// now add this dependency as an Edge in our graph
							CD.addEdge(v1, v2);
							
						}
						
					}			
				
				
			}
		}
		
		
		output = CD.topo();
		// if false there is a problem (cyclic dependency)
		
		return output;
	}
	
	
	

	  // list of vertices
	  private Vertex vertexList[];

	  // adjacency matrix
	  private int matrix[][]; 

	 // current number of vertices
	  private int numVerts; 

	  private String sortedArray[];

	  public CyclicDependency() 
	  {
	    vertexList = new Vertex[MAX_VERTS];
	    matrix = new int[MAX_VERTS][MAX_VERTS];
	    numVerts = 0;
	    for (int i = 0; i < MAX_VERTS; i++)
	      for (int k = 0; k < MAX_VERTS; k++)
	        matrix[i][k] = 0;
	    sortedArray = new String[MAX_VERTS]; 
	    // sorted vert labels
	  }

	  public void addVertex(String lab , int id) 
	  {
	    vertexList[numVerts++] = new Vertex(lab, id);
	  }

	  public void addEdge(int start, int end) 
	  {
	    matrix[start][end] = 1;
	  }

	  public void displayVertex(int v)
	  {
	    System.out.print(vertexList[v].label);
	  }

	  public boolean topo() 
	  {
		 boolean output = true; 
		  
		// toplogical sort

	    while (numVerts > 0) // while vertices remain,
	    {
	      // get a vertex with no successors, or -1
	      int currentVertex = noSuccessors();
	      if (currentVertex == -1) // must be a cycle
	      {

	        return false;
	      }
	      // insert vertex label in sorted array (start at end)
	      sortedArray[numVerts - 1] = vertexList[currentVertex].label;

	      deleteVertex(currentVertex); 
	      // delete vertex
	    }

	    return output;
	  }

	  public int noSuccessors() // returns vert with no successors (or -1 if no such verts)
	  { 
	    boolean isEdge; // edge from row to column in adjMat

	    for (int row = 0; row < numVerts; row++)
	    {
	      isEdge = false; // check edges
	      for (int col = 0; col < numVerts; col++)
	      {
	        if (matrix[row][col] > 0) // if edge to another,
	        {
	          isEdge = true;
	          break; // this vertex has a successor try another
	        }
	      }
	      if (!isEdge) // if no edges, has no successors
	        return row;
	    }
	    return -1; // no
	  }

	  
	  
	  public void deleteVertex(int delVert)
	  {
	    if (delVert != numVerts - 1) // if not last vertex, delete from vertexList
	    {
	      for (int j = delVert; j < numVerts - 1; j++)
	        vertexList[j] = vertexList[j + 1];

	      for (int row = delVert; row < numVerts - 1; row++)
	        moveRowUp(row, numVerts);

	      for (int col = delVert; col < numVerts - 1; col++)
	        moveColLeft(col, numVerts - 1);
	    }
	    numVerts--; // one less vertex
	  }

	  private void moveRowUp(int row, int length) 
	  {
	    for (int col = 0; col < length; col++)
	      matrix[row][col] = matrix[row + 1][col];
	  }

	  private void moveColLeft(int col, int length)
	  {
	    for (int row = 0; row < length; row++)
	      matrix[row][col] = matrix[row][col + 1];
	  }
	  
	  
	  
	  class Vertex {
		  
		  public int id;
		  public String label;

		  public Vertex(String lab, int id)
		  {
		    this.label = lab;
		    this.id = id;
		  }
		}
	
}
