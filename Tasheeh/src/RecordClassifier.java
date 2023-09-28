import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecordClassifier {
	
	public List<Integer> ill_well_classification(List<ComparePatternCombinations> cpcList, List<Integer> all_rows_list)
	{
	    Set<Integer> already_parsed_rows = new HashSet<Integer>();
		
		for(ComparePatternCombinations cpc : cpcList)
		{
			already_parsed_rows.addAll(cpc.intersection);
		}
		
		List<Integer> parsed_rows_indicies_LIST = new ArrayList<Integer>(already_parsed_rows);
		Collections.sort(parsed_rows_indicies_LIST);
			
		List<Integer> possible_Outlier_Rows_indicies_LIST = new ArrayList<Integer>();
		for (Integer item : all_rows_list) {
		    if (parsed_rows_indicies_LIST.contains(item)) {
		    } else {
		    	possible_Outlier_Rows_indicies_LIST.add(item);
		    }
		}
		return possible_Outlier_Rows_indicies_LIST;
	}  
}
