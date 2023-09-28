import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import javafx.util.Pair;
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

public class PatternAlignment {

	private static final Gap_Class GAP_CLASS = new Gap_Class();
	
	private static final WhiteSpace_Class WHITESPACE_CLASS= new WhiteSpace_Class();
	private static final Sequence_Digit_Class SEQUENCE_DIGIT_CLASS = new Sequence_Digit_Class();
	private static final Sequence_LowerLetter_Class SEQUENCE_LOWER_LETTER_CLASS = new Sequence_LowerLetter_Class();
	private static final Sequence_UpperLetter_Class SEQUENCE_UPPER_LETTER_CLASS = new Sequence_UpperLetter_Class();
	private static final Space_Class SPACE_CLASS = new Space_Class();
	private static final Digit_Class DIGIT_CLASS = new Digit_Class();
	private static final Upper_Letter_Class UPPER_LETTER_CLASS = new Upper_Letter_Class();
	private static final Lower_Letter_Class LOWER_LETTER_CLASS = new Lower_Letter_Class();
	
	public static float[][] sequenceAlignment(List<List<Object>> potDomPat, List<List<Object>> domPat)
	{	   
			   
			   int patToAlign= potDomPat.size();
		       int patToRefer =  domPat.size();
		       float[][] dynamicMAT = new float[patToRefer + 1][patToAlign + 1];
		       dynamicMAT[0][0] = 0;
		      
		       for (int i = 1; i <= patToRefer; i++)
		       	dynamicMAT[i][0] = 	dynamicMAT[i-1][0] + 1;
		       
		       for (int j = 1; j <= patToAlign; j++)
		       	dynamicMAT[0][j] = dynamicMAT[0][j-1] +  1;

		       // Compute the pattern alignment matrix
		       for (int i = 1; i <= patToRefer; i++) {
		           for (int j = 1; j <= patToAlign; j++) {
		        	   
		        	   float temp1 = dynamicMAT[i-1][j] +  1;    //  ----------------------------------------------- horizontal gap insertion cost
		        	   float temp2 = dynamicMAT[i][j-1] +  1;    //  -----------------------------------------------  vertical gap insertion cost
		              
		        	   float temp3 =  dynamicMAT[i-1][j-1] + PatternAlignmentScore.pairScore(potDomPat.get(j-1) , domPat.get(i-1));
		              
		               float edit_count = min(temp1, temp2, temp3);
		               dynamicMAT[i][j] = edit_count;
		           }
		       }
		       return dynamicMAT;
	  }
	  
	  static float min(float a, float b, float c)
	  {
		  float z = Math.min(a, b);
	      return Math.min(z, c);
	  }
  
