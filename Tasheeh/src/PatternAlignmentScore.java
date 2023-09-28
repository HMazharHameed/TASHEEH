import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

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
import javafx.util.Pair;

public class PatternAlignmentScore {

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
	
	static Map<LinkedHashMap<List<Pair<List<Object>, List<Object>>>, List<Pair<Integer, Integer>>>, Float> optimal_Alignment;

	public static Map<LinkedHashMap<List<Pair<List<Object>, List<Object>>>, List<Pair<Integer, Integer>>>, Float> getAlignmentScore(Map<List<Pair<List<Object>, List<Object>>>, List<Pair<Integer, Integer>>> alignments)
	{ 
		optimal_Alignment = new LinkedHashMap<LinkedHashMap<List<Pair<List<Object>,List<Object>>>,List<Pair<Integer,Integer>>>, Float>();
		int alignment_counter = 0;
		Map<Integer, Float> index_value_MAP = new LinkedHashMap<Integer, Float>();
		List<List<Object>> value_and_alignment_Storer = new LinkedList<List<Object>>();
		
		
		for(Entry<List<Pair<List<Object>,List<Object>>>, List<Pair<Integer, Integer>>> entry : alignments.entrySet())	
		{
		    index_value_MAP.put(alignment_counter, parseAlignmentSegments(entry.getKey()));
			List subList = new LinkedList<>();
			subList.add(alignment_counter);
			subList.add(entry.getKey());
			subList.add(entry.getValue());
			subList.add(parseAlignmentSegments(entry.getKey()));
			value_and_alignment_Storer.add(subList);
			alignment_counter++;
		}
		
		
		Float min_score = Collections.min(index_value_MAP.values());
		List<Integer> indix_extractor = new LinkedList<Integer>();            
		for (Entry<Integer, Float> entry : index_value_MAP.entrySet()) 
		{
			if (entry.getValue().equals(min_score)) 
				indix_extractor.add(entry.getKey());  
		}
		
		// Incremental alignment for the sequences with the same score.
		if(indix_extractor.size() > 1)
		{
			incremental_Alignment_Score(indix_extractor, value_and_alignment_Storer);
		}
		else
		{
			LinkedHashMap<List<Pair<List<Object>, List<Object>>>, List<Pair<Integer, Integer>>> map = new LinkedHashMap<>();
			map.put((List<Pair<List<Object>, List<Object>>>) value_and_alignment_Storer.get(indix_extractor.get(0)).get(1), 
					(List<Pair<Integer, Integer>>) value_and_alignment_Storer.get(indix_extractor.get(0)).get(2));
			optimal_Alignment.put(map, min_score);	
		}
		
		
		
		return optimal_Alignment;
	}
	
	public static float parseAlignmentSegments(List<Pair<List<Object>, List<Object>>> list)
	{
		float score = 0;
		final DecimalFormat df = new DecimalFormat("0.00");
		for(Pair<List<Object>, List<Object>> p : list)
		{
//			System.out.println(p+"    score   "+ pairScore(p.getKey(), p.getValue()));
			score += pairScore(p.getKey(), p.getValue());
		}
		
		return score/list.size();
	}
	
