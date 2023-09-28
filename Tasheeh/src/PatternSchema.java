import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

import abstractions.Arithmetic_Oprt_Class;
import abstractions.EmptyValues_Class;
import abstractions.Padded_Class;
import abstractions.Upper_Letter_Class;

public class PatternSchema {

	private static final Padded_Class PADDED_CLASS = new Padded_Class();
	static  Map<String, List<List<Object>>> storeAbstractionsMap = new LinkedHashMap<String, List<List<Object>>>();
    static Set<Integer> already_parsed_rows = new HashSet<Integer>();
	
	public List<ComparePatternCombinations> schemaGeneration (int row_T, int all_rows_list, List<List<Object>> optimal_pattern)
	{
		
//		for(int i = 0; i<optimal_pattern.size(); i++)
//		{
//			System.out.println(optimal_pattern.get(i));
//		}
		
		for(int i = 0; i< optimal_pattern.size(); i++)
	   	{
			if(i+1 < optimal_pattern.size() &&  (int)(((List)optimal_pattern.get(i+1).get(0)).get(0)) - (int)(((List)optimal_pattern.get(i).get(0)).get(0)) > 1)
			{
				return null;
			}
			
	   	}
		
		int threshold = (int)((float)(row_T*all_rows_list/100.0));  
		if(threshold == 0)
			threshold = 1;
		
			ArrayList<ComparePatternCombinations> temp = new ArrayList<>();
			if(!(optimal_pattern.isEmpty()))
			{
				for(Object l : optimal_pattern.get(0))
				{
					List<Object> lPat = (List<Object>) l;
					temp.add(new ComparePatternCombinations((List<Object>) lPat.get(1), (int) lPat.get(0), (List<Integer>)lPat.get(2)));
				}
			}
			
			try
			{
				for (int i = 1; i < optimal_pattern.size();i++)
		        {
					ArrayList<ComparePatternCombinations> temp1 = new ArrayList<>();
					if(!(optimal_pattern.isEmpty()))
					{
						for(Object l : optimal_pattern.get(i) )
						{
							List<Object> lPat = (List<Object>) l;
							temp1.add(new ComparePatternCombinations((List<Object>) lPat.get(1), (int) lPat.get(0), (List<Integer>)lPat.get(2)));
						}
					}

		           temp = compare(temp, temp1, threshold);  
		        }	
			}
	        catch(Exception ex)
			{
	        	System.out.println(ex);
			}
			
			class ListComparatorPLI implements Comparator<ComparePatternCombinations> {

				  @Override
				  public int compare(ComparePatternCombinations o1, ComparePatternCombinations o2) {					
				      
				    return Integer.compare(o1.intersection.size(), o2.intersection.size());
				  }

				}   
			
			
			Collections.sort(temp, new ListComparatorPLI());
			
			
			List<ComparePatternCombinations> cleanPatterns = new ArrayList<>();
			
			
			for(int i = 0; i< temp.size(); i++)
			{
				boolean foundGeneric = false;
				
				for( int j = i+1; j< temp.size(); j++)
				{
					if(temp.get(j).intersection.containsAll(temp.get(i).intersection))
					{
						foundGeneric = true;
						break;
					}
				}
				if(!foundGeneric)
				{
					cleanPatterns.add(temp.get(i));
				}
			}
			
			for(int i = 0; i<cleanPatterns.size(); i++)
			{
				Iterator list_ITR = cleanPatterns.get(i).abstrcationInstances.iterator();
				
				while(list_ITR.hasNext())
				{
					List l = (List) list_ITR.next();
					
					if(l.get(0) instanceof Padded_Class)
					{
						list_ITR.remove();
					}
				}
			}
			
			return cleanPatterns;
			
	}
	  
	
	public static ArrayList<ComparePatternCombinations> compare(ArrayList<ComparePatternCombinations> list1, ArrayList<ComparePatternCombinations> list2, float threshold) {
	    ArrayList<ComparePatternCombinations> result = new ArrayList<>();
	   
	    
	    for(ComparePatternCombinations c1: list1)
	    {
	    	for(ComparePatternCombinations c2 : list2)
	    	{
	    		ComparePatternCombinations intersectionOfCPC = c1.combine(c2, threshold);
	    		if(intersectionOfCPC != null)
	    		{
	    			result.add(intersectionOfCPC);
	    		}
	    	}
	    }
	    return result;
	}

 
}