  public static boolean sequenceCheck(List<Object> list1, List<Object> list2) 
  {
	   list1 = noConsecutiveDups(list1);
	   list2 = noConsecutiveDups(list2);
	   
	   //System.out.println("testing list1 "+ list1);
	   //System.out.println("testing list2 "+ list2);
	   
	   int counterONE = 0;   // list 1  // potential dominant
	   int counterTWO = 0;   // list 2  // dominant 
	   
	   List<Boolean> flag_dependency_check = new ArrayList<Boolean>(); 
	  
	   while(counterONE < list1.size() && counterTWO < list2.size())
	   {
		   if(list1.get(counterONE) == null || list2.get(counterTWO) == null)    // if value of list1 or list2 is null
			{
			 flag_dependency_check.add(CostMatrix.objectCheck(list1.get(counterONE), list2.get(counterTWO)));
			}
		   
		   else if (list1.get(counterONE).toString().equals(list2.get(counterTWO).toString()))
		   {
			   flag_dependency_check.add(true);
		   }
		   else if(   (  list1.get(counterONE).toString().contains(UPPER_LETTER_CLASS.toString())           &&  list2.get(counterTWO).toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString())   )  ||  
				      (  list1.get(counterONE).toString().contains(DIGIT_CLASS.toString())                  &&  list2.get(counterTWO).toString().contains(SEQUENCE_DIGIT_CLASS.toString())          )  ||            
				      (  list1.get(counterONE).toString().contains(LOWER_LETTER_CLASS.toString())           &&  list2.get(counterTWO).toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString())   )  ||
				      (  list1.get(counterONE).toString().contains(SPACE_CLASS.toString())                  &&  list2.get(counterTWO).toString().contains(WHITESPACE_CLASS.toString())              )  ||                     
				      (  list1.get(counterONE).toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString())  &&  list2.get(counterTWO).toString().contains(UPPER_LETTER_CLASS.toString())   )  ||  
				      (  list1.get(counterONE).toString().contains(SEQUENCE_DIGIT_CLASS.toString())         &&  list2.get(counterTWO).toString().contains(DIGIT_CLASS.toString())          )  ||            
				      (  list1.get(counterONE).toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString())  &&  list2.get(counterTWO).toString().contains(LOWER_LETTER_CLASS.toString())   )  ||
				      (  list1.get(counterONE).toString().contains(WHITESPACE_CLASS.toString())             &&  list2.get(counterTWO).toString().contains(SPACE_CLASS.toString())              )            )
		     {
			   flag_dependency_check.add(true);   
		     }
		   
		    else
		    {
			   flag_dependency_check.add(CostMatrix.objectCheck(list1.get(counterONE), list2.get(counterTWO)));
		    }  
		   counterONE++;
		   counterTWO++;
	   }
	   
	   int difference = 0;
       difference = Math.abs((list1.size()-counterONE) - (list2.size()-counterTWO)); 
      
	   if(difference != 0)
	   {
		   flag_dependency_check.add(false);
	   }
	   
	   if(areAllTrue(flag_dependency_check))
	   {
	    return true;
	   }
	  else
	  {
	    return false;
	  } 
  }

  public static boolean areAllTrue(List<Boolean> bool_data)
	 {
	      for(boolean b : bool_data) if(!b) return false;
	      return true;
	 }
  
  public static ArrayList<Object> noConsecutiveDups(List<Object> input) 
	 {
		  ArrayList<Object> newList = new ArrayList<Object>();
		  newList.add(input.get(0));

		  // Iterate the remaining values
		  for(int i = 1; i < input.size(); i++) {
		    // Compare current value to previous
		    if(input.get(i-1).toString() != input.get(i).toString()) {
		       newList.add(input.get(i));
		    }
		  }

		  return newList;
	}
 
  public static boolean similarityCheck(List<Object> potentialPattern, List<Object> dominantPattern)  // return true only if exact match
  {
  	if(potentialPattern.equals(dominantPattern))
  	 return true;
  	else
  	 return	false;
  }
 

  static List<PatternAlignmentResult> shortestPathGraphCreation(float[][] matrix, List<List<Object>> potential, List<List<Object>> dominant, List<Integer> crossponding_Rows) 
  {
      // mark all the vertices 
	  
	  Vertex[][] vertices = new Vertex[dominant.size() + 1][potential.size() + 1];
	       
	  for (int i = 0; i < matrix.length; i++) 
	  {
          for (int j = 0; j < matrix[0].length; j++) 
          {
        	  vertices[i][j] = new Vertex(new Pair<Integer, Integer>(i,j));
          }
	  }
      
      // set the edges and weight

	  for (int i = vertices.length-1; i > 0; i--)
		  vertices[i][0].adjacencies = 	new Edge[] { new Edge(vertices[i-1][0], 1)};
	       
	  for (int j =  vertices[0].length-1; j > 0; j--)
		  vertices[0][j].adjacencies = new Edge[] { new Edge(vertices[0][j-1], 1)};
	       
	  for (int i = vertices.length-1; i > 0; i--) 
	  {
          for (int j =  vertices[0].length-1; j > 0; j--) 
          {
        	  
//        	  if(PatternAlignmentScore.pairScore(potential.get(j-1) , dominant.get(i-1)) == 0)
//        	  {
//        		  vertices[i][j].adjacencies = new Edge[] { 
//               		   new Edge(vertices[i][j-1],    Math.abs(matrix[i][j] - matrix[i][j-1])),
//               		   new Edge(vertices[i-1][j-1], 0),
//               		   new Edge(vertices[i-1][j],    Math.abs(matrix[i][j] - matrix[i-1][j])  )};  
//        	  }
//        	  else
//        	  {
//        		  vertices[i][j].adjacencies = new Edge[] { 
//                  		   new Edge(vertices[i][j-1],   Math.abs(matrix[i][j] - matrix[i][j-1])),        // Math.abs(matrix[i][j] - matrix[i][j-1])
//                  		   new Edge(vertices[i-1][j-1], PatternAlignmentScore.pairScore(potential.get(j-1) , dominant.get(i-1))),
//                  		   new Edge(vertices[i-1][j],   Math.abs(matrix[i][j] - matrix[i-1][j]) )  };     // Math.abs(matrix[i][j] - matrix[i-1][j])
//        	  }
        	  
        	  if(similarityCheck(potential.get(j-1), dominant.get(i-1)))
        	  {
        		  vertices[i][j].adjacencies = new Edge[] { 
               		   new Edge(vertices[i][j-1],   1),
               		   new Edge(vertices[i-1][j-1], 0),
               		   new Edge(vertices[i-1][j],   1)};  
        	  }
        	  else
        	  {
        		    float shortest =  (float) 0.0;
	              	if(i>0  && j>0)
	              	{
	              		float temp1 = matrix[i-1][j];
	                   	float temp2 = matrix[i][j-1];
	                   	float temp3 = matrix[i-1][j-1];	
	                   	
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
        		    	else if(matrix[i][j-1] + 1 == matrix[i][j])
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
        		    	else if(matrix[i-1][j-1] + PatternAlignmentScore.pairScore(potential.get(j-1) , dominant.get(i-1)) == matrix[i][j])
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
        		    	else if(matrix[i-1][j-1] + PatternAlignmentScore.pairScore(potential.get(j-1) , dominant.get(i-1)) == matrix[i][j])
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
      //System.out.println("Distance to " + vertices[0][0] + ": " + vertices[0][0].minDistance);
      List<Vertex> path = Dijkstra.getShortestPathTo(vertices[0][0]);
      //System.out.println("Shortest Path:  "+path);
      
      List<PatternAlignmentResult> instance_PAR_result = getPatternSegments(matrix, path, potential, dominant, crossponding_Rows);
      return instance_PAR_result;
}

  
  public static List<PatternAlignmentResult> getPatternSegments(float[][] matrix, List<Vertex> path, List<List<Object>> potential, List<List<Object>> dominant, List<Integer> crossponding_Rows)
  {
	  List<Pair<Integer, Integer>> segmentKeys = new ArrayList<Pair<Integer,Integer>>();
	  
	  List<Pair<List<Object>,List<Object>>> segmentValues = new ArrayList<Pair<List<Object>,List<Object>>>();
		
	  List<PatternAlignmentResult> instance_PAR_new = new LinkedList<PatternAlignmentResult>();
	  
	  Map<String, Pair<List<Object>,List<Object>>> transformationBlocks = new LinkedHashMap<String, Pair<List<Object>,List<Object>>>();
	 
	  
	  for(int m = path.size()-1; m >= 0; m--)
	  {  
		  
		  if(m-1 >= 0)
		  {
			  if(path.get(m-1).name.getKey() != path.get(m).name.getKey()   && path.get(m-1).name.getValue() != path.get(m).name.getValue()  ) // replace
			  {
				  if(PatternAlignmentScore.pairScore(potential.get(path.get(m).name.getValue()), dominant.get(path.get(m).name.getKey()))==0)  // try to increase threshold ">0"
 				  {
 					  segmentKeys.add(new Pair<Integer, Integer>(path.get(m).name.getKey(),path.get(m).name.getValue()));
 	 				  segmentValues.add(new Pair<List<Object>, List<Object>>(dominant.get(path.get(m).name.getKey()), potential.get(path.get(m).name.getValue())));  
 	 				  
 	 				  String s = m+" Match";
 	 				  transformationBlocks.put(s, new Pair<List<Object>, List<Object>>(dominant.get(path.get(m).name.getKey()), potential.get(path.get(m).name.getValue())));
 				  }
 				  else
 				  {
 					  segmentKeys.add(new Pair<Integer, Integer>(path.get(m).name.getKey(),path.get(m).name.getValue()));
	 				  segmentValues.add(new Pair<List<Object>, List<Object>>(dominant.get(path.get(m).name.getKey()), potential.get(path.get(m).name.getValue()))); 
	 				  
	 				  String s = m+" Mismatch";
	 				  transformationBlocks.put(s, new Pair<List<Object>, List<Object>>(dominant.get(path.get(m).name.getKey()), potential.get(path.get(m).name.getValue())));
 				  } 
			  }
			  else if(path.get(m-1).name.getKey() == path.get(m).name.getKey() )   // insert gap in dominant
			  {
				  List<Object> l = new ArrayList<>() ; l.add(GAP_CLASS.toString());
				  segmentKeys.add(new Pair<Integer, Integer>(path.get(m).name.getKey(),path.get(m).name.getValue()));
				  segmentValues.add(new Pair<List<Object>, List<Object>>(l, potential.get(path.get(m).name.getValue())));
				  
				  String s = m+" Horizontal";
 				  transformationBlocks.put(s, new Pair<List<Object>, List<Object>>(l, potential.get(path.get(m).name.getValue())));
			  }
			  else if(path.get(m-1).name.getValue() == path.get(m).name.getValue()) // insert gap in potential
			  {
				  List<Object> l = new ArrayList<>() ; l.add(GAP_CLASS.toString());
				  segmentKeys.add(new Pair<Integer, Integer>(path.get(m).name.getKey(),path.get(m).name.getValue()));
				  segmentValues.add(new Pair<List<Object>, List<Object>>(dominant.get(path.get(m).name.getKey()), l));

				  String s = m+" Vertical";
 				  transformationBlocks.put(s, new Pair<List<Object>, List<Object>>(dominant.get(path.get(m).name.getKey()), l));
			  }   
		  }
		 
		 
	  }
	  
	  float score = matrix[matrix.length-1][matrix[matrix.length-1].length-1];
      
	  instance_PAR_new.add(new PatternAlignmentResult(dominant, potential, segmentValues, segmentKeys, crossponding_Rows, score/(path.size()-1), transformationBlocks));
	  
	  return instance_PAR_new;  
  }
  
}
