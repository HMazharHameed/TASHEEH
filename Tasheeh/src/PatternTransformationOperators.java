import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.spec.PSource;
import javax.swing.RepaintManager;
import javax.swing.text.Position;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.util.concurrent.CycleDetectingLockFactory.PotentialDeadlockException;
import com.sun.javafx.scene.EnteredExitedHandler;

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
import abstractions.Padded_Class;
import abstractions.Quotation_Class;
import abstractions.Sequence_Digit_Class;
import abstractions.Sequence_LowerLetter_Class;
import abstractions.Sequence_UpperLetter_Class;
import abstractions.Space_Class;
import abstractions.Symbol_Class;
import abstractions.Text_Class;
import abstractions.Upper_Letter_Class;
import abstractions.WhiteSpace_Class;
import algebra.Drop;
import algebra.Merge;
import algebra.Extract;
import javafx.util.Pair;

public class PatternTransformationOperators {


	private static final Padded_Class PADDED_CLASS = new Padded_Class();
	private static final Gap_Class GAP_CLASS = new Gap_Class();
	private static final Candidate_Delimiter_Class CANDIDATE_DELIMITER_CLASS = new Candidate_Delimiter_Class();
	
	private static Map<Integer, String[]> transformedd = new LinkedHashMap<Integer, String[]>();
	
	public Map<Integer, String[]> cleanErrors(Map<Integer, String[]> valuesMap, TransformationInputBlocks patternBlocks) 
	{
		 
		 transformedd = valuesMap;
//		 for(Entry<Integer, String[]> mapValues: valuesMap.entrySet())
//		 {
//			 for(String s: mapValues.getValue())
//				 System.out.println("new values  "+s);
//		 }
		 
		 for(int i = 0; i < patternBlocks.blocks.size(); i++)
		 {
			 if(patternBlocks.blocks.get(i).getKey().toString().contains("Match"))
			 {
				 // skip blocks -- no transformation required  --
			 }
			 else if(patternBlocks.blocks.get(i).getKey().toString().contains("Mismatch"))
			 {
				 PatternTransformationOperators.diagonal_Transform(transformedd, patternBlocks.blocks.get(i).getValue(), patternBlocks.positions.get(i) );
			 }
			 else if(patternBlocks.blocks.get(i).getKey().toString().contains("Horizontal"))
			 {
				PatternTransformationOperators.horizontal_Transform(transformedd, patternBlocks.blocks.get(i).getValue(), patternBlocks.positions.get(i) );
			 }
			 else if(patternBlocks.blocks.get(i).getKey().toString().contains("Vertical"))
			 { 
				 PatternTransformationOperators.vertical_Transform(transformedd, patternBlocks.blocks.get(i).getValue(), patternBlocks.positions.get(i) );
				
//				 for(Entry<Integer, String[]> mapValues: transformedd.entrySet())
//				 {
//					 String[] test = mapValues.getValue(); 	
//					 System.out.println("Transformed Row");
//					 for(int j = 0; j <test.length;j++)
//					 {
//						 System.out.print(test[j]);
//					 }
//				 }
			 }
		 }
		
		return transformedd;
	}

	public static Map<Integer, String[]> diagonal_Transform(Map<Integer, String[]> valuesMap, Pair<List<Object>, List<Object>> pair, Pair<Integer, List<Integer>> postion_pair)
	{		
		List<Object> readyto_Transform = new ArrayList<Object>(pair.getValue());
		
		Extract split_Object = new Extract();
		Map<Integer, Object> split_Output = split_Object.apply(pair.getKey(), pair.getValue());
				
		for(Entry<Integer, String[]> mapValues: valuesMap.entrySet())
		{
			if(split_Output == null || split_Output.isEmpty())
			{
				transformedd.put(mapValues.getKey(), mapValues.getValue());
			}
			else
			{
				String[] test = mapValues.getValue();
				List<String> reparied_new = new ArrayList<String>();
				for (int j = 0; j < test.length; j++)
				{
				  reparied_new.add(test[j]);
				}
							    
			    String toBeFixed = reparied_new.get(postion_pair.getKey());
			    StringBuilder sb = new StringBuilder();
			    
			    List<Integer> deleteEntries = new ArrayList<Integer>();
			    for(Entry<Integer, Object> entryVal: split_Output.entrySet())
			    {
			    	deleteEntries.add(entryVal.getKey());
			    }
		        
			    boolean flag_for_character_check = true;
			    for(int i = 0; i <readyto_Transform.size(); i++)
			    {
			    	if(!(readyto_Transform.get(i) instanceof Character))
			    	{
			    		flag_for_character_check = false;
			    	}
			    }

			    if(flag_for_character_check == true)
			    {
			    	for(int j = 0; j < toBeFixed.length(); j++)
				    {
				    	if(!(deleteEntries.contains(j)))
						    sb.append(toBeFixed.charAt(j));
				    }
			    }
			    else 
			    {
			    	List<Object> string_TO_object = new ArrayList<Object>(); 
			    	for(int k = 0; k<toBeFixed.length(); k++) 
			    	{
			    		string_TO_object.add(toBeFixed.charAt(k));  
			    	}
			    	
			    	GetAbstractionIndex getIndex_Object = new GetAbstractionIndex();

			    	for(Entry<Integer,List<Integer>> entry : getIndex_Object.abstractionIndex(readyto_Transform, new ArrayList<Object>(string_TO_object)).entrySet())
			    	{
			    		if(deleteEntries.contains(entry.getKey()))
			    		{
			    			for(int m = 0; m<entry.getValue().size();m++)
			    			{
			    				string_TO_object.set(entry.getValue().get(m), PADDED_CLASS);
			    			}
			    		}
			    	}
			    	
			    	string_TO_object.removeIf( obj -> obj.equals(PADDED_CLASS) );

			    	//converting List<object> to string
			    	for(int i =0; i<string_TO_object.size(); i++)
			    	{
			    		sb.append(string_TO_object.get(i));
			    	}
			    }
		
			    reparied_new.set(postion_pair.getKey(), sb.toString());
		    
				String[] stringArr = new String[reparied_new.size()];
				stringArr = reparied_new.toArray(new String[0]);

				transformedd.put(mapValues.getKey(), stringArr);
			}
			
		}
		
			return transformedd;
	}
	
