package algebra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import java.lang.Object;

import abstractions.Arithmetic_Oprt_Class;
import abstractions.Brackets_Class;
import abstractions.Candidate_Delimiter_Class;
import abstractions.Digit_Class;
import abstractions.EmptyValues_Class;
import abstractions.Full_Text_Class;
import abstractions.Gap_Class;
import abstractions.Line_Break_Class;
import abstractions.Lower_Letter_Class;
import abstractions.MissingValues_Class;
import abstractions.Number_Class;
import abstractions.Quotation_Class;
import abstractions.Sequence_Digit_Class;
import abstractions.Sequence_LowerLetter_Class;
import abstractions.Sequence_UpperLetter_Class;
import abstractions.Space_Class;
import abstractions.Symbol_Class;
import abstractions.Text_Class;
import abstractions.Upper_Letter_Class;
import abstractions.WhiteSpace_Class;
import alignment.Dijkstra;
import alignment.Edge;
import alignment.Vertex;
import javafx.util.Pair;

public class Extract {
	private static final Drop DROP = new Drop();

	private static final Gap_Class GAP_CLASS = new Gap_Class();
	
	private static final Number_Class NUMBER_CLASS = new Number_Class();
	private static final MissingValues_Class MISSING_VALUES_CLASS = new MissingValues_Class();
	private static final EmptyValues_Class EMPTY_VALUES_CLASS = new EmptyValues_Class();
	private static final Line_Break_Class LINE_BREAK_CLASS = new Line_Break_Class();
	private static final Full_Text_Class FULL_TEXT_CLASS = new Full_Text_Class();
	private static final Text_Class TEXT_CLASS = new Text_Class();
	private static final WhiteSpace_Class WHITESPACE_CLASS= new WhiteSpace_Class();
	private static final Sequence_Digit_Class SEQUENCE_DIGIT_CLASS = new Sequence_Digit_Class();
	private static final Sequence_LowerLetter_Class SEQUENCE_LOWER_LETTER_CLASS = new Sequence_LowerLetter_Class();
	private static final Sequence_UpperLetter_Class SEQUENCE_UPPER_LETTER_CLASS = new Sequence_UpperLetter_Class();
	private static final Symbol_Class SYMBOL_CLASS = new Symbol_Class();
	private static final Arithmetic_Oprt_Class ARITHMETIC_OPRT_CLASS = new Arithmetic_Oprt_Class();
	private static final Brackets_Class BRACKETS_CLASS = new Brackets_Class();
	private static final Space_Class SPACE_CLASS = new Space_Class();
	private static final Candidate_Delimiter_Class CANDIDATE_DELIMITER_CLASS = new Candidate_Delimiter_Class();
	private static final Quotation_Class QUOTATION_CLASS = new Quotation_Class();
	private static final Digit_Class DIGIT_CLASS = new Digit_Class();
	private static final Upper_Letter_Class UPPER_LETTER_CLASS = new Upper_Letter_Class();
	private static final Lower_Letter_Class LOWER_LETTER_CLASS = new Lower_Letter_Class();
	
	
	static List<Object> symbol ;
	static
	{
		symbol =  new ArrayList<>();
		
		symbol.add(ARITHMETIC_OPRT_CLASS.toString());   
		symbol.add(SYMBOL_CLASS.toString()); // 
		symbol.add(LINE_BREAK_CLASS.toString()); //  
		symbol.add(QUOTATION_CLASS.toString()); // 
		symbol.add(BRACKETS_CLASS.toString()); // 
		symbol.add(SPACE_CLASS.toString()); // 
		symbol.add(WHITESPACE_CLASS.toString()); // 
		symbol.add(FULL_TEXT_CLASS.toString()); // 
	}
	
	static List<Object> digit;
	static
	{
		digit =  new ArrayList<>();
		
		digit.add(DIGIT_CLASS.toString()); //  
		digit.add(SEQUENCE_DIGIT_CLASS.toString()); // 
		digit.add(NUMBER_CLASS.toString()); // 
	}
	
	static List<Object> letter;
	static
	{
		letter =  new ArrayList<>();
		
		letter.add(UPPER_LETTER_CLASS.toString()); // 
		letter.add(LOWER_LETTER_CLASS.toString()); // 
		letter.add(SEQUENCE_UPPER_LETTER_CLASS.toString()); // 
		letter.add(SEQUENCE_LOWER_LETTER_CLASS.toString()); // 
		letter.add(TEXT_CLASS.toString()); //
	}
	