	public static float pairScore(List<Object> listONE, List<Object> listTWO)
	{
		 
		if (listONE.equals(listTWO))
			return 0;
		else if(listONE.toString().contains(CANDIDATE_DELIMITER_CLASS.toString()) || listTWO.toString().contains(CANDIDATE_DELIMITER_CLASS.toString()))
			return 1;
		else if(listONE.toString().contains(GAP_CLASS.toString()) || listTWO.toString().contains(GAP_CLASS.toString()))
			return 1;
		else if(listONE.toString().contains(EMPTY_VALUES_CLASS.toString()) || listTWO.toString().contains(EMPTY_VALUES_CLASS.toString()))
			return 1;
		else if(listONE.toString().contains(MISSING_VALUES_CLASS.toString()) || listTWO.toString().contains(MISSING_VALUES_CLASS.toString()))
			return 1;
		else if(listONE.toString().contains(LINE_BREAK_CLASS.toString()) || listTWO.toString().contains(LINE_BREAK_CLASS.toString()))
			return 1;
		else
		{
			int countSymbol_ListONE = 0, countLetters_ListONE = 0, countDigits_ListONE = 0;
			int countSymbol_ListTWO = 0, countLetters_ListTWO = 0, countDigits_ListTWO = 0;
			
			for(Object obj: listONE)
			{
				if(obj.toString().matches("[0-9]") || digit.contains(obj.toString()))
					countDigits_ListONE++;
				else if (obj.toString().matches("[a-zA-Z]") || letter.contains(obj.toString()))
					countLetters_ListONE++;
				else
					countSymbol_ListONE++;
			}
			
			for(Object obj: listTWO)
			{
				if(obj.toString().matches("[0-9]") || digit.contains(obj.toString()))
					countDigits_ListTWO++;
				else if (obj.toString().matches("[a-zA-Z]") || letter.contains(obj.toString()))
					countLetters_ListTWO++;
				else
					countSymbol_ListTWO++;
			}
			
//			System.out.println(countSymbol_ListONE);
//			System.out.println(countSymbol_ListTWO);
//			System.out.println();
//			System.out.println(countDigits_ListONE);
//			System.out.println(countDigits_ListTWO);
//			System.out.println();
//			System.out.println(countLetters_ListONE);
//			System.out.println(countLetters_ListTWO);
//			
//			System.out.println(Math.min(countSymbol_ListONE, countSymbol_ListTWO) + Math.min(countDigits_ListONE, countDigits_ListTWO) + Math.min(countLetters_ListONE, countLetters_ListTWO));
//			System.out.println(Math.max(listONE.size(), listTWO.size()));
			
			int sum = Math.min(countSymbol_ListONE, countSymbol_ListTWO) + Math.min(countDigits_ListONE, countDigits_ListTWO) + Math.min(countLetters_ListONE, countLetters_ListTWO);
			int size = Math.max(listONE.size(), listTWO.size());
			float out = (float) sum / size;
			return  1 - out;
		}
	}
	
    public static void incremental_Alignment_Score(List<Integer> indix_extractor, List<List<Object>> value_and_alignment_Storer) 
    {
		
      	Map<List<Pair<List<Object>, List<Object>>>, List<Pair<Integer, Integer>>> repairedSequneceOutput = new LinkedHashMap<List<Pair<List<Object>,List<Object>>>, List<Pair<Integer,Integer>>>();
      	Map<Map<List<Pair<List<Object>, List<Object>>>, List<Pair<Integer, Integer>>>, Float> test = new LinkedHashMap<Map<List<Pair<List<Object>,List<Object>>>,List<Pair<Integer,Integer>>>, Float>();
		for(int in : indix_extractor)
		{
			repairedSequneceOutput.put(repairAlignments((List<Pair<List<Object>, List<Object>>>) value_and_alignment_Storer.get(in).get(1)), 
					(List<Pair<Integer, Integer>>) value_and_alignment_Storer.get(in).get(2));
		}
		
		for(Entry<List<Pair<List<Object>,List<Object>>>, List<Pair<Integer, Integer>>> entry : repairedSequneceOutput.entrySet())	
		{
			Map map = new LinkedHashMap<>();
			for(int m = 0; m < ((List<Pair<List<Object>, List<Object>>>)(entry.getKey())).size(); m++)
			{
				if(((List<Pair<List<Object>, List<Object>>>)(entry.getKey())).get(m).getKey().toString().contains(GAP_CLASS.toString()))
				{
					Iterator itr = ((List<Pair<List<Object>, List<Object>>>)(entry.getKey())).get(m).getKey().iterator();
					while (itr.hasNext())
					{
						Object obj = itr.next();
						if(obj.equals(GAP_CLASS.toString()))
							itr.remove();
					}
				}
			}
			map.put(entry.getKey(), entry.getValue());
			test.put(map, PatternAlignmentScore.parseAlignmentSegments(entry.getKey()));	
		}
		
		Float min_score_alignment = Collections.min(test.values()); 
		optimal_Alignment.put(((LinkedHashMap<List<Pair<List<Object>, List<Object>>>, List<Pair<Integer, Integer>>>) getKey(test , min_score_alignment)), min_score_alignment); 
	}
    