	public static boolean noQuoteEnd(List<Object> input) 
	{
		if(input.get(input.size()-1) == Main_Class.univocityDetetced_QUOTE || (input.get(input.size()-1) instanceof Character && (char)input.get(input.size()-1) == 34) )
			return true;
		else
		   return false;
	}

	public static Map<Integer, String[]> horizontal_Transform(Map<Integer, String[]> valuesMap, Pair<List<Object>, List<Object>> pair, Pair<Integer, List<Integer>> postion_pair)
	{
		
		if( !(pair.getValue().get(0).toString().contains(Main_Class.univocityDetetced_QUOTE)) || noQuoteEnd((List<Object>)pair.getValue().get(0)) )
		{
			
			List<Object> delete_metadata = new ArrayList<Object>(); 
			
			int index = 0;
			
			for(int j = 0; j<pair.getValue().size(); j++)
			{
				if(pair.getValue().get(j).toString().contains(CANDIDATE_DELIMITER_CLASS.toString()))
				{
				    index = pair.getValue().indexOf(pair.getValue().get(j));
					break;
				}
			}
			
			pair.getValue().subList(index, pair.getValue().size()).clear();  
			List<Object> new_valueList =  new ArrayList<Object>((List<Object>) pair.getValue().get(0)) ; 
			
			 List<Object> readyto_Transform = new ArrayList<Object>((List<Object>) pair.getValue().get(0)) ;
			
			for(int i = postion_pair.getKey()+1; i<postion_pair.getValue().get(postion_pair.getValue().size()-1)+1; i++)
			{
				delete_metadata.add(i);
			}
			
			Extract split_Object = new Extract();
			Map<Integer, Object> split_Output = split_Object.apply(pair.getKey(), new_valueList); 
			
			for(Entry<Integer, String[]> mapValues: valuesMap.entrySet())
			{
				if(split_Output == null || split_Output.isEmpty())
				{
					if(delete_metadata.isEmpty())
					{
						transformedd.put(mapValues.getKey(), mapValues.getValue());
					}
					else  
					{
						String[] test = mapValues.getValue();
						List<String> reparied_new = new ArrayList<String>();
						for (int j = 0; j < test.length; j++)
						{
						  reparied_new.add(test[j]);
						}
						
						try {
							 reparied_new.subList((int)delete_metadata.get(0), (int)delete_metadata.get(delete_metadata.size()-1)+1).clear();  // +1 because sublist excludes the last item 
						}
					    catch(Exception ex)
					    {
					    	System.out.println("Exception  "+ ex);
					    }
					    					    
						String[] stringArr = new String[reparied_new.size()];
						stringArr = reparied_new.toArray(new String[0]);

						transformedd.put(mapValues.getKey(), stringArr);
					}
					
				}
				else
				{
					String[] test = mapValues.getValue();
					List<String> reparied_new = new ArrayList<String>();
					for (int j = 0; j < test.length; j++)
					{
					  reparied_new.add(test[j]);
					}
					
				    reparied_new.subList((int)delete_metadata.get(0), (int)delete_metadata.get(delete_metadata.size()-1)+1).clear();  // +1 because sublist excludes the last item 
				    
				    String toBeFixed = reparied_new.get(postion_pair.getKey());
				    StringBuilder sb = new StringBuilder();
				    
				    List<Integer> deleteEntries = new ArrayList<Integer>();
				    for(Entry<Integer, Object> entryVal: split_Output.entrySet())
				    {
				    	deleteEntries.add(entryVal.getKey());
				    }
			        
				    boolean flag_for_character_check = true;
				    for(int i = 0; i <readyto_Transform.size(); i++)
				    {
				    	if(!(readyto_Transform.get(i) instanceof Character))
				    	{
				    		flag_for_character_check = false;
				    	}
				    }
				   
				    if(flag_for_character_check == true)
				    {
				    	for(int j = 0; j < toBeFixed.length(); j++)
					    {
					    	if(!(deleteEntries.contains(j)))
							    sb.append(toBeFixed.charAt(j));
					    }
				    }
				    else 
				    {
				    	
				    	List<Object> string_TO_object = new ArrayList<Object>(); 
				    	for(int k = 0; k<toBeFixed.length(); k++) 
				    	{
				    		string_TO_object.add(toBeFixed.charAt(k));   
				    	}
				    	
				    	GetAbstractionIndex getIndex_Object = new GetAbstractionIndex();
				    	
				    	for(Entry<Integer,List<Integer>> entry : getIndex_Object.abstractionIndex(new ArrayList<Object>(readyto_Transform), new ArrayList<Object>(string_TO_object)).entrySet())
				    	{
				    		if(deleteEntries.contains(entry.getKey()))
				    		{
				    			for(int m = 0; m<entry.getValue().size();m++)
				    			{
				    				string_TO_object.set(entry.getValue().get(m), PADDED_CLASS);
				    			}
				    		}
				    	}
				    	
				    	string_TO_object.removeIf( obj -> obj.equals(PADDED_CLASS) );

				    	for(int i =0; i<string_TO_object.size(); i++)
				    	{
				    		sb.append(string_TO_object.get(i));
				    	}
				    }
				   	reparied_new.set(postion_pair.getKey(), sb.toString());
			    
					String[] stringArr = new String[reparied_new.size()];
					stringArr = reparied_new.toArray(new String[0]);

					transformedd.put(mapValues.getKey(), stringArr);
				}
				
			}
			
		}
		else
		{
			for(Entry<Integer, String[]> mapValues: valuesMap.entrySet())
			{
				String[] test = mapValues.getValue();
				List<String> reparied_new = new ArrayList<String>();
				for (int j = 0; j < test.length; j++)
				{
				  reparied_new.add(test[j]);
				}
				
				Merge merge_Object = new Merge();
			    StringBuilder toBeFixed = new StringBuilder();
			   
			    for(int t = postion_pair.getValue().get(0); t<=postion_pair.getValue().get(postion_pair.getValue().size()-1); t++)
			    {
			    	toBeFixed.append(reparied_new.get(t));
			    }
			   
			    for(int t = postion_pair.getValue().get(0); t<=postion_pair.getValue().get(postion_pair.getValue().size()-1); t++)
			    {	
			    	reparied_new.set(t, PADDED_CLASS.toString()); 
			    }
			    
			    reparied_new.set(postion_pair.getKey(), merge_Object.reQuote_reEscape(toBeFixed.toString(), Main_Class.univocityDetetced_QUOTE, Main_Class.univocityDetetced_ESCAPE));  // sending shifted values for repairing
			    
				String[] stringArr = new String[reparied_new.size()];
				stringArr = reparied_new.toArray(new String[0]);

				transformedd.put(mapValues.getKey(), stringArr);
				}	
		} 
		
		return transformedd;
	}
	
