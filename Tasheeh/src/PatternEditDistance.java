import java.util.*;



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

public class PatternEditDistance {

	private static final Candidate_Delimiter_Class CANDIDATE_DELIMITER_CLASS = new Candidate_Delimiter_Class();
	private static final Gap_Class GAP_CLASS = new Gap_Class();
	
	private static final Space_Class SPACE_CLASS = new Space_Class();
	private static final WhiteSpace_Class WHITESPACE_CLASS= new WhiteSpace_Class();
	
	private static final Digit_Class DIGIT_CLASS = new Digit_Class();
	private static final Sequence_Digit_Class SEQUENCE_DIGIT_CLASS = new Sequence_Digit_Class();
	
	private static final Upper_Letter_Class UPPER_LETTER_CLASS = new Upper_Letter_Class();
	private static final Sequence_UpperLetter_Class SEQUENCE_UPPER_LETTER_CLASS = new Sequence_UpperLetter_Class();
	
	private static final Lower_Letter_Class LOWER_LETTER_CLASS = new Lower_Letter_Class();
	private static final Sequence_LowerLetter_Class SEQUENCE_LOWER_LETTER_CLASS = new Sequence_LowerLetter_Class();

   
   static int[][] editDP(List<List<Object>> potDomPat, List<List<Object>> domPat)
   {	   
	   
	   int patToTransform = potDomPat.size();
       int patToRefer =  domPat.size();
       int[][] dynamicMAT = new int[patToRefer + 1][patToTransform + 1];
       dynamicMAT[0][0] = 0;
       for (int i = 1; i <= patToRefer; i++)
       	dynamicMAT[i][0] = 	dynamicMAT[i-1][0] + getCost(domPat.get(i-1));
       
       for (int j = 1; j <= patToTransform; j++)
       	dynamicMAT[0][j] = dynamicMAT[0][j-1] + getCost(potDomPat.get(j-1));

       // Compute the edit distance matrix
       for (int i = 1; i <= patToRefer; i++) {
           for (int j = 1; j <= patToTransform; j++) {
        	   
              int temp1 = dynamicMAT[i-1][j] + getCost(domPat.get(i-1));   // ----------------------------------------------- insertion cost
              int temp2 = dynamicMAT[i][j-1] + getCost(potDomPat.get(j-1));   // -----------------------------------------------  deletion cost
              
              int temp3 = 0;
   
              if(similarityCheck(potDomPat.get(j-1) , domPat.get(i-1)))
           	     temp3 = dynamicMAT[i-1][j-1];
              else
           	     temp3 =  dynamicMAT[i-1][j-1] + get_Substitution_Cost(potDomPat.get(j-1) , domPat.get(i-1));  // -----------------------------------------------  substitution cost
              
              int edit_count = min(temp1, temp2, temp3);
              dynamicMAT[i][j] = edit_count;
           }
       }
       return dynamicMAT;
	}
	    
   
   static int get_Substitution_Cost (List<Object> list1, List<Object> list2)
   {
       List<Object> sequenceStore  = new ArrayList<Object>();
       sequenceStore.add(SEQUENCE_DIGIT_CLASS.toString()); sequenceStore.add(SEQUENCE_LOWER_LETTER_CLASS.toString()); 
       sequenceStore.add(SEQUENCE_UPPER_LETTER_CLASS.toString()); sequenceStore.add(WHITESPACE_CLASS.toString());
      
	   int counterONE = 0;   // list 1  // potential dominant
	   int counterTWO = 0;   // list 2  // dominant 
	   int substitution = 0;
	   boolean sequenceCheckFlag = false;
	   boolean longSequence = false;
	   boolean potentialFlag = false;
	   boolean dominantFlag = false;
	   while(counterONE < list1.size() && counterTWO < list2.size())
	   {
		   
		   if(        (  list1.get(counterONE).toString().contains(UPPER_LETTER_CLASS.toString())           &&  list2.get(counterTWO).toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString())   )  ||  
				      (  list1.get(counterONE).toString().contains(DIGIT_CLASS.toString())                  &&  list2.get(counterTWO).toString().contains(SEQUENCE_DIGIT_CLASS.toString())          )  ||            
				      (  list1.get(counterONE).toString().contains(LOWER_LETTER_CLASS.toString())           &&  list2.get(counterTWO).toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString())   )  ||
				      (  list1.get(counterONE).toString().contains(SPACE_CLASS.toString())                  &&  list2.get(counterTWO).toString().contains(WHITESPACE_CLASS.toString())              )  ||                     
				      (  list1.get(counterONE).toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString())  &&  list2.get(counterTWO).toString().contains(UPPER_LETTER_CLASS.toString())   )  ||  
				      (  list1.get(counterONE).toString().contains(SEQUENCE_DIGIT_CLASS.toString())         &&  list2.get(counterTWO).toString().contains(DIGIT_CLASS.toString())          )  ||            
				      (  list1.get(counterONE).toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString())  &&  list2.get(counterTWO).toString().contains(LOWER_LETTER_CLASS.toString())   )  ||
				      (  list1.get(counterONE).toString().contains(WHITESPACE_CLASS.toString())             &&  list2.get(counterTWO).toString().contains(SPACE_CLASS.toString())              )            )
		     {
				   if(sequenceCheckFlag)
				   {
					   longSequence = true;
					   if(potentialFlag)
						   counterTWO++;
					   else if(dominantFlag)
						   counterONE++;
				   }
				   else
				   {
					   sequenceCheckFlag = true; 
					   substitution += CostMatrix.ping_CostMatrix(list1.get(counterONE),list2.get(counterTWO));
					   if(sequenceStore.contains(list1.get(counterONE).toString()))   // potential contain sequence
					    {
						 potentialFlag = true;
						 counterTWO++;
					    }
					   else if(sequenceStore.contains(list2.get(counterTWO).toString()))  // dominant contain sequence
					    {
						  dominantFlag = true;
						  counterONE++;
					    }
						   
				   }
			   }
		      
		    else if(sequenceCheckFlag)
		       {
		    	  if(longSequence)
		    	  {
		    		  substitution += 20;
		    	  }
		    	  if(potentialFlag)
		    		  counterONE++;
		    	  else if(dominantFlag)
		    		  counterTWO++;
		    	  longSequence = false;
		    	  sequenceCheckFlag = false;
		    	  potentialFlag = false;
		    	  dominantFlag = false;
		       }
		      
			 else
			   {
				   substitution += CostMatrix.ping_CostMatrix(list1.get(counterONE),list2.get(counterTWO));
				   counterONE++; counterTWO++;
			   } 
		   
		    if(counterONE +1 > list1.size() && dominantFlag && sequenceCheckFlag && longSequence )   
		    	substitution += 20;
		    else if(counterTWO +1 > list2.size() && potentialFlag && sequenceCheckFlag && longSequence )
		    	substitution+= 20;
	   
	   }
	   
   
       int difference = 0;
       difference = Math.abs((list1.size()-counterONE) - (list2.size()-counterTWO)); 
       if(sequenceCheckFlag && longSequence)
       {
    	   difference--;
       }
	   if(difference != 0)
	   {
		   substitution += difference*50;
	   }
   
		return substitution;
   }
    
   
   static int getCost (List<Object> obj)
    {
 	   if(obj.toString().contains(CANDIDATE_DELIMITER_CLASS.toString()))
 	      return 500;
 	   else
 		  return obj.size()*50;
    }
    
    static boolean similarityCheck(List<Object> potentialPattern, List<Object> dominantPattern)  // return true only if exact match
    {
    	if(potentialPattern.equals(dominantPattern))
    	 return true;
    	else
    	 return	false;
    }
    
    
    
    static int min(int a, int b, int c)
    {
        int z = Math.min(a, b);
        return Math.min(z, c);
    }
  
   static List<PatternEditDistanceResult> shortestPathGraphCreation(int[][] matrix, List<List<Object>> potential, List<List<Object>> dominant, List<Integer> crossponding_Rows) 
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
 		  vertices[i][0].adjacencies = 	new Edge[] { new Edge(vertices[i-1][0],  getCost(dominant.get(i-1)))};
 	       
 	  for (int j =  vertices[0].length-1; j > 0; j--)
 		  vertices[0][j].adjacencies = new Edge[] { new Edge(vertices[0][j-1], getCost(potential.get(j-1)))};
 	       
 	  for (int i = vertices.length-1; i > 0; i--) 
 	  {
           for (int j =  vertices[0].length-1; j > 0; j--) 
           {
         	 
        	  if(similarityCheck(potential.get(j-1), dominant.get(i-1)))
              {
         		  vertices[i][j].adjacencies = new Edge[] { 
                		   new Edge(vertices[i][j-1],    getCost(potential.get(j-1))),
                		   new Edge(vertices[i-1][j-1], 0),
                		   new Edge(vertices[i-1][j],    getCost(dominant.get(i-1))) };  
         	  }
        	  else
        	  {
        		    int shortest =  (int) 0.0;
	              	if(i>0  && j>0)
	              	{
	              		int temp1 = matrix[i-1][j];
	                   	int temp2 = matrix[i][j-1];
	                   	int temp3 = matrix[i-1][j-1];	
	                   	
	                   	 shortest = min(temp1, temp2, temp3);
	              	}
        		    	
        		    if(matrix[i-1][j] == shortest && matrix[i][j-1]  == shortest && matrix[i-1][j-1]  == shortest)
        		    {
        		    	if(matrix[i-1][j] +  getCost(dominant.get(i-1)) == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                         		   new Edge(vertices[i][j-1],   getCost(potential.get(j-1)) ),        
                         		   new Edge(vertices[i-1][j-1], get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) ),
                         		   new Edge(vertices[i-1][j],   0 )  };
        		    	}
        		    	else if(matrix[i][j-1] + getCost(potential.get(j-1)) == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                          		   new Edge(vertices[i][j-1],   0 ),        
                          		   new Edge(vertices[i-1][j-1],  get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) ),
                          		   new Edge(vertices[i-1][j],   getCost(dominant.get(i-1)))   };
        		    	}
        		    	else
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                           		   new Edge(vertices[i][j-1],   getCost(potential.get(j-1))  ),        
                           		   new Edge(vertices[i-1][j-1], 0 ),
                           		   new Edge(vertices[i-1][j],   getCost(dominant.get(i-1)))   };
        		    	}
        		    	    
        		    }
        		    else if(matrix[i-1][j] == shortest && matrix[i][j-1]  == shortest)
        		    {
        		    	if(matrix[i-1][j] + getCost(dominant.get(i-1)) == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                         		   new Edge(vertices[i][j-1],   getCost(potential.get(j-1)) ),        
                         		   new Edge(vertices[i-1][j-1], get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) ),
                         		   new Edge(vertices[i-1][j],   0 )  };
        		    	}
        		    	else if(matrix[i][j-1] + getCost(potential.get(j-1)) == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                          		   new Edge(vertices[i][j-1],   0 ),        
                          		   new Edge(vertices[i-1][j-1], get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) ),
                          		   new Edge(vertices[i-1][j],   getCost(dominant.get(i-1)))   };
        		    	} 
        		    }
              	
        		    else if(matrix[i][j-1]  == shortest && matrix[i-1][j-1]  == shortest )
        		    {
        		    	if(matrix[i][j-1] + getCost(potential.get(j-1)) == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                          		   new Edge(vertices[i][j-1],   0 ),        
                          		   new Edge(vertices[i-1][j-1], get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) ),
                          		   new Edge(vertices[i-1][j],   getCost(dominant.get(i-1)))   };
        		    	} 
        		    	else if(matrix[i-1][j-1] + get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                            		   new Edge(vertices[i][j-1],   getCost(potential.get(j-1))  ),        
                            		   new Edge(vertices[i-1][j-1], 0 ),
                            		   new Edge(vertices[i-1][j],   getCost(dominant.get(i-1)))   };
        		    	} 
        		    }
        		    else if(matrix[i-1][j]  == shortest && matrix[i-1][j-1]  == shortest )
        		    {
        		    	if(matrix[i-1][j] +  getCost(dominant.get(i-1)) == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                         		   new Edge(vertices[i][j-1],   getCost(potential.get(j-1)) ),        
                         		   new Edge(vertices[i-1][j-1], get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) ),
                         		   new Edge(vertices[i-1][j],   0 )  };
        		    	}
        		    	else if(matrix[i-1][j-1] + get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) == matrix[i][j])
        		    	{
        		    		vertices[i][j].adjacencies = new Edge[] { 
                            		   new Edge(vertices[i][j-1],   getCost(potential.get(j-1))  ),        
                            		   new Edge(vertices[i-1][j-1], 0 ),
                            		   new Edge(vertices[i-1][j],   getCost(dominant.get(i-1)))   };
        		    	} 
        		    }
        		    
        		    else if(matrix[i-1][j] == shortest)
            		    {
        		    	vertices[i][j].adjacencies = new Edge[] { 
                      		   new Edge(vertices[i][j-1],   getCost(potential.get(j-1)) ),        
                      		   new Edge(vertices[i-1][j-1], get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) ),
                      		   new Edge(vertices[i-1][j],   0 )  };  
            		    }
        		    else if(matrix[i][j-1]  == shortest)
            		    {
        		    	vertices[i][j].adjacencies = new Edge[] { 
                       		   new Edge(vertices[i][j-1],   0 ),        
                       		   new Edge(vertices[i-1][j-1], get_Substitution_Cost(potential.get(j-1), dominant.get(i-1)) ),
                       		   new Edge(vertices[i-1][j],   getCost(dominant.get(i-1)))   }; 
            		    }
                  	
        		    else if(matrix[i-1][j-1]  == shortest)
            		    {
        		    	vertices[i][j].adjacencies = new Edge[] { 
                     		   new Edge(vertices[i][j-1],   getCost(potential.get(j-1))  ),        
                     		   new Edge(vertices[i-1][j-1], 0 ),
                     		   new Edge(vertices[i-1][j],   getCost(dominant.get(i-1)))   };
            		    }
        	   }
               
           }
 	  }