	static List<Object> literalLetters = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
	
	static List<Object> literalDigits = Arrays.asList('0','1','2','3','4','5','6','7','8','9');
	
	public Map<Integer, Object> apply(List<Object> dominantPart, List<Object> potentialPart) 
	{
		
		Map<Integer, Object> clean_ouput = new HashMap<Integer, Object>();
		List<Pair<Object, Object>> cleaningTrace = new ArrayList<Pair<Object, Object>>();
		
		Ignore ignoreObject = new Ignore();
		
		if(ignoreObject.checkMismatch(dominantPart, potentialPart)) 
		{
			
			return null;
		}
		else
		{
			cleaningTrace = Extract.shortestPathGraphCreation(Extract.sequenceAlignment(potentialPart, dominantPart), potentialPart, dominantPart);
			
			List<String> strings = new ArrayList<>(potentialPart.size());
			for (Object object : potentialPart) {
			    strings.add(Objects.toString(object, null));
			}
			
			if(Collections.disjoint(strings, symbol) && Collections.disjoint(strings, letter) && Collections.disjoint(strings, digit)
					&& Collections.disjoint(potentialPart, literalDigits) && Collections.disjoint(potentialPart, literalLetters))
			{
				for(int i= 0; i<potentialPart.size(); i++)
				{
					if( potentialPart.get(i) instanceof Character && (char)potentialPart.get(i) != 34 && (char)potentialPart.get(i) != 32)
					  clean_ouput.put(i, DROP.toString());
				}
			}
			else
			{
				
				StringBuilder sb_dom = new StringBuilder();
				for(int i= 0; i<dominantPart.size(); i++)
				{
					sb_dom.append(dominantPart.get(i));
				}
				if(StringUtils.isBlank(sb_dom.toString()))
				{
					
					return null;
				}
				else
				{
					List<Object> temp_Store = potentialPart;
					for(int i = cleaningTrace.size()-1 ; i >=0; i--)
					{
						if(cleaningTrace.get(i).getKey().toString().contains(GAP_CLASS.toString()))  
						{
							if(i< potentialPart.size())  
							{
								temp_Store.remove(i);
								if(ignoreObject.checkMismatch(dominantPart, temp_Store))
								{
									clean_ouput.put(i , DROP.toString());
									break;
								}
								else
								{
									clean_ouput.put(i , DROP.toString());
								}
							}
							
						}
						else if(cleaningTrace.get(i).getValue().toString().contains(GAP_CLASS.toString()))   
						{
							return null;
	 					}
					}
				}
				
				if(!(clean_ouput.isEmpty()))
				{
						
					for(Entry<Integer,Object> entry: clean_ouput.entrySet())
					{
						potentialPart.remove(entry.getKey());
					}
					
					if(ignoreObject.checkMismatch(dominantPart, potentialPart))
					{
						return clean_ouput;
					}
					else
					{
						return null;
					}
				}
			}
			
			return clean_ouput;
		}
	}
	
	public static int[][] sequenceAlignment(List<Object> potDomPat, List<Object> domPat)
	{	   
		int patToAlign = potDomPat.size();
		int patToRefer = domPat.size();
		int[][] dynamicMAT = new int[patToRefer + 1][patToAlign + 1];
		dynamicMAT[0][0] = 0;

		for (int i = 1; i <= patToRefer; i++)
			dynamicMAT[i][0] = dynamicMAT[i - 1][0] + 1;

		for (int j = 1; j <= patToAlign; j++)
			dynamicMAT[0][j] = dynamicMAT[0][j - 1] + 1;

		for (int i = 1; i <= patToRefer; i++) {
			for (int j = 1; j <= patToAlign; j++) {

				int temp1 = dynamicMAT[i - 1][j] + 1; // ----------------------------------------------- horizontal      // gap insertion cost
				int temp2 = dynamicMAT[i][j - 1] + 1; // ----------------------------------------------- vertical gap	// insertion cost

				int temp3 = 0;

				if (similarityCheck(potDomPat.get(j - 1), domPat.get(i - 1)))
					temp3 = dynamicMAT[i - 1][j - 1];
				else
					temp3 = dynamicMAT[i - 1][j - 1] + 1;

				int edit_count = min(temp1, temp2, temp3);
				dynamicMAT[i][j] = edit_count;
			}
		}
		return dynamicMAT;
	  }
	  