	public static Map<Integer, String[]> vertical_Transform(Map<Integer, String[]> valuesMap, Pair<List<Object>, List<Object>> pair, Pair<Integer, List<Integer>> postion_pair)
	{
		if(pair.getKey().toString().contains(CANDIDATE_DELIMITER_CLASS.toString()) && pair.getValue().toString().contains(GAP_CLASS.toString()))
		{
			 for(Entry<Integer, String[]> mapValues: valuesMap.entrySet())
			 {
				String[] test = mapValues.getValue();
				List<String> reparied_new = new ArrayList<String>();
				
				for (int j = 0; j < test.length; j++)
				{
					reparied_new.add(test[j]);
				}
		
				reparied_new.add(postion_pair.getKey(), Main_Class.univocityDetetced_delimiter);   
				
				String[] stringArr = new String[reparied_new.size()];
				stringArr = reparied_new.toArray(new String[0]);

				
				transformedd.put(mapValues.getKey(), stringArr);
			 }
		}
		else if(!(pair.getKey().toString().contains(CANDIDATE_DELIMITER_CLASS.toString())) && pair.getValue().toString().contains(GAP_CLASS.toString()))
		{
			for(Entry<Integer, String[]> mapValues: valuesMap.entrySet())
			 {
				String[] test = mapValues.getValue();
				List<String> reparied_new = new ArrayList<String>();
				
				for (int j = 0; j < test.length; j++)
				{
					reparied_new.add(test[j]);
				}
				
				reparied_new.add(postion_pair.getKey(), null);    
				
				String[] stringArr = new String[reparied_new.size()];
				stringArr = reparied_new.toArray(new String[0]);

				
				transformedd.put(mapValues.getKey(), stringArr);
			 }
		}
		else
		{
			System.out.println("Vertical transformation error at! "+ pair);
		}
		 
		return transformedd;
	}
}
