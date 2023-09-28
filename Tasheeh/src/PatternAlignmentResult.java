import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class PatternAlignmentResult {

	List<List<Object>> dominantPattern ;
	List<List<Object>> potentialDominantPattern ;
	List<Pair<List<Object>, List<Object>>> pairs_of_patterns;
	List<Pair<Integer, Integer>> pairs_of_indices;
	List<Integer> row_indices;
	float pairs_score;
	
	Map<String, Pair<List<Object>,List<Object>>> transformationBlocks;

	
	
	public PatternAlignmentResult(List<List<Object>> dominantPattern, List<List<Object>> potentialDominantPattern,
			List<Pair<List<Object>, List<Object>>> pairs_of_patterns, List<Pair<Integer, Integer>> pairs_of_indices,
			List<Integer> row_indices, float pairs_score, Map<String, Pair<List<Object>,List<Object>>> transformationBlocks) {
		super();
		this.dominantPattern = dominantPattern;
		this.potentialDominantPattern = potentialDominantPattern;
		this.pairs_of_patterns = pairs_of_patterns;
		this.pairs_of_indices = pairs_of_indices;
		this.row_indices = row_indices;
		this.pairs_score = pairs_score;
		this.transformationBlocks = transformationBlocks;
	}
}
