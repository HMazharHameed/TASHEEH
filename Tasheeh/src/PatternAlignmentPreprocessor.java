import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import abstractions.Candidate_Delimiter_Class;
import abstractions.Gap_Class;
import abstractions.Line_Break_Class;
import abstractions.Quotation_Class;
import javafx.util.Pair;

public class PatternAlignmentPreprocessor {

	private static final Quotation_Class QUOTATION_CLASS = new Quotation_Class();
	private static final Line_Break_Class LINE_BREAK_CLASS = new Line_Break_Class();
	private static final Gap_Class GAP_CLASS = new Gap_Class();	
	private static final Candidate_Delimiter_Class CANDIDATE_DELIMITER_CLASS = new Candidate_Delimiter_Class();

	
	public static PatternAlignmentResult repairPatterns(PatternAlignmentResult patternAlignmentResult)
	{
		boolean flag_for_Gap = false;
		boolean flag_for_Quote = false;
		
    	List<Object> mergeList = new ArrayList<Object>();
    	List<Pair<List<Object>, List<Object>>> repaired_pat_segments = new ArrayList<Pair<List<Object>,List<Object>>>();
		List<Pair<Integer, Integer>> repaired_pat_indices = new ArrayList<Pair<Integer,Integer>>();
		
		Collections.reverse(patternAlignmentResult.pairs_of_patterns);
		Collections.reverse(patternAlignmentResult.pairs_of_indices);
		
		for(int i = 0; i<patternAlignmentResult.pairs_of_indices.size(); i++)
		{

		   if(patternAlignmentResult.pairs_of_patterns.get(i).getKey().toString().contains(LINE_BREAK_CLASS.toString())) // or getValue() contains a Line_Break
		    {
				   repaired_pat_segments.add(patternAlignmentResult.pairs_of_patterns.get(i));
				   repaired_pat_indices.add(patternAlignmentResult.pairs_of_indices.get(i));
		    }
		   else if(patternAlignmentResult.pairs_of_patterns.get(i).getKey().toString().contains(GAP_CLASS.toString()))  // shift and metadata/Data cases
		    {
			   if(patternAlignmentResult.pairs_of_patterns.get(i).getValue().toString().contains(Main_Class.univocityDetetced_QUOTE)  || flag_for_Quote == true)
		        {
			       flag_for_Gap= true;  
			       flag_for_Quote = true;
			       //replace <DEL> with literal
			       if(patternAlignmentResult.pairs_of_patterns.get(i).getValue().toString().contains(CANDIDATE_DELIMITER_CLASS.toString()))
			       {
			    	   mergeList.add(Main_Class.univocityDetetced_delimiter);
			       }
			       else
			       {
			    	   mergeList.addAll(patternAlignmentResult.pairs_of_patterns.get(i).getValue());
			       }
		        }
		         else
		        {
			       
		        }
			}
		   else
		    {		
			   if(flag_for_Quote)
			   {
				   flag_for_Quote = false;
				   mergeList.addAll(patternAlignmentResult.pairs_of_patterns.get(i).getValue());				
				   
				   repaired_pat_segments.add(new Pair(patternAlignmentResult.pairs_of_patterns.get(i).getKey(), new ArrayList<>(mergeList)));
				   repaired_pat_indices.add(patternAlignmentResult.pairs_of_indices.get(i));  
				   
				   mergeList.clear();
			   }
			   else
			   {
				   repaired_pat_segments.add(patternAlignmentResult.pairs_of_patterns.get(i));
				   repaired_pat_indices.add(patternAlignmentResult.pairs_of_indices.get(i));   
			   }
			   
		    }
		}
		
		//reverse pattern segments for printing order
		Collections.reverse(patternAlignmentResult.pairs_of_patterns);
		Collections.reverse(patternAlignmentResult.pairs_of_indices);
				
		PatternAlignmentResult rapaired_pat_Object = new PatternAlignmentResult(patternAlignmentResult.dominantPattern, patternAlignmentResult.potentialDominantPattern, patternAlignmentResult.pairs_of_patterns, patternAlignmentResult.pairs_of_indices, 
				patternAlignmentResult.row_indices, PatternAlignmentScore.parseAlignmentSegments(repaired_pat_segments), patternAlignmentResult.transformationBlocks);
		
		return rapaired_pat_Object;
	}

}
