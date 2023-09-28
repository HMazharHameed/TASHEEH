import java.util.List;

import javafx.util.Pair;

public class PatternEditDistanceResult {

	List<Pair<List<Object>, List<Object>>> pairs_of_patterns;
	List<Pair<Integer, Integer>> pairs_of_indices;
	List<Integer> row_indices;
	int pairs_score;
	
	
	public PatternEditDistanceResult(List<Pair<List<Object>, List<Object>>> pairs_of_patterns,
			List<Pair<Integer, Integer>> pairs_of_indices, List<Integer> row_indices, int pairs_score) {
		super();
		this.pairs_of_patterns = pairs_of_patterns;
		this.pairs_of_indices = pairs_of_indices;
		this.row_indices = row_indices;
		this.pairs_score = pairs_score;
	}
	
	
	
}