    public static List<Pair<List<Object>, List<Object>>> repairAlignments(List<Pair<List<Object>, List<Object>>> pairedAlignment) 
    {   
       
    	List<Pair<List<Object>, List<Object>>> repaired = new ArrayList<Pair<List<Object>,List<Object>>>();
    	boolean flag_for_Delimiter = false;  
    	boolean flag_for_Text = false;
    	boolean flag_for_Gap = false;
    	boolean gap_after_Text = false;
    	boolean gap_after_Delimiter = false;
    	for(int i = 0; i < pairedAlignment.size(); i++)
    	{
    		if( ( !(pairedAlignment.get(i).getKey().toString().contains(GAP_CLASS.toString()))  && !(pairedAlignment.get(i).getValue().toString().contains(GAP_CLASS.toString()))  )
    				&& flag_for_Gap == false )
    		{
    		   if(!(pairedAlignment.get(i).getKey().toString().contains(CANDIDATE_DELIMITER_CLASS.toString()))  && !(pairedAlignment.get(i).getValue().toString().contains(CANDIDATE_DELIMITER_CLASS.toString())))
    			{
    			   repaired.add(new Pair(pairedAlignment.get(i).getKey(),pairedAlignment.get(i).getValue()));
    			   flag_for_Text = true;
    			   flag_for_Delimiter = false;
    		    }
    		   else if(pairedAlignment.get(i).getKey().toString().contains(CANDIDATE_DELIMITER_CLASS.toString())  && pairedAlignment.get(i).getValue().toString().contains(CANDIDATE_DELIMITER_CLASS.toString()))
   			   {
   			       repaired.add(new Pair(pairedAlignment.get(i).getKey(),pairedAlignment.get(i).getValue()));
   			       flag_for_Delimiter = true;
   			       flag_for_Text = false;
   		       }   
    		}
    		else if( (pairedAlignment.get(i).getKey().toString().contains(CANDIDATE_DELIMITER_CLASS.toString()) && 
    				pairedAlignment.get(i).getValue().toString().contains(CANDIDATE_DELIMITER_CLASS.toString()) )   && flag_for_Gap == true )
	   		{
  			       repaired.add(new Pair(pairedAlignment.get(i).getKey(),pairedAlignment.get(i).getValue()));
  			       flag_for_Gap = false;
  			       flag_for_Delimiter = true;
  		    }  
    		else
    		{
    			if(flag_for_Text)
    			{
    				gap_after_Text = true;
    			}
    			else if(flag_for_Delimiter)
    			{
    				gap_after_Delimiter = true;
    			}
    			
    			if(pairedAlignment.get(i).getValue().toString().contains(CANDIDATE_DELIMITER_CLASS.toString()))
    			{
    				pairedAlignment.get(i).getValue().set(0,  Main_Class.univocityDetetced_delimiter);
    			}
    			
    			if(gap_after_Text) 
    			{
    				repaired.get(repaired.size()-1).getValue().addAll(pairedAlignment.get(i).getValue());
    				flag_for_Gap = true;
    			}
    			else if(gap_after_Delimiter) 
    			{
    				repaired.add(new Pair(pairedAlignment.get(i).getKey(),pairedAlignment.get(i).getValue()));
    				flag_for_Gap = true;
    				gap_after_Delimiter = false;
    				flag_for_Delimiter = false;
    			}
    			else
    			{
    				if(repaired.isEmpty())
    				{
    					repaired.add(new Pair(pairedAlignment.get(i).getKey(),pairedAlignment.get(i).getValue()));
    					flag_for_Gap = true;
    				}
    				else
    				{
    					repaired.get(repaired.size()-1).getKey().addAll(pairedAlignment.get(i).getKey());
        				repaired.get(repaired.size()-1).getValue().addAll(pairedAlignment.get(i).getValue());
        				flag_for_Gap = true;
    				}
    				
    			}
    			
    				
    		}
    	}
		return repaired;
    }
    
    public static Object getKey(Map<Map<List<Pair<List<Object>, List<Object>>>, List<Pair<Integer, Integer>>>, Float> test, float value)  
	 {
	        for (Object key: test.keySet())
	        {
	        	float compare = test.get(key);
	            if (compare == value) {
	                return key;
	            }
	        }
	        return null;
	  } 
	
}