	  static int min(int a, int b, int c)
	  {
		  int z = Math.min(a, b);
	      return Math.min(z, c);
	  }
  
  public static boolean similarityCheck(Object object1, Object object2)  
  {
  	if(object1.equals(object2))
  	 return true;
  	else if(similarity_Class_Check(object1, object2))
  		return true;
  	else
  	 return	false;
  }
   
  public static boolean similarity_Class_Check(Object object1, Object object2)  // return true only if exact match
  {
	 if( (object1.toString().matches("[0-9]") || digit.contains(object1.toString())) && (object2.toString().matches("[0-9]") || digit.contains(object2.toString())))
	 {
		 return true;
	 }	
	 else if( (object1.toString().matches("[a-zA-Z]") || letter.contains(object1.toString())) && (object2.toString().matches("[a-zA-Z]") || letter.contains(object2.toString())))
	 {
		 return true;
	 }	
	 else
		return false;
  }
  
  static  List<Pair<Object, Object>> shortestPathGraphCreation(int[][] matrix, List<Object> potential, List<Object> dominant) 
  {
	  
	  Vertex[][] vertices = new Vertex[dominant.size() + 1][potential.size() + 1];
	       
	  for (int i = 0; i < matrix.length; i++) 
	  {
          for (int j = 0; j < matrix[0].length; j++) 
          {
        	  vertices[i][j] = new Vertex(new Pair<Integer, Integer>(i,j));
          }
	  }
      
	  for (int i = vertices.length-1; i > 0; i--)
		  vertices[i][0].adjacencies = 	new Edge[] { new Edge(vertices[i-1][0], 1)};
	       
	  for (int j =  vertices[0].length-1; j > 0; j--)
		  vertices[0][j].adjacencies = new Edge[] { new Edge(vertices[0][j-1], 1)};
	       
	  for (int i = vertices.length-1; i > 0; i--) 
	  {
          for (int j =  vertices[0].length-1; j > 0; j--) 
          {
        	  
        	  if(similarityCheck(potential.get(j-1), dominant.get(i-1)))
        	  {
        		  vertices[i][j].adjacencies = new Edge[] { 
               		   new Edge(vertices[i][j-1],   1),
               		   new Edge(vertices[i-1][j-1], 0),
               		   new Edge(vertices[i-1][j],   1)};  

        	  }
        	  else
        	  {
        		    int shortest =  0;
	              	if(i>0  && j>0)
	              	{
	              		int temp1 = matrix[i-1][j];
	                   	int temp2 = matrix[i][j-1];
	                   	int temp3 = matrix[i-1][j-1];	
	                   	
	                   	 shortest = min(temp1, temp2, temp3);
	              	}
        		    	
        		    if(matrix[i-1][j] == shortest && matrix[i][j-1]  == shortest && matrix[i-1][j-1]  == shortest)
        		    {
        		    	if(matrix[i-1][j] + 1 == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                         		   new Edge(vertices[i][j-1],   1 ),        
                         		   new Edge(vertices[i-1][j-1], 1 ),
                         		   new Edge(vertices[i-1][j],   0 )  };
        		    	}
        		    	else if(matrix[i][j-1] + 1 == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                          		   new Edge(vertices[i][j-1],   0 ),        
                          		   new Edge(vertices[i-1][j-1], 1 ),
                          		   new Edge(vertices[i-1][j],   1 )  };
        		    	}
        		    	else
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                           		   new Edge(vertices[i][j-1],   1 ),        
                           		   new Edge(vertices[i-1][j-1], 0 ),
                           		   new Edge(vertices[i-1][j],   1 )  };
        		    	}
        		    	    
        		    }
        		    else if(matrix[i-1][j] == shortest && matrix[i][j-1]  == shortest)
        		    {
        		    	if(matrix[i-1][j] + 1 == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                         		   new Edge(vertices[i][j-1],   1 ),        
                         		   new Edge(vertices[i-1][j-1], 1 ),
                         		   new Edge(vertices[i-1][j],   0 )  };
        		    	}
        		    	if(matrix[i][j-1] + 1 == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                          		   new Edge(vertices[i][j-1],   0 ),        
                          		   new Edge(vertices[i-1][j-1], 1 ),
                          		   new Edge(vertices[i-1][j],   1 )  };
        		    	} 
        		    }
              	
        		    else if(matrix[i][j-1]  == shortest && matrix[i-1][j-1]  == shortest )
        		    {
        		    	if(matrix[i][j-1] + 1 == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                         		   new Edge(vertices[i][j-1],   0 ),        
                         		   new Edge(vertices[i-1][j-1], 1 ),
                         		   new Edge(vertices[i-1][j],   1 )  };
        		    	}
        		    	if(matrix[i-1][j-1] + 1 == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                          		   new Edge(vertices[i][j-1],   1 ),        
                          		   new Edge(vertices[i-1][j-1], 0 ),
                          		   new Edge(vertices[i-1][j],   1 )  };
        		    	} 
        		    	
        		    }
        		    else if(matrix[i-1][j]  == shortest && matrix[i-1][j-1]  == shortest )
        		    {
        		    	if(matrix[i-1][j] + 1 == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                         		   new Edge(vertices[i][j-1],   1 ),        
                         		   new Edge(vertices[i-1][j-1], 1 ),
                         		   new Edge(vertices[i-1][j],   0 )  };
        		    	}
        		    	if(matrix[i-1][j-1] + 1 == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                          		   new Edge(vertices[i][j-1],   1 ),        
                          		   new Edge(vertices[i-1][j-1], 0 ),
                          		   new Edge(vertices[i-1][j],   1 )  };
        		    	} 
        		    }
        		    
        		    else if(matrix[i-1][j] == shortest)
            		    {
            		    	 vertices[i][j].adjacencies = new Edge[] { 
                            		   new Edge(vertices[i][j-1],   1 ),        
                            		   new Edge(vertices[i-1][j-1], 1 ),
                            		   new Edge(vertices[i-1][j],   0 )  };   
            		    }
        		    else if(matrix[i][j-1]  == shortest)
            		    {
            		    	 vertices[i][j].adjacencies = new Edge[] { 
                            		   new Edge(vertices[i][j-1],   0 ),        
                            		   new Edge(vertices[i-1][j-1], 1 ),
                            		   new Edge(vertices[i-1][j],   1 )  };   
            		    }
                  	
        		    else if(matrix[i-1][j-1]  == shortest)
            		    {
            		    	 vertices[i][j].adjacencies = new Edge[] { 
                            		   new Edge(vertices[i][j-1],   1 ),        
                            		   new Edge(vertices[i-1][j-1], 0 ),
                            		   new Edge(vertices[i-1][j],   1 )  };   
            		    }
        	   }
              
          }
	  }

		
      Dijkstra.computePaths(vertices[vertices.length-1][vertices[vertices.length-1].length-1]); // run Dijkstra
