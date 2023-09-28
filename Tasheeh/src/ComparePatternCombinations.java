import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class ComparePatternCombinations {

	public List<List<Object>> abstrcationInstances;
	public List<Integer> columnIndices;
	public List<Integer> intersection;
	
	public ComparePatternCombinations (List<Object> abstractions, int column, List<Integer> rows)
	{
		abstrcationInstances = Arrays.asList(abstractions);   
		columnIndices = Arrays.asList(column);
		intersection = rows;
		
	}
	
	
	public ComparePatternCombinations (List<List<Object>> abstractions, List<Integer> column, List<Integer> rows)
	{
		abstrcationInstances = abstractions;
		columnIndices = column;
		intersection = rows;
		
	}
	
	
	public ComparePatternCombinations combine (ComparePatternCombinations cpc, float threshold)
	{
		
	            ArrayList<Integer> intersection = intersection_Guava(this.intersection, cpc.intersection);
	            if (intersection.size() >= threshold) {                                              // change to >= on 08 February 2022 to allow patterns for a single row
	            	
	            	List<List<Object>> abstractionCombined = new ArrayList(this.abstrcationInstances);
	            	abstractionCombined.addAll(cpc.abstrcationInstances);
	            	
	            	List<Integer> columnsCombined = new ArrayList(this.columnIndices);
	            	columnsCombined.addAll(cpc.columnIndices);
	            	
                    return new ComparePatternCombinations(abstractionCombined, columnsCombined, intersection);

	            }
	            
	    return null;
	}
	
	
  public static ArrayList<Integer> intersection_Guava(List<Integer> intersection2, List<Integer> intersection3 ){
		
		
		Set<Integer> result = Sets.intersection(Sets.newHashSet(intersection2), Sets.newHashSet(intersection3));
		ArrayList<Integer> result1 = new ArrayList<Integer>(result);    
	    return result1;
		
	}


	public static ArrayList<Integer> intersection(List<Integer> intersection2, List<Integer> intersection3 ){
	        Set<Integer> result = intersection2.stream()
	            		  .distinct()
	            		  .filter(intersection3::contains)
	            		  .collect(Collectors.toSet());
	            ArrayList<Integer> result1 = new ArrayList<Integer>(result);    
	    return result1;
	}   


	@Override
	public String toString() {
		return "ComparePatternCombinations [abstrcationInstances=" + abstrcationInstances + ", columnIndices="
				+ columnIndices + ", intersection=" + intersection + "]";
	}
	
	
	
}
