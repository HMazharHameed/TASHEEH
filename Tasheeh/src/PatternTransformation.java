import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import abstractions.Gap_Class;
import javafx.util.Pair;


public class PatternTransformation {

	private static final Gap_Class GAP_CLASS = new Gap_Class();
    
	public void patternReciever(String cleanedFile, List<PatternAlignmentResult> patterns_to_Transform, Map<Integer, String[]> indicesForTransformation, List<Integer> wanted, List<Integer> wellFormed) 
	{
//		System.out.println("------------------------------------- Transformation Ready --------------------------------------");
		
		List<TransformationInputBlocks> patterns_For_Transformation = new ArrayList<TransformationInputBlocks>();
		for(PatternAlignmentResult pr : patterns_to_Transform)
		{
			List< Pair<String, Pair<List<Object>, List<Object>>> > blocks = new ArrayList<Pair<String,Pair<List<Object>,List<Object>>>>();
			List<String> keyList = new ArrayList<String>(pr.transformationBlocks.keySet());
	
			for(int i = 0; i < keyList.size(); i++) 
			{
			   if(i+1 < keyList.size())
			   {
				    if(keyList.get(i).toString().contains("Match") && !(keyList.get(i+1).toString().contains("Horizontal"))) 
				    {
				    	blocks.add(new Pair<String, Pair<List<Object>, List<Object>>>("Match", pr.pairs_of_patterns.get(i)));
				    }
				    else if(keyList.get(i).toString().contains("Mismatch") && !(keyList.get(i+1).toString().contains("Horizontal")))
				    {
				    	blocks.add(new Pair<String, Pair<List<Object>, List<Object>>>("Mismatch", pr.pairs_of_patterns.get(i)));
				    }
				    else if( (keyList.get(i).toString().contains("Mismatch") && keyList.get(i+1).toString().contains("Horizontal") )
				    		|| keyList.get(i).toString().contains("Horizontal"))
				    {
				    	blocks.add(new Pair<String, Pair<List<Object>, List<Object>>>("Horizontal", pr.pairs_of_patterns.get(i)));
				    }
				    else if( (keyList.get(i).toString().contains("Match") && keyList.get(i+1).toString().contains("Horizontal"))
				    		|| keyList.get(i).toString().contains("Horizontal"))
				    {
				    	blocks.add(new Pair<String, Pair<List<Object>, List<Object>>>("Horizontal", pr.pairs_of_patterns.get(i)));
				    }
				    else if(keyList.get(i).toString().contains("Vertical"))
				    {
				    	blocks.add(new Pair<String, Pair<List<Object>, List<Object>>>("Vertical", pr.pairs_of_patterns.get(i)));
				    } 
			    }
			    
		    }
			
			// combine  horizontal and vertical blocks together with position indices before sending them for transformations
	        
			List<Pair<Integer, List<Integer>>> position = new ArrayList<Pair<Integer,List<Integer>>>();
			List< Pair<String, Pair<List<Object>, List<Object>>> > new_blocks = new ArrayList<Pair<String,Pair<List<Object>,List<Object>>>>();
			int start = 0;
			
			for(int i = 0; i< blocks.size(); i++)
			{
				if(blocks.get(i).getKey().toString().matches("Match") || blocks.get(i).getKey().toString().matches("Mismatch"))
				{
					position.add(new Pair(start,i));
					new_blocks.add(new Pair(blocks.get(i).getKey(), new Pair (new ArrayList<>(blocks.get(i).getValue().getKey()), new ArrayList<>(blocks.get(i).getValue().getValue()))));
//					new_blocks.add(blocks.get(i));
					start++;
					
				}
				else if(blocks.get(i).getKey().toString().matches("Horizontal"))
				{
					List<Object> mergeList_Keys = new ArrayList<Object>();
					List<Object> mergeList_Values = new ArrayList<Object>();
					int blockSize = 0;
					mergeList_Keys.addAll(blocks.get(i).getValue().getKey());		
					while(i< blocks.size() && blocks.get(i).getKey().toString().matches("Horizontal"))
					{
						mergeList_Values.add(blocks.get(i).getValue().getValue());
						i++;
						blockSize++;
					}
					i--;
					new_blocks.add(new Pair("Horizontal", new Pair (new ArrayList<>(mergeList_Keys), new ArrayList<>(mergeList_Values))));
					
					List l = new LinkedList<>();
					for(int m = 0; m<blockSize; m++)
					{
						l.add(i-m);
					}
					Collections.reverse(l);
					position.add(new Pair(start, l));
					
					start++;
				}
				
				else if(blocks.get(i).getKey().toString().matches("Vertical"))
				{
					position.add(new Pair(start, i));
					new_blocks.add(new Pair(blocks.get(i).getKey(), new Pair (new ArrayList<>(blocks.get(i).getValue().getKey()), new ArrayList<>(blocks.get(i).getValue().getValue()))));
//					new_blocks.add(blocks.get(i));
					start++;
				}
				
			}
			
			// creating instance of ready for transformation blocks 
			patterns_For_Transformation.add(new TransformationInputBlocks(new_blocks, position, pr.row_indices));
    	 }  
	     
		 // clean records table
		 Map<Integer, String[]> cleanRecords = new LinkedHashMap<Integer, String[]>();
		 cleanRecords = PatternTransformation.extractRecords(indicesForTransformation, wellFormed);  // add well formed (wanted) records to clean records list
		
//		 for(Entry<Integer, String[]> mapValues: cleanRecords.entrySet())
//		 {
//			 for(String s: mapValues.getValue())
//				 System.out.println("new values  "+s);
//		 }
		 
		
		 for(int i = 0; i<patterns_For_Transformation.size(); i++)
		 {
			 
    		 PatternTransformationOperators patternTransformationOperator_Object = new PatternTransformationOperators();			 
			 
			 Map<Integer, String[]> valueMap = new LinkedHashMap<Integer, String[]>();
	
			 valueMap = PatternTransformation.extractRecords(indicesForTransformation, patterns_For_Transformation.get(i).row_indices); 
			 
			 cleanRecords.putAll( patternTransformationOperator_Object.cleanErrors( valueMap , patterns_For_Transformation.get(i) )); 
		 }
		 
		 // printing final output 
		 LinkedHashMap<Integer, String[]> sortedMap = new LinkedHashMap<>();
		 cleanRecords.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		 
		 
		 Csv_Writer.cleanCSV(sortedMap, cleanedFile);
	 }
	
	public static Map<Integer, String[]> extractRecords(Map<Integer, String[]> recordWithIndicesList, List<Integer> indices)
	{
		Map<Integer, String[]> brokenRecords = new LinkedHashMap<Integer, String[]>();
		
		for(int i= 0; i<indices.size(); i++)
		{
			brokenRecords.put(indices.get(i), recordWithIndicesList.get(indices.get(i)));
		}
		
		return brokenRecords;
	}
	
}