// 	for (Vertex[] x : vertices)
//     {
//          for (Vertex y : x)
//          {
//               System.out.print(y + " ");
//               if(y.adjacencies!=null)
//               {
//             	  for(Edge  e : y.adjacencies)
//                 		  System.out.print("  target edge "+ e.target +"  weight   "+  e.weight +"   ");
//               }
//               
//          }
//          System.out.println();
//       }
 		
       Dijkstra.computePaths(vertices[vertices.length-1][vertices[vertices.length-1].length-1]); // run Dijkstra
       //System.out.println("Distance to " + vertices[0][0] + ": " + vertices[0][0].minDistance);
       List<Vertex> path = Dijkstra.getShortestPathTo(vertices[0][0]);
       //System.out.println("Shortest Path:  "+path);
       
       List<PatternEditDistanceResult> instance_PEDR_result = getPatternSegments(matrix, path, potential, dominant, crossponding_Rows);
       return instance_PEDR_result;
   }
  
   
   public static List<PatternEditDistanceResult> getPatternSegments(int[][] matrix, List<Vertex> path, List<List<Object>> potential, List<List<Object>> dominant, List<Integer> crossponding_Rows)
   {
 	  List<Pair<Integer, Integer>> segmentKeys = new ArrayList<Pair<Integer,Integer>>();
 	  
 	  List<Pair<List<Object>,List<Object>>> segmentValues = new ArrayList<Pair<List<Object>,List<Object>>>();
 		
 	  List<PatternEditDistanceResult> instance_PEDR_new = new LinkedList<PatternEditDistanceResult>();
 	  
 	  for(int m = path.size()-1; m >= 0; m--)
 	  {  
 		  
 		  if(m-1 >= 0)
 		  {
 			  if(path.get(m-1).name.getKey() != path.get(m).name.getKey()   && path.get(m-1).name.getValue() != path.get(m).name.getValue()  )
 			  {
 				  if(similarityCheck(potential.get(path.get(m).name.getValue()), dominant.get(path.get(m).name.getKey())))
 				  {
 					  segmentKeys.add(new Pair<Integer, Integer>(path.get(m).name.getKey(),path.get(m).name.getValue()));
 	 				  segmentValues.add(new Pair<List<Object>, List<Object>>(dominant.get(path.get(m).name.getKey()), potential.get(path.get(m).name.getValue())));  
 				  }
 				  else
 				  {
 					  List<Object> l = new ArrayList<>() ; l.add("replace "+ potential.get(path.get(m).name.getValue()));
 					  segmentKeys.add(new Pair<Integer, Integer>(path.get(m).name.getKey(),path.get(m).name.getValue()));
 	 				  segmentValues.add(new Pair<List<Object>, List<Object>>(l, dominant.get(path.get(m).name.getKey()))); 
 				  }
 			  }
 			  else if(path.get(m-1).name.getKey() == path.get(m).name.getKey() )
 			  {
 				  List<Object> l = new ArrayList<>() ; l.add("delete ");
 				  segmentKeys.add(new Pair<Integer, Integer>(path.get(m).name.getKey(),path.get(m).name.getValue()));
 				  segmentValues.add(new Pair<List<Object>, List<Object>>(l, potential.get(path.get(m).name.getValue())));
 			  }
 			  else if(path.get(m-1).name.getValue() == path.get(m).name.getValue())
 			  {
 				 List<Object> l = new ArrayList<>() ; l.add("insert ");
 				  segmentKeys.add(new Pair<Integer, Integer>(path.get(m).name.getKey(),path.get(m).name.getValue()));
 				  segmentValues.add(new Pair<List<Object>, List<Object>>(l , dominant.get(path.get(m).name.getKey())));

 			  }   
 		  }
 		 
 		 
 	  }
 	  
 	  int score = matrix[matrix.length-1][matrix[matrix.length-1].length-1];
       
 	  instance_PEDR_new.add(new PatternEditDistanceResult(segmentValues, segmentKeys, crossponding_Rows, score));
 	  
 	  return instance_PEDR_new;  
   }
    
}


