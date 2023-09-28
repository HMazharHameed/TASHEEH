import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PatternRegeneration {

	public static List<ComparePatternCombinations> patternRegeneration (Map<Integer, List<String>> recordWithIndicesList, List<Integer> outlierRows)
	{
					PatternGeneration patGen_Object = new PatternGeneration();
					PruningPatterns pruPat_Object = new PruningPatterns();
					PatternSchema patSchema_Object = new PatternSchema();
					
					
					List<String> valueList = new ArrayList<String>();
					
					for(Entry<Integer,List<String>> mapValues : PatternRegeneration.extractRecords(recordWithIndicesList, outlierRows).entrySet())
					{
						valueList.addAll(mapValues.getValue());
					}
					
					int getMaxListSize = getMaxSizeList(PatternRegeneration.extractRecords(recordWithIndicesList, outlierRows));
					Map<Integer,List<List<Object>>> map_regeneratedPatterns = patGen_Object.patternComputation(valueList, getMaxListSize, Main_Class.univocityDetetced_delimiter);   
					List<List<Object>> optimalObjectList = pruPat_Object.patternWeights_patternPruning(map_regeneratedPatterns, 1, 1, outlierRows.size()); 
					List<ComparePatternCombinations> outputPatterns= patSchema_Object.schemaGeneration(Main_Class.row_T, outlierRows.size(), optimalObjectList);
					
					if(patSchema_Object.schemaGeneration(Main_Class.row_T, outlierRows.size(), optimalObjectList) == null)  
						return null;
					
						
					return outputPatterns;
	}

	
	public static Map<Integer, List<String>> extractRecords(Map<Integer, List<String>> recordWithIndicesList, List<Integer> possible_Outlier_Rows_indicies_LIST)
	{
		Map<Integer, List<String>> brokenRecords = new LinkedHashMap<Integer, List<String>>();
		
		for(int i= 0; i<possible_Outlier_Rows_indicies_LIST.size(); i++)
		{
			brokenRecords.put(possible_Outlier_Rows_indicies_LIST.get(i), recordWithIndicesList.get(possible_Outlier_Rows_indicies_LIST.get(i)));
		}
		
		return brokenRecords;
	}
	
	public static int getMaxSizeList (Map<Integer, List<String>> map)
	{
		int listSize = 0;
		
		for(Entry<Integer, List<String>> var: map.entrySet())
		{
			if(var.getValue().size()>listSize)
			{
				listSize = var.getValue().size();
			}
		}
		
		return listSize ;
	}
}