//      System.out.println("Distance to " + vertices[0][0] + ": " + vertices[0][0].minDistance);
      List<Vertex> path = Dijkstra.getShortestPathTo(vertices[0][0]);
//      System.out.println("Shortest Path:  "+path);
      
      List<Pair<Object, Object>> backtrackTrace = new ArrayList<Pair<Object, Object>>();
      backtrackTrace = getPatternSegments(matrix, path, potential, dominant);
      
    return backtrackTrace;
  }
  
  public static List<Pair<Object, Object>> getPatternSegments(int[][] matrix, List<Vertex> path, List<Object> potential, List<Object> dominant)
  {
	  List<Pair<Object, Object>> backtrackTrace = new ArrayList<Pair<Object, Object>>();
	 
	  for(int m = path.size()-1; m >= 0; m--)
	  {  
		  
		  if(m-1 >= 0)
		  {
			  if(path.get(m-1).name.getKey() != path.get(m).name.getKey()   && path.get(m-1).name.getValue() != path.get(m).name.getValue()  ) // replace
			  {
				  backtrackTrace.add(new Pair<Object, Object>(dominant.get(path.get(m).name.getKey()), potential.get(path.get(m).name.getValue())));
			  }
			  else if(path.get(m-1).name.getKey() == path.get(m).name.getKey() )   // insert gap in dominant
			  {
				  backtrackTrace.add(new Pair<Object, Object>(GAP_CLASS.toString(), potential.get(path.get(m).name.getValue())));
			  }
			  else if(path.get(m-1).name.getValue() == path.get(m).name.getValue()) // insert gap in potential
			  {
				  backtrackTrace.add(new Pair<Object, Object>(dominant.get(path.get(m).name.getKey()), GAP_CLASS.toString()));
			  }   
		  }
		  
	  } 
	
	  return backtrackTrace;  
  }
}
